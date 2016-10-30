/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package etl;

import java.net.URL;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class Etl {

    public boolean run() {
        try {
            KettleEnvironment.init();
            URL location = Etl.class.getProtectionDomain().getCodeSource().getLocation();
            String projectLocation = String.valueOf(location.getFile());
            String ETLLoc = projectLocation.replace("build/web/WEB-INF/classes/", "ETL.ktr");
            TransMeta metaData = new TransMeta(ETLLoc);
            Trans trans = new Trans(metaData);
            trans.execute(null);
            trans.waitUntilFinished();
            if (trans.getErrors() == 0) {
                return true;
            }
        } catch (KettleException e) {
            e.printStackTrace();
        }
        return false;
    }
}
