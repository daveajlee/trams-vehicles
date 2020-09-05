package de.davelee.trams.vehicles.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.davelee.trams.vehicles.api.BusResponse;
import de.davelee.trams.vehicles.api.TramResponse;
import de.davelee.trams.vehicles.api.TrainResponse;
import de.davelee.trams.vehicles.api.BusesResponse;
import de.davelee.trams.vehicles.api.TramsResponse;
import de.davelee.trams.vehicles.api.TrainsResponse;
import de.davelee.trams.vehicles.api.DeleteAssignRequest;
import de.davelee.trams.vehicles.api.VehicleAssignRequest;
import de.davelee.trams.vehicles.model.CompanyData;
import de.davelee.trams.vehicles.rest.controllers.VehicleOperationsRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * This class provides common methods for working on vehicles.
 * @author Dave Lee
 */
@Service
public class VehicleCompanyService {

    @Autowired
    private VehicleOperationsRestController vehicleOperationsRestController;

    private final static Logger logger = LoggerFactory.getLogger(VehicleCompanyService.class);

    /**
     * Assign a vehicle to a route for a particular company according to the desired route number, timetable number and fleet number.
     * @param routeNumber a <code>String</code> with the route number.
     * @param timetableNumber a <code>String</code> with the timetable number (must be a number).
     * @param fleetNumber a <code>String</code> with the fleet number (must be a number).
     * @param companyName a <code>String</code> representing the name of the company.
     */
    public void assignVehicle(final String routeNumber, final String timetableNumber, final String fleetNumber, final String companyName) {
        VehicleAssignRequest vehicleAssignRequest = new VehicleAssignRequest();
        vehicleAssignRequest.setAssignedRouteSchedule(routeNumber + "/" +timetableNumber);
        vehicleAssignRequest.setCompany(companyName);
        vehicleAssignRequest.setVehicleCompanyId(Integer.parseInt(fleetNumber));
        vehicleOperationsRestController.assignRoute(vehicleAssignRequest);
    }

    /**
     * Change a vehicle allocation for a particular company and route route.
     * @param routeNumber a <code>String</code> with the route number.
     * @param timetableNumber a <code>String</code> with the timetable number.
     * @param fleetNumber a <code>String</code> with the fleet number.
     * @param companyName a <code>String</code> representing the name of the company.
     */
    public void changeVehicle (final String routeNumber, final String timetableNumber, final String fleetNumber, final String companyName) {
        removeVehicle(routeNumber + "/" + timetableNumber, companyName);
        assignVehicle(routeNumber, timetableNumber, fleetNumber, companyName);
    }

    /**
     * Remove a vehicle allocation for a particular route and company.
     * @param timetableNumber a <code>int</code> with the timetable number.
     * @param companyName a <code>String</code> representing the name of the company.
     */
    public void removeVehicle (final String timetableNumber, final String companyName) {
        DeleteAssignRequest deleteAssignRequest = new DeleteAssignRequest();
        deleteAssignRequest.setAssignedRouteSchedule(timetableNumber);
        deleteAssignRequest.setCompany(companyName);
        vehicleOperationsRestController.deleteAllocation(deleteAssignRequest);
    }

    /**
     * Search for the specified fleet number and company. Return the relevant vehicle information.
     * @param fleetNumber a <code>String</code> with the fleet number (must be a number).
     * @param companyName a <code>String</code> representing the name of the company.
     * @return a <code>String</code> with the fleet information or Fleet No Not Founded if no vheicle found.
     */
    public String searchByFleetNumber ( final String fleetNumber, final String companyName ) {
        BusResponse busResponse = vehicleOperationsRestController.getBus(Integer.parseInt(fleetNumber), companyName).getBody();
        if ( busResponse != null ) {
            return busResponse.toString();
        }
        TrainResponse trainResponse = vehicleOperationsRestController.getTrain(Integer.parseInt(fleetNumber), companyName).getBody();
        if ( trainResponse != null ) {
            return trainResponse.toString();
        }
        TramResponse tramResponse = vehicleOperationsRestController.getTram(Integer.parseInt(fleetNumber), companyName).getBody();
        if ( tramResponse != null ) {
            return tramResponse.toString();
        }
        return "Fleet No not found";
    }

