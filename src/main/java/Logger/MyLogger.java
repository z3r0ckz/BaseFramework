package Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger {
    private static final Logger logger = LogManager.getLogger();
    public static void info(String message) {
        logger.info(message);
    }
    public static void error(String message, Exception e){logger.error(message);}
}
