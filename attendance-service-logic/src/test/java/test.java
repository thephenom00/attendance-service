
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.attendance.service.AttendanceServiceApp;
import cz.cvut.fel.attendance.service.model.Child;
import cz.cvut.fel.attendance.service.model.School;
import cz.cvut.fel.attendance.service.model.Training;
import cz.cvut.fel.attendance.service.repository.ChildRepository;
import cz.cvut.fel.attendance.service.repository.SchoolRepository;

import cz.cvut.fel.attendance.service.repository.TrainingRepository;
import cz.fel.cvut.attendance.service.model.TrainingDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AttendanceServiceApp.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class test {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        schoolRepository.deleteAll();
    }

    @Test
    void createSchoolTest() throws Exception {
        String schoolJson = """
                {
                    "name": "New School",
                    "address": "123 Main St",
                    "city": "Prague",
                    "contactPerson": "John Doe",
                    "contactNumber": "123456789",
                    "instructions": "Go home"
                }
                """;

        mvc.perform(post("/school/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(schoolJson))
                .andExpect(jsonPath("$.name").value("New School"))
                .andExpect(jsonPath("$.address").value("123 Main St"))
                .andExpect(jsonPath("$.city").value("Prague"));
    }

    @Test
    void returnAllSchoolsTest() throws Exception {
        School school = new School("New School", "123 Main St", "Prague", "John Doe", "123456789", "Go home");
        schoolRepository.save(school);

        mvc.perform(get("/school")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("New School"))
                .andExpect(jsonPath("$[0].address").value("123 Main St"))
                .andExpect(jsonPath("$[0].city").value("Prague"));
    }

    @Test
    void returnSchoolByIdTest() throws Exception {
        School school = new School("New School", "123 Main St", "Prague", "John Doe", "123456789", "Go home");
        School savedSchool = schoolRepository.save(school);

        mvc.perform(get("/school/" + savedSchool.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New School"))
                .andExpect(jsonPath("$.address").value("123 Main St"))
                .andExpect(jsonPath("$.city").value("Prague"));
    }

    @Test
    void updateSchoolTest() throws Exception {
        School school = new School("New School", "123 Main St", "Prague", "John Doe", "123456789", "Go home");
        School savedSchool = schoolRepository.save(school);

        String updatedSchoolJson = """
                {
                    "name": "Kindergarten",
                    "address": "321 St"
                }
                """;

        mvc.perform(patch("/school/" + savedSchool.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedSchoolJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kindergarten"))
                .andExpect(jsonPath("$.address").value("321 St"));
    }

    @Test
    void deleteSchoolTest() throws Exception {
        School school = new School("New School", "123 Main St", "Prague", "John Doe", "123456789", "Go home");
        School savedSchool = schoolRepository.save(school);

        mvc.perform(delete("/school/" + savedSchool.getId()))
                .andExpect(status().isNoContent());

        mvc.perform(get("/school")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));
    }

    @Test
    void createTrainingTest() throws Exception {
        School school = new School("New School", "123 Main St", "Prague", "John Doe", "123456789", "Go home");
        School savedSchool = schoolRepository.save(school);

        TrainingDto expectedTraining = new TrainingDto(
                1L, "Beginners", DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(16, 0), "2024/2025",
                2100, 20
        );

        String trainingJson = """
                {
                    "name": "Beginners",
                    "dayOfWeek": "MONDAY",
                    "startTime": "14:30:00",
                    "endTime": "16:00:00",
                    "schoolYear": "2024/2025",
                    "price": 2100,
                    "capacity": 20
                }
                """;

        MvcResult result = mvc.perform(post("/training/create/" + savedSchool.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(trainingJson))
                .andReturn();

        TrainingDto actualTraining = objectMapper.readValue(result.getResponse().getContentAsString(), TrainingDto.class);

        assertEquals(expectedTraining, actualTraining);
    }

    @Transactional
    @Test
    void addChildToTrainingTest() throws Exception {
        Training training = new Training("Beginners", DayOfWeek.MONDAY, LocalTime.now(), LocalTime.now(), "2024/2025", 2100, 20);
        training = trainingRepository.save(training);

        Child child = new Child("Karel", "Novak", LocalDate.of(2023, 2, 2), "Street", "City", 123,"321", training.getId());
        child = childRepository.save(child);

        mvc.perform(patch("/child/" + child.getId() + "/addToTraining" ));

        Optional<Child> savedChild = childRepository.findById(child.getId());
        Training savedTraining = trainingRepository.findById(training.getId()).orElseThrow();

        assertThat(savedChild.get().getRequestedTrainingId()).isNull();
        assertThat(savedTraining.getChildren().size()).isEqualTo(1);
        assertThat(savedTraining.getChildren().get(0)).isEqualTo(child);
    }

}
