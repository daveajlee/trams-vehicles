package de.davelee.trams.vehicles.admin.ui;

import de.davelee.trams.vehicles.admin.ui.pages.TramsVehiclesManagementPage;
import de.davelee.trams.vehicles.services.VehicleCompanyService;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

/**
 * Test the management page and ensure that it can be displayed successfully.
 * Use test wicket class to prevent Spring Security problems.
 * @author Dave Lee
 */
public class TramsVehiclesManagementPageTest {

    private WicketTester tester;

    @Mock
    private VehicleCompanyService vehicleCompanyService;

    @Before
    /**
     * Setup the test by creating a new Wicket test application.
     */
    public void setUp ( ) {
        MockitoAnnotations.initMocks(this);
        //Creates a new application context mock.
        ApplicationContextMock applicationContextMock = new ApplicationContextMock();
        applicationContextMock.putBean("vehicleCompanyService", vehicleCompanyService);

        //Create a new wicket tester.
        tester = new WicketTester(new TramsVehiclesManagementWicketTestApplication());
        tester.getApplication().getComponentInstantiationListeners().add(new SpringComponentInjector(tester.getApplication(), applicationContextMock));
    }

    @Test
    /**
     * Test case: Check that the page and that all tabs can be rendered successfully.
     * Expected result: all assertions are fulfilled.
     */
    public void testHomepageRendersSuccessfully(){
        assertNotNull(tester);
        tester.getSession().setAttribute("operator", "Sample Operator");
        //start and render the test page
        tester.startPage(TramsVehiclesManagementPage.class);
        //assert rendered page class
        tester.assertRenderedPage(TramsVehiclesManagementPage.class);
        //click on first tab.
        tester.clickLink("mytabpanel:tabs-container:tabs:1:link");
        //assert rendered page class
        tester.assertRenderedPage(TramsVehiclesManagementPage.class);
        //click on second tab.
        tester.clickLink("mytabpanel:tabs-container:tabs:2:link");
        //assert rendered page class
        tester.assertRenderedPage(TramsVehiclesManagementPage.class);
        //click on third tab.
        tester.clickLink("mytabpanel:tabs-container:tabs:3:link");
        //assert rendered page class
        tester.assertRenderedPage(TramsVehiclesManagementPage.class);
        //click on fourth tab.
        tester.clickLink("mytabpanel:tabs-container:tabs:4:link");
        //assert rendered page class
        tester.assertRenderedPage(TramsVehiclesManagementPage.class);
    }

}
