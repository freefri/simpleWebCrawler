package es.freefri.crawler;


import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;


public class SpiderTest {

    @Test
    public void httpRequest() throws Exception {
        String targetUrl = "http://54.72.118.190/pingtest.html";
        String response = new Spider().getWeb(new URL(targetUrl));
        String expected = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Ping Test 4 Load Balancer</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    Load Balancer Health Test\n" +
                "</body>\n" +
                "</html>";
        assertEquals(expected, response);
    }

    @Test(expected = IOException.class)
    public void httpRequestThrowsIOExceptionWith400Response() throws Exception {
        String targetUrl = "https://graph.facebook.com/oauth/authorize?client_id=144245675601028";
        new Spider().getWeb(new URL(targetUrl));
    }

    @Test(expected = IOException.class)
    public void httpRequestThrowIOExceptionWithMalformedURL() throws Exception {
        new Spider().getWeb(new URL("hfgp://xx"));
    }
}