package cz.cvut.fel.attendance.service.config;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@UtilityClass
public class SecurityEndpoints {
    final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            antMatcher(HttpMethod.POST,"/auth/**")
    );

    final RequestMatcher PARENT_URLS = new OrRequestMatcher(
            antMatcher(HttpMethod.GET,"/parent/**/trainingUnit/upcoming")
    );

    final RequestMatcher TRAINER_URLS = new OrRequestMatcher(
            // TrainingUnits
            antMatcher(HttpMethod.GET,"/trainer/**/trainingUnit/upcoming"),
            antMatcher(HttpMethod.GET,"/trainer/**/trainingUnit/past"),
            antMatcher(HttpMethod.PATCH,"/trainingUnit/**/description"),
            antMatcher(HttpMethod.GET,"/trainingUnit/**"),
            // Attendances
            antMatcher(HttpMethod.GET,"/trainingUnit/**/trainerAttendance"),
            antMatcher(HttpMethod.GET,"/trainingUnit/**/childAttendance"),
            antMatcher(HttpMethod.PATCH,"/childAttendance/**/markPresent"),
            antMatcher(HttpMethod.PATCH,"/childAttendance/**/markAbsent"),
            antMatcher(HttpMethod.PATCH,"/trainerAttendance/**/markPresent"),
            antMatcher(HttpMethod.PATCH,"/trainerAttendance/**/markAbsent"),
            antMatcher(HttpMethod.GET,"/childAttendance/**/parentContact"),
            // Report
            antMatcher(HttpMethod.PATCH,"/trainer/**/report"),
            // Event
            antMatcher(HttpMethod.PATCH,"/event/**/registeredChildren")


    );

    final RequestMatcher MULTI_ROLE_URLS = new OrRequestMatcher(
            antMatcher(HttpMethod.GET,"/event"),
            antMatcher(HttpMethod.GET,"/news")
    );
}
