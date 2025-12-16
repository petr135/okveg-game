import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class OkvedFactory {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private OkvedFactory() {}

    public static List<OkvedNode> loadTree(OkvedConfig okvedConfig) {
        if(okvedConfig == null || okvedConfig.getUrl() == null){
            System.out.println("can't get config or url not valid");
            throw new RuntimeException("can't get config or url not valid");
        }
        String url = okvedConfig.getUrl();
        int timeoutSeconds = okvedConfig.getTimeoutSeconds();

        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(timeoutSeconds))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("HTTP error: " + response.statusCode());
            }

            return MAPPER.readValue(
                    response.body(),
                    new TypeReference<List<OkvedNode>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to load OKVED", e);
        }
    }
}
