package org.ga4gh.starterkit.startup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.ConfigurationException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.ga4gh.starterkit.configuration.ServicesConfig;
import org.ga4gh.starterkit.configuration.StarterKitConfigContainer;
import org.ga4gh.starterkit.configuration.services.ServiceConfig;
import static org.ga4gh.starterkit.common.constant.StarterKitConstants.DRS;
import static org.ga4gh.starterkit.common.constant.StarterKitConstants.WES;

public class Startup {

    public static void startup(String[] args) throws ParseException, IllegalArgumentException, IOException, ConfigurationException {
        String configFilePath = getConfigFilePath(args);
        if (configFilePath == null) {
            throw new IllegalArgumentException("GA4GH Starter Kit could not be setup, no config file provided.");
        }

        StarterKitConfigContainer container = loadConfigFile(configFilePath);
        boolean profileAdded = includeServiceProfiles(container);
        if (!profileAdded) {
            throw new ConfigurationException("No 'services' included in config file.");
        }
    }

    private static String getConfigFilePath(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("c", "config", true, "Path to Starter Kit YAML config file");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        return cmd.getOptionValue("config");
    }

    private static StarterKitConfigContainer loadConfigFile(String configFilePath) throws IOException {
        File configFile = new File(configFilePath);
        if (configFile.exists() && !configFile.isDirectory()) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(configFile, StarterKitConfigContainer.class);
        } else {
            throw new FileNotFoundException("Invalid config file path provided: " + configFilePath);
        }
    }

    private static boolean includeServiceProfiles(StarterKitConfigContainer container) {
        ServicesConfig services = container.getGa4ghStarterKit().getServices();
        boolean profileAdded = false;

        Map<String, ServiceConfig> profiles = new HashMap<>();
        profiles.put(DRS, services.getDrs());
        profiles.put(WES, services.getWes());

        ArrayList<String> profilesToInclude = new ArrayList<>();
        for (String profileKey : profiles.keySet()) {
            if (profiles.get(profileKey) != null) {
                profilesToInclude.add(profileKey);
                profileAdded = true;
            }
        }
        System.setProperty("spring.profiles.include", String.join(",", profilesToInclude));
        return profileAdded;
    }
}
