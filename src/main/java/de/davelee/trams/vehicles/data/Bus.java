package de.davelee.trams.vehicles.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class represents a bus which is a specialised type of vehicle.
 * @author Dave Lee
 */
@Entity
@Table(name="VEHICLES_BUSES")
public class Bus extends Vehicle {

    @Column
    private String registrationNumber;

    /**
     * Get the registration number of this bus.
     * @return a <code>String</code> with the registration number of this bus.
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Set the registration number of this bus.
     * @param registrationNumber a <code>String</code> with the registration number.
     */
    public void setRegistrationNumber(final String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
