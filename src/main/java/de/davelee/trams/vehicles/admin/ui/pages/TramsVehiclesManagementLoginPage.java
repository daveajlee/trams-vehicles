package de.davelee.trams.vehicles.admin.ui.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import de.davelee.trams.vehicles.admin.ui.forms.LoginForm;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@WicketSignInPage
/**
 * This page displays the login form to the user.
 * @author Dave Lee
 */
public class TramsVehiclesManagementLoginPage extends WebPage {

    /**
     * Create a new LoginPage which either displays the login form to the user or redirects the user
     * to their original destination if the user is already logged in.
     * @param pageParameters a <code>PageParameters</code> object which is not currently read.
     */
    public TramsVehiclesManagementLoginPage(final PageParameters pageParameters) {
        super(pageParameters);

        if (((AbstractAuthenticatedWebSession) getSession()).isSignedIn()) {
            continueToOriginalDestination();
        }

        add(new Label("title", "Vehicles Administration"));

        add(new LoginForm("loginForm"));
    }

}