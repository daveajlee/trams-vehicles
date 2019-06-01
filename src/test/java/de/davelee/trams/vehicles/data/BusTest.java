package de.davelee.trams.vehicles.data;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Test the <code>Bus</code> class.
 * @author Dave Lee
 */
public class BusTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        Bus bus = new Bus();
        bus.setRegistrationNumber("ZZZ22-333");
        assertEquals(bus.getRegistrationNumber(), "ZZZ22-333");
        bus.setDeliveryDate(LocalDate.of(2016, 9, 5));
        assertEquals(bus.getDeliveryDate(), LocalDate.of(2016, 9, 5));
        bus.setSeatingCapacity(40);
        assertEquals(bus.getSeatingCapacity(), 40);
        bus.setStandingCapacity(50);
        assertEquals(bus.getStandingCapacity(), 50);
        bus.setVehicleIdentifier(new VehicleIdentifier("Lee Buses", 1234));
        assertEquals(bus.getVehicleIdentifier().getVehicleCompanyId(), 1234);
        assertEquals(bus.getVehicleIdentifier().getCompany(), "Lee Buses");
        bus.setType("Single Decker");
        assertEquals(bus.getType(), "Single Decker");
        bus.setLivery("LeeBusesAdvert");
        assertEquals(bus.getLivery(), "LeeBusesAdvert");
    }

}