    /**
     * Return a List of Buses representing all buses belonging to the specified company.
     * @param companyName a <code>String</code> representing the name of the company.
     * @return a <code>List</code> of type <code>BusResponse</code> with each position in the list representing a bus.
     */
    public List<BusResponse> getBuses ( final String companyName ) {
        BusesResponse busesResponse = vehicleOperationsRestController.getBuses(companyName).getBody();
        return busesResponse.getBusResponseList();
    }

    /**
     * Return a List of Trams representing all trams belonging to the specified company.
     * @param companyName a <code>String</code> representing the name of the company.
     * @return a <code>List</code> of type <code>TramResponse</code> with each position in the list representing a tram.
     */
    public List<TramResponse> getTrams ( final String companyName ) {
        TramsResponse tramsResponse = vehicleOperationsRestController.getTrams(companyName).getBody();
        return tramsResponse.getTramResponseList();
    }

    /**
     * Return a List of Trains representing all trains belonging to the specified company.
     * @param companyName a <code>String</code> representing the name of the company.
     * @return a <code>List</code> of type <code>TrainResponse</code> with each position in the list representing a train.
     */
    public List<TrainResponse> getTrains ( final String companyName ) {
        TrainsResponse trainsResponse = vehicleOperationsRestController.getTrains(companyName).getBody();
        return trainsResponse.getTrainResponseList();
    }

    /**
     * This method exports all of the company information (name, vehicles) to a JSON string.
     * @param companyName a <code>String</code> representing the name of the company.
     * @return a <code>String</code> which contains the company information as JSON string.
     */
    public String exportCompany ( final String companyName ) {
        //Define json exporter.
        final ObjectMapper mapper = new ObjectMapper();
        //Build company data object.
        CompanyData companyData = new CompanyData();
        companyData.setCompanyName(companyName);
        companyData.setBusResponseList(getBuses(companyName));
        companyData.setTramResponseList(getTrams(companyName));
        companyData.setTrainResponseList(getTrains(companyName));
        //Output json.
        try {
            StringWriter stringWriter = new StringWriter();
            mapper.writeValue(stringWriter, companyData);
            return stringWriter.toString();
        } catch ( IOException ioException) {
            logger.error("Failure converting to json: " + ioException);
            return "";
        }
    }

    /**
     * This method loads all of the company information (name, vehicles) from a JSON file.
     * @param inputStream a <code>InputStream</code> object representing the contents of the file to load.
     * @return a <code>boolean</code> which is true iff the load is completed successfully.
     */
    public boolean loadCompany ( final InputStream inputStream ) {
        //Define json importer.
        final ObjectMapper mapper = new ObjectMapper();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);   // add everything to StringBuilder
            }
            CompanyData companyData = mapper.readValue(out.toString(), CompanyData.class);
            loadCompanyData(companyData);
            return true;
        } catch ( Exception exception ) {
            logger.error("Load Exception", exception);
            return false;
        }
    }

    /**
     * This methods loads the bus, tram and train information into the database.
     * @param companyData a <code>CompanyData</code> object containing the information to add to the database.
     */
    public void loadCompanyData ( final CompanyData companyData ) {
        if ( companyData.getBusResponseList() != null ) {
            vehicleOperationsRestController.addBuses(companyData.getBusResponseList());
        }
        if ( companyData.getTramResponseList() != null ) {
            vehicleOperationsRestController.addTrams(companyData.getTramResponseList());
        }
        if ( companyData.getTrainResponseList() != null ) {
            vehicleOperationsRestController.addTrains(companyData.getTrainResponseList());
        }
    }

}
