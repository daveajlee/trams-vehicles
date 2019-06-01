package de.davelee.trams.vehicles.data;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Test the <code>VehicleHistory</code> class.
 * @author Dave Lee
 */
public class VehicleHistoryTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        VehicleHistory vehicleHistory = new VehicleHistory();
        vehicleHistory.setComment("Purchased");
        vehicleHistory.setDate(LocalDate.of(2016, 10, 3));
        vehicleHistory.setStatus(VehicleStatus.PURCHASED);
        assertEquals(vehicleHistory.getComment(), "Purchased");
        assertEquals(vehicleHistory.getDate(), LocalDate.of(2016,10,3));
        assertEquals(vehicleHistory.getStatus(), VehicleStatus.PURCHASED);
    }

}
