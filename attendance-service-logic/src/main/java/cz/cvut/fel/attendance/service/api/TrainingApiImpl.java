package cz.cvut.fel.attendance.service.api;

import cz.cvut.fel.attendance.service.service.TrainingService;
import cz.fel.cvut.attendance.service.api.TrainingApi;
import cz.fel.cvut.attendance.service.model.TrainingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrainingApiImpl implements TrainingApi {

    private final TrainingService trainingService;

    @Override
    public ResponseEntity<TrainingDto> createTraining(TrainingDto trainingDto, Long schoolId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trainingService.createTraining(trainingDto, schoolId));
    }

    @Override
    public ResponseEntity<TrainingDto> getTraining(Long id) {
        return ResponseEntity.ok(trainingService.getTraining(id));
    }

    @Override
    public ResponseEntity<List<TrainingDto>> getTrainings() {
        return ResponseEntity.ok(trainingService.getTrainings());
    }

    @Override
    public ResponseEntity<Void> deleteTraining(Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<TrainingDto> updateTraining(Long id, TrainingDto trainingDto) {
        return ResponseEntity.ok(trainingService.updateTraining(id, trainingDto));
    }
}
