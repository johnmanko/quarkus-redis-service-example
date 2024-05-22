package com.johnmanko.service.data;

import com.johnmanko.services.data.services.DataRecord;
import com.johnmanko.services.data.services.RedisException;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import static io.restassured.RestAssured.given;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@QuarkusIntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("IT: Data REST Resource")
public class DataEndpointResourceIT {

    @Test
    @Order(1)
    @DisplayName("Endpoint: POST /data")
    public void postLock() throws RedisException {

        DataRecord data = new DataRecord("asdf", "this is data");

        DataRecord dataReturned = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .body(data)
                .post("/api/v1/data")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().body().as(new TypeRef<>() {
                });

        Assertions.assertNotNull(dataReturned);

    }

}
