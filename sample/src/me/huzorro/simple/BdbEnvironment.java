/**
 * 
 */
package me.huzorro.simple;

import java.io.File;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Durability;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

/**
 * @author huzorro
 *
 */
public class BdbEnvironment {
	private Environment environment;
	private StoredClassCatalog classCatalog;
	private Database classCatalogDB;
	/**
	 * 
	 */
	public BdbEnvironment(String envHome) {
		File home = new File(envHome);
		EnvironmentConfig environmentConfig = new EnvironmentConfig();
		
//		environmentConfig.setConfigParam(EnvironmentConfig.LOG_FILE_MAX, "20000");
		environmentConfig.setTransactional(true);
		environmentConfig.setAllowCreate(true);
		environmentConfig.setDurability(Durability.COMMIT_WRITE_NO_SYNC);
//		environmentConfig.setConfigParam(EnvironmentConfig.LOG_FILE_MAX, value)
//		environmentConfig.setConfigParam("je.log.fileMax", "20000000"); //设置日志大小20M
		environment = new Environment(home, environmentConfig);
	}

	public Environment getEnvironment() {
		return environment;
	}
    public StoredClassCatalog getJavaCatalog() {
        if(classCatalog == null) {
            DatabaseConfig dbConfig = new DatabaseConfig();
            dbConfig.setAllowCreate(true);
            try {
            	
                classCatalogDB = environment.openDatabase(null, "classCatalog", dbConfig);
                System.out.println(classCatalogDB);
                classCatalog = new StoredClassCatalog(classCatalogDB);
            } catch (DatabaseException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException(e);
            }
        }
        return classCatalog;
    }	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
