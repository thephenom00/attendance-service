//package cz.cvut.fel.attendance.service.service;
//
//import cz.cvut.fel.attendance.service.mappers.ChildMapper;
//import cz.cvut.fel.attendance.service.mappers.ChildUpcomingTrainingUnitMapper;
//import cz.cvut.fel.attendance.service.model.Child;
//import cz.cvut.fel.attendance.service.model.Parent;
//import cz.cvut.fel.attendance.service.model.Trainer;
//import cz.cvut.fel.attendance.service.model.Training;
//import cz.cvut.fel.attendance.service.model.TrainingUnit;
//import cz.cvut.fel.attendance.service.repository.TrainingUnitRepository;
//import cz.cvut.fel.attendance.service.repository.UserRepository;
//import cz.fel.cvut.attendance.service.exception.UserException;
//import cz.fel.cvut.attendance.service.model.ChildDto;
//import cz.fel.cvut.attendance.service.model.TrainingUnitDto;
//import cz.fel.cvut.attendance.service.model.parent.ChildUpcomingTrainingUnitDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class ParentService {
//
//    private final TrainingUnitRepository trainingUnitRepository;
//    private final ChildUpcomingTrainingUnitMapper childUpcomingTrainingUnitMapper;
//    private final UserRepository userRepository;
//    private final ChildMapper childMapper;
//
//    public List<ChildUpcomingTrainingUnitDto> getUpcomingTrainingUnits(String email) {
//        List<TrainingUnit> trainingUnits = trainingUnitRepository.findUpcomingUnitsByParentEmail(email);
//        return trainingUnits.stream()
//                .map(childUpcomingTrainingUnitMapper::toDto)
//                .toList();
//    }
//
//    public List<ChildDto> getChildren(String email) {
//        Parent parent = (Parent) userRepository.findByEmail(email)
//                .orElseThrow(() -> new UserException("Parent not found.", HttpStatus.NOT_FOUND));
//
//        List<Child> children = parent.getChildren();
//
//        return childMapper.toDtoList(children);
//    }
//}
