package de.davelee.trams.vehicles.admin.ui.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import de.davelee.trams.vehicles.admin.ui.AdditionalFeatures;
import de.davelee.trams.vehicles.admin.ui.panels.*;
import de.davelee.trams.vehicles.services.VehicleCompanyService;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Adminstration page for the TraMS Vehicle Admin UI - provides tabbed interface for all admin functions.
 * @author davelee
 */
@WicketHomePage
@AuthorizeInstantiation("USER")
public class TramsVehiclesManagementPage extends WebPage {

    @SpringBean
    private VehicleCompanyService vehicleCompanyService;

    /**
     * Create the page content with a title, login display box and tabs.
     */
    public TramsVehiclesManagementPage() {

        add(new Label("title", "Vehicle Administration"));

        add(new Label("userOperator", getSession().getAttribute("displayName") + ", " + getSession().getAttribute("operator")));

        add(new AjaxTabbedPanel("mytabpanel", newTabList()));
    }

    /**
     * Create the tab list of functions.
     * @return a <code>List</code> of <code>ITab</code> objects representing the tabs in the interface.
     */
    private List<ITab> newTabList () {
        List<ITab> tabs = new ArrayList<ITab>();
        //Assign
        tabs.add(new AbstractTab(Model.of("Assign")) {
            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new VehicleAssignmentPanel(panelId, false);
            }
        });
        //Change
        tabs.add(new AbstractTab(Model.of("Change")) {
            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new VehicleAssignmentPanel(panelId, true);
            }
        });
        //Remove
        tabs.add(new AbstractTab(Model.of("Remove")) {
            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new VehicleRemoveAssignmentPanel(panelId);
            }
        });
        //Search fleet
        tabs.add(new AbstractTab(Model.of("Search Fleet")) {
            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new SearchFleetPanel(panelId);
            }
        });
        //Display fleet
        tabs.add(new AbstractTab(Model.of("Display Fleet")) {
            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new DisplayFleetPanel(panelId);
            }
        });
        //Save (tab is only visible if enabled by Togglz)
        tabs.add(new AbstractTab(Model.of("Export")) {
            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new ExportPanel(panelId);
            }

            @Override
            public boolean isVisible() {
                return AdditionalFeatures.EXPORT_FEATURE.isActive();
            }
        });
        //Load (tab is only visible if enabled by Togglz)
        tabs.add(new AbstractTab(Model.of("Import")) {
            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new LoadPanel(panelId);
            }

            @Override
            public boolean isVisible() {
                return AdditionalFeatures.IMPORT_FEATURE.isActive();
            }
        });

        return tabs;
    }

}
