package cz.cvut.fel.attendance.service.repository;

import cz.cvut.fel.attendance.service.model.TrainingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingUnitRepository extends JpaRepository<TrainingUnit, Long> {
    List<TrainingUnit> findByCurrentIsTrue();
}
