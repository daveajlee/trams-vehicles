package de.davelee.trams.vehicles.data;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Test the <code>Train</code> class.
 * @author Dave Lee
 */
public class TrainTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        Train train = new Train();
        train.setDeliveryDate(LocalDate.of(2016, 9, 5));
        assertEquals(train.getDeliveryDate(), LocalDate.of(2016,9,5));
        train.setSeatingCapacity(140);
        assertEquals(train.getSeatingCapacity(), 140);
        train.setStandingCapacity(90);
        assertEquals(train.getStandingCapacity(), 90);
        train.setVehicleIdentifier(new VehicleIdentifier("Lee Trains", 123));
        assertEquals(train.getVehicleIdentifier().getVehicleCompanyId(), 123);
        train.setOperatingMode("Electric");
        assertEquals(train.getOperatingMode(), "Electric");
        assertEquals(train.getVehicleIdentifier().getCompany(), "Lee Trains");
        train.setType("LR3466");
        assertEquals(train.getType(), "LR3466");
        train.setLivery("LeeTrainsAdvert");
        assertEquals(train.getLivery(), "LeeTrainsAdvert");
    }

}
