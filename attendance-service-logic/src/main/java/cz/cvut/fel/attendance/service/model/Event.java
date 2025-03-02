package cz.cvut.fel.attendance.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "event")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "events")
    private List<Child> children = new ArrayList<>();

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private int freePlaces;

    private int price;

    private String description;

    public boolean addChild(Child child) {
        if (!this.children.contains(child)) {
            this.children.add(child);
            return true;
        }
        return false;
    }

    public boolean removeChild(Child child) {
        if (this.children.contains(child)) {
            this.children.remove(child);
            return true;
        }
        return false;
    }

}
