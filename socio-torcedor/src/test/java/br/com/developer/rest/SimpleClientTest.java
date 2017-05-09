package br.com.developer.rest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.junit.Ignore;
import org.junit.Test;

import br.com.developer.model.Campanha;
import junit.framework.Assert;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 *          https://github.com/resteasy/resteasy-examples/blob/master/jaxrs-2.0/simple-client/src/test/java/org/jboss/test/SimpleClientTest.java
 */
public class SimpleClientTest {
    
    @Test
    public void teetete() {
        try {
            final Client client = ClientBuilder.newBuilder().build();
            final List<Campanha> list = client.target("http://localhost:8082/campanha/rest/campanhas").request().accept(
                            MediaType.APPLICATION_JSON).get(new GenericType<List<Campanha>>() {});
            for (final Campanha campanha : list) {
                System.out.println(campanha);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Ignore
    public void testCampanha() throws Exception {
        // fill out a query param and execute a get request
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target("http://localhost:9095/customers");
        try {
            // extract customer directly expecting success
            final Campanha cust = target.queryParam("name", "Bill").request().get(Campanha.class);
            Assert.assertEquals("Bill", cust.getNome());
        } finally {
            client.close();
        }
    }

    @Ignore
    public void testAsyncCallback() throws Exception {
        // fill out a query param and execute a get request
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target("http://localhost:9095/customers");
        try {
            // execute in background
            final CountDownLatch latch = new CountDownLatch(1);
            target.queryParam("name", "Bill").request().async().get(new InvocationCallback<Campanha>() {
                @Override
                public void completed(Campanha customer) {
                    System.out.println("Obtained customer: " + customer.getNome());
                    latch.countDown();
                }

                @Override
                public void failed(Throwable error) {
                    latch.countDown();
                }
            });
            // await for callback to wake us up
            latch.await(10, TimeUnit.SECONDS);
        } finally {
            client.close();
        }
    }
}
