package cz.cvut.fel.attendance.service.repository;

import cz.cvut.fel.attendance.service.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}
