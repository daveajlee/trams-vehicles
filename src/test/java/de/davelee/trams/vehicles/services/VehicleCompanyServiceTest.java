package de.davelee.trams.vehicles.services;

import de.davelee.trams.vehicles.TramsVehiclesServerApplication;
import de.davelee.trams.vehicles.api.BusResponse;
import de.davelee.trams.vehicles.model.CompanyData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test the <code>VehicleCompanyService</code> class.
 * @author Dave Lee
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=TramsVehiclesServerApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleCompanyServiceTest {

    @Autowired
    private VehicleCompanyService vehicleCompanyService;

    @Test
    /**
     * Test case: save & load company based on test data.
     * Expected result: no errors are thrown.
     */
    public void testSaveAndLoadCompany() {
        vehicleCompanyService.exportCompany( "Sample Operator");
    }

    @Test
    /**
     * Test case: load company data based on test data.
     * Expected result: no errors are thrown.
     */
    public void testCompanyData() {
        CompanyData companyData = new CompanyData();
        companyData.setCompanyName("MyDaveBuses");
        BusResponse busResponse = new BusResponse();
        busResponse.setType("Double Decker");
        busResponse.setLivery("Fast Line");
        busResponse.setStandingCapacity(30);
        busResponse.setSeatingCapacity(40);
        busResponse.setDeliveryDate("01-01-2019");
        busResponse.setVehicleCompanyId(123);
        busResponse.setRegistrationNumber("234-56-ABC");
        busResponse.setCompany(companyData.getCompanyName());
        busResponse.setVehicleStatus("Delivered");
        List<BusResponse> busResponseList = new ArrayList<>();
        busResponseList.add(busResponse);
        companyData.setBusResponseList(busResponseList);
        vehicleCompanyService.loadCompanyData(companyData);
    }

}
