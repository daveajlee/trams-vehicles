package de.davelee.trams.vehicles.rest.controllers;

import de.davelee.trams.vehicles.api.BusRequest;
import de.davelee.trams.vehicles.api.TrainRequest;
import de.davelee.trams.vehicles.api.TramRequest;
import de.davelee.trams.vehicles.api.BusResponse;
import de.davelee.trams.vehicles.api.TrainResponse;
import de.davelee.trams.vehicles.api.TramResponse;
import de.davelee.trams.vehicles.api.BusesResponse;
import de.davelee.trams.vehicles.api.TrainsResponse;
import de.davelee.trams.vehicles.api.TramsResponse;
import de.davelee.trams.vehicles.api.VehicleResponse;
import de.davelee.trams.vehicles.api.VehicleHoursRequest;
import de.davelee.trams.vehicles.api.CheckVehicleHoursResponse;
import de.davelee.trams.vehicles.api.RetrieveVehicleRequest;
import de.davelee.trams.vehicles.api.CheckInspectionResponse;
import de.davelee.trams.vehicles.api.VehicleAssignRequest;
import de.davelee.trams.vehicles.api.DeleteAssignRequest;
import de.davelee.trams.vehicles.api.VehicleRequest;
import de.davelee.trams.vehicles.api.VehicleHistoryResponse;
import de.davelee.trams.vehicles.data.Bus;
import de.davelee.trams.vehicles.data.VehicleIdentifier;
import de.davelee.trams.vehicles.data.VehicleStatus;
import de.davelee.trams.vehicles.data.Tram;
import de.davelee.trams.vehicles.data.Train;
import de.davelee.trams.vehicles.data.VehicleHistory;
import de.davelee.trams.vehicles.data.Vehicle;
import de.davelee.trams.vehicles.services.BusService;
import de.davelee.trams.vehicles.services.TrainService;
import de.davelee.trams.vehicles.services.TramService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value="vehicle", description="Vehicle Operations")
@RequestMapping(value="/vehicle")
/**
 * This class defines all of the Rest Operations possible for Vehicles in the REST API.
 * @author Dave Lee
 */
public class VehicleOperationsRestController {

    @Autowired
    private BusService busService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private TramService tramService;

    @Value("${bus.permitted.hours.max}")
    private int maxBusHours;

    @Value("${bus.inspection.interval}")
    private long busInspectionInterval;

    @Value("${train.inspection.interval}")
    private long trainInspectionInterval;

    @Value("${tram.inspection.interval}")
    private long tramInspectionInterval;

