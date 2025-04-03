package Browser;

import Logger.MyLogger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FirefoxCaps {

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        try {
            String browserConfig = new String(Files.readAllBytes(Paths.get("src/main/resources/Config/firefoxConfig.json")));
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(browserConfig);
            
            // Convertimos JSONObject en un Map<String, Object>
            
            @SuppressWarnings("unchecked")
            Map<String, Object> jsonObject = (Map<String, Object>) jsonArray.get(0);

            // Hacer casting explícito al tipo correcto
            String browserMode = (String) jsonObject.getOrDefault("browserMode", "");
            String language = (String) jsonObject.getOrDefault("language", "en-US");
            boolean disableTracking = (boolean) jsonObject.getOrDefault("disableTrackingProtection", false);
            boolean acceptInsecureCerts = (boolean) jsonObject.getOrDefault("acceptInsecureCerts", false);
            boolean disableSafeBrowsing = (boolean) jsonObject.getOrDefault("disableSafeBrowsing", false);
            boolean allowMixedContent = (boolean) jsonObject.getOrDefault("allowMixedContent", false);

            // Aplicar configuraciones a FirefoxOptions
            options.addArguments(browserMode);
            options.addPreference("intl.accept_languages", language);

            if (disableTracking) {
                options.addPreference("privacy.trackingprotection.enabled", false);
            }
            if (acceptInsecureCerts) {
                options.setAcceptInsecureCerts(true);
                options.addPreference("webdriver_accept_untrusted_certs", true);
                options.addPreference("webdriver_assume_untrusted_issuer", false);
            }
            if (disableSafeBrowsing) {
                options.addPreference("browser.safebrowsing.malware.enabled", false);
                options.addPreference("browser.safebrowsing.phishing.enabled", false);
                options.addPreference("browser.safebrowsing.downloads.enabled", false);
            }
            if (allowMixedContent) {
                options.addPreference("security.mixed_content.block_active_content", false);
                options.addPreference("security.mixed_content.block_display_content", false);
            }

            MyLogger.info("Firefox options successfully configured.");

        } catch (IOException | ParseException e) {
            MyLogger.error("Error reading Firefox configuration", e);
        }

        return options;
    }
}
