package de.davelee.trams.vehicles.admin.ui.forms;

import de.davelee.trams.vehicles.services.VehicleCompanyService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Form to assign vehicles in the TraMS Vehicles Admin GUI.
 * @author Dave Lee
 */
public class VehicleAssignmentForm extends Form {

    private TextField routeNumberField;
    private TextField timetableNumberField;
    private TextField fleetNumberField;

    private boolean isChange;

    @SpringBean
    private VehicleCompanyService vehicleCompanyService;

    /**
     * Create a new form and add the components.
     * @param id a <code>String</code> containing the matching id element in HTML.
     * @param isChange a <code>boolean</code> which is true iff we are in change mode rather than add.
     */
    public VehicleAssignmentForm(final String id, final boolean isChange) {
        super(id);
        this.isChange = isChange;
        if ( isChange ) {
            add(new Label("headingLabel", "Change Vehicle Assignment by Route & Timetable"));
        } else {
            add(new Label("headingLabel", "Assign Vehicle to another Route & Timetable"));
        }
        add(new Label("routeNumberLabel", "Route Number"));
        routeNumberField = new TextField("routeNumberField", Model.of(""));
        add(routeNumberField);
        add(new Label("timetableNumberLabel", "Timetable Number"));
        timetableNumberField = new TextField("timetableNumberField", Model.of(""));
        add(timetableNumberField);
        add(new Label("fleetNumberLabel", "Fleet Number"));
        fleetNumberField = new TextField("fleetNumberField", Model.of(""));
        add(fleetNumberField);
        String buttonText;
        if ( isChange  ) {
            buttonText = "Change";
        } else {
            buttonText = "Assign";
        }
        add(new Button("submitButton", Model.of(buttonText)));
    }

    /**
     * Submit the form by calling the assignVehicle method in vehicleRestService.
     */
    public final void onSubmit() {
        String routeNumber = routeNumberField.getDefaultModelObjectAsString();
        String timetableNumber = timetableNumberField.getDefaultModelObjectAsString();
        String fleetNumber = fleetNumberField.getDefaultModelObjectAsString();
        if ( isChange ) {
            vehicleCompanyService.changeVehicle(routeNumber, timetableNumber, fleetNumber, getSession().getAttribute("operator").toString());
        } else {
            vehicleCompanyService.assignVehicle(routeNumber, timetableNumber, fleetNumber, getSession().getAttribute("operator").toString());
        }
    }
}
