package de.davelee.trams.vehicles.config;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import de.davelee.trams.vehicles.admin.ui.pages.TramsVehiclesManagementLoginPage;
import de.davelee.trams.vehicles.admin.ui.pages.TramsVehiclesManagementPage;
import org.apache.wicket.protocol.http.WebApplication;

@ApplicationInitExtension
/**
 * This class extends the Wicket web application with further configuration including the mounting of pages.
 * @author Dave Lee
 */
public class WicketPageConfiguration implements WicketApplicationInitConfiguration {

    @Override
    /**
     * Mount pages to the supplied Wicket web application.
     * @param webApplication a <code>WebApplication</code> representing the web application to configure.
     */
    public void init(final WebApplication webApplication) {
        webApplication.mountPage("/login", TramsVehiclesManagementLoginPage.class);
        webApplication.mountPage("/manage", TramsVehiclesManagementPage.class);
    }

}
