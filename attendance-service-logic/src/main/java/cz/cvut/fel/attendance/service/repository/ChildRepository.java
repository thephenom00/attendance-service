package cz.cvut.fel.attendance.service.repository;

import cz.cvut.fel.attendance.service.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    boolean existsByBirthNumber(String birthNumber);
    boolean existsByLastName(String lastName);
    boolean existsByFirstName(String firstName);
}
