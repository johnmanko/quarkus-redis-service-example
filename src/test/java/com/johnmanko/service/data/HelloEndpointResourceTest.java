package com.johnmanko.service.data;

import com.johnmanko.services.data.rest.HellowWorldResource;
import com.johnmanko.services.data.services.RedisException;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Hello REST Resource")
public class HelloEndpointResourceTest {
    
    @Test
    @Order(1)
    @DisplayName("Endpoint: GET /hello")
    public void getData() throws RedisException {

        String world = given()
                .accept(ContentType.TEXT)
                .when()
                .get("/api/v1/hello")
                .then()
                .statusCode(200)
                .extract().body().asString();

        Assertions.assertNotNull(world);
        Assertions.assertEquals(HellowWorldResource.WORLD, world);

    }
    
}
