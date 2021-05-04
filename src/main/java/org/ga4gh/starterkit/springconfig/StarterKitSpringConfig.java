package org.ga4gh.starterkit.springconfig;

import org.apache.commons.cli.Options;
import org.ga4gh.starterkit.common.config.DatabaseProps;
import org.ga4gh.starterkit.common.config.ServerProps;
import org.ga4gh.starterkit.common.util.CliYamlConfigLoader;
import org.ga4gh.starterkit.common.util.DeepObjectMerger;
import org.ga4gh.starterkit.configuration.StarterKitConfigContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ConfigurationProperties
public class StarterKitSpringConfig {

    /* ******************************
     * STARTER KIT CONFIG CONTAINER BEANS
     * ****************************** */

    @Bean
    public Options getCommandLineOptions() {
        final Options options = new Options();
        options.addOption("c", "config", true, "Path to Starter Kit config file");
        return options;
    }

    @Bean
    @Scope("prototype")
    @Qualifier("emptyStarterKitConfigContainer")
    public StarterKitConfigContainer emptyStarterKitConfigContainer() {
        return new StarterKitConfigContainer();
    }

    @Bean
    @Qualifier("defaultStarterKitConfigContainer")
    public StarterKitConfigContainer defaultStarterKitConfigContainer() {
        return new StarterKitConfigContainer();
    }

    @Bean
    @Qualifier("runtimeStarterKitConfigContainer")
    public StarterKitConfigContainer runtimeStarterKitConfigContainer(
        @Autowired ApplicationArguments args,
        @Autowired() Options options,
        @Qualifier("emptyStarterKitConfigContainer") StarterKitConfigContainer emptyContainer
    ) {
        StarterKitConfigContainer runtimeContainer = CliYamlConfigLoader.load(StarterKitConfigContainer.class, args, options, "config");
        if (runtimeContainer != null) {
            return runtimeContainer;
        }
        return emptyContainer;
    }

    @Bean
    @Qualifier("finalStarterKitConfigContainer")
    public StarterKitConfigContainer finalStarterKitConfigContainer(
        @Qualifier("defaultStarterKitConfigContainer") StarterKitConfigContainer defaultContainer,
        @Qualifier("runtimeStarterKitConfigContainer") StarterKitConfigContainer runtimeContainer
    ) {
        DeepObjectMerger.merge(runtimeContainer, defaultContainer);
        return defaultContainer;
    }

    @Bean
    public ServerProps getServerProps(
        @Qualifier("finalStarterKitConfigContainer") StarterKitConfigContainer container
    ) {
        return container.getGa4ghStarterKit().getServerProps();
    }

    @Bean
    public DatabaseProps getDatabaseProps(
        @Qualifier("finalStarterKitConfigContainer") StarterKitConfigContainer container
    ) {
        return container.getGa4ghStarterKit().getDatabaseProps();
    }
}
