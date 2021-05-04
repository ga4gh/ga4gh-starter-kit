package org.ga4gh.starterkit.configuration.services;

import org.ga4gh.starterkit.wes.model.WesServiceInfo;

public class WesServiceConfig implements ServiceConfig {

    private WesServiceInfo serviceInfo;

    public WesServiceConfig() {
        serviceInfo = new WesServiceInfo();
    }

    public void setServiceInfo(WesServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public WesServiceInfo getServiceInfo() {
        return serviceInfo;
    }
}
