package de.davelee.trams.vehicles.api;

/**
 * Class to represent the request of retrieving a vehicle in the TraMS Vehicle API.
 * @author Dave Lee
 */
public class RetrieveVehicleRequest {

    private int vehicleCompanyId;

    private String company;

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
     * Return the company for which the vehicle should be retrieved.
     * @return a <code>String</code> containing the company.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set the company for which the vehicle should be retrieved.
     * @param company a <code>String</code> containing the company.
     */
    public void setCompany(final String company) {
        this.company = company;
    }
}
