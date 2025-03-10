package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.ChildAttendanceMapper;
import cz.cvut.fel.attendance.service.model.ChildAttendance;
import cz.cvut.fel.attendance.service.model.TrainingUnit;
import cz.cvut.fel.attendance.service.repository.ChildAttendanceRepository;
import cz.fel.cvut.attendance.service.exception.AttendanceException;
import cz.fel.cvut.attendance.service.exception.TrainingUnitException;
import cz.fel.cvut.attendance.service.model.ChildAttendanceDto;
import cz.fel.cvut.attendance.service.model.ParentContactDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChildAttendanceService {

    private final ChildAttendanceRepository childAttendanceRepository;
    private final ChildAttendanceMapper childAttendanceMapper;


    public ChildAttendanceDto markChildAttendance(Long id) {
        ChildAttendance childAttendance = childAttendanceRepository.findById(id)
                .orElseThrow(() -> new AttendanceException("Attendance with ID " + id + " not found", HttpStatus.NOT_FOUND));

        childAttendance.setPresent(!childAttendance.isPresent());

        childAttendanceRepository.save(childAttendance);

        return childAttendanceMapper.toDto(childAttendance);
    }

    public ParentContactDto getParentContact(Long id) {
        ChildAttendance childAttendance = childAttendanceRepository.findById(id)
                .orElseThrow(() -> new AttendanceException("Attendance with ID " + id + " not found", HttpStatus.NOT_FOUND));

        String firstName = childAttendance.getChild().getParent().getFirstName();
        String lastName = childAttendance.getChild().getParent().getLastName();
        String email = childAttendance.getChild().getParent().getEmail();
        String phoneNumber = childAttendance.getChild().getParent().getPhoneNumber();

        return new ParentContactDto(firstName, lastName, email, phoneNumber);
    }
}
