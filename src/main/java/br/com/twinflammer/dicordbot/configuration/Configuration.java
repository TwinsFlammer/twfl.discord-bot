package br.com.twinflammer.dicordbot.configuration;

import br.com.twinflammer.dicordbot.DiscordBot;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import lombok.Getter;
import org.apache.commons.io.Charsets;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;

/**
 * @author SrGutyerrez
 */
public class Configuration {
    private final String FILE_NAME = "configuration.json";

    @Getter
    private static JSONObject jsonObject;

    public Configuration() {
        JSONObject jsonObject1 =  null;

        File file = new File(Configuration.this.FILE_NAME);

        System.out.println(file);

        if (!file.exists()) {
            try {
                file.createNewFile();

                ByteSource byteSource = new ByteSource() {
                    @Override
                    public InputStream openStream() {
                        return DiscordBot.getInstance().getResource(Configuration.this.FILE_NAME);
                    }
                };

                String fileValues = byteSource.asCharSource(Charsets.UTF_8).read();

                Files.write(fileValues, file, Charsets.UTF_8);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        try {
            FileReader fileReader = new FileReader(file);

            jsonObject1 = (JSONObject) JSONValue.parse(fileReader);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        Configuration.jsonObject = jsonObject1;
    }
}
