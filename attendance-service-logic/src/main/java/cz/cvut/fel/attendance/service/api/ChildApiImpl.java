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
    public ResponseEntity<ChildDto> getChild(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<ChildDto>> getChildren() {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteChild(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ChildDto> updateChild(Long id, ChildDto childDto) {
        return null;
    }
}
