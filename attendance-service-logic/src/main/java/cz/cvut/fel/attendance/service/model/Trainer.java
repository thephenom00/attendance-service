package cz.cvut.fel.attendance.service.model;

import cz.cvut.fel.attendance.service.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("TRAINER")
public class Trainer extends User {

    @ManyToMany(mappedBy = "trainers")
    private List<Training> trainings = new ArrayList<>();

    @OneToMany(mappedBy = "trainer")
    private List<TrainerAttendance> attendances = new ArrayList<>();

    private String description;

    public Trainer(String firstName, String lastName, String email, String phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber, password, Role.ROLE_TRAINER);
    }

    public boolean addTraining(Training training) {
        if (!this.trainings.contains(training)) {
            this.trainings.add(training);
            return true;
        }
        return false;
    }

    public boolean removeTraining(Training training) {
        if (this.trainings.contains(training)) {
            this.trainings.remove(training);
            return true;
        }
        return false;
    }

    public boolean addTrainerAttendance(TrainerAttendance trainerAttendance) {
        if (!this.attendances.contains(trainerAttendance)) {
            this.attendances.add(trainerAttendance);
            return true;
        }
        return false;
    }




}
