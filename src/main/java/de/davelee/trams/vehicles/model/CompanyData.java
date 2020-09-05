package de.davelee.trams.vehicles.model;

import de.davelee.trams.vehicles.api.BusResponse;
import de.davelee.trams.vehicles.api.TrainResponse;
import de.davelee.trams.vehicles.api.TramResponse;

import java.util.List;

/**
 * This class represents the model of a company and all of its vehicles which can be exported or imported
 * in JSON format.
 * @author Dave Lee
 */
public class CompanyData {

    private String companyName;

    private List<BusResponse> busResponseList;

    private List<TramResponse> tramResponseList;

    private List<TrainResponse> trainResponseList;

    /**
     * Return the name of the company.
     * @return a <code>String</code> containing the name of the company.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Set the name of the company.
     * @param companyName a <code>String</code> containing the name of the company.
     */
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    /**
     * Return a list of all buses belonging to this company.
     * @return a <code>List</code> of <code>BusResponse</code> objects representing all buses belonging to
     * this company.
     */
    public List<BusResponse> getBusResponseList() {
        return busResponseList;
    }

    /**
     * Set a list of all buses belonging to this company.
     * @param busResponseList a <code>List</code> of <code>BusResponse</code> objects representing all buses belonging to
     * this company.
     */
    public void setBusResponseList(final List<BusResponse> busResponseList) {
        this.busResponseList = busResponseList;
    }

    /**
     * Return a list of all trams belonging to this company.
     * @return a <code>List</code> of <code>TramResponse</code> objects representing all trams belonging to
     * this company.
     */
    public List<TramResponse> getTramResponseList() {
        return tramResponseList;
    }

    /**
     * Set a list of all trams belonging to this company.
     * @param tramResponseList a <code>List</code> of <code>TramResponse</code> objects representing all trams belonging to
     * this company.
     */
    public void setTramResponseList(final List<TramResponse> tramResponseList) {
        this.tramResponseList = tramResponseList;
    }

    /**
     * Return a list of all trains belonging to this company.
     * @return a <code>List</code> of <code>TrainResponse</code> objects representing all trains belonging to
     * this company.
     */
    public List<TrainResponse> getTrainResponseList() {
        return trainResponseList;
    }

    /**
     * Set a list of all trains belonging to this company.
     * @param trainResponseList a <code>List</code> of <code>TrainResponse</code> objects representing all trains belonging to
     * this company.
     */
    public void setTrainResponseList(final List<TrainResponse> trainResponseList) {
        this.trainResponseList = trainResponseList;
    }

}
