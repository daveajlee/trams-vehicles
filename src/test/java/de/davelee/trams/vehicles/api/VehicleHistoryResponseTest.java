package de.davelee.trams.vehicles.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the <code>VehicleHistoryResponse</code> class.
 * @author Dave Lee
 */
public class VehicleHistoryResponseTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        VehicleHistoryResponse vehicleHistoryResponse = new VehicleHistoryResponse();
        vehicleHistoryResponse.setComment("My comment");
        assertEquals("My comment", vehicleHistoryResponse.getComment());
        vehicleHistoryResponse.setDate("26-07-2017");
        assertEquals("26-07-2017", vehicleHistoryResponse.getDate());
        vehicleHistoryResponse.setStatus("Delivered");
        assertEquals("Delivered", vehicleHistoryResponse.getStatus());
    }

}
