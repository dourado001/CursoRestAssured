package rest.aquino;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthJWT {

    @Test
    public void deveTrazerContas(){
        HashMap<String, String> login = new HashMap<String, String>();
        login.put("email","guilherme.dourado95@gmail.com");
        login.put("senha","123456");

                String token = given()
                        .log().all()
                        .body(login)
                        .contentType(ContentType.JSON)
                .when()
                        .post("https://barrigarest.wcaquino.me/signin")
                .then()
                        .log().all()
                        .statusCode(200)
                        .extract().path("token")
                ;

                        given()
                                .log().all()
                                .header("Authorization","JWT "+ token)
                        .when()
                                .get("https://barrigarest.wcaquino.me/contas")
                        .then()
                                .log().all()
                                .statusCode(200)
                                .body("nome", hasItem("Teste"))
                        ;
    }
}
