package rest.aquino;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class VerbosTest {
    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "http://restapi.wcaquino.me";
        RestAssured.port = 80;
    }

    @Test
    public void deveSalvarUsuarios(){
                given()
                        .log().all()
                        .contentType("application/json")
                        .body("{ \"name\" : \"Guilherme\", \"age\": 26}")
                .when()
                        .post("/users")
                .then()
                        .log().all()
                        .statusCode(201)
                        .body("id",is(notNullValue()))
                        .body("name", is("Guilherme"))
                        .body("age", is(26))
                ;
    }
























    @Test
    public void naoDeveSalvarUsuario(){
                given()
                        .log().all()
                        .contentType("application/json")
                        .body("{\"age\": 69}")
                .when()
                        .post("/users")
                .then()
                        .log().all()
                        .statusCode(400)
                        .body("error",is("Name é um atributo obrigatório"))
                ;
    }
}

