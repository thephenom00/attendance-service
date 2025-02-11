package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.repository.ChildRepository;
import cz.fel.cvut.attendance.service.model.ChildDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChildService {
    private final ChildRepository childRepository;

    public ChildDto createChild(ChildDto childDto) {
        return null;
    }
}
