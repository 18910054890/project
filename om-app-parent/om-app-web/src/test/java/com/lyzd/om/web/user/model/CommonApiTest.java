package com.lyzd.om.web.user.model;

import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author Thinker
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class CommonApiTest {

    @Autowired

    protected WebApplicationContext context;

    @BeforeEach
    public void init() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }


    @AfterEach
    public void reset() {
        RestAssuredMockMvc.reset();
    }


    protected MockMvcRequestSpecification given() {
        return RestAssuredMockMvc.given()
                .config(RestAssuredMockMvcConfig.config().encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .config(RestAssuredMockMvcConfig.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()));
    }
}
