package de.davelee.trams.vehicles.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class represents a tram which is a specialised type of vehicle.
 * @author Dave Lee
 */
@Entity
@Table(name="VEHICLES_TRAMS")
public class Tram extends Vehicle {

    @Column
    private boolean bidirectional;

    /**
     * Return whether or not this tram has 2 driver cabs so that it can travel bidirectionally.
     * @return a <code>boolean</code> which is true iff the tram can travel bidirectionally.
     */
    public boolean isBidirectional() {
        return bidirectional;
    }

    /**
     * Set whether or not this tram has 2 driver cabs so that it can travel bidirectionally.
     * @param bidirectional a <code>boolean</code> which is true iff the tram can travel bidirectionally.
     */
    public void setBidirectional(final boolean bidirectional) {
        this.bidirectional = bidirectional;
    }

}
