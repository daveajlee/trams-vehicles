package de.davelee.trams.vehicles.api;

/**
 * Class to represent vehicle history responses in the TraMS Vehicle API.
 * @author Dave Lee
 */
public class VehicleHistoryResponse {

    private String date;
    private String status;
    private String comment;

    /**
     * Return the date that this vehicle history entry took place.
     * @return a <code>String</code> containing the date in format dd-MM-yyyy.
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date that this vehicle history entry took place.
     * @param date a <code>String</code> containing the date in format dd-MM-yyyy.
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * Return the status for this vehicle history entry.
     * @return a <code>String</code> containing the status (possible values in VehicleStatus object).
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status for this vehicle history entry.
     * @param status a <code>String</code> containing the status (possible values in VehicleStatus object).
     */
    public void setStatus(final String status) {
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
