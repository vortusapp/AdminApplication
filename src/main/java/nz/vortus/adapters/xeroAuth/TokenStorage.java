package nz.vortus.adapters.xeroAuth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class TokenStorage {
    Logger logger = LoggerFactory.getLogger(TokenStorage.class);

    private static final String STORAGE_DIRECTORY = "storage";
    private static final String FILE_EXTENSION = ".txt";
    String rootPath = "C:\\Users\\Tim Broadbent\\Projects\\AdminIntegrator\\src\\";

    private static final HashMap<String, String> tokens = new HashMap<>();

    public static HashMap<String, String> getTokens() {
        return tokens;
    }


    public String get(String key) {
        String item = null;
        String fileName =  key + FILE_EXTENSION;
        String rootPath = "C:\\Users\\Tim Broadbent\\Projects\\AdminIntegrator\\src\\";
        String directoryPath = rootPath+STORAGE_DIRECTORY;

        Path directory = Paths.get(directoryPath);
        Path file = directory.resolve(fileName);
          try {  if (Files.exists(file)) {
            item = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
        }
        } catch (NoSuchFileException e){
            return "";

        } catch (IOException e) {
            e.printStackTrace();
        }

        return item;
    }

    public void clear() {
        HashMap<String, String> map = new HashMap<>();
        map.put("jwt_token", "");
        map.put("id_token", "");
        map.put("access_token", "");
        map.put("refresh_token", "");
        map.put("expires_in_seconds", "");
        map.put("xero_tenant_id", "");

        save(map);
    }

    public void saveItem(String key, String value) {

            String fileName = key + FILE_EXTENSION;
            String content = value;
        String rootPath = "C:\\Users\\Tim Broadbent\\Projects\\AdminIntegrator\\src\\";
        String directoryPath = rootPath+STORAGE_DIRECTORY;
            // Create the directory and file if they don't exist
            try {
                Path directory = Paths.get(directoryPath);
                Path file = directory.resolve(fileName);
                logger.info(String.valueOf(file.toAbsolutePath()));
                if (!Files.exists(directory)) {
                    Files.createDirectories(directory);
                }

                if (!Files.exists(file)) {
                    Files.createFile(file);
                }

                // Write content to the file
                Files.write(file, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
                logger.info("File write successful!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    public void save(HashMap<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            saveItem(key, value);
        }
    }


    public TokenStorage() {
        super();
    }


    public String get(HttpServletRequest request, String key) {
        return get(key);
    }

    public void clear(HttpServletResponse response) {
        clear();
    }

    public void saveItem(HttpServletResponse response, String key, String value) {
      saveItem(key, value);
    }

    public void save(HttpServletResponse response, HashMap<String, String> map) {
        save(map);
    }
}