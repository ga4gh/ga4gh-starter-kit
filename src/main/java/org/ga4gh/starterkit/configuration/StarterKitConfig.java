package org.ga4gh.starterkit.configuration;

import org.ga4gh.starterkit.common.config.DatabaseProps;
import org.ga4gh.starterkit.common.config.ServerProps;

public class StarterKitConfig {

    private ServicesConfig services;
    private ServerProps serverProps;
    private DatabaseProps databaseProps;

    public StarterKitConfig() {
        services = new ServicesConfig();
        serverProps = new ServerProps();
        databaseProps = new DatabaseProps();
    }

    public void setServices(ServicesConfig services) {
        this.services = services;
    }

    public ServicesConfig getServices() {
        return services;
    }

    public void setServerProps(ServerProps serverProps) {
        this.serverProps = serverProps;
    }

    public ServerProps getServerProps() {
        return serverProps;
    }

    public void setDatabaseProps(DatabaseProps databaseProps)  {
        this.databaseProps = databaseProps;
    }

    public DatabaseProps getDatabaseProps() {
        return databaseProps;
    }
}
