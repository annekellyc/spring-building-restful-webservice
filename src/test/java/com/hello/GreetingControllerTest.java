package com.hello;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.hello.Resource.getResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GreetingControllerTest {

    @BeforeClass
    public static void setup() throws Exception {
        SpringApplication.run(Application.class, new String[]{});
    }

    @Test
    public void respondsWith200() throws IOException {
        HttpResponse response = getResponse("");

        assertThat(response.getStatusLine().getStatusCode(), is(200));
    }

    @Test
    public void getGreetingWithDefaultName() throws IOException {
        HttpResponse response = getResponse("");
        JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));

        assertThat("Hello, World!", is(jsonObject.get("content")));
    }

    @Test
    public void getGreetingWithNameEqualsBlah() throws IOException {
        HttpResponse response = getResponse("Blah");
        JSONObject jsonObject =  new JSONObject(EntityUtils.toString(response.getEntity()));

        assertThat("Hello, Blah!", is(jsonObject.get("content")));
    }

    @Test
    public void buildGreeting() {
        GreetingController greetingController = new GreetingController();
        Greeting greeting = greetingController.greeting("Blah");

        assertThat("Hello, Blah!", is(greeting.getContent()));
    }
}

class Resource {

    public static final String RESOURCE_URL = "http://localhost:8080/greeting";
    public static final String PARAM = "?name=";

    static HttpResponse getResponse(String name) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(name.isEmpty() ? RESOURCE_URL : RESOURCE_URL.concat(PARAM).concat(name));
        get.setHeader("Accept", "application/json");

        return httpClient.execute(get);
    }
}
