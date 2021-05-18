package rest;

import groovy.transform.ASTTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundo {
    public static void main(String[] args) {

        Response request = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
        System.out.println("-------------------------Requisição API Aquino--------------------");
        System.out.println(request.getBody().asString());

        ValidatableResponse response = request.then();
        response.statusCode(200);

        System.out.println("-------------------------Requisição Json PlaceHolder--------------------");
        Response user = RestAssured.request(Method.GET, "https://jsonplaceholder.typicode.com/users/3");
        System.out.println(user.getBody().asString());

        ValidatableResponse responseUser = user.then();
        responseUser.statusCode(201);
    }
}
