package de.davelee.trams.vehicles.admin.ui.panels;

import de.davelee.trams.vehicles.api.BusResponse;
import de.davelee.trams.vehicles.api.TrainResponse;
import de.davelee.trams.vehicles.api.TramResponse;
import de.davelee.trams.vehicles.services.VehicleCompanyService;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Panel to display fleet information.
 * @author Dave Lee
 */
public class DisplayFleetPanel extends Panel {

    @SpringBean
    private VehicleCompanyService vehicleCompanyService;

    /**
     * Create a new panel and add its components.
     * @param panelId a <code>String</code> with the matching ID element in HTML.
     */
    public DisplayFleetPanel(final String panelId) {
        super(panelId);
        add(new Label("fleetInfoLabel", "Fleet Information for " + getSession().getAttribute("operator")));

        //buses
        List<BusResponse> buses = vehicleCompanyService.getBuses(getSession().getAttribute("operator").toString());
        add(new Label("numBusesLabel", buses.size() + " buses found"));

        final ListDataProvider<BusResponse> listDataBusProvider = new ListDataProvider<BusResponse>(buses);

        List<IColumn> busColumns = new ArrayList<>(5);
        busColumns.add(new PropertyColumn(Model.of("Fleet Number"), "vehicleCompanyId"));
        busColumns.add(new PropertyColumn(Model.of("Type"), "type"));
        busColumns.add(new PropertyColumn(Model.of("Livery"), "livery"));
        busColumns.add(new PropertyColumn(Model.of("Registration Number"), "registrationNumber"));
        busColumns.add(new PropertyColumn(Model.of("Allocation"), "assignedRouteSchedule"));

        DataTable busTable = new DataTable("busdatatable", busColumns, listDataBusProvider, 10);
        busTable.addTopToolbar(new HeadersToolbar<>(busTable, null));

        add(busTable);

        //trains
        List<TrainResponse> trains = vehicleCompanyService.getTrains(getSession().getAttribute("operator").toString());
        add(new Label("numTrainsLabel", trains.size() + " trains found"));

        final ListDataProvider<TrainResponse> listDataTrainProvider = new ListDataProvider<TrainResponse>(trains);

        List<IColumn> trainColumns = new ArrayList<>(5);
        trainColumns.add(new PropertyColumn(Model.of("Fleet Number"), "vehicleCompanyId"));
        trainColumns.add(new PropertyColumn(Model.of("Type"), "type"));
        trainColumns.add(new PropertyColumn(Model.of("Livery"), "livery"));
        trainColumns.add(new PropertyColumn(Model.of("Operating Mode"), "operatingMode"));
        trainColumns.add(new PropertyColumn(Model.of("Allocation"), "assignedRouteSchedule"));

        DataTable trainTable = new DataTable("traindatatable", trainColumns, listDataTrainProvider, 10);
        trainTable.addTopToolbar(new HeadersToolbar<>(trainTable, null));

        add(trainTable);

        //trams
        List<TramResponse> trams = vehicleCompanyService.getTrams(getSession().getAttribute("operator").toString());
        add(new Label("numTramsLabel", trams.size() + " trams found"));

        final ListDataProvider<TramResponse> listDataTramProvider = new ListDataProvider<TramResponse>(trams);

        List<IColumn> tramColumns = new ArrayList<>(5);
        tramColumns.add(new PropertyColumn(Model.of("Fleet Number"), "vehicleCompanyId"));
        tramColumns.add(new PropertyColumn(Model.of("Type"), "type"));
        tramColumns.add(new PropertyColumn(Model.of("Livery"), "livery"));
        tramColumns.add(new PropertyColumn(Model.of("Bidirectional"), "bidirectional"));
        tramColumns.add(new PropertyColumn(Model.of("Allocation"), "assignedRouteSchedule"));

        DataTable tramTable = new DataTable("tramdatatable", tramColumns, listDataTramProvider, 10);
        tramTable.addTopToolbar(new HeadersToolbar<>(tramTable, null));

        add(tramTable);



    }

}
