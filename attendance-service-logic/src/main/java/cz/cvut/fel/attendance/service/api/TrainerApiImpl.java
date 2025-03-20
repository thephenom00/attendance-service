package cz.cvut.fel.attendance.service.api;

import cz.cvut.fel.attendance.service.service.TrainerService;
import cz.fel.cvut.attendance.service.api.TrainerApi;
import cz.fel.cvut.attendance.service.model.TrainingUnitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrainerApiImpl implements TrainerApi {

    private final TrainerService trainerService;

    @Override
    public ResponseEntity<List<TrainingUnitDto>> getUpcomingTrainingUnits(String email) {
        return ResponseEntity.ok(trainerService.getUpcomingTrainingUnits(email));
    }

    @Override
    public ResponseEntity<List<TrainingUnitDto>> getPastTrainingUnits(String email) {
        return ResponseEntity.ok(trainerService.getPastTrainingUnits(email));
    }
}
