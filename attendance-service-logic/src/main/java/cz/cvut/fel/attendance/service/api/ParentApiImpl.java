//package cz.cvut.fel.attendance.service.api;
//
//import cz.cvut.fel.attendance.service.service.ParentService;
//import cz.fel.cvut.attendance.service.api.ParentApi;
//import cz.fel.cvut.attendance.service.model.ChildDto;
//import cz.fel.cvut.attendance.service.model.parent.ChildUpcomingTrainingUnitDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class ParentApiImpl implements ParentApi {
//
//    private final ParentService parentService;
//
//    @Override
//    public ResponseEntity<List<ChildUpcomingTrainingUnitDto>> getUpcomingTrainingUnits(String email) {
//        return ResponseEntity.ok(parentService.getUpcomingTrainingUnits(email));
//    }
//
//    @Override
//    public ResponseEntity<List<ChildDto>> getChildren(String email) {
//        return ResponseEntity.ok(parentService.getChildren(email));
//    }
//}
