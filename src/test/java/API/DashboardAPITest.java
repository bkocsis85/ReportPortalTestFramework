package test.java.API;

import main.java.utils.Constants;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.java.BaseTest;

import java.io.IOException;

public class DashboardAPITest extends BaseTest {

    @Test
    public void testGetDashboard() throws IOException {

        String project = Constants.project;
        String URL = Constants.baseAPIUrl + project + "/dashboard";

        HttpUriRequest request = new HttpGet(URL);
        request.setHeader("Authorization", Constants.APIToken);

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "Response code is not 200");
    }
}
