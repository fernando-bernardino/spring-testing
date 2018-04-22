package org.grumpy.blog.justme;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.grumpy.blog.notme.Dependency;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;
import static org.mockito.Mockito.when;

public class JaxRsControllerITTest {

    private int port = 8282;
    private String baseUrl = "http://localhost";
    private HttpServer server;
    private Dependency dependency;


    @Test
    public void testJaxRsStartingServer() throws Exception {
        String id = "id";
        String description = "description";
        when(dependency.getItemDescription(id)).thenReturn(description);

        HttpResponse response = executeRequest("/item/" + id);

        assertThat(response.getStatusLine().getStatusCode(), is(200));
        assertThat(EntityUtils.toString(response.getEntity()), containsString(description));
    }

    @Before
    public void setup() throws IOException {
        JaxRsApplication jaxRsApplication = new JaxRsApplication();

        server = startServer(jaxRsApplication);

        dependency = jaxRsApplication.springContext().getBean(Dependency.class);
    }

    @After
    public void stopServer() {
        if(server != null) {
            server.stop(0);
        }
    }

    private HttpServer startServer(JaxRsApplication jaxRsApplication) throws IOException {
        URI uri = UriBuilder.fromUri(baseUrl).port(port).build();

        HttpServer server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(jaxRsApplication, HttpHandler.class);
        server.createContext(uri.getPath(), handler);

        server.start();

        return server;
    }

    private HttpResponse executeRequest(String id) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(baseUrl + ":" + port + id);

        return httpclient.execute(httpGet);
    }
}