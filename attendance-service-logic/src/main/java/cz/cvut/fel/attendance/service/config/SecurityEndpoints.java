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

    final RequestMatcher AUTHENTICATED_URLS = new OrRequestMatcher(
            antMatcher(HttpMethod.POST,"/school/create"),
            antMatcher(HttpMethod.GET,"/school/**/trainings"),
            antMatcher(HttpMethod.PATCH,"/school/**"),
            antMatcher(HttpMethod.GET,"/school/**"),
            antMatcher(HttpMethod.GET,"/school"),
            antMatcher(HttpMethod.DELETE,"/school/**"),
            antMatcher(HttpMethod.POST,"/training/create/**"),
            antMatcher(HttpMethod.GET,"/training/**")
    );

    final RequestMatcher ADMIN_URLS = new OrRequestMatcher(
            antMatcher(HttpMethod.GET,"/training/**")
    );
}
