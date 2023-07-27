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

public class ChromeCaps {
    public static String @NotNull [] readChromeBrowserConfig() {
        try {
            String browserConfig = new String(Files.readAllBytes(Paths.get("src/main/resources/Config/chromeConfig.json")));

            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(browserConfig);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            String browserMode = (String) jsonObject.get("browserMode");
            String language = (String) jsonObject.get("language");
            String remoteAllow = (String) jsonObject.get("remoteAllow");
            return new String[]{browserMode, language, remoteAllow};
        } catch (IOException | ParseException e) {
            MyLogger.error("No options found for Chrome", e);
        }
        return new String[0];
    }
}
