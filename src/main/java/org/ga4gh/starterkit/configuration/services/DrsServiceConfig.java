package org.ga4gh.starterkit.configuration.services;

import org.ga4gh.starterkit.drs.model.DrsServiceInfo;

public class DrsServiceConfig implements ServiceConfig {

    private DrsServiceInfo serviceInfo;

    public DrsServiceConfig() {
        serviceInfo = new DrsServiceInfo();
    }

    public void setServiceInfo(DrsServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public DrsServiceInfo getServiceInfo() {
        return serviceInfo;
    }
}
