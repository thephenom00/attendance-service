package cz.cvut.fel.attendance.service.model;

import cz.cvut.fel.attendance.service.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name="trainer")
@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("TRAINER")
public class Trainer extends User {

    @ManyToMany(mappedBy = "trainers")
    private List<Training> trainings = new ArrayList<>();

    @OneToMany(mappedBy = "trainer")
    private List<TrainerAttendance> attendances;

    private String description;

    public Trainer(String firstName, String lastName, String email, String phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber, password, Role.TRAINER);
    }

    private void addTraining(Training training) {
        this.trainings.add(training);
    }

}
