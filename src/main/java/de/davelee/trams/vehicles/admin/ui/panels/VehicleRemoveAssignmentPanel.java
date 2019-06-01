package de.davelee.trams.vehicles.admin.ui.panels;

import de.davelee.trams.vehicles.admin.ui.forms.VehicleRemoveAssignmentForm;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * The panel for removing assignments to vehicles.
 * @author Dave Lee
 */
public class VehicleRemoveAssignmentPanel extends Panel {

    /**
     * Create a new panel and add the components.
     * @param id a <code>String</code> with the matching id element in HTML.
     */
    public VehicleRemoveAssignmentPanel(final String id) {
        super(id);
        add(new VehicleRemoveAssignmentForm("deleteAllocationForm"));
    }

}
