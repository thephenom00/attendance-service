package cz.cvut.fel.attendance.service.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Table(name = "school")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "school", cascade = CascadeType.REMOVE)
    private List<Training> trainings;

    private String name;

    private String address;

    private String city;

    private String contactPerson;

    private String contactNumber;

    private String instructions;

    public void addTraining(Training training) {
        this.trainings.add(training);
        training.setSchool(this);
    }

}
