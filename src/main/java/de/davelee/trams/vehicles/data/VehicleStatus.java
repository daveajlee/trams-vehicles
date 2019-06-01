package de.davelee.trams.vehicles.data;

/**
 * Enum representing possible statuses of a vehicle.
 * @author Dave Lee
 */
public enum VehicleStatus {

    /**
     * Vehicle is purchased but not yet delivered.
     */
    PURCHASED {
        /**
         * Get the text description of this status.
         * @return a <code>String</code> containing the description of this status.
         */
        public String getText() {
            return "Purchased but not yet delivered";
        }
    },
    /**
     * Vehicle has been delivered.
     */
    DELIVERED {
        /**
         * Get the text description of this status.
         * @return a <code>String</code> containing the description of this status.
         */
        public String getText() {
            return "Delivered";
        }
    },
    /**
     * Vehicle has been inspected.
     */
    INSPECTED {
        /**
         * Get the text description of this status.
         * @return a <code>String</code> containing the description of this status.
         */
        public String getText() {
            return "Inspected";
        }
    },
    /**
     * Vehicle has been sold.
     */
    SOLD {
        /**
         * Get the text description of this status.
         * @return a <code>String</code> containing the description of this status.
         */
        public String getText() {
            return "Sold";
        }
    };

    /**
     * Get the text description of this status.
     * @return a <code>String</code> containing the description of this status.
     */
    public abstract String getText();

    /**
     * Static method to retrieve the Vehicle Status Enum based on the status text.
     * @param statusText a <code>String</code> containing the status text to search by.
     * @return a <code>VehicleStatus</code> object containing the matching enum or null if none found.
     */
    public static VehicleStatus getVehicleStatus ( final String statusText ) {
        for ( VehicleStatus vehicleStatus : VehicleStatus.values() ) {
            if ( vehicleStatus.getText().contentEquals(statusText) ) {
                return vehicleStatus;
            }
        }
        return null;
    }

}
