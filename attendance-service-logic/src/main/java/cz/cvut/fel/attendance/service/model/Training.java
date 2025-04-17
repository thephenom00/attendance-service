package cz.cvut.fel.attendance.service.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "training")
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "trainers_in_training",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id")
    )

    private List<Trainer> trainers = new ArrayList<>();

    @OneToMany(mappedBy = "training")
    private List<Child> children = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "training")
    private List<TrainingUnit> trainingUnits = new ArrayList<>();

    private String name;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private String schoolYear;

    private int price;

    private int capacity;

    @ElementCollection
    @CollectionTable(name = "training_holidays", joinColumns = @JoinColumn(name = "training_id"))
    @Column(name = "holiday_date")
    private List<LocalDate> holidays;

    public Training(String name, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, String schoolYear, int price, int capacity) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.schoolYear = schoolYear;
        this.price = price;
        this.capacity = capacity;
    }

    public boolean addTrainer(Trainer trainer) {
        if (!this.trainers.contains(trainer)) {
            this.trainers.add(trainer);
            return true;
        }
        return false;
    }

    public boolean removeTrainer(Trainer trainer) {
        if (this.trainers.contains(trainer)) {
            this.trainers.remove(trainer);
            trainer.getTrainings().remove(this);
            return true;
        }
        return false;
    }

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

    public void addHoliday(LocalDate date) {
        this.holidays.add(date);
    }

    public void addTrainingUnit(TrainingUnit trainingUnit) {
        this.trainingUnits.add(trainingUnit);
    }

    public TrainingUnit getCurrentTrainingUnit() {
        return this.trainingUnits.stream()
                .filter(t -> t.isCurrent())
                .findFirst()
                .orElse(null);
    }

    public List<TrainingUnit> getPastTrainingUnits() {
        return this.trainingUnits.stream()
                .filter(t -> !t.isCurrent())
                .collect(Collectors.toList());
    }


}
