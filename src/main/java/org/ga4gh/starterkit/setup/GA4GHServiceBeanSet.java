package org.ga4gh.starterkit.setup;

import java.util.HashSet;
import java.util.Set;

// Provides information about all classes that must be injected into the
// context for a single GA4GH web service (e.g. DRS, Beacon) to function
public class GA4GHServiceBeanSet {

    private Set<Class<?>> classes;

    public GA4GHServiceBeanSet() {
        classes = new HashSet<>();
    }

    public GA4GHServiceBeanSet(Set<Class<?>> classes) {
        this.classes = classes;
    }

    public void setClasses(Set<Class<?>> classes) {
        this.classes = classes;
    }

    public Set<Class<?>> getClasses() {
        return classes;
    }
}
