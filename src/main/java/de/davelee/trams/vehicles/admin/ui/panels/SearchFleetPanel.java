package de.davelee.trams.vehicles.admin.ui.panels;

import de.davelee.trams.vehicles.admin.ui.forms.SearchFleetForm;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * Panel for showing and displaying the results of search by fleet number.
 * @author Dave Lee
 */
public class SearchFleetPanel extends Panel {

    /**
     * Create a new panel and add its components.
     * @param id a <code>String</code> with the matching ID element in HTML.
     */
    public SearchFleetPanel(final String id) {
        super(id);
        Model resultModel = Model.of("");
        add(new SearchFleetForm("searchFleetForm", resultModel));
        add(new Label("resultLabel", resultModel));
    }

}

