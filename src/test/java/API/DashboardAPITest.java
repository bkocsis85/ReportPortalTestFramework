package test.java.API;

import io.restassured.RestAssured;
import main.java.utils.Constants;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DashboardAPITest {

    @Test
    public void testGetDashboard() throws IOException {

        String project = Constants.project;
        String URL = Constants.baseAPIUrl + project + "/dashboard";

        HttpUriRequest request = new HttpGet(URL);
        request.setHeader("Authorization", Constants.APIToken);

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "Response code is not 200");
    }

    @Test
    public void verifyDashboardName() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard";

        given().header("Authorization", Constants.APIToken).when().get().then().
                body("content[0].name", equalTo("DEMO DASHBOARD"));
    }

    @Test
    public void verifyDashboardIDNegative() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard";

        given().header("Authorization", Constants.APIToken).when().get().then().
                body("content[0].id", not(13));
    }

    @Test
    public void createDashboard() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard";

        Map<String, String> dashboard = new HashMap<>();
        dashboard.put("description", "Created by POST request");
        dashboard.put("name", "Dashboard PUT");

        given().header("Authorization", Constants.APIToken).contentType("application/json").body(dashboard).
                when().post().then().statusCode(201);
    }

    @Test
    public void createDuplicatedDashboard() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard";

        Map<String, String> dashboard = new HashMap<>();
        dashboard.put("description", "Created by POST request");
        dashboard.put("name", "Dashboard PUT");

        given().header("Authorization", Constants.APIToken).contentType("application/json").body(dashboard).
                when().post().then().body("errorCode", equalTo(4091));
    }

    @Test
    public void createDashboardWithWrongProject() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL_WRONG/dashboard";

        Map<String, String> dashboard = new HashMap<>();
        dashboard.put("description", "Created by POST request");
        dashboard.put("name", "Dashboard PUT");

        given().header("Authorization", Constants.APIToken).contentType("application/json").body(dashboard).
                when().post().then().
                body("message", equalTo("Project 'SUPERADMIN_PERSONAL_WRONG' not found. Did you use correct project name?"));
    }

    @Test
    public void deleteDashboard() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard";

        Map<String, String> dashboard = new HashMap<>();
        dashboard.put("description", "");
        dashboard.put("name", "Dashboard for Delete");

        int id = given().header("Authorization", Constants.APIToken).contentType("application/json").
                body(dashboard).when().post().then().extract().path("id");

        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard/" + id;

        given().header("Authorization", Constants.APIToken).when().delete().then().statusCode(200);

    }

    @Test
    public void deleteWrongDashboard() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard/365";

        given().header("Authorization", Constants.APIToken).when().delete().then().
                body("errorCode", equalTo(40422));

    }

    @Test
    public void updateDashboard() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard";

        Map<String, String> dashboard = new HashMap<>();
        dashboard.put("description", "");
        dashboard.put("name", "Dashboard for Update");

        int id = given().header("Authorization", Constants.APIToken).contentType("application/json").
                body(dashboard).when().post().then().extract().path("id");

        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard/" + id + "/add";
        String body = "{\n" +
                "  \"addWidget\": {\n" +
                "    \"widgetId\": 12,\n" +
                "    \"widgetName\": \"string\",\n" +
                "    \"widgetOptions\": {},\n" +
                "    \"widgetPosition\": {\n" +
                "      \"positionX\": 0,\n" +
                "      \"positionY\": 0\n" +
                "    },\n" +
                "    \"widgetSize\": {\n" +
                "      \"height\": 0,\n" +
                "      \"width\": 0\n" +
                "    },\n" +
                "    \"widgetType\": \"string\"\n" +
                "  }\n" +
                "}";

        given().header("Authorization", Constants.APIToken).contentType("application/json").body(body).
                when().put().then().body(containsString("successfully added"));

        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard/" + id;

        given().header("Authorization", Constants.APIToken).when().delete().then().statusCode(200);

    }

    @Test
    public void updateDashboardWithWrongWidgetID() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard";

        Map<String, String> dashboard = new HashMap<>();
        dashboard.put("description", "");
        dashboard.put("name", "Dashboard for Update");

        int id = given().header("Authorization", Constants.APIToken).contentType("application/json").
                body(dashboard).when().post().then().extract().path("id");

        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard/" + id + "/add";
        String body = "{\n" +
                "  \"addWidget\": {\n" +
                "    \"widgetId\": 0,\n" +
                "    \"widgetName\": \"string\",\n" +
                "    \"widgetOptions\": {},\n" +
                "    \"widgetPosition\": {\n" +
                "      \"positionX\": 0,\n" +
                "      \"positionY\": 0\n" +
                "    },\n" +
                "    \"widgetSize\": {\n" +
                "      \"height\": 0,\n" +
                "      \"width\": 0\n" +
                "    },\n" +
                "    \"widgetType\": \"string\"\n" +
                "  }\n" +
                "}";

        given().header("Authorization", Constants.APIToken).contentType("application/json").body(body).
                when().put().then().body("errorCode", equalTo(40420));

        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard/" + id;

        given().header("Authorization", Constants.APIToken).when().delete().then().statusCode(200);

    }

    @Test
    public void updateDashboardWithWrongDahboardID() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1/SUPERADMIN_PERSONAL/dashboard/3000/add";

        String body = "{\n" +
                "  \"addWidget\": {\n" +
                "    \"widgetId\": 13,\n" +
                "    \"widgetName\": \"string\",\n" +
                "    \"widgetOptions\": {},\n" +
                "    \"widgetPosition\": {\n" +
                "      \"positionX\": 0,\n" +
                "      \"positionY\": 0\n" +
                "    },\n" +
                "    \"widgetSize\": {\n" +
                "      \"height\": 0,\n" +
                "      \"width\": 0\n" +
                "    },\n" +
                "    \"widgetType\": \"string\"\n" +
                "  }\n" +
                "}";

        given().header("Authorization", Constants.APIToken).contentType("application/json").body(body).
                when().put().then().body("errorCode", equalTo(40422));
    }
}
