package de.davelee.trams.vehicles;

import com.jayway.restassured.RestAssured;
import de.davelee.trams.vehicles.api.*;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=TramsVehiclesServerApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
/**
 * Test the Spring Boot application to make sure it starts and swagger can be called.
 * @author Dave Lee
 */
public class TramsVehiclesServerApplicationTest {

    @LocalServerPort
    int port;

    @Before
    /**
     * Set up the test by setting the port correctly.
     */
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    /**
     * Test case: make sure that the swagger can be called.
     * Expected result: 200 status code OK.
     */
    public void testSwagger() {
        when().
                get("/swagger-ui.html").
                then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    /**
     * Test case: test bus operations in the rest api.
     * Expected result: all assserts and givens are fulfilled.
     */
    public void testBus() {
        BusRequest busRequest = new BusRequest();
        busRequest.setRegistrationNumber("AA66 AAA");
        busRequest.setDeliveryDate("05-09-2013");
        busRequest.setSeatingCapacity(40);
        busRequest.setStandingCapacity(50);
        busRequest.setVehicleCompanyId(1234);
        busRequest.setCompany("Lee Buses");
        busRequest.setType("Single Decker");
        busRequest.setLivery("LeeBusesAdvert");
        given()
                .contentType("application/json")
                .body(busRequest)
                .when()
                .post("/vehicle/purchaseBus")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        when()
                .get("/vehicle/getBus?vehicleCompanyId=1233&company=Lee Buses")
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        when()
                .get("/vehicle/getBus?vehicleCompanyId=1234&company=Lee Buses")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("registrationNumber", equalTo("AA66 AAA"));
        when()
                .get("/vehicle/getBuses?company=Lee Buses")
                .then()
                .statusCode(HttpStatus.SC_OK);
        VehicleAssignRequest vehicleAssignRequest = new VehicleAssignRequest();
        vehicleAssignRequest.setAssignedRouteSchedule("1/1");
        vehicleAssignRequest.setCompany("Lee Buses");
        vehicleAssignRequest.setVehicleCompanyId(1234);
        DeleteAssignRequest deleteAssignRequest = new DeleteAssignRequest();
        deleteAssignRequest.setAssignedRouteSchedule("1/1");
        deleteAssignRequest.setCompany("Lee Buses");
        given()
                .contentType("application/json")
                .body(vehicleAssignRequest)
                .when()
                .post("/vehicle/assignRoute")
                .then()
                .statusCode(HttpStatus.SC_OK);
        when()
                .get("/vehicle/getBus?vehicleCompanyId=1234&company=Lee Buses")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("assignedRouteSchedule", equalTo("1/1"));
        given()
                .contentType("application/json")
                .body(deleteAssignRequest)
                .when()
                .post("/vehicle/removeAllocation")
                .then()
                .statusCode(HttpStatus.SC_OK);
        when()
                .get("/vehicle/getBus?vehicleCompanyId=1234&company=Lee Buses")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("assignedRouteSchedule", equalTo(null));
        VehicleHoursRequest vehicleHoursRequest = new VehicleHoursRequest();
        given()
                .contentType("application/json")
                .body(vehicleHoursRequest)
                .when()
                .post("/vehicle/trackHours")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        vehicleHoursRequest.setHours(5);
        vehicleHoursRequest.setVehicleCompanyId(1234);
        vehicleHoursRequest.setCompany("Lee Buses");
        given()
                .contentType("application/json")
                .body(vehicleHoursRequest)
                .when()
                .post("/vehicle/trackHours")
                .then()
                .statusCode(HttpStatus.SC_OK);
        RetrieveVehicleRequest retrieveVehicleRequest = new RetrieveVehicleRequest();
        retrieveVehicleRequest.setVehicleCompanyId(1234);
        retrieveVehicleRequest.setCompany("Lee Buses");
        given()
                .contentType("application/json")
                .body(vehicleHoursRequest)
                .when()
                .post("/vehicle/checkHours")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("furtherHoursAllowed", equalTo(true))
                .body("remainingHours", equalTo(11));
        VehicleHoursRequest moreVehicleHoursRequest = new VehicleHoursRequest();
        moreVehicleHoursRequest.setHours(11);
        moreVehicleHoursRequest.setVehicleCompanyId(1234);
        moreVehicleHoursRequest.setCompany("Lee Buses");
        given()
                .contentType("application/json")
                .body(moreVehicleHoursRequest)
                .when()
                .post("/vehicle/trackHours")
                .then()
                .statusCode(HttpStatus.SC_OK);
        given()
                .contentType("application/json")
                .body(vehicleHoursRequest)
                .when()
                .post("/vehicle/checkHours")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("furtherHoursAllowed", equalTo(false))
                .body("remainingHours", equalTo(0));
        given()
                .contentType("application/json")
                .body(retrieveVehicleRequest)
                .when()
                .post("/vehicle/checkInspectionDate")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("inspectionDue", equalTo(true));
        given()
                .contentType("application/json")
                .body(retrieveVehicleRequest)
                .when()
                .post("/vehicle/inspectVehicle")
                .then()
                .statusCode(HttpStatus.SC_OK);
        when()
                .get("/vehicle/getBus?vehicleCompanyId=1234&company=Lee Buses")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("inspectionDate", equalTo(todayAsString()));
        given()
                .contentType("application/json")
                .body(retrieveVehicleRequest)
                .when()
                .post("/vehicle/checkInspectionDate")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("inspectionDue", equalTo(false))
                .body("remainingDaysUntilNextInspection", equalTo(1095));
        given()
                .contentType("application/json")
                .body(retrieveVehicleRequest)
                .when()
                .post("/vehicle/sellVehicle")
                .then()
                .statusCode(HttpStatus.SC_OK);
        when()
                .get("/vehicle/getBus?vehicleCompanyId=1234&company=Lee Buses")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("vehicleStatus", equalTo("Sold"));
        given()
                .contentType("application/json")
                .body(busRequest)
                .when()
                .post("vehicle/purchaseBus")
                .then()
                .statusCode(HttpStatus.SC_CONFLICT);
    }

    @Test
    /**
     * Test case: test tram operations in the rest api.
     * Expected result: all assserts and givens are fulfilled.
     */
    public void testTram() {
        TramRequest tramRequest = new TramRequest();
        tramRequest.setBidirectional(true);
        tramRequest.setDeliveryDate("05-09-2008");
        tramRequest.setSeatingCapacity(60);
        tramRequest.setStandingCapacity(100);
        tramRequest.setVehicleCompanyId(4101);
        tramRequest.setCompany("Lee Trams");
        tramRequest.setLivery("LeeTramsAdvert");
        tramRequest.setType("ShortTram");
        given()
                .contentType("application/json")
                .body(tramRequest)
                .when()
                .post("vehicle/purchaseTram")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        when()
                .get("/vehicle/getTram?vehicleCompanyId=4100&company=Lee Trams")
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        when()
                .get("/vehicle/getTram?vehicleCompanyId=4101&company=Lee Trams")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("bidirectional", equalTo(true));
        VehicleAssignRequest vehicleAssignRequest = new VehicleAssignRequest();
        vehicleAssignRequest.setAssignedRouteSchedule("M1/1");
        vehicleAssignRequest.setCompany("Lee Trams");
        vehicleAssignRequest.setVehicleCompanyId(4101);
        DeleteAssignRequest deleteAssignRequest = new DeleteAssignRequest();
        deleteAssignRequest.setAssignedRouteSchedule("M1/1");
        deleteAssignRequest.setCompany("Lee Trams");
        given()
                .contentType("application/json")
                .body(vehicleAssignRequest)
                .when()
                .post("/vehicle/assignRoute")
                .then()
                .statusCode(HttpStatus.SC_OK);
        when()
                .get("/vehicle/getTram?vehicleCompanyId=4101&company=Lee Trams")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("assignedRouteSchedule", equalTo("M1/1"));
        given()
                .contentType("application/json")
                .body(deleteAssignRequest)
                .when()
                .post("/vehicle/removeAllocation")
                .then()
                .statusCode(HttpStatus.SC_OK);
        when()
                .get("/vehicle/getTram?vehicleCompanyId=4101&company=Lee Trams")
                .then()
                .statusCode(HttpStatus.SC_OK);
        VehicleHoursRequest vehicleHoursRequest = new VehicleHoursRequest();
        vehicleHoursRequest.setHours(6);
        vehicleHoursRequest.setVehicleCompanyId(4101);
        vehicleHoursRequest.setCompany("Lee Trams");
        given()
                .contentType("application/json")
                .body(vehicleHoursRequest)
                .when()
                .post("/vehicle/trackHours")
                .then()
                .statusCode(HttpStatus.SC_OK);
        RetrieveVehicleRequest retrieveVehicleRequest = new RetrieveVehicleRequest();
        retrieveVehicleRequest.setCompany("Lee Trams");
        retrieveVehicleRequest.setVehicleCompanyId(4101);
        given()
                .contentType("application/json")
                .body(retrieveVehicleRequest)
                .when()
                .post("/vehicle/checkInspectionDate")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("inspectionDue", equalTo(true));
        given()
                .contentType("application/json")
                .body(retrieveVehicleRequest)
                .when()
                .post("/vehicle/inspectVehicle")
                .then()
                .statusCode(HttpStatus.SC_OK);
        when()
                .get("/vehicle/getTram?vehicleCompanyId=4101&company=Lee Trams")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("inspectionDate", equalTo(todayAsString()));
        given()
                .contentType("application/json")
                .body(retrieveVehicleRequest)
                .when()
                .post("/vehicle/checkInspectionDate")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("inspectionDue", equalTo(false))
                .body("remainingDaysUntilNextInspection", equalTo(2920));
        given()
                .contentType("application/json")
                .body(retrieveVehicleRequest)
                .when()
                .post("/vehicle/sellVehicle")
                .then()
                .statusCode(HttpStatus.SC_OK);
        when()
                .get("/vehicle/getTram?vehicleCompanyId=4101&company=Lee Trams")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("vehicleStatus", equalTo("Sold"));
        given()
                .contentType("application/json")
                .body(tramRequest)
                .when()
                .post("vehicle/purchaseTram")
                .then()
                .statusCode(HttpStatus.SC_CONFLICT);
    }

    @Test
    /**
     * Test case: test train operations in the rest api.
     * Expected result: all assserts and givens are fulfilled.
     */
    public void testTrain() {
        TrainRequest trainRequest = new TrainRequest();
        trainRequest.setOperatingMode("DIESEL");
        trainRequest.setDeliveryDate("05-09-2009");
        trainRequest.setSeatingCapacity(120);
        trainRequest.setStandingCapacity(60);
        trainRequest.setVehicleCompanyId(567);
        trainRequest.setCompany("Lee Trains");
        trainRequest.setType("ER4567");
        trainRequest.setLivery("LeeTrainsAdvert");
        given()
                .contentType("application/json")
                .body(trainRequest)
                .when()
                .post("vehicle/purchaseTrain")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        when()
                .get("/vehicle/getTrain?vehicleCompanyId=566&company=Lee Trains")
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        when()
                .get("/vehicle/getTrain?vehicleCompanyId=567&company=Lee Trains")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("operatingMode", equalTo("DIESEL"));
        VehicleAssignRequest vehicleAssignRequest = new VehicleAssignRequest();
        vehicleAssignRequest.setAssignedRouteSchedule("KBS632.1");
        vehicleAssignRequest.setCompany("Lee Trains");
        vehicleAssignRequest.setVehicleCompanyId(567);
        DeleteAssignRequest deleteAssignRequest = new DeleteAssignRequest();
        deleteAssignRequest.setAssignedRouteSchedule("KBS632.1");
        deleteAssignRequest.setCompany("Lee Trains");
        given()
                .contentType("application/json")
                .body(vehicleAssignRequest)
                .when()
                .post("/vehicle/assignRoute")
                .then()
                .statusCode(HttpStatus.SC_OK);
        when()
                .get("/vehicle/getTrain?vehicleCompanyId=567&company=Lee Trains")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("assignedRouteSchedule", equalTo("KBS632.1"));
        given()
                .contentType("application/json")
                .body(deleteAssignRequest)
                .when()
                .post("/vehicle/removeAllocation")
                .then()
                .statusCode(HttpStatus.SC_OK);
        when()
                .get("/vehicle/getTrain?vehicleCompanyId=567&company=Lee Trains")
                .then()
                .statusCode(HttpStatus.SC_OK);
        VehicleHoursRequest vehicleHoursRequest = new VehicleHoursRequest();
        vehicleHoursRequest.setHours(12);
        vehicleHoursRequest.setVehicleCompanyId(567);
        vehicleHoursRequest.setCompany("Lee Trains");
        given()
                .contentType("application/json")
                .body(vehicleHoursRequest)
                .when()
                .post("/vehicle/trackHours")
                .then()
                .statusCode(HttpStatus.SC_OK);
        VehicleHoursRequest vehicleHoursRequest2 = new VehicleHoursRequest();
        vehicleHoursRequest2.setHours(12);
        vehicleHoursRequest2.setVehicleCompanyId(566);
        vehicleHoursRequest2.setCompany("Lee Trains");
        given()
                .contentType("application/json")
                .body(vehicleHoursRequest2)
                .when()
                .post("/vehicle/trackHours")
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        RetrieveVehicleRequest retrieveVehicleRequest = new RetrieveVehicleRequest();
        retrieveVehicleRequest.setCompany("Lee Trains");
        retrieveVehicleRequest.setVehicleCompanyId(567);
        given()
                .contentType("application/json")
                .body(retrieveVehicleRequest)
                .when()
                .post("/vehicle/checkInspectionDate")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("inspectionDue", equalTo(true));
        given()
                .contentType("application/json")
                .body(retrieveVehicleRequest)
                .when()
                .post("/vehicle/inspectVehicle")
                .then()
                .statusCode(HttpStatus.SC_OK);
        when()
                .get("/vehicle/getTrain?vehicleCompanyId=567&company=Lee Trains")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("inspectionDate", equalTo(todayAsString()));
        given()
                .contentType("application/json")
                .body(retrieveVehicleRequest)
                .when()
                .post("/vehicle/checkInspectionDate")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("inspectionDue", equalTo(false))
                .body("remainingDaysUntilNextInspection", equalTo(2555));
        given()
                .contentType("application/json")
                .body(retrieveVehicleRequest)
                .when()
                .post("/vehicle/sellVehicle")
                .then()
                .statusCode(HttpStatus.SC_OK);
        when()
                .get("/vehicle/getTrain?vehicleCompanyId=567&company=Lee Trains")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("vehicleStatus", equalTo("Sold"));
        given()
                .contentType("application/json")
                .body(trainRequest)
                .when()
                .post("vehicle/purchaseTrain")
                .then()
                .statusCode(HttpStatus.SC_CONFLICT);
    }

    /**
     * Helper method to return the current date in the format dd-MM-yyyy.^
     * @return a <code>String</code> containing the current date in format dd-MM-yyyy
     */
    private String todayAsString() {
        LocalDate today = LocalDate.now();
        return today.getDayOfMonth() + "-" + today.getMonthValue() + "-" + today.getYear();
    }

}