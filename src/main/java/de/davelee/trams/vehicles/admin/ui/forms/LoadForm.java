package de.davelee.trams.vehicles.admin.ui.forms;

import de.davelee.trams.vehicles.services.VehicleCompanyService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Form to load file in the TraMS Vehicles UI.
 * @author Dave Lee
 */
public class LoadForm extends Form {

    private FileUploadField fileUploadField;

    private static Logger logger = LoggerFactory.getLogger(LoadForm.class);

    @SpringBean
    private VehicleCompanyService vehicleCompanyService;

    /**
     * Create a new form and add the components
     * @param id a <code>String</code> containing the matching id element in HTML.
     */
    public LoadForm(final String id) {
        super(id);
        add(new Label("fileLabel", "Path to File"));
        add(fileUploadField = new FileUploadField("fileUploadField"));
    }

    /**
     * Submit the form by calling the loadCompany method in vehicleRestService.
     */
    public final void onSubmit() {
        final FileUpload uploadedFile = fileUploadField.getFileUpload();
        try {
            vehicleCompanyService.loadCompany(uploadedFile.getInputStream());
        } catch ( Exception exception ) {
            logger.error("Exception whilst importing " + exception);
        }
    }
}
