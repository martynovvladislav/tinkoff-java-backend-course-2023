package edu.hw6;

import edu.hw6.task5.HackerNews;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static org.mockito.ArgumentMatchers.any;

public class TestsForTask5 {
    @Test
    @DisplayName("hacker news top stories parse test")
    public void topStoriesTest() throws IOException, InterruptedException {
        HttpResponse<String> response = Mockito.mock(HttpResponse.class);
        HttpClient client = Mockito.mock(HttpClient.class);
        String expectedBody = "[12345,54321,23456]";
        Mockito.when(response.body()).thenReturn(expectedBody);
        Mockito.when(client.send(any(), any(HttpResponse.BodyHandlers.ofString().getClass()))).thenReturn(response);
        HackerNews hackerNews = new HackerNews(client, response);
        Assertions.assertArrayEquals(hackerNews.hackerNewsTopStories(), new long[] {12345, 54321, 23456});
    }

    @Test
    @DisplayName("get news title test")
    void newsTitleTest() {
        Assertions.assertEquals(new HackerNews(HttpClient.newBuilder().build()).news(37570037), "JDK 21 Release Notes");
    }
}
