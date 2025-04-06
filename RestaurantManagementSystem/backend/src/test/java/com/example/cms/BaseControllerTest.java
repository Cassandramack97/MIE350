// src/test/java/com/example/cms/BaseControllerTest.java
package com.example.cms;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.UUID;

public abstract class BaseControllerTest {

    @DynamicPropertySource
    static void overrideDatasourceUrl(DynamicPropertyRegistry registry) {
        String url = "jdbc:h2:mem:testdb-" + UUID.randomUUID()
                + ";DB_CLOSE_DELAY=0;DB_CLOSE_ON_EXIT=FALSE";
        registry.add("spring.datasource.url", () -> url);
    }
}