package com.johnmanko.service.data;

import com.johnmanko.services.data.services.RedisService;
import com.johnmanko.services.data.services.RedisException;
import com.johnmanko.services.data.services.DataRecord;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import static org.mockito.Mockito.when;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Data REST Resource")
public class DataEndpointResourceTest {

    @InjectMock
    RedisService redisServiceMock;

    Map<String, DataRecord> testDate;

    @BeforeEach
    public void setUp() throws RedisException {

        testDate = new HashMap<>();

        DataRecord r = new DataRecord("1234", "data1");
        testDate.put(r.getKey(), r);
        r = new DataRecord("asdf", "data2");
        testDate.put(r.getKey(), r);

    }

    @Test
    @Order(1)
    @DisplayName("Endpoint: GET /data")
    public void getData() throws RedisException {        

        when(redisServiceMock.getAllValues()).thenReturn(new ArrayList<>(testDate.values()));

        List<DataRecord> list = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/v1/data")
                .then()
                .statusCode(200)
                .extract().body().as(new TypeRef<>() {
                });

        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());

    }

}
