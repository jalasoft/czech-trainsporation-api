package cz.jalasoft.trainsportation;

import com.google.common.base.Optional;

/**
 * Created by honzales on 3.5.15.
 */
public interface Train {

    TrainNumber number();

    TrainSchedule schedule();
}