    @ApiOperation(value = "Purchase a bus", notes="Method to purchase a bus and add it to the depot.")
    @RequestMapping(method = RequestMethod.POST, produces="text/plain", value="/purchaseBus")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code=201,message="Successfully purchased bus and added to database"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to purchase a bus and add it to the depot.
     * @param busRequest a <code>BusRequest</code> object containing the information about the bus to purchase.
     * @return a <code>ResponseEntity</code> object with either Bad Request or Conflict if the request is not valid
     * or created if the bus is successfully purchased or internal server error if no DB available.
     */
    public ResponseEntity<String> purchaseBus ( @RequestBody final BusRequest busRequest ) {
        if (!validateInput(busRequest) )
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        Bus existingBus = busService.findByVehicleCompanyIdAndCompany(busRequest.getVehicleCompanyId(),busRequest.getCompany());
        if ( existingBus != null )
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        Bus bus = new Bus();
        bus.setRegistrationNumber(busRequest.getRegistrationNumber());
        bus.setSeatingCapacity(busRequest.getSeatingCapacity());
        bus.setStandingCapacity(busRequest.getStandingCapacity());
        bus.setVehicleIdentifier(new VehicleIdentifier(busRequest.getCompany(), busRequest.getVehicleCompanyId()));
        bus.setDeliveryDate(translateDateFromString(busRequest.getDeliveryDate()));
        bus.setLivery(busRequest.getLivery());
        bus.setType(busRequest.getType());
        bus.setInspectionDate(bus.getDeliveryDate());
        bus.setVehicleStatus(VehicleStatus.PURCHASED);
        bus.addHistory(createPurchaseHistoryEntry());
        if (busService.addBus(bus) != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @ApiOperation(value = "Purchase a train", notes="Method to purchase a train and add it to the depot.")
    @RequestMapping(method = RequestMethod.POST, produces="text/plain", value="/purchaseTrain")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code=201,message="Successfully purchased train and added to database"),@ApiResponse(code=409,message="Could not purchase train as train number already exists"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to purchase a train and add it to the depot.
     * @param trainRequest a <code>TrainRequest</code> object containing the information about the train to purchase.
     * @return a <code>ResponseEntity</code> object with either Bad Request or Conflict if the request is not valid
     * or created if the bus is successfully purchased or internal server error if no DB available.
     */
    public ResponseEntity<String> purchaseTrain ( @RequestBody final TrainRequest trainRequest ) {
        if (!validateInput(trainRequest) )
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        Train existingTrain = trainService.findByVehicleCompanyIdAndCompany(trainRequest.getVehicleCompanyId(), trainRequest.getCompany());
        if ( existingTrain != null )
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        Train train = new Train();
        train.setOperatingMode(trainRequest.getOperatingMode());
        train.setSeatingCapacity(trainRequest.getSeatingCapacity());
        train.setStandingCapacity(trainRequest.getStandingCapacity());
        train.setVehicleIdentifier(new VehicleIdentifier(trainRequest.getCompany(), trainRequest.getVehicleCompanyId()));
        train.setDeliveryDate(translateDateFromString(trainRequest.getDeliveryDate()));
        train.setLivery(trainRequest.getLivery());
        train.setType(trainRequest.getType());
        train.setInspectionDate(train.getDeliveryDate());
        train.setVehicleStatus(VehicleStatus.PURCHASED);
        train.addHistory(createPurchaseHistoryEntry());
        if ( trainService.addTrain(train) != null )
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @ApiOperation(value = "Purchase a tram", notes="Method to purchase a tram and add it to the depot.")
    @RequestMapping(method = RequestMethod.POST, produces="text/plain", value="/purchaseTram")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code=201,message="Successfully purchased tram and added to database"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to purchase a tram and add it to the depot.
     * @param tramRequest a <code>TramRequest</code> object containing the information about the tram to purchase.
     * @return a <code>ResponseEntity</code> object with either Bad Request or Conflict if the request is not valid
     * or created if the bus is successfully purchased or internal server error if no DB available.
     */
    public ResponseEntity<String> purchaseTram ( @RequestBody final TramRequest tramRequest ) {
        if (!validateInput(tramRequest) )
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        Tram existingTram = tramService.findByVehicleCompanyIdAndCompany(tramRequest.getVehicleCompanyId(), tramRequest.getCompany());
        if ( existingTram != null )
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        Tram tram = new Tram();
        tram.setBidirectional(tramRequest.isBidirectional());
        tram.setSeatingCapacity(tramRequest.getSeatingCapacity());
        tram.setStandingCapacity(tramRequest.getStandingCapacity());
        tram.setVehicleIdentifier(new VehicleIdentifier(tramRequest.getCompany(), tramRequest.getVehicleCompanyId()));
        tram.setDeliveryDate(translateDateFromString(tramRequest.getDeliveryDate()));
        tram.setLivery(tramRequest.getLivery());
        tram.setType(tramRequest.getType());
        tram.setInspectionDate(tram.getDeliveryDate());
        tram.setVehicleStatus(VehicleStatus.PURCHASED);
        tram.addHistory(createPurchaseHistoryEntry());
        if ( tramService.addTram(tram) != null )
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @ApiOperation(value = "Get bus", notes="Method to get a bus's details by vehicle company id and company.")
    @RequestMapping(method = RequestMethod.GET, produces="application/json", value="/getBus")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully retrieved bus details"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to get a bus's details by fleet number and company.
     * @param vehicleCompanyId a <code>int</code> containing the fleet number.
     * @param company a <code>String</code> containing the company.
     * @return a <code>ResponseEntity</code> object with either the bus information or internal
     * server error if no bus found.
     */
    public ResponseEntity<BusResponse> getBus ( @RequestParam("vehicleCompanyId") final int vehicleCompanyId, @RequestParam("company") final String company ) {
        Bus bus = busService.findByVehicleCompanyIdAndCompany(vehicleCompanyId, company);
        if ( bus == null ) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        BusResponse busResponse = new BusResponse();
        busResponse.setRegistrationNumber(bus.getRegistrationNumber());
        setGeneralVehicleInfos(busResponse, bus);
        return new ResponseEntity<>(busResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Get bus", notes="Method to get all buses for a company.")
    @RequestMapping(method = RequestMethod.GET, produces="application/json", value="/getBuses")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully retrieved buses"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to get all buses for a company.
     * @param company a <code>String</code> containing the company.
     * @return a <code>ResponseEntity</code> object with either all buses or internal
     * server error if no bus found.
     */
    public ResponseEntity<BusesResponse> getBuses ( @RequestParam("company") final String company ) {
        List<Bus> buses = busService.findByCompany(company);
        if ( buses == null ) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        BusesResponse busesResponse = new BusesResponse();
        for ( Bus bus : buses ) {
            BusResponse busResponse = new BusResponse();
            busResponse.setRegistrationNumber(bus.getRegistrationNumber());
            setGeneralVehicleInfos(busResponse, bus);
            busesResponse.addBusResponse(busResponse);
        }
        return new ResponseEntity<>(busesResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Add buses", notes="Method to get all buses for a company.")
    @RequestMapping(method = RequestMethod.POST, produces="text/plain", value="/addBuses")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully add buses"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to add buses to the database.
     * @param busRequests a <code>String</code> containing the company.
     * @return a <code>ResponseEntity</code> object with the result of the add buses.
     */
    public ResponseEntity<String> addBuses ( @RequestBody final List<BusResponse> busResponses ) {
        for ( BusResponse busResponse : busResponses ) {
            Bus bus = new Bus();
            bus.setRegistrationNumber(busResponse.getRegistrationNumber());
            bus.setAssignedRouteSchedule(busResponse.getAssignedRouteSchedule());
            bus.setDeliveryDate(translateDateFromString(busResponse.getDeliveryDate()));
            if ( busResponse.getInspectionDate() != null ) {
                bus.setInspectionDate(translateDateFromString(busResponse.getInspectionDate()));
            } else {
                bus.setInspectionDate(translateDateFromString(busResponse.getDeliveryDate()));
            }
            bus.setLivery(busResponse.getLivery());
            bus.setSeatingCapacity(busResponse.getSeatingCapacity());
            bus.setStandingCapacity(busResponse.getStandingCapacity());
            bus.setType(busResponse.getType());
            bus.setVehicleIdentifier(new VehicleIdentifier(busResponse.getCompany(), busResponse.getVehicleCompanyId()));
            bus.setVehicleStatus(VehicleStatus.getVehicleStatus(busResponse.getVehicleStatus()));
            if ( busService.addBus(bus) == null ) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ApiOperation(value = "Get tram", notes="Method to get a trams's details by vehicle company id and company.")
    @RequestMapping(method = RequestMethod.GET, produces="application/json", value="/getTram")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully retrieved tram details"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to get a tram's details by fleet number and company.
     * @param vehicleCompanyId a <code>int</code> containing the fleet number.
     * @param company a <code>String</code> containing the company.
     * @return a <code>ResponseEntity</code> object with either the tram information or internal
     * server error if no tram found.
     */
    public ResponseEntity<TramResponse> getTram ( @RequestParam("vehicleCompanyId") final int vehicleCompanyId, @RequestParam("company") final String company ) {
        Tram tram = tramService.findByVehicleCompanyIdAndCompany(vehicleCompanyId, company);
        if ( tram == null ) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        TramResponse tramResponse = new TramResponse();
        tramResponse.setBidirectional(tram.isBidirectional());
        setGeneralVehicleInfos(tramResponse, tram);
        return new ResponseEntity<>(tramResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Get trams", notes="Method to get all trams for a company.")
    @RequestMapping(method = RequestMethod.GET, produces="application/json", value="/getTrams")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully retrieved trams"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to get all trams for a company.
     * @param company a <code>String</code> containing the company.
     * @return a <code>ResponseEntity</code> object with either all trams or internal
     * server error if no tram found.
     */
    public ResponseEntity<TramsResponse> getTrams ( @RequestParam("company") final String company ) {
        List<Tram> trams = tramService.findByCompany(company);
        if ( trams == null ) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        TramsResponse tramsResponse = new TramsResponse();
        for ( Tram tram : trams ) {
            TramResponse tramResponse = new TramResponse();
            tramResponse.setBidirectional(tram.isBidirectional());
            setGeneralVehicleInfos(tramResponse, tram);
            tramsResponse.addTramResponse(tramResponse);
        }
        return new ResponseEntity<>(tramsResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Add trams", notes="Method to get all trams for a company.")
    @RequestMapping(method = RequestMethod.POST, produces="text/plain", value="/addTrams")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully add trams"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to add trams to the database.
     * @param tramRequests a <code>String</code> containing the company.
     * @return a <code>ResponseEntity</code> object with the result of the add trams.
     */
    public ResponseEntity<String> addTrams ( @RequestBody final List<TramResponse> tramResponses ) {
        for ( TramResponse tramResponse : tramResponses ) {
            Tram tram = new Tram();
            tram.setBidirectional(tramResponse.isBidirectional());
            tram.setAssignedRouteSchedule(tramResponse.getAssignedRouteSchedule());
            tram.setDeliveryDate(translateDateFromString(tramResponse.getDeliveryDate()));
            if ( tram.getInspectionDate() != null ) {
                tram.setInspectionDate(translateDateFromString(tramResponse.getInspectionDate()));
            } else {
                tram.setInspectionDate(translateDateFromString(tramResponse.getDeliveryDate()));
            }
            tram.setLivery(tramResponse.getLivery());
            tram.setSeatingCapacity(tramResponse.getSeatingCapacity());
            tram.setStandingCapacity(tramResponse.getStandingCapacity());
            tram.setType(tramResponse.getType());
            tram.setVehicleIdentifier(new VehicleIdentifier(tramResponse.getCompany(), tramResponse.getVehicleCompanyId()));
            tram.setVehicleStatus(VehicleStatus.getVehicleStatus(tramResponse.getVehicleStatus()));
            if ( tramService.addTram(tram) == null ) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ApiOperation(value = "Get train", notes="Method to get a train's details by vehicle company id and company.")
    @RequestMapping(method = RequestMethod.GET, produces="application/json", value="/getTrain")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully retrieved train details"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to get a train's details by fleet number and company.
     * @param vehicleCompanyId a <code>int</code> containing the fleet number.
     * @param company a <code>String</code> containing the company.
     * @return a <code>ResponseEntity</code> object with either the train information or internal
     * server error if no train found.
     */
    public ResponseEntity<TrainResponse> getTrain ( @RequestParam("vehicleCompanyId") final int vehicleCompanyId, @RequestParam("company") final String company ) {
        Train train = trainService.findByVehicleCompanyIdAndCompany(vehicleCompanyId, company);
        if ( train == null ) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        TrainResponse trainResponse = new TrainResponse();
        trainResponse.setOperatingMode(train.getOperatingMode());
        setGeneralVehicleInfos(trainResponse, train);
        return new ResponseEntity<>(trainResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Get trains", notes="Method to get all trains for a company.")
    @RequestMapping(method = RequestMethod.GET, produces="application/json", value="/getTrains")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully retrieved trains"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to get all trains for a company.
     * @param company a <code>String</code> containing the company.
     * @return a <code>ResponseEntity</code> object with either all trains or internal
     * server error if no train found.
     */
    public ResponseEntity<TrainsResponse> getTrains ( @RequestParam("company") final String company ) {
        List<Train> trains = trainService.findByCompany(company);
        if ( trains == null ) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        TrainsResponse trainsResponse = new TrainsResponse();
        for ( Train train : trains ) {
            TrainResponse trainResponse = new TrainResponse();
            trainResponse.setOperatingMode(train.getOperatingMode());
            setGeneralVehicleInfos(trainResponse, train);
            trainsResponse.addTrainResponse(trainResponse);
        }
        return new ResponseEntity<>(trainsResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Add trains", notes="Method to add all trains for a company.")
    @RequestMapping(method = RequestMethod.POST, produces="text/plain", value="/addTrains")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully add trains"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to add trains to the database.
     * @param trainRequests a <code>String</code> containing the company.
     * @return a <code>ResponseEntity</code> object with the result of the add trains.
     */
    public ResponseEntity<String> addTrains ( @RequestBody final List<TrainResponse> trainResponses ) {
        for ( TrainResponse trainResponse : trainResponses ) {
            Train train = new Train();
            train.setOperatingMode(trainResponse.getOperatingMode());
            train.setAssignedRouteSchedule(trainResponse.getAssignedRouteSchedule());
            train.setDeliveryDate(translateDateFromString(trainResponse.getDeliveryDate()));
            if ( train.getInspectionDate() != null ) {
                train.setInspectionDate(translateDateFromString(trainResponse.getInspectionDate()));
            } else {
                train.setInspectionDate(translateDateFromString(trainResponse.getDeliveryDate()));
            }
            train.setLivery(trainResponse.getLivery());
            train.setSeatingCapacity(trainResponse.getSeatingCapacity());
            train.setStandingCapacity(trainResponse.getStandingCapacity());
            train.setType(trainResponse.getType());
            train.setVehicleIdentifier(new VehicleIdentifier(trainResponse.getCompany(), trainResponse.getVehicleCompanyId()));
            train.setVehicleStatus(VehicleStatus.getVehicleStatus(trainResponse.getVehicleStatus()));
            if ( trainService.addTrain(train) == null ) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ApiOperation(value = "Track hours", notes="Method to track hours for a particular vehicle company id and company.")
    @RequestMapping(method = RequestMethod.POST, produces="text/plain", value="/trackHours")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully track hours"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to track hours for a particular fleet number and company.
     * @param vehicleHoursRequest a <code>VehicleHoursRequest</code> object containing the fleet number and company.
     * @return a <code>ResponseEntity</code> object which is ok if the hours could be added or internal server error
     * if no vehicle found.
     */
    public ResponseEntity<Void> trackHours ( @RequestBody final VehicleHoursRequest vehicleHoursRequest ) {
        // Check that the vehicle company id and hours are greater than 0 otherwise bad request.
        if ( vehicleHoursRequest.getVehicleCompanyId() > 0 && vehicleHoursRequest.getHours() > 0 ) {
            //Check if the vehicle is a bus, if so increment hours.
            Bus bus = busService.findByVehicleCompanyIdAndCompany(vehicleHoursRequest.getVehicleCompanyId(), vehicleHoursRequest.getCompany());
            if ( bus != null ) {
                busService.incrementHours(bus, vehicleHoursRequest.getHours());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                Tram tram = tramService.findByVehicleCompanyIdAndCompany(vehicleHoursRequest.getVehicleCompanyId(), vehicleHoursRequest.getCompany());
                if ( tram != null ) {
                    tramService.incrementHours(tram, vehicleHoursRequest.getHours());
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    Train train = trainService.findByVehicleCompanyIdAndCompany(vehicleHoursRequest.getVehicleCompanyId(), vehicleHoursRequest.getCompany());
                    if ( train != null ) {
                        trainService.incrementHours(train, vehicleHoursRequest.getHours());
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Check hours for bus", notes="Method to check hours worked by a bus. Returns true if and only if the vehicle has worked less today than the maximum hours permitted")
    @RequestMapping(method = RequestMethod.POST, produces="application/json", value="/checkHours")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully checked hours"), @ApiResponse(code=400,message="Input was not valid"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to check hours for a bus. All other vehicle types can work unlimited hours.
     * @param retrieveVehicleRequest a <code>RetrieveVehicleRequest</code> object containing the fleet number and company
     *                               for the vehicle to check.
     * @return a <code>ResponseEntity</code> object which is ok and has a response true or false if vehicle found
     * otherwise internal server error.
     */
    public ResponseEntity<CheckVehicleHoursResponse> checkHours ( @RequestBody final RetrieveVehicleRequest retrieveVehicleRequest ) {
        Bus bus = busService.findByVehicleCompanyIdAndCompany(retrieveVehicleRequest.getVehicleCompanyId(), retrieveVehicleRequest.getCompany());
        if ( bus != null ) {
            if ( bus.getHoursWorkedForDate(LocalDate.now()) != null ) {
                    CheckVehicleHoursResponse checkDriverHoursResponse = new CheckVehicleHoursResponse();
                    checkDriverHoursResponse.setFurtherHoursAllowed(bus.getHoursWorkedForDate(LocalDate.now()) < maxBusHours);
                    checkDriverHoursResponse.setRemainingHours(maxBusHours - bus.getHoursWorkedForDate(LocalDate.now()));
                    return new ResponseEntity<>(checkDriverHoursResponse, HttpStatus.OK);
            }
            CheckVehicleHoursResponse checkVehicleHoursResponse = new CheckVehicleHoursResponse();
            checkVehicleHoursResponse.setFurtherHoursAllowed(true);
            checkVehicleHoursResponse.setRemainingHours(maxBusHours);
            return new ResponseEntity<>(checkVehicleHoursResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Sell vehicle", notes="Method to sell vehicle for a particular vehicle company id and company.")
    @RequestMapping(method = RequestMethod.POST, produces="text/plain", value="/sellVehicle")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully sold vehicle"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to sell a particular vehicle by fleet number and company id.
     * @param retrieveVehicleRequest a <code>RetrieveVehicleRequest</code> object containing fleet number and company id.
     * @return a <code>ResponseEntity</code> which is ok if the vehicle was sold successfully or internal server error if the vehicle is not found.
     */
    public ResponseEntity<Void> sellVehicle ( @RequestBody final RetrieveVehicleRequest retrieveVehicleRequest ) {
        // Check that the vehicle company id is greater than 0 and company is not null otherwise bad request.
        if ( retrieveVehicleRequest.getVehicleCompanyId() > 0 && retrieveVehicleRequest.getCompany() != null ) {
            //Check if the vehicle is a bus, if so sell vehicle.
            Bus bus = busService.findByVehicleCompanyIdAndCompany(retrieveVehicleRequest.getVehicleCompanyId(), retrieveVehicleRequest.getCompany());
            if ( bus != null ) {
                busService.sellBus(bus);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                Tram tram = tramService.findByVehicleCompanyIdAndCompany(retrieveVehicleRequest.getVehicleCompanyId(), retrieveVehicleRequest.getCompany());
                if ( tram != null ) {
                    tramService.sellTram(tram);
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    Train train = trainService.findByVehicleCompanyIdAndCompany(retrieveVehicleRequest.getVehicleCompanyId(), retrieveVehicleRequest.getCompany());
                    if ( train != null ) {
                        trainService.sellTrain(train);
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Check if inspection needed", notes="Method to check for a particular vehicle company id and company if a vehicle is due for inspection.")
    @RequestMapping(method = RequestMethod.POST, produces="application/json", value="/checkInspectionDate")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully checked inspection date"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to check if vehicle is due for inspection by fleet number and company.
     * @param retrieveVehicleRequest a <code>RetrieveVehicleRequest</code> object containing the fleet number and company.
     * @return a <code>ResponseEntity</code> object which is ok and contains the date when inspection required or internal
     * server error if vehicle is not found.
     */
    public ResponseEntity<CheckInspectionResponse> checkInspectionDate ( @RequestBody final RetrieveVehicleRequest retrieveVehicleRequest ) {
        // Check that the vehicle company id is greater than 0 and company is not null otherwise bad request.
        if ( retrieveVehicleRequest.getVehicleCompanyId() > 0 && retrieveVehicleRequest.getCompany() != null ) {
            //Check if the vehicle is a bus, if so inspect vehicle.
            Bus bus = busService.findByVehicleCompanyIdAndCompany(retrieveVehicleRequest.getVehicleCompanyId(), retrieveVehicleRequest.getCompany());
            if ( bus != null ) {
                return new ResponseEntity<>(generateCheckInspectionResponse(bus.getInspectionDate(), busInspectionInterval), HttpStatus.OK);
            } else {
                Tram tram = tramService.findByVehicleCompanyIdAndCompany(retrieveVehicleRequest.getVehicleCompanyId(), retrieveVehicleRequest.getCompany());
                if ( tram != null ) {
                    return new ResponseEntity<>(generateCheckInspectionResponse(tram.getInspectionDate(), tramInspectionInterval), HttpStatus.OK);
                } else {
                    Train train = trainService.findByVehicleCompanyIdAndCompany(retrieveVehicleRequest.getVehicleCompanyId(), retrieveVehicleRequest.getCompany());
                    if ( train != null ) {
                        return new ResponseEntity<>(generateCheckInspectionResponse(train.getInspectionDate(), trainInspectionInterval), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Inspect vehicle", notes="Method to inspect vehicle for a particular vehicle company id and company.")
    @RequestMapping(method = RequestMethod.POST, produces="text/plain", value="/inspectVehicle")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully inspect vehicle"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to inspect a vehicle by fleet number and company.
     * @param retrieveVehicleRequest a <code>RetrieveVehicleRequest</code> object containing the fleet number and company.
     * @return a <code>ResponseEntity</code> object which is ok if the vehicle could be inspected or internal server
     * error if no vehicle found.
     */
    public ResponseEntity<Void> inspectVehicle ( @RequestBody final RetrieveVehicleRequest retrieveVehicleRequest ) {
        // Check that the vehicle company id is greater than 0 and company is not null otherwise bad request.
        if ( retrieveVehicleRequest.getVehicleCompanyId() > 0 && retrieveVehicleRequest.getCompany() != null ) {
            //Check if the vehicle is a bus, if so inspect vehicle.
            Bus bus = busService.findByVehicleCompanyIdAndCompany(retrieveVehicleRequest.getVehicleCompanyId(), retrieveVehicleRequest.getCompany());
            if ( bus != null ) {
                busService.inspectBus(bus);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                Tram tram = tramService.findByVehicleCompanyIdAndCompany(retrieveVehicleRequest.getVehicleCompanyId(), retrieveVehicleRequest.getCompany());
                if ( tram != null ) {
                    tramService.inspectTram(tram);
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    Train train = trainService.findByVehicleCompanyIdAndCompany(retrieveVehicleRequest.getVehicleCompanyId(), retrieveVehicleRequest.getCompany());
                    if ( train != null ) {
                        trainService.inspectTrain(train);
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Assign route", notes="Method to assign a route schedule for a particular vehicle company id and company.")
    @RequestMapping(method = RequestMethod.POST, produces="text/plain", value="/assignRoute")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully assigned"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to assign a route schedule by fleet number and company.
     * @param vehicleAssignRequest a <code>VehicleAssignRequest</code> containing fleet number, company and route schedule.
     * @return a <code>ResponseEntity</code> object which is ok if the vehicle could be successfully
     * assigned or internal server error if no vehicle found.
     */
    public ResponseEntity<Void> assignRoute ( @RequestBody final VehicleAssignRequest vehicleAssignRequest ) {
        // Check that the vehicle company id is greater than 0 and company is not null otherwise bad request.
        if ( vehicleAssignRequest.getVehicleCompanyId() > 0 && vehicleAssignRequest.getCompany() != null && vehicleAssignRequest.getAssignedRouteSchedule() != null ) {
            //Check if the vehicle is a bus, if so inspect vehicle.
            Bus bus = busService.findByVehicleCompanyIdAndCompany(vehicleAssignRequest.getVehicleCompanyId(), vehicleAssignRequest.getCompany());
            if ( bus != null ) {
                busService.assignBus(bus, vehicleAssignRequest.getAssignedRouteSchedule());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                Tram tram = tramService.findByVehicleCompanyIdAndCompany(vehicleAssignRequest.getVehicleCompanyId(), vehicleAssignRequest.getCompany());
                if ( tram != null ) {
                    tramService.assignTram(tram, vehicleAssignRequest.getAssignedRouteSchedule());
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    Train train = trainService.findByVehicleCompanyIdAndCompany(vehicleAssignRequest.getVehicleCompanyId(), vehicleAssignRequest.getCompany());
                    if ( train != null ) {
                        trainService.assignTrain(train, vehicleAssignRequest.getAssignedRouteSchedule());
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Remove allocation", notes="Method to remove an allocation for a particular route schedule id and company.")
    @RequestMapping(method = RequestMethod.POST, produces="text/plain", value="/removeAllocation")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successfully removed"), @ApiResponse(code=500,message="Database not available")})
    /**
     * Method to remove  a route schedule by fleet number and company.
     * @param deleteAssignRequest a <code>DeleteAssignRequest</code> containing fleet number, company and route schedule.
     * @return a <code>ResponseEntity</code> object which is ok if the vehicle could be successfully
     * unassigned or internal server error if no vehicle found.
     */
    public ResponseEntity<Void> deleteAllocation ( @RequestBody final DeleteAssignRequest deleteAssignRequest ) {
        // Check that the assigned route schedule and company is not null otherwise bad request.
        if ( deleteAssignRequest.getAssignedRouteSchedule() != null && deleteAssignRequest.getCompany() != null ) {
            //Check if the vehicle is a bus, if so remove allocation.
            Bus bus = busService.findByAssignedRouteScheduleAndCompany(deleteAssignRequest.getAssignedRouteSchedule(), deleteAssignRequest.getCompany());
            if ( bus != null ) {
                busService.removeAllocation(bus);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                Tram tram = tramService.findByAssignedRouteScheduleAndCompany(deleteAssignRequest.getAssignedRouteSchedule(), deleteAssignRequest.getCompany());
                if ( tram != null ) {
                    tramService.removeAllocation(tram);
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    Train train = trainService.findByAssignedRouteScheduleAndCompany(deleteAssignRequest.getAssignedRouteSchedule(), deleteAssignRequest.getCompany());
                    if ( train != null ) {
                        trainService.removeAllocation(train);
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Validation rules for a bus:
     * all fields are required.
     * @param busRequest the object to validate.
     * @return true iff the input is correct.
     */
    public boolean validateInput(final BusRequest busRequest) {
        if ( busRequest.getRegistrationNumber() == null ) {
            return false;
        } else {
            return validateVehicle(busRequest);
        }
    }

    /**
     * Validation rules for a train:
     * all fields are required.
     * @param trainRequest the object to validate.
     * @return true iff the input is correct.
     */
    public boolean validateInput(final TrainRequest trainRequest) {
        if ( trainRequest.getOperatingMode() == null ) {
            return false;
        } else {
            return validateVehicle(trainRequest);
        }
    }

    /**
     * Validation rules for a tram:
     * all fields are required.
     * @param tramRequest the object to validate.
     * @return true iff the input is correct.
     */
    public boolean validateInput(final TramRequest tramRequest) {
        return validateVehicle(tramRequest);
    }

    /**
     * Validation rules for a vehicle:
     * all fields are required.
     * @param vehicleRequest the object to validate.
     * @return true iff the input is correct.
     */
    public boolean validateVehicle(final VehicleRequest vehicleRequest) {
        if ( vehicleRequest.getDeliveryDate() == null ) {
            return false;
        } else if ( vehicleRequest.getSeatingCapacity() <= 0 ) {
            return false;
        } else if ( vehicleRequest.getStandingCapacity() <= 0 ) {
            return false;
        } else if ( vehicleRequest.getVehicleCompanyId() <= 0 ) {
            return false;
        } else if ( !validateDate(vehicleRequest.getDeliveryDate())) {
            return false;
        } else if ( vehicleRequest.getCompany() == null ) {
            return false;
        }
        return vehicleRequest.getType() != null;
    }

    /**
     * Convert date from string format: dd-MM-yyyy to localdate.
     * @param dateStr a <code>String</code> containing the date to convert to LocalDate format.
     * @return a <code>LocalDate</code> object containing the converted date.
     */
    private LocalDate translateDateFromString ( final String dateStr ) {
        String[] dateArray = dateStr.split("-");
        return LocalDate.of(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[0]));
    }

    /**
     * Convert date to string format: localdate to dd-MM-yyyy (string).
     * @param date a <code>LocalDate</code> containing the date to convert to String format.
     * @return a <code>String</code> object containing the converted date.
     */
    private String translateDateToString ( final LocalDate date ) {
        return date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear();
    }

    /**
     * Validate date to ensure it exists as a valid date!
     * @param date a <code>String</code> containing the date to validate.
     * @return a <code>boolean</code> which is true iff the date is valid.
     */
    private boolean validateDate ( final String date ) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        format.setLenient(false);
        try {
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Helper method to create a history entry for purchasing a vehicle.
     * @return a <code>VehicleHistory</code> object for purchasing a vehicle.
     */
    private VehicleHistory createPurchaseHistoryEntry ( ) {
        VehicleHistory vehicleHistory = new VehicleHistory();
        vehicleHistory.setDate(LocalDate.now());
        vehicleHistory.setStatus(VehicleStatus.PURCHASED);
        vehicleHistory.setComment(VehicleStatus.PURCHASED.getText());
        return vehicleHistory;
    }

    /**
     * Helper method to fill a vehicle response based on the supplied vehicle data.
     * @param vehicleResponse a <code>VehicleResponse</code> object to fill with data.
     * @param vehicle a <code>Vehicle</code> object containing the vehicle data.
      */
    private void setGeneralVehicleInfos ( final VehicleResponse vehicleResponse, final Vehicle vehicle ) {
        vehicleResponse.setDeliveryDate(translateDateToString(vehicle.getDeliveryDate()));
        vehicleResponse.setInspectionDate(translateDateToString(vehicle.getInspectionDate()));
        vehicleResponse.setSeatingCapacity(vehicle.getSeatingCapacity());
        vehicleResponse.setStandingCapacity(vehicle.getStandingCapacity());
        vehicleResponse.setVehicleCompanyId(vehicle.getVehicleIdentifier().getVehicleCompanyId());
        vehicleResponse.setCompany(vehicle.getVehicleIdentifier().getCompany());
        vehicleResponse.setLivery(vehicle.getLivery());
        vehicleResponse.setType(vehicle.getType());
        vehicleResponse.setAssignedRouteSchedule(vehicle.getAssignedRouteSchedule());
        vehicleResponse.setVehicleStatus(vehicle.getVehicleStatus().getText());
        List<VehicleHistory> vehicleHistoryList = vehicle.getVehicleHistoryList();
        List<VehicleHistoryResponse> vehicleHistoryResponseList = new ArrayList<>(vehicleHistoryList.size());
        for ( VehicleHistory vehicleHistory : vehicleHistoryList ) {
            VehicleHistoryResponse vehicleHistoryResponse = new VehicleHistoryResponse();
            vehicleHistoryResponse.setComment(vehicleHistory.getComment());
            vehicleHistoryResponse.setDate(translateDateToString(vehicleHistory.getDate()));
            vehicleHistoryResponse.setStatus(vehicleHistory.getStatus().getText());
            vehicleHistoryResponseList.add(vehicleHistoryResponse);
        }
        vehicleResponse.setVehicleHistoryList(vehicleHistoryResponseList);
    }

    /**
     * Helper method to generate a check inspection response based on the supplied data.
     * @param inspectionDate a <code>LocalDate</code> object containing the inspection date.
     * @param inspectionInterval a <code>long</code> containing how often an inspection should take place in days.
     * @return a <code>CheckInspectionResponse</code> object containing the generated CheckInspectionResponse.
     */
    private CheckInspectionResponse generateCheckInspectionResponse ( final LocalDate inspectionDate, final long inspectionInterval ) {
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(inspectionDate, LocalDate.now());
        CheckInspectionResponse checkInspectionResponse = new CheckInspectionResponse();
        checkInspectionResponse.setInspectionDue(daysBetween > inspectionInterval);
        checkInspectionResponse.setRemainingDaysUntilNextInspection(inspectionInterval - daysBetween);
        return checkInspectionResponse;
    }

}