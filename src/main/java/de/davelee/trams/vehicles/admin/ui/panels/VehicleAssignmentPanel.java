package de.davelee.trams.vehicles.admin.ui.panels;

import de.davelee.trams.vehicles.admin.ui.forms.VehicleAssignmentForm;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * The panel for assigning schedules to vehicles.
 * @author Dave Lee
 */
public class VehicleAssignmentPanel extends Panel {

    /**
     * Create a new panel and add the components.
     * @param id a <code>String</code> with the matching id element in HTML.
     * @param isChange a <code>boolean</code> which is true iff we are in change mode rather than add.
     */
    public VehicleAssignmentPanel(final String id, final boolean isChange) {
        super(id);
        add(new VehicleAssignmentForm("busAssignForm", isChange));
    }

}
