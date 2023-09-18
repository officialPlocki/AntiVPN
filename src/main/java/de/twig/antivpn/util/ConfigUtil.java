package de.twig.antivpn.util;

import java.io.*;

public class ConfigUtil {

    private final File file;

    public ConfigUtil() {
        file = new File("apiKey");
        if(!file.exists()) {
            try {
                file.createNewFile();
                PrintWriter writer = new PrintWriter(new FileWriter(file));
                writer.write("WRITE-API-KEY-HERE-FROM-IPHUNTER.INFO");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getAPIKey() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String string = reader.readLine();
            reader.close();
            return string;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
