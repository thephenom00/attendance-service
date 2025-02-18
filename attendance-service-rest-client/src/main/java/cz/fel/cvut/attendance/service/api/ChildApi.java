package cz.fel.cvut.attendance.service.api;

import cz.fel.cvut.attendance.service.model.ChildDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/child")
public interface ChildApi {
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ChildDto> createChild(@RequestBody ChildDto childDto);

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/addToTraining")
    ResponseEntity<ChildDto> addChildToTraining(@PathVariable Long id);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    ResponseEntity<ChildDto> getChild(@PathVariable Long id);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    ResponseEntity<List<ChildDto>> getChildren();

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteChild(@PathVariable Long id);

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    ResponseEntity<ChildDto> updateChild(@PathVariable Long id, @RequestBody ChildDto childDto);
}
