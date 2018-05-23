package net.h2.web.test.config;

import net.h2.web.mob.profile.IProfileService;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Mohammad Anbari
 *
 */
@Configuration
public class TestConfig {

    @Bean
    public IProfileService profileService() {
        return Mockito.mock(IProfileService.class);
    }
}
