package cz.cvut.fel.attendance.service.api;

import cz.cvut.fel.attendance.service.service.TrainerAttendanceService;
import cz.fel.cvut.attendance.service.api.TrainerAttendanceApi;
import cz.fel.cvut.attendance.service.model.TrainerAttendanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TrainerAttendanceApiImpl implements TrainerAttendanceApi {

    private final TrainerAttendanceService trainerAttendanceService;

    @Override
    public ResponseEntity<TrainerAttendanceDto> markAttendance(Long id) {
        return ResponseEntity.ok(trainerAttendanceService.markAttendance(id));
    }
}
