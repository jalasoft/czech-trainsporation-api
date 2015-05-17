package cz.jala.trainsportation;

import cz.jalasoft.net.http.netty.NettyHttpClient;
import cz.jalasoft.trainsportation.Trainsportation;
import cz.jalasoft.trainsportation.impl.CzechTrainsportation;

/**
 * Created by honzales on 3.5.15.
 */
public class Util {

    public static void main(String[] args) throws Exception {

        Trainsportation trainsportation = new CzechTrainsportation(new NettyHttpClient());
        trainsportation.lookupTrain("146");
    }
}
