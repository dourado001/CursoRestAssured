package rest.aquino;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Serializacao {

    @BeforeClass
    public static void setup(){
        baseURI = "http://restapi.wcaquino.me";
        port = 80;
    }

    @Test
    public void deveSalvarUsuarioUsandoMap(){

        Map<String,Object> params= new HashMap<String, Object>();
        params.put("name","Guilherme");
        params.put("age",30);

                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(params)
                .when()
                        .post("/users")
                .then()
                        .log().all()
                        .statusCode(201)
                        .body("id",is(notNullValue()))
                        .body("name",is("Guilherme"))
                        .body("age",is(30))
                ;
    }

    @Test
    public void deveSalvarUsuarioUsandoSerializacaoObjeto(){
        User user = new User("Michael Phelps",36);
        user.setSalary(36000);
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(user)
                .when()
                        .post("/users")
                .then()
                        .log().all()
                        .statusCode(201)
                        .body("name",is("Michael Phelps"))
                        .body("age",is(36))
                        .body("salary",is(36000))
                ;
    }

    @Test
    public void deveSalvarUsuarioUsandoDeserializacaoObjeto(){
        User user = new User("Usain Bolt",31);
            User usuarioPreenchido = given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(user)
                .when()
                    .post("/users")
                .then()
                    .log().all()
                    .statusCode(201)
                    .extract().body().as(User.class)
        ;

        Assert.assertThat(usuarioPreenchido.getName(),is("Usain Bolt"));
        Assert.assertThat(usuarioPreenchido.getAge(),is(31));
        Assert.assertThat(usuarioPreenchido.getId(),is(notNullValue()));
    }
}


