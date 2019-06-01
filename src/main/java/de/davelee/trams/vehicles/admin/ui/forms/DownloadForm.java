package de.davelee.trams.vehicles.admin.ui.forms;

import de.davelee.trams.vehicles.admin.ui.pages.TramsVehiclesManagementJsonPage;
import de.davelee.trams.vehicles.services.VehicleCompanyService;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * This class makes it possible to download the data of the current operator as a JSON file.
 * @author Dave Lee
 */
public class DownloadForm extends Form {

    @SpringBean
    private VehicleCompanyService vehicleCompanyService;

    /**
     * Create a new download form containing a download button which can be clicked to download the JSON.
     * @param id a <code>String</code> containing the id for this wicket form.
     */
    public DownloadForm( final String id) {
        super(id);

        add(new Button("downloadButton", Model.of("Download")));

    }

    /**
     * When clicking the download button, export the JSON of the current operator and display it on a new page.
     */
    public void onSubmit() {
        String json = vehicleCompanyService.exportCompany(getSession().getAttribute("operator").toString());
        PageParameters params = new PageParameters();
        params.set("json", json);
        this.setResponsePage(TramsVehiclesManagementJsonPage.class, params);
    }

}
