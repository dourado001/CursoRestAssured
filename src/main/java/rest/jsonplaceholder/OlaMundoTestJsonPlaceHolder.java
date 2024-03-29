package rest.jsonplaceholder;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class OlaMundoTestJsonPlaceHolder {

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
    public void deveTrazerTodosComentarios(){
                given()
                .when()
                        .get("https://jsonplaceholder.typicode.com/comments")
                .then()
                        .statusCode(200);
    }
}
