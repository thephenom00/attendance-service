package cz.cvut.fel.attendance.service.repository;

import cz.cvut.fel.attendance.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
