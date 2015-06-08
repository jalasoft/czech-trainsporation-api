package cz.jala.trainsportation;

import cz.jalasoft.net.http.netty.NettyHttpClient;
import cz.jalasoft.trainsportation.TrainId;
import cz.jalasoft.trainsportation.TrainPosition;
import cz.jalasoft.trainsportation.TrainSchedule;
import cz.jalasoft.trainsportation.Trainsportation;

/**
 * Created by honzales on 3.5.15.
 */
public class Util {

    private static final Trainsportation trainsportation = new Trainsportation(new NettyHttpClient());

    public static void main(String[] args) throws Exception {

        TrainId train = trainsportation.lookupTrain("1412233");

        TrainSchedule schedule = trainsportation.queryTrainSchedule(train);
        TrainPosition position = trainsportation.queryPosition(train);
    }
}
