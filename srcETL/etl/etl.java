/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etl;

import java.net.URL;
import static javax.servlet.SessionTrackingMode.URL;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

/**
 *
 * @author Shermaine
 */
public class etl {

    public boolean run() {
        try {
            KettleEnvironment.init();
            URL location = etl.class.getProtectionDomain().getCodeSource().getLocation();
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
