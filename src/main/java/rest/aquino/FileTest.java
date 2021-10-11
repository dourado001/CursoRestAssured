package rest.aquino;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FileTest {

    @BeforeClass
    public static void setup() {
        baseURI = "http://restapi.wcaquino.me";
    }

    @Test
    public void deveObrigarEnvioArquivo() {

        given()
                .log().all()
                .when()
                .post("/upload")
                .then()
                .log().all()
                .statusCode(404)
                .body("error", is("Arquivo não enviado"))
        ;
    }

    @Test
    public void deveFazerUploadArquivo() {

        given()
                .log().all()
                .multiPart("arquivo",new File("src/main/resources/lista_exercicios_POO.pdf"))
                .when()
                .post("/upload")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(4000l))
                .body("name",is("lista_exercicios_POO.pdf"))
        ;
    }
}
