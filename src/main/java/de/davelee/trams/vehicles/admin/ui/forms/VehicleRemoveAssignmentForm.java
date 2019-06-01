package de.davelee.trams.vehicles.admin.ui.forms;

import de.davelee.trams.vehicles.services.VehicleCompanyService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Form to remove an allocation for a vehicle in the TraMS Vehicle Admin UI.
 * @author davelee
 */
public class VehicleRemoveAssignmentForm extends Form {

    private TextField routeNumberField;
    private TextField timetableNumberField;

    @SpringBean
    private VehicleCompanyService vehicleRestService;

    /**
     * Create a new form and add the components.
     * @param id a <code>String</code> containing the matching id element in HTML.
     */
    public VehicleRemoveAssignmentForm(final String id) {
        super(id);
        add(new Label("headingLabel", "Remove Vehicle Assignment by Route & Timetable"));
        add(new Label("routeNumberLabel", "Route Number"));
        routeNumberField = new TextField("routeNumberField", Model.of(""));
        add(routeNumberField);
        add(new Label("timetableNumberLabel", "Timetable Number"));
        timetableNumberField = new TextField("timetableNumberField", Model.of(""));
        add(timetableNumberField);
    }

    /**
     * Submit the form by caling the removeVehicle method in vehicleRestService.
     */
    public final void onSubmit() {
        String routeNumber = routeNumberField.getDefaultModelObjectAsString();
        String timetableNumber = timetableNumberField.getDefaultModelObjectAsString();
        vehicleRestService.removeVehicle(routeNumber + "/" + timetableNumber, getSession().getAttribute("operator").toString());
    }
}
