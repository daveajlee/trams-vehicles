package de.davelee.trams.vehicles.admin.ui;

import de.davelee.trams.vehicles.admin.ui.pages.TramsVehiclesManagementPage;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * This test wicket application is used to prevent it being necessary to login with Spring Security for tests.
 * @author Dave Lee
 */
public class TramsVehiclesManagementWicketTestApplication extends WebApplication {

    @Override
    /**
     * Get the home page for this Wicket application.
     * @return the <code>Page</code> object representing the start page.
     */
    public Class<? extends Page> getHomePage() {
        return TramsVehiclesManagementPage.class;
    }

}
