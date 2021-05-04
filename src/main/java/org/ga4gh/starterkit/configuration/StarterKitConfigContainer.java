package org.ga4gh.starterkit.configuration;

public class StarterKitConfigContainer {

    private StarterKitConfig ga4ghStarterKit;

    public StarterKitConfigContainer() {
        ga4ghStarterKit = new StarterKitConfig();
    }

    public void setGa4ghStarterKit(StarterKitConfig ga4ghStarterKit) {
        this.ga4ghStarterKit = ga4ghStarterKit;
    }

    public StarterKitConfig getGa4ghStarterKit() {
        return ga4ghStarterKit;
    }
}
