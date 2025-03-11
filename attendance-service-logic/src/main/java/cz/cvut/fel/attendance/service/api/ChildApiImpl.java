package cz.cvut.fel.attendance.service.api;

import cz.cvut.fel.attendance.service.service.ChildService;
import cz.fel.cvut.attendance.service.api.ChildApi;
import cz.fel.cvut.attendance.service.model.ChildDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChildApiImpl implements ChildApi {

    private final ChildService childService;

    @Override
    public ResponseEntity<ChildDto> createChild(ChildDto childDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(childService.createChild(childDto));
    }

    @Override
    public ResponseEntity<ChildDto> addChildToTraining(Long id) {
        return ResponseEntity.ok(childService.addChildToTraining(id));
    }

    @Override
    public ResponseEntity<Void> removeChildFromTraining(Long childId, Long trainingId) {
        childService.removeChildFromTraining(childId, trainingId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ChildDto> registerChildToEvent(Long childId, Long eventId) {
        return ResponseEntity.ok(childService.registerChildToEvent(childId, eventId));
    }

    @Override
    public ResponseEntity<Void> unregisterChildFromEvent(Long childId, Long eventId) {
        childService.unregisterChildFromEvent(childId, eventId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ChildDto> getChild(Long id) {
        return ResponseEntity.ok(childService.getChild(id));
    }

    @Override
    public ResponseEntity<List<ChildDto>> getChildren() {
        return ResponseEntity.ok(childService.getChildren());
    }

    @Override
    public ResponseEntity<Void> deleteChild(Long id) {
        childService.deleteChild(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ChildDto> updateChild(Long id, ChildDto childDto) {
        return ResponseEntity.ok(childService.updateChild(id, childDto));
    }
}
