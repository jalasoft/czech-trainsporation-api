package cz.jalasoft.transportation;

import java.util.Optional;

/**
 * Created by honzales on 16.5.15.
 */
public interface Transport {

    Optional<String> name();

    String code();

    String fullIdentification();

    String carrierName();
}
