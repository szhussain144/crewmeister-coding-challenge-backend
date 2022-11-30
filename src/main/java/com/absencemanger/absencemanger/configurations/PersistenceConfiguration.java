package com.absencemanger.absencemanger.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
@RequiredArgsConstructor
@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class PersistenceConfiguration {
    @Bean
    AuditorAware<Long> auditorProvider() {
        return new AuditorAwareImpl();
    }

    private class AuditorAwareImpl implements AuditorAware<Long> {
        @Override
        public Optional<Long> getCurrentAuditor() {
            Long currentUser = 0L;
//            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass() == String.class){
//                currentUser = 0L;
//            }else {
//                Members member = (Members) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//                currentUser = member.getId();
//            }
            return Optional.of(currentUser);
        }
    }
}