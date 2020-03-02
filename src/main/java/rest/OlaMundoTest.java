package rest;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

public class OlaMundoTest {
    @Test
    public void testeInicial(){
        Response request = RestAssured.request(Method.GET,"http://restapi.wcaquino.me/ola");
        System.out.println(request.getBody().asString());

        ValidatableResponse validatableResponse = request.then();
    }

}
