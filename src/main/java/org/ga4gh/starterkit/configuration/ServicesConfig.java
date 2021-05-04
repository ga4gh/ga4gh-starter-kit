package org.ga4gh.starterkit.configuration;

import org.ga4gh.starterkit.configuration.services.DrsServiceConfig;
import org.ga4gh.starterkit.configuration.services.WesServiceConfig;

public class ServicesConfig {

    private DrsServiceConfig drs;
    private WesServiceConfig wes;

    public ServicesConfig() {

    }

    public void setDrs(DrsServiceConfig drs) {
        this.drs = drs;
    }

    public DrsServiceConfig getDrs() {
        return drs;
    }

    public void setWes(WesServiceConfig wes) {
        this.wes = wes;
    }

    public WesServiceConfig getWes() {
        return wes;
    }
}
