package rest.aquino;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoAquino {
    public static void main(String[] args) {

        Response request = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
        System.out.println("-------------------------Requisição API Aquino--------------------");
        System.out.println(request.getBody().asString());

        ValidatableResponse response = request.then();
        response.statusCode(200);
    }
}
