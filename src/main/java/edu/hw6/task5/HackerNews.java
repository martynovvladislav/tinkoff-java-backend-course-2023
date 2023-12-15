package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private static final String TOP_STORIES_ENDPOINT = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String MESSAGE_ENDPOINT = "https://hacker-news.firebaseio.com/v0/item/";
    private static final Pattern TITLE_PATTERN = Pattern.compile("\"title\":\"([^\"]+)\"");
    private HttpClient client;
    private HttpResponse<String> response;

    public HackerNews(HttpClient client) {
        this.client = client;
    }

    public HackerNews(HttpClient client, HttpResponse<String> response) {
        this.client = client;
        this.response = response;
    }

    public long[] hackerNewsTopStories() {
        try {
            String answer = getResponse(TOP_STORIES_ENDPOINT);
            String[] stringIds = answer.substring(1, answer.length() - 1).split(",");
            long[] ids = new long[stringIds.length];
            for (int i = 0; i < ids.length; i++) {
                ids[i] = Long.parseLong(stringIds[i]);
            }
            return ids;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private String getResponse(String uri) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET().build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String news(long id) {
        try {
            String answer = getResponse(MESSAGE_ENDPOINT + Long.toString(id) + ".json");
            Matcher matcher = TITLE_PATTERN.matcher(answer);
            matcher.find();
            return matcher.group(1);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
