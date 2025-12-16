import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.InputStream;

public class ConfigFactory {
    private static final ObjectMapper MAPPER =
            new ObjectMapper(new YAMLFactory());

    private ConfigFactory() {}

    public static AppConfig load(String configPath) {

        try (InputStream in = ConfigFactory.class
                .getClassLoader()
                .getResourceAsStream(configPath)) {

            if (in == null) {
                throw new RuntimeException("application.yml not found");
            }

            return MAPPER.readValue(in, AppConfig.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }
}
