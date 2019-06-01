package de.davelee.trams.vehicles.data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
/**
 * Class to represent a primary key identifier for a vehicle consisting of fleet number and company.
 * @author Dave Lee
 */
public class VehicleIdentifier implements Serializable  {

    @Column
    private int vehicleCompanyId;

    @Column
    private String company;

    /**
     * Default constructor which is necessary for Spring Data.
     */
    public VehicleIdentifier ( ) {

    }

    /**
     * Create a new primary key identifier consisting of fleet number and company.
     * @param company a <code>String</code> containing the name of the company.
     * @param vehicleCompanyId a <code>int</code> containing the fleet number.
     */
    public VehicleIdentifier ( final String company, final int vehicleCompanyId ) {
        this.company = company;
        this.vehicleCompanyId = vehicleCompanyId;
    }

    /**
     * Return the fleet number.
     * @return a <code>int</code> containing the fleet number.
     */
    public int getVehicleCompanyId() {
        return vehicleCompanyId;
    }

    /**
     * Set the fleet number.
     * @param vehicleCompanyId a <code>int</code> containing the fleet number.
     */
    public void setVehicleCompanyId(final int vehicleCompanyId) {
        this.vehicleCompanyId = vehicleCompanyId;
    }

    /**
     * Get the company.
     * @return a <code>String</code> containing the company.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set the company.
     * @param company a <code>String</code> containing the company.
     */
    public void setCompany(final String company) {
        this.company = company;
    }

}
