package edu.project3.parsers;

import edu.project3.logHandlers.LogRecord;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class HttpParser {

    public List<LogRecord> parseHttp(String path) {
        List<LogRecord> logRecords = new ArrayList<>();
        List<String> logsString = sendRequest(path).lines().toList();
        for (String line : logsString) {
            logRecords.add(LogParser.parseLog(line));
        }
        return logRecords;
    }

    private static String sendRequest(String url) {
        try (HttpClient httpClient = HttpClient.newBuilder().build()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
