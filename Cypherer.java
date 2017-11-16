package step33;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by 122 on 04.08.2015.
 */
public interface Cypherer {
    void script(String pathFrom) throws FileNotFoundException, IOException;
    void descript(String pathTo) throws FileNotFoundException, IOException;
}
