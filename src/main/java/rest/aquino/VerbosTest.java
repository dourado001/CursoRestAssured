package rest.aquino;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
                        .contentType(ContentType.JSON)
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
                        .contentType(ContentType.JSON)
                        .body("{\"age\": 69}")
                .when()
                        .post("/users")
                .then()
                        .log().all()
                        .statusCode(400)
                        .body("error",is("Name é um atributo obrigatório"))
                ;
    }

    @Test
    public void deveAlterarUsuario(){
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body("{\"name\": \"Creuza\",\"endereco\": {\"rua\": \"Rua dos troxa\",\"numero\": 69},\"age\": \"99\",\"salary\": \"10000f\"}")
                .when()
                        .put("users/2")
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("name",is("Creuza"))
                        .body("age",is("99"))
                        .body("endereco.rua",is("Rua dos troxa"))
                        .body("endereco.numero",is(69))
                        .body("salary",is("10000f"))
                ;
    }

    @Test
    public void deveCustomizarURL(){
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body("{ \"name\" : \"Goku\", \"age\": 201}")
                        .pathParams("entidade","users")
                        .pathParams("id","1")
                .when()
                        .put("/{entidade}/{id}")
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("name",is("Goku"))
                        .body("age",is(201))
                ;
    }

    @Test
    public void deveRemoverUsuario(){
                given()
                        .log().all()
                        .pathParams("entidade","users")
                        .pathParams("id","3")
                .when()
                        .delete("/{entidade}/{id}")
                .then()
                        .log().all()
                        .statusCode(204)
                ;
    }

    @Test
    public void naoDeveDeletarUsuarioInexistente(){
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .pathParams("entidade","users")
                        .pathParams("id",99)
                .when()
                        .delete("/{entidade}/{id}")
                .then()
                        .log().all()
                        .statusCode(400)
                        .body("error",is("Registro inexistente"))
                ;
    }
}
