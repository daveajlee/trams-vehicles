package de.davelee.trams.vehicles.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the <code>VehicleStatusy</code> class.
 * @author Dave Lee
 */
public class VehicleStatusTest {

    @Test
    /**
     * Test case: check the purchased enum.
     * Expected result: text for purchased status is returned.
     */
    public void testPurchased() {
        VehicleStatus vehicleStatus = VehicleStatus.PURCHASED;
        assertEquals(vehicleStatus.getText(), "Purchased but not yet delivered");
    }

    @Test
    /**
     * Test case: check the delivered enum.
     * Expected result: text for delivered status is returned.
     */
    public void testDelivered() {
        VehicleStatus vehicleStatus = VehicleStatus.DELIVERED;
        assertEquals(vehicleStatus.getText(), "Delivered");
    }

    @Test
    /**
     * Test case: check the sold enum.
     * Expected result: text for sold status is returned.
     */
    public void testSold() {
        VehicleStatus vehicleStatus = VehicleStatus.SOLD;
        assertEquals(vehicleStatus.getText(), "Sold");
    }

}
