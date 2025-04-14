package cz.fel.cvut.attendance.service.api;

import cz.fel.cvut.attendance.service.model.trainer.ReportDto;
import cz.fel.cvut.attendance.service.model.TrainingUnitDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/trainer")
public interface TrainerApi {
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(value="/{email}/training-unit/upcoming")
    ResponseEntity<List<TrainingUnitDto>> getUpcomingTrainingUnits(@PathVariable String email);

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(value="/{email}/training-unit/past")
    ResponseEntity<List<TrainingUnitDto>> getPastTrainingUnits(@PathVariable String email);

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(value="/{email}/report/current-month")
    ResponseEntity<List<ReportDto>> getCurrentReport(@PathVariable String email);

}
