package rest.aquino;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class AuthTestAPIAquino {

    @Test
    public void naoDeveValidarAutenticacaoBasica(){
                given()
                        .log().all()
                .when()
                        .get("http://restapi.wcaquino.me/basicAuth")
                .then()
                        .log().all()
                        .statusCode(401)
                ;
    }

    @Test
    public void deveValidarAutenticacaoBasica(){
                given()
                        .log().all()
                .when()
                        .get("http://admin:senha@restapi.wcaquino.me/basicAuth")
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("status",is("logado"))
                ;
    }

    @Test
    public void deveValidarAutenticacaoBasica2(){
                given()
                        .log().all()
                        .auth().basic("admin","senha")
                .when()
                        .get("http://restapi.wcaquino.me/basicAuth")
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("status",is("logado"))
                ;
    }

    @Test
    public void deveValidarAutenticacaoBasicaChallenge(){
                given()
                        .log().all()
                        .auth().preemptive().basic("admin","senha")
                .when()
                        .get("http://restapi.wcaquino.me/basicAuth2")
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("status",is("logado"))
                ;
    }

}
