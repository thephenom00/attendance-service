//package cz.fel.cvut.attendance.service.api;
//
//import cz.fel.cvut.attendance.service.model.ChildDto;
//import cz.fel.cvut.attendance.service.model.parent.ChildUpcomingTrainingUnitDto;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.List;
//
//@RequestMapping("/parent")
//public interface ParentApi {
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(value="/{email}/trainingUnit/upcoming")
//    ResponseEntity<List<ChildUpcomingTrainingUnitDto>> getUpcomingTrainingUnits(@PathVariable String email);
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(value="/{email}/children")
//    ResponseEntity<List<ChildDto>> getChildren(@PathVariable String email);
//}
