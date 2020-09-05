package de.davelee.trams.vehicles.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.time.LocalDate;

/**
 * Class to represent history information for a vehicle including date, status and comment.
 * @author Dave Lee
 */
@Entity
@Table(name="VEHICLE_HISTORY")
public class VehicleHistory {

    @Id
    @GeneratedValue
    @Column
    private long id;

    @Column
    private LocalDate date;

    @Column
    private VehicleStatus status;

    @Column
    private String comment;

    /**
     * Get the database id for this history entry.
     * @return a <code>long</code> containing the database id.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the database id for this history entry.
     * @param id a <code>long</code> containing the database id.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Return the date that this vehicle history entry took place.
     * @return a <code>LocalDate</code> containing the date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Set the date that this vehicle history entry took place.
     * @param date a <code>LocalDate</code> containing the date.
     */
    public void setDate(final LocalDate date) {
        this.date = date;
    }

    /**
     * Return the status for this vehicle history entry.
     * @return a <code>VehicleStatus</code> containing the status.
     */
    public VehicleStatus getStatus() {
        return status;
    }

    /**
     * Set the status for this vehicle history entry.
     * @param status a <code>VehicleStatus</code> containing the status.
     */
    public void setStatus(final VehicleStatus status) {
        this.status = status;
    }

    /**
     * Return the comment for this vehicle history entry.
     * @return a <code>String</code> containing the comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set the comment for this vehicle history entry.
     * @param comment a <code>String</code> containing the comment.
     */
    public void setComment(final String comment) {
        this.comment = comment;
    }
}
