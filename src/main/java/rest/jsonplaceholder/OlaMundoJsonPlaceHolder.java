package rest.jsonplaceholder;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoJsonPlaceHolder {
    public static void main(String[] args) {

        System.out.println("-------------------------Requisição Json PlaceHolder--------------------");
        Response user = RestAssured.request(Method.GET, "https://jsonplaceholder.typicode.com/users/3");
        System.out.println(user.getBody().asString());

        ValidatableResponse responseUser = user.then();
        responseUser.statusCode(201);
    }
}
