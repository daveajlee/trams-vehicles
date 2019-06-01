package de.davelee.trams.vehicles.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class represents a train which is a specialised type of vehicle.
 * @author Dave Lee
 */
@Entity
@Table(name="VEHICLES_TRAINS")
public class Train extends Vehicle {

    @Column
    private String operatingMode;

    /**
     * Get the operating mode of this train e.g. electric or diesel.
     * @return a <code>String</code> containing the operating mode of this train.
     */
    public String getOperatingMode() {
        return operatingMode;
    }

    /**
     * Set the operating mode of this train e.g. electric or diesel.
     * @param operatingMode a <code>String</code> containing the operating mode of this train.
     */
    public void setOperatingMode(final String operatingMode) {
        this.operatingMode = operatingMode;
    }
}
