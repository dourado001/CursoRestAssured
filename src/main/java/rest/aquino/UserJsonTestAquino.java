package rest.aquino;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserJsonTestAquino {

    public static RequestSpecification reqSpec;
    public static ResponseSpecification resSpec;

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "http://restapi.wcaquino.me";
        RestAssured.port = 80;
        //RestAssured.basePath = "/v2";

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.log(LogDetail.ALL);
        RestAssured.requestSpecification = reqBuilder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectStatusCode(200);
        RestAssured.responseSpecification = resBuilder.build();

//        RestAssured.responseSpecification = resSpec;
//        RestAssured.requestSpecification = reqSpec;
    }

    @Test
    public void deveTrazerJsonPrimeiroNivel(){
                given()
                .when()
                        .get("/users/3")
                .then()
                        .body("id", is(3))
                        .body("name",is("Ana Júlia"))
                        .body("age",is(20))
                ;
    }

    @Test
    public void deveTrazerJsonSegundoNivel(){

                given()
                .when()
                        .get("/users/2")
                .then()
                        .body("endereco.numero",is(0))
                ;
    }
    @Test
    public void deveTrazerJsonLista(){

        given()
                .when()
                    .get("/users/3")
                .then()
                    .body("filhos",hasSize(2))
                    .body("filhos[0].name",is("Zezinho"))
                    .body("filhos[1].name",is("Luizinho"))
                    .body("filhos.name",hasItem("Luizinho"))
                    .body("filhos.name",hasItems("Zezinho","Luizinho"))
        ;
    }
}

