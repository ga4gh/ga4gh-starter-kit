package org.ga4gh.starterkit.springconfig.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import static org.ga4gh.starterkit.common.constant.StarterKitConstants.WES;

import org.ga4gh.starterkit.configuration.StarterKitConfigContainer;
import org.ga4gh.starterkit.wes.model.WesServiceInfo;

@Configuration
@ComponentScan(basePackages = {
    "org.ga4gh.starterkit.wes.beanconfig",
    "org.ga4gh.starterkit.wes.controller"
})
@Profile(WES)
public class WesServiceSpringConfig {

    @Bean
    public WesServiceInfo getWesServiceInfo(
        @Qualifier("finalStarterKitConfigContainer") StarterKitConfigContainer configContainer
    ) {
        return configContainer.getGa4ghStarterKit().getServices().getWes().getServiceInfo();
    }
}
