package ru.hostco.pp86.helpers;

import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import ru.hostco.pp86.config.CredentialsConfig;

import static io.restassured.RestAssured.given;

public class Tests {

    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    public static String getAuthCookie() {
        Response r = given()
                .auth().preemptive().basic(config.getLogin(), config.getPassword())
                .log().all()
                .when()
                .post("https://cas-test.hostco.ru/realms/esia/login-actions/authenticate")
                .then()
                .log().all()
                .extract().response();
        return "s";
    }


}
