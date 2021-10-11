package rest.aquino;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class FormatoComunicacao {

    @BeforeClass
    public static void setup(){
        baseURI = "http://restapi.wcaquino.me";
        port = 80;
    }

    @Test
    public void deveTestarQueryParametrizavel() {
                given()
                        .log().all()
                        .queryParam("format","xml")
                .when()
                        .get("v2/users")
                .then()
                        .log().all()
                        .statusCode(200)
                        .contentType(ContentType.XML)
                        .contentType(Matchers.containsString("utf-8"))
                ;
    }

    @Test
    public void deveTestarHeader(){
                given()
                        .log().all()
                        .accept(ContentType.XML)
                .when()
                        .get("/users")
                .then()
                        .log().all()
                        .statusCode(200)
                        .contentType(ContentType.XML)
                ;
    }
}
