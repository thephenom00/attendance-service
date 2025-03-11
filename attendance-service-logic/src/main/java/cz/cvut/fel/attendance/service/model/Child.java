package cz.cvut.fel.attendance.service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "child")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "children_in_events",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @OneToMany(mappedBy = "child")
    private List<ChildAttendance> attendances = new ArrayList<>();

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String street;

    private String city;

    private int zip;

    private String birthNumber;

    private Long requestedTrainingId;

    public Child(String firstName, String lastName, LocalDate dateOfBirth, String street, String city, int zip, String birthNumber, Long requestedTrainingId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.birthNumber = birthNumber;
        this.requestedTrainingId = requestedTrainingId;
    }

    public boolean addEvent(Event event) {
        if (!this.events.contains(event)) {
            this.events.add(event);
            return true;
        }
        return false;
    }

    public boolean removeEvent(Event event) {
        if (this.events.contains(event)) {
            this.events.remove(event);
            return true;
        }
        return false;
    }

    public boolean addChildAttendance(ChildAttendance childAttendance) {
        if (!this.attendances.contains(childAttendance)) {
            this.attendances.add(childAttendance);
            return true;
        }
        return false;
    }

}
