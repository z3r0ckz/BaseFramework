package ConfigManager;

import Logger.MyLogger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ConfigManager {
    private static final String CONFIG_FILE_PATH = "src/main/resources/Config/config.json";
    private static JSONObject config;

    public static void loadConfig() {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(CONFIG_FILE_PATH);
            config = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            MyLogger.info("Error al leer el archivo de configuración "+ e);
        }
    }
    public static String get(String key) {
        if (config == null) {
            loadConfig();
        }
        return config.get(key).toString();
    }
}
