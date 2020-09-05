package de.davelee.trams.vehicles.services;

import de.davelee.trams.vehicles.data.Bus;
import de.davelee.trams.vehicles.data.VehicleHistory;
import de.davelee.trams.vehicles.data.VehicleIdentifier;
import de.davelee.trams.vehicles.data.VehicleStatus;
import de.davelee.trams.vehicles.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * This class controls access to the bus database and calls appropriate operations.
 * @author Dave Lee
 */
@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Transactional
    /**
     * Add a bus to the database.
     * @param bus a <code>Bus</code> object containing the bus data to add to the database.
     */
    public Bus addBus ( final Bus bus ) {
        return busRepository.save(bus);
    }

    /**
     * Return the number of buses in the database.
     * @return a <code>long</code> containing the number of buses.
     */
    public long getNumBuses ( ) {
        return busRepository.count();
    }

    /**
     * Get the bus by fleet number and company.
     * @param vehicleCompanyId a <code>int</code> containing the fleet number.
     * @param company a <code>String</code> containing the company.
     * @return a <code>Bus</code> object containing the bus object found or null if no bus is found.
     */
    public Bus findByVehicleCompanyIdAndCompany ( final int vehicleCompanyId, final String company ) {
        return busRepository.findByVehicleIdentifier(new VehicleIdentifier(company, vehicleCompanyId));
    }

    /**
     * Return all buses for a company.
     * @param company a <code>String</code> containing the company.
     * @return a <code>List</code> of <code>Bus</code> objects belonging to the specified company.
     */
    public List<Bus> findByCompany (final String company ) {
        return busRepository.findByVehicleIdentifierCompany(company);
    }

    /**
     * Find a bus with the assigned route schedule and company.
     * @param assignedRouteSchedule a <code>String</code> containing the assigned route schedule.
     * @param company a <code>String</code> containing the company.
     * @return a <code>Bus</code> object containing the bus object found or null if no bus is found.
     */
    public Bus findByAssignedRouteScheduleAndCompany(final String assignedRouteSchedule, final String company) {
        return busRepository.findByAssignedRouteScheduleAndVehicleIdentifierCompany(assignedRouteSchedule, company);
    }

    @Transactional
    /**
     * Increment hours by a specified number for a specified bus.
     * @param bus a <code>Bus</code> object containing the bus to increment the hours for.
     * @param hours a <code>int</code> containing the hours to add.
     */
    public void incrementHours ( final Bus bus, final int hours ) {
        bus.incrementVehicleHours(LocalDate.now(), hours);
        busRepository.saveAndFlush(bus);
    }

    @Transactional
    /**
     * Sell a bus by adding a comment to its history.
     * @param bus a <code>Bus</code> object to add the history to.
     */
    public void sellBus( final Bus bus ) {
        bus.setVehicleStatus(VehicleStatus.SOLD);
        VehicleHistory vehicleHistory = new VehicleHistory();
        vehicleHistory.setComment("Sold vehicle");
        vehicleHistory.setDate(LocalDate.now());
        vehicleHistory.setStatus(VehicleStatus.SOLD);
        bus.addHistory(vehicleHistory);
        busRepository.saveAndFlush(bus);
    }

    @Transactional
    /**
     * Put a bus through an inspection by adding a comment to its history.
     * @param bus a <code>Bus</code> object to add the history to.
     */
    public void inspectBus( final Bus bus ) {
        bus.setInspectionDate(LocalDate.now());
        VehicleHistory vehicleHistory = new VehicleHistory();
        vehicleHistory.setComment("Vehicle Inspected");
        vehicleHistory.setDate(LocalDate.now());
        vehicleHistory.setStatus(VehicleStatus.INSPECTED);
        bus.addHistory(vehicleHistory);
        busRepository.saveAndFlush(bus);
    }

    @Transactional
    /**
     * Assign a route schedule to a bus.
     * @param bus a <code>Bus</code> object to add the route schedule to.
     * @param routeSchedule a <code>String</code> containing the route schedule.
     */
    public void assignBus ( final Bus bus, final String routeSchedule ) {
        bus.setAssignedRouteSchedule(routeSchedule);
        busRepository.saveAndFlush(bus);
    }

    @Transactional
    /**
     * Remove the route schedule for the specified bus so that the bus is once again unallocated.
     * @param bus a <code>Bus</code> object to remove the route schedule from.
     */
    public void removeAllocation ( final Bus bus ) {
        bus.setAssignedRouteSchedule(null);
        busRepository.saveAndFlush(bus);
    }


}
