package rest.aquino;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthTestWeatherMap {
    //84421357c78f7005e3ed04f0b91b30c4

    @BeforeClass
    public static void setup(){
        baseURI = "http://api.openweathermap.org";
        basePath= "/data/2.5";
    }

    ///weather?q={city name}&appid={API key}

    @Test
    public void deveTrazerClimaAtualPorCidade(){
                given()
                        .log().all()
                        .queryParam("q","Jandira,BR")
                        .queryParam("appid","84421357c78f7005e3ed04f0b91b30c4")
                        .queryParam("units","metric")
                .when()
                        .get("/weather")
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("name", is("Jandira"))
                ;
    }
}
