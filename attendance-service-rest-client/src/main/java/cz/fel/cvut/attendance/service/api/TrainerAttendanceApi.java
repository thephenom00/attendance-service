package cz.fel.cvut.attendance.service.api;

import cz.fel.cvut.attendance.service.model.TrainerAttendanceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/trainerAttendance")
public interface TrainerAttendanceApi {
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/markAttendance")
    ResponseEntity<TrainerAttendanceDto> markAttendance(@PathVariable Long id);
}
