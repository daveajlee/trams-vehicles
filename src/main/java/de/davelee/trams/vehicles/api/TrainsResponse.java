package de.davelee.trams.vehicles.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Container class for a number of TrainResponse objects.
 * @author Dave Lee
 */
public class TrainsResponse {

    public List<TrainResponse> trainResponseList = new ArrayList<>();

    /**
     * Return a list of TrainResponse objects contained in this object.
     * @return a <code>List</code> of <code>TrainResponse</code> objects.
     */
    public List<TrainResponse> getTrainResponseList() {
        return trainResponseList;
    }

    /**
     * Set a list of TrainResponse objects contained in this object.
     * @param trainResponseList a <code>List</code> of <code>TrainResponse</code> objects.
     */
    public void setTrainResponseList(final List<TrainResponse> trainResponseList) {
        this.trainResponseList = trainResponseList;
    }

    /**
     * Add a new TrainResponse object to this container.
     * @param trainResponse a <code>TrainResponse</code> object to add.
     */
    public void addTrainResponse ( final TrainResponse trainResponse) {
        trainResponseList.add(trainResponse);
    }

}
