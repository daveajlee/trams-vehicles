package de.davelee.trams.vehicles.admin.ui.forms;

import de.davelee.trams.vehicles.admin.ui.pages.TramsVehiclesManagementPage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.util.Arrays;
import java.util.List;

/**
 * Form to make it possible to login to the TraMS Vehicle UI with operator, username and password.
 * @author Dave Lee
 */
public class LoginForm extends Form {

    private List operators = Arrays.asList(new String[] { "Sample Operator", "Another Sample Operator"});
    private String selectedOperator = "Sample Operator";

    private DropDownChoice<String> operatorSelect;
    private TextField<String> usernameField;
    private TextField<String> passwordField;

    /**
     * Create a new login form which can be embedded via the supplied wicket id.
     * @param id a <code>String</code> containing the id for this wicket form.
     */
    public LoginForm(final String id) {
        super(id);

        add(new FeedbackPanel("feedback"));

        add(new Label("operatorLabel", "Operator:"));

        operatorSelect = new DropDownChoice<String>("operatorSelect", new PropertyModel(this, "selectedOperator"), operators);
        add(operatorSelect);

        add(new Label("usernameLabel", "Username:"));

        usernameField = new TextField<String>("usernameField", new Model<String>(""));
        add(usernameField.setRequired(true));

        add(new Label("passwordLabel", "Password:"));

        passwordField = new PasswordTextField("passwordField", new Model<String>(""));
        add(passwordField.setRequired(true));

        add(new Button("loginButton"));
    }

    @Override
    /**
     * Attempt to authenticate the user based on the supplied credentials. If successful, redirect them to
     * the start page of the admin page. If unsuccessful (i.e. credentials were not right), log the attempt.
     */
    public void onSubmit() {
        AuthenticatedWebSession session = AuthenticatedWebSession.get();
        if (session.signIn(usernameField.getModelObject(), passwordField.getModelObject())) {
            getSession().setAttribute("displayName", "Max Mustermann");
            getSession().setAttribute("operator", operatorSelect.getModel().getObject());
            this.setResponsePage(TramsVehiclesManagementPage.class);
        } else {
            error("Login failed");
        }
    }

}
