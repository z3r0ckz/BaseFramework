package Browser;

import Logger.MyLogger;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FirefoxCaps {
    public static String @NotNull [] readFirefoxBrowserConfig() {
        try {
            String browserConfig = new String(Files.readAllBytes(Paths.get("src/main/resources/Config/firefoxConfig.json")));

            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(browserConfig);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            String browserMode = (String) jsonObject.get("browserMode");
            String language = (String) jsonObject.get("language");
            return new String[]{browserMode, language};
        } catch (IOException | ParseException e) {
            MyLogger.error("No options found for Firefox", e);
        }
        return new String[0];
    }
}
