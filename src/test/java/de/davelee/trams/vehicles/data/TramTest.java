package de.davelee.trams.vehicles.data;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the <code>Tram</code> class.
 * @author Dave Lee
 */
public class TramTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        Tram tram = new Tram();
        tram.setDeliveryDate(LocalDate.of(2016, 9, 5));
        assertEquals(tram.getDeliveryDate(), LocalDate.of(2016,9,5));
        tram.setSeatingCapacity(60);
        assertEquals(tram.getSeatingCapacity(),60);
        tram.setStandingCapacity(130);
        assertEquals(tram.getStandingCapacity(),130);
        tram.setVehicleIdentifier(new VehicleIdentifier("Lee Trams", 85804));
        assertEquals(tram.getVehicleIdentifier().getVehicleCompanyId(),85804);
        tram.setBidirectional(true);
        assertTrue(tram.isBidirectional());
        assertEquals(tram.getVehicleIdentifier().getCompany(), "Lee Trams");
        tram.setType("ShortTram");
        assertEquals(tram.getType(), "ShortTram");
        tram.setLivery("LeeTramsAdvert");
        assertEquals(tram.getLivery(), "LeeTramsAdvert");
    }

}
