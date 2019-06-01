package de.davelee.trams.vehicles.services;

import de.davelee.trams.vehicles.data.*;
import de.davelee.trams.vehicles.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * This class controls access to the train database and calls appropriate operations.
 * @author Dave Lee
 */
@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Transactional
    /**
     * Add a train to the database.
     * @param train a <code>Train</code> object containing the train data to add to the database.
     */
    public Train addTrain ( final Train train ) {
        return trainRepository.save(train);
    }

    /**
     * Return the number of trains in the database.
     * @return a <code>long</code> containing the number of trains.
     */
    public long getNumTrains ( ) {
        return trainRepository.count();
    }

    /**
     * Get the train by fleet number and company.
     * @param vehicleCompanyId a <code>int</code> containing the fleet number.
     * @param company a <code>String</code> containing the company.
     * @return a <code>Train</code> object containing the train object found or null if no train is found.
     */
    public Train findByVehicleCompanyIdAndCompany ( final int vehicleCompanyId, final String company ) {
        return trainRepository.findByVehicleIdentifier(new VehicleIdentifier(company, vehicleCompanyId));
    }

    /**
     * Return all trains for a company.
     * @param company a <code>String</code> containing the company.
     * @return a <code>List</code> of <code>Train</code> objects belonging to the specified company.
     */
    public List<Train> findByCompany (final String company ) {
        return trainRepository.findByVehicleIdentifierCompany(company);
    }

    /**
     * Find a train with the assigned route schedule and company.
     * @param assignedRouteSchedule a <code>String</code> containing the assigned route schedule.
     * @param company a <code>String</code> containing the company.
     * @return a <code>Train</code> object containing the train object found or null if no train is found.
     */
    public Train findByAssignedRouteScheduleAndCompany(final String assignedRouteSchedule, final String company) {
        return trainRepository.findByAssignedRouteScheduleAndVehicleIdentifierCompany(assignedRouteSchedule, company);
    }

    @Transactional
    /**
     * Increment hours by a specified number for a specified bus.
     * @param bus a <code>Bus</code> object containing the bus to increment the hours for.
     * @param hours a <code>int</code> containing the hours to add.
     */
    public void incrementHours ( final Train train, final int hours ) {
        train.incrementVehicleHours(LocalDate.now(), hours);
        trainRepository.saveAndFlush(train);
    }

    @Transactional
    /**
     * Sell a train by adding a comment to its history.
     * @param train a <code>Train</code> object to add the history to.
     */
    public void sellTrain( final Train train ) {
        train.setVehicleStatus(VehicleStatus.SOLD);
        VehicleHistory vehicleHistory = new VehicleHistory();
        vehicleHistory.setComment("Sold vehicle");
        vehicleHistory.setDate(LocalDate.now());
        vehicleHistory.setStatus(VehicleStatus.SOLD);
        train.addHistory(vehicleHistory);
        trainRepository.saveAndFlush(train);
    }

    @Transactional
    /**
     * Put a train through an inspection by adding a comment to its history.
     * @param train a <code>Train</code> object to add the history to.
     */
    public void inspectTrain( final Train train ) {
        train.setInspectionDate(LocalDate.now());
        VehicleHistory vehicleHistory = new VehicleHistory();
        vehicleHistory.setComment("Vehicle Inspected");
        vehicleHistory.setDate(LocalDate.now());
        vehicleHistory.setStatus(VehicleStatus.INSPECTED);
        train.addHistory(vehicleHistory);
        trainRepository.saveAndFlush(train);
    }

    @Transactional
    /**
     * Assign a route schedule to a train.
     * @param train a <code>Train</code> object to add the route schedule to.
     * @param routeSchedule a <code>String</code> containing the route schedule.
     */
    public void assignTrain ( Train train, String routeSchedule ) {
        train.setAssignedRouteSchedule(routeSchedule);
        trainRepository.saveAndFlush(train);
    }

    @Transactional
    /**
     * Remove the route schedule for the specified bus so that the bus is once again unallocated.
     * @param bus a <code>Bus</code> object to remove the route schedule from.
     */
    public void removeAllocation ( Train train ) {
        train.setAssignedRouteSchedule(null);
        trainRepository.saveAndFlush(train);
    }

}
