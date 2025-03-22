package cz.cvut.fel.attendance.service.config.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CachingConfiguration {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(
                "upcomingTrainingUnits",
                "pastTrainingUnits",
                "childAttendance",
                "trainerAttendance"
        );
    }

}