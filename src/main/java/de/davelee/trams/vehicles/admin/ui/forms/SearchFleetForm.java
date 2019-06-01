package de.davelee.trams.vehicles.admin.ui.forms;

import de.davelee.trams.vehicles.services.VehicleCompanyService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Form to search by fleet number.
 * @author davelee
 */
public class SearchFleetForm extends Form {

    private TextField fleetNumberField;
    private Model resultModel;

    @SpringBean
    private VehicleCompanyService vehicleCompanyService;

    private final static Logger logger = LoggerFactory.getLogger(SearchFleetForm.class);

    /**
     * Create a new form and add its components.
     * @param id a <code>String</code> with the matching ID element in HTML
     * @param resultModel a <code>Model</code> object where the result of the submission should be displayed.
     */
    public SearchFleetForm(final String id, final Model resultModel) {
        super(id);
        this.resultModel = resultModel;
        add(new Label("headingLabel", "Search Fleet by Fleet Number"));
        add(new Label("fleetNumberLabel", "Fleet Number"));
        fleetNumberField = new TextField("fleetNumberField", Model.of(""));
        add(fleetNumberField);

    }

    /**
     * Submit the form, search using the vehicleRestService and store the result in the saved model object.
     */
    public final void onSubmit() {
        String fleetNumber = fleetNumberField.getDefaultModelObjectAsString();
        logger.info("Fleet number: " + fleetNumber);
        String result = vehicleCompanyService.searchByFleetNumber(fleetNumber, getSession().getAttribute("operator").toString());
        logger.info("Result: " + result);
        resultModel.setObject(result);
    }

}
