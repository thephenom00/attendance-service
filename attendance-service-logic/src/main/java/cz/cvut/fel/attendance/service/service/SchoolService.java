package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.TrainingMapper;
import cz.cvut.fel.attendance.service.model.Training;
import cz.fel.cvut.attendance.service.exception.SchoolException;
import cz.fel.cvut.attendance.service.model.SchoolDto;
import cz.cvut.fel.attendance.service.mappers.SchoolMapper;
import cz.cvut.fel.attendance.service.model.School;
import cz.cvut.fel.attendance.service.repository.SchoolRepository;
import cz.fel.cvut.attendance.service.model.TrainingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SchoolService {

    private final SchoolRepository schoolRepository;

    private final SchoolMapper schoolMapper;

    private final TrainingMapper trainingMapper;

    public SchoolDto createSchool(SchoolDto schoolDto) {
        School school = schoolMapper.toEntity(schoolDto);

        if (schoolRepository.existsByName(schoolDto.name())) {
            throw new SchoolException("School with name '" + schoolDto.name() + "' already exists.",
                    HttpStatus.CONFLICT);
        }

        schoolRepository.save(school);
        return schoolMapper.toDto(school);
    }

    public void deleteSchool(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new SchoolException("School with ID: " + id + " is not existing.",
                        HttpStatus.NOT_FOUND));

        for (Training training : school.getTrainings()) {
            training.setSchool(null);
        }

        schoolRepository.delete(school);
    }

    public SchoolDto getSchool(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new SchoolException("School with ID " + id + " not found.",
                        HttpStatus.NOT_FOUND));
        return schoolMapper.toDto(school);
    }

    public List<SchoolDto> getSchools() {
        List<School> schools = schoolRepository.findAll();
        return schoolMapper.toDtoList(schools);
    }

    public SchoolDto updateSchool(Long id, SchoolDto schoolDto) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new SchoolException("School with ID " + id + " not found.",
                        HttpStatus.NOT_FOUND));

        schoolMapper.updateSchoolFromDto(schoolDto, school);

        schoolRepository.save(school);
        return schoolMapper.toDto(school);
    }

    public List<TrainingDto> getTrainingsBySchool(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new SchoolException("School with ID " + id + " not found.",
                        HttpStatus.NOT_FOUND));

        List<Training> trainings = school.getTrainings();

        return trainingMapper.toDtoList(trainings);
    }
}
