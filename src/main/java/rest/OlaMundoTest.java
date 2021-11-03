package rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class OlaMundoTest {

    @Test
    public void olaMundoAquino(){
        Response request = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
        System.out.println("-------------------------Requisição API Aquino--------------------");
        System.out.println(request.getBody().asString());

        ValidatableResponse response = request.then();
        response.statusCode(200);
    }

    @Test
    public void olaMundoJsonPlaceHolder(){
        System.out.println("-------------------------Requisição Json PlaceHolder--------------------");
        Response user = RestAssured.request(Method.GET, "https://jsonplaceholder.typicode.com/users/3");
        System.out.println(user.getBody().asString());
        Assert.assertEquals(200,user.statusCode());

//        ValidatableResponse responseUser = user.then();
//        responseUser.statusCode(201);
    }

    @Test
    public void novaFormaValidarRestAssured(){
        given()
                //Pré condições
                .when()
                //Ações
                .get("https://jsonplaceholder.typicode.com/posts/5")
                .then()
                //Validações
                .statusCode(200);
    }

    @Test
    public void deveCadastrarPost(){
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body("  {\n" +
                                "    \"userId\": 1,\n" +
                                "    \"id\": 1,\n" +
                                "    \"title\": \"TOP\",\n" +
                                "    \"body\": \"Testando\"\n" +
                                "  }")
                .when()
                        .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                        .log().all()
                        .statusCode(201)
                        .body("title", is("TOP"))
                ;
    }


}
