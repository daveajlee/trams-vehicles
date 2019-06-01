package de.davelee.trams.vehicles.admin.ui;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

/**
 * This class defines the features which can be activated via Togglz. Each feature has a label (for
 * Togglz console) and an enum which can be referenced in Java code.
 * @author Dave Lee
 */
public enum AdditionalFeatures implements Feature {

    @Label("Export Feature")
    EXPORT_FEATURE,

    @Label("Import Feature")
    IMPORT_FEATURE;

    /**
     * This method checks if the feature is currently enabled.
     * @return a <code>boolean</code> which is true iff the feature is currently enabled.
     */
    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }

}
