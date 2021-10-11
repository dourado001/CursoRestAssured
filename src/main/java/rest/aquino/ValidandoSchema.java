package rest.aquino;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXParseException;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;

public class ValidandoSchema {
    @BeforeClass
    public static void setup() {
        baseURI = "http://restapi.wcaquino.me";
    }

    @Test
    public void deveValidarSquemaXLM(){
                given()
                        .log().all()
                .when()
                        .get("usersXML")
                .then()
                        .statusCode(200)
                        .body(RestAssuredMatchers.matchesXsdInClasspath("users.xsd"))
                ;
    }

    @Test(expected = SAXParseException.class)
    public void naoDeveValidarSquemaXLM(){
        given()
                .log().all()
                .when()
                .get("invalidusersXML")
                .then()
                .statusCode(200)
                .body(RestAssuredMatchers.matchesXsdInClasspath("users.xsd"))
        ;
    }

    @Test
    public void deveValidarSquemaJson(){
        given()
                .log().all()
                .when()
                .get("users")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("users.json"))
        ;
    }
}
