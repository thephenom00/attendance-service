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

@Table(name = "training_unit")
@Entity
@NoArgsConstructor
@Setter
@Getter
public class TrainingUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @OneToMany(mappedBy = "trainingUnit")
    private List<TrainerAttendance> trainerAttendances;

    @OneToMany(mappedBy = "trainingUnit")
    private List<ChildAttendance> childAttendances;

    private LocalDate date;

    private String description;
}
