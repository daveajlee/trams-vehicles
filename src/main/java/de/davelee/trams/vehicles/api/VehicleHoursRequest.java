package de.davelee.trams.vehicles.api;

/**
 * Request to get or set the number of hours that a vehicle has been used.
 * @author Dave Lee
 */
public class VehicleHoursRequest {

    private int vehicleCompanyId;

    private String company;

    private int hours;

    /**
     * Get the fleet number of the vehicle.
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
     * Get the company of the vehicle.
     * @return a <code>String</code> containing the company of the vehicle.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set the company of the vehicle.
     * @param company a <code>String</code> containing the company of the vehicle.
     */
    public void setCompany(final String company) {
        this.company = company;
    }

    /**
     * Get the hours of the vehicle.
     * @return a <code>int</code> containing the hours of the vehicle.
     */
    public int getHours() {
        return hours;
    }

    /**
     * Set the hours of the vehicle.
     * @param hours a <code>int</code> containing the hours of the vehicle.
     */
    public void setHours(final int hours) {
        this.hours = hours;
    }

}
