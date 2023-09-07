package Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NewsApiReader {
    private static HttpResponse<String> response;
    private URI uri;

    private static HttpRequest getRequest(URI newUri) {
        return HttpRequest.newBuilder()
                .uri(newUri)
                .build();
    }

    private static HttpClient getClient() {
        return HttpClient.newBuilder().build();
    }

    public static HttpResponse<String> getResponse(URI uri) {
        var client = getClient();
        var request = getRequest(uri);
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }


}