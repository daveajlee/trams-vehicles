package de.davelee.trams.vehicles.api;

/**
 * Class representing a request to delete an assignment in the TraMS Vehicle API.
 * @author Dave Lee
 */
public class VehicleAssignRequest {

    private String assignedRouteSchedule;
    private int vehicleCompanyId;
    private String company;

    /**
     * Return the route schedule for which the assignment should be removed.
     * @return a <code>String</code> containing the route schedule id.
     */
    public String getAssignedRouteSchedule() {
        return assignedRouteSchedule;
    }

    /**
     * Set the route schedule for which the assignment should be removed.
     * @param assignedRouteSchedule a <code>String</code> containing the route schedule id.
     */
    public void setAssignedRouteSchedule(final String assignedRouteSchedule) {
        this.assignedRouteSchedule = assignedRouteSchedule;
    }

    /**
     * Return the fleet number of the vehicle.
     * @return a <code>int</code> containing the fleet number of the vehicle.
     */
    public int getVehicleCompanyId() {
        return vehicleCompanyId;
    }

    /**
     * Set the fleet number of the vehicle.
     * @param vehicleCompanyId a <code>int</code> containing the fleet number of the vehicle.
     */
    public void setVehicleCompanyId(final int vehicleCompanyId) {
        this.vehicleCompanyId = vehicleCompanyId;
    }

    /**
     * Return the company for which the assignment should be removed.
     * @return a <code>String</code> containing the company.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set the company for which the assignment should be removed.
     * @param company a <code>String</code> containing the company.
     */
    public void setCompany(final String company) {
        this.company = company;
    }
}
