package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.TrainerAttendanceMapper;
import cz.cvut.fel.attendance.service.model.TrainerAttendance;
import cz.cvut.fel.attendance.service.repository.TrainerAttendanceRepository;
import cz.fel.cvut.attendance.service.exception.AttendanceException;
import cz.fel.cvut.attendance.service.model.TrainerAttendanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainerAttendanceService {

    private final TrainerAttendanceRepository trainerAttendanceRepository;
    private final TrainerAttendanceMapper trainerAttendanceMapper;

    public TrainerAttendanceDto markPresent(Long id) {
        TrainerAttendance trainerAttendance = trainerAttendanceRepository.findById(id)
                .orElseThrow(() -> new AttendanceException("Attendance with ID " + id + " not found", HttpStatus.NOT_FOUND));

        trainerAttendance.setPresent(true);

        trainerAttendanceRepository.save(trainerAttendance);

        return trainerAttendanceMapper.toDto(trainerAttendance);
    }

    public TrainerAttendanceDto markAbsent(Long id) {
        TrainerAttendance trainerAttendance = trainerAttendanceRepository.findById(id)
                .orElseThrow(() -> new AttendanceException("Attendance with ID " + id + " not found", HttpStatus.NOT_FOUND));

        trainerAttendance.setPresent(false);

        trainerAttendanceRepository.save(trainerAttendance);

        return trainerAttendanceMapper.toDto(trainerAttendance);
    }
}
