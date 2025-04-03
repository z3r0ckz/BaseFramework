package ConfigManager;

import Logger.MyLogger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private static final String CONFIG_FILE_PATH = "src/main/resources/Config/config.xlsx";
    private static Map<String, String> config = new HashMap<>();

    @SuppressWarnings("deprecation")
	public static void loadConfig() {
        try (FileInputStream file = new FileInputStream(CONFIG_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Usa la primera hoja del Excel
            for (Row row : sheet) {
                Cell keyCell = row.getCell(0);
                Cell valueCell = row.getCell(1);

                if (keyCell != null && valueCell != null) {
                    keyCell.setCellType(CellType.STRING);
                    valueCell.setCellType(CellType.STRING);
                    config.put(keyCell.getStringCellValue(), valueCell.getStringCellValue());
                }
            }
        } catch (IOException e) {
            MyLogger.info("Error al leer el archivo de configuración: " + e);
        }
    }

    public static String get(String key) {
        if (config.isEmpty()) {
            loadConfig();
        }
        return config.getOrDefault(key, "Clave no encontrada");
    }
}