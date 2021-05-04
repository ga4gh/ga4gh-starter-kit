package org.ga4gh.starterkit.springconfig.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import static org.ga4gh.starterkit.common.constant.StarterKitConstants.DRS;

import org.ga4gh.starterkit.configuration.StarterKitConfigContainer;
import org.ga4gh.starterkit.drs.model.DrsServiceInfo;

@Configuration
@ComponentScan(basePackages = {
    "org.ga4gh.starterkit.drs.beanconfig",
    "org.ga4gh.starterkit.drs.controller"
})
@Profile(DRS)
public class DrsServiceSpringConfig {

    @Bean
    public DrsServiceInfo getDrsServiceInfo(
        @Qualifier("finalStarterKitConfigContainer") StarterKitConfigContainer container
    ) {
        return container.getGa4ghStarterKit().getServices().getDrs().getServiceInfo();
    }
}
