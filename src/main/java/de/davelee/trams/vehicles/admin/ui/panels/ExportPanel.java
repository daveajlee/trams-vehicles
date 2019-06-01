package de.davelee.trams.vehicles.admin.ui.panels;

import de.davelee.trams.vehicles.admin.ui.forms.DownloadForm;
import de.davelee.trams.vehicles.services.VehicleCompanyService;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Panel to save a file.
 * @author Dave Lee
 */

public class ExportPanel extends Panel {

    @SpringBean
    private VehicleCompanyService vehicleRestService;

    /**
     * Create a new panel and add its components.
     * @param panelId a <code>String</code> with the matching ID element in HTML.
     */
    public ExportPanel(final String panelId) {
        super(panelId);

        //Add json text area with export of json.
        TextArea jsonArea = new TextArea("exportArea", Model.of(vehicleRestService.exportCompany(getSession().getAttribute("operator").toString())));
        add(jsonArea);

        //Add download form.
        add(new DownloadForm("downloadForm"));

    }

}
