package rest.aquino;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserJsonTestAquino {

    @Test
    public void deveTrazerJsonPrimeiroNivel(){
                given()
                .when()
                        .get("http://restapi.wcaquino.me/users/3")
                .then()
                        .statusCode(200)
                        .body("id", is(3))
                        .body("name",is("Ana Júlia"))
                        .body("age",is(20))
                ;
    }

    @Test
    public void deveTrazerJsonSegundoNivel(){

                given()
                .when()
                        .get("http://restapi.wcaquino.me/users/2")
                .then()
                        .statusCode(200)
                        .body("endereco.numero",is(0))
                ;
    }
    @Test
    public void deveTrazerJsonLista(){

        given()
                .when()
                    .get("http://restapi.wcaquino.me/users/3")
                .then()
                .statusCode(200)
                    .body("filhos",hasSize(2))
                    .body("filhos[0].name",is("Zezinho"))
                    .body("filhos[1].name",is("Luizinho"))
                    .body("filhos.name",hasItem("Luizinho"))
                    .body("filhos.name",hasItems("Zezinho","Luizinho"))
        ;
    }
}

