package de.davelee.trams.vehicles.admin.ui.panels;

import de.davelee.trams.vehicles.admin.ui.forms.LoadForm;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.lang.Bytes;

/**
 * Panel to load or import a file.
 * @author Dave Lee
 */
public class LoadPanel extends Panel {

    /**
     * Create a new panel and add its components.
     * @param panelId a <code>String</code> with the matching ID element in HTML.
     */
    public LoadPanel(final String panelId) {
        super(panelId);
        Form loadForm = new LoadForm("loadForm");
        loadForm.setMultiPart(true);
        loadForm.setMaxSize(Bytes.megabytes(1));
        add(loadForm);

    }

}
