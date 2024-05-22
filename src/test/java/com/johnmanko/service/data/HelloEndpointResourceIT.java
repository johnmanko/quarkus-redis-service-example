package com.johnmanko.service.data;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusIntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("IT: Hello REST Resource")
public class HelloEndpointResourceIT extends HelloEndpointResourceTest {

}
