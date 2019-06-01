package de.davelee.trams.vehicles.rest.controllers;

import de.davelee.trams.vehicles.TramsVehiclesServerApplication;
import de.davelee.trams.vehicles.api.BusRequest;
import de.davelee.trams.vehicles.api.TrainRequest;
import de.davelee.trams.vehicles.api.TramRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test the <code>VehicleOperationsRestController</code> class.
 * @author Dave Lee
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=TramsVehiclesServerApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleOperationsRestControllerTest {

    @Autowired
    private VehicleOperationsRestController vehicleOperationsRestController;

    @Test
    /**
     * Test case: check that a valid bus request is approved as valid.
     * Expected result: valid request and bus may be purchased.
     */
    public void testValidBusInput() {
        BusRequest busRequest = new BusRequest();
        busRequest.setRegistrationNumber("ZZZ22-333");
        busRequest.setDeliveryDate("07-09-2016");
        busRequest.setSeatingCapacity(30);
        busRequest.setStandingCapacity(50);
        busRequest.setVehicleCompanyId(1001);
        busRequest.setType("Single Decker");
        busRequest.setCompany("Lee Buses");
        assertTrue(vehicleOperationsRestController.validateInput(busRequest));
        assertEquals(vehicleOperationsRestController.purchaseBus(busRequest), ResponseEntity.status(HttpStatus.CREATED).body(null));
    }

    @Test
    /**
     * Test case: check that an invalid bus request without a company is rejected.
     * Expected result: invalid request and bus may not be purchased.
     */
    public void testMissingCompany() {
        BusRequest busRequest = new BusRequest();
        busRequest.setRegistrationNumber("ZZZ22-333");
        busRequest.setDeliveryDate("07-09-2016");
        busRequest.setSeatingCapacity(30);
        busRequest.setStandingCapacity(50);
        busRequest.setVehicleCompanyId(1001);
        busRequest.setType("Single Decker");
        assertFalse(vehicleOperationsRestController.validateInput(busRequest));
        assertEquals(vehicleOperationsRestController.purchaseBus(busRequest), ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @Test
    /**
     * Test case: check that an invalid bus request without a type is rejected.
     * Expected result: invalid request and bus may not be purchased.
     */
    public void testMissingType() {
        BusRequest busRequest = new BusRequest();
        busRequest.setRegistrationNumber("ZZZ22-333");
        busRequest.setDeliveryDate("07-09-2016");
        busRequest.setSeatingCapacity(30);
        busRequest.setStandingCapacity(50);
        busRequest.setVehicleCompanyId(1001);
        busRequest.setCompany("Lee Buses");
        assertFalse(vehicleOperationsRestController.validateInput(busRequest));
        assertEquals(vehicleOperationsRestController.purchaseBus(busRequest), ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @Test
    /**
     * Test case: check that an invalid bus request without a registration number is rejected.
     * Expected result: invalid request and bus may not be purchased.
     */
    public void testMissingRegNumber() {
        BusRequest busRequest = new BusRequest();
        busRequest.setDeliveryDate("07-09-2016");
        busRequest.setSeatingCapacity(30);
        busRequest.setStandingCapacity(50);
        busRequest.setVehicleCompanyId(1001);
        busRequest.setCompany("Lee Buses");
        busRequest.setType("Single Decker");
        assertFalse(vehicleOperationsRestController.validateInput(busRequest));
        assertEquals(vehicleOperationsRestController.purchaseBus(busRequest), ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @Test
    /**
     * Test case: check that an invalid bus request without a delivery date.
     * Expected result: invalid request and bus may not be purchased.
     */
    public void testInvalidDeliveryDate() {
        BusRequest busRequest = new BusRequest();
        busRequest.setRegistrationNumber("ZZZ22-333");
        busRequest.setDeliveryDate("31-09-2016");
        busRequest.setSeatingCapacity(30);
        busRequest.setStandingCapacity(50);
        busRequest.setVehicleCompanyId(1001);
        busRequest.setCompany("Lee Buses");
        busRequest.setType("Single Decker");
        assertFalse(vehicleOperationsRestController.validateInput(busRequest));
    }

    @Test
    /**
     * Test case: check that an invalid bus request without a seating capacity is rejected.
     * Expected result: invalid request and bus may not be purchased.
     */
    public void testInvalidSeatingCapacity() {
        BusRequest busRequest = new BusRequest();
        busRequest.setRegistrationNumber("ZZZ22-333");
        busRequest.setDeliveryDate("07-09-2016");
        busRequest.setSeatingCapacity(-1);
        busRequest.setStandingCapacity(50);
        busRequest.setVehicleCompanyId(1001);
        busRequest.setCompany("Lee Buses");
        busRequest.setType("Single Decker");
        assertFalse(vehicleOperationsRestController.validateInput(busRequest));
    }

    @Test
    /**
     * Test case: check that an invalid bus request without a standing capacity is rejected.
     * Expected result: invalid request and bus may not be purchased.
     */
    public void testInvalidStandingCapacity() {
        BusRequest busRequest = new BusRequest();
        busRequest.setRegistrationNumber("ZZZ22-333");
        busRequest.setDeliveryDate("07-09-2016");
        busRequest.setSeatingCapacity(30);
        busRequest.setStandingCapacity(-1);
        busRequest.setVehicleCompanyId(1001);
        busRequest.setCompany("Lee Buses");
        busRequest.setType("Single Decker");
        assertFalse(vehicleOperationsRestController.validateInput(busRequest));
    }

    @Test
    /**
     * Test case: check that an invalid bus request without a fleet number is rejected.
     * Expected result: invalid request and bus may not be purchased.
     */
    public void testInvalidCompanyId() {
        BusRequest busRequest = new BusRequest();
        busRequest.setRegistrationNumber("ZZZ22-333");
        busRequest.setDeliveryDate("07-09-2016");
        busRequest.setSeatingCapacity(30);
        busRequest.setStandingCapacity(50);
        busRequest.setVehicleCompanyId(-1);
        busRequest.setCompany("Lee Buses");
        busRequest.setType("Single Decker");
        assertFalse(vehicleOperationsRestController.validateInput(busRequest));
    }

    @Test
    /**
     * Test case: check that a valid train request is approved as valid.
     * Expected result: valid request and train may be purchased.
     */
    public void testValidTrainInput() {
        TrainRequest trainRequest = new TrainRequest();
        trainRequest.setOperatingMode("Diesel");
        trainRequest.setDeliveryDate("07-09-2016");
        trainRequest.setStandingCapacity(30);
        trainRequest.setSeatingCapacity(20);
        trainRequest.setVehicleCompanyId(1542);
        trainRequest.setCompany("Lee Trains");
        trainRequest.setType("TE4567");
        assertTrue(vehicleOperationsRestController.validateInput(trainRequest));
        assertEquals(vehicleOperationsRestController.purchaseTrain(trainRequest), ResponseEntity.status(HttpStatus.CREATED).body(null));
    }

    @Test
    /**
     * Test case: check that an invalid train request without an operating mode is rejected.
     * Expected result: invalid request and train may not be purchased.
     */
    public void testMissingOperatingMode() {
        TrainRequest trainRequest = new TrainRequest();
        trainRequest.setDeliveryDate("07-09-2016");
        trainRequest.setStandingCapacity(30);
        trainRequest.setSeatingCapacity(20);
        trainRequest.setVehicleCompanyId(1542);
        trainRequest.setCompany("Lee Trains");
        trainRequest.setType("TE4567");
        assertFalse(vehicleOperationsRestController.validateInput(trainRequest));
        assertEquals(vehicleOperationsRestController.purchaseTrain(trainRequest), ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @Test
    /**
     * Test case: check that a valid tram request is approved as valid.
     * Expected result: valid request and tram may be purchased.
     */
    public void testValidTramInput() {
        TramRequest tramRequest = new TramRequest();
        tramRequest.setBidirectional(true);
        tramRequest.setDeliveryDate("07-09-2016");
        tramRequest.setSeatingCapacity(30);
        tramRequest.setStandingCapacity(40);
        tramRequest.setVehicleCompanyId(4001);
        tramRequest.setCompany("Lee Trams");
        tramRequest.setType("ShortTram");
        assertTrue(vehicleOperationsRestController.validateInput(tramRequest));
        assertEquals(vehicleOperationsRestController.purchaseTram(tramRequest), ResponseEntity.status(HttpStatus.CREATED).body(null));
    }

}
