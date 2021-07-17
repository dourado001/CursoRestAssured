package rest.aquino;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class OlaMundoTestAquino {

    @Test
    public void olaMundoAquino(){
        Response request = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
        System.out.println("-------------------------Requisição API Aquino--------------------");
        System.out.println(request.getBody().asString());

        ValidatableResponse response = request.then();
        response.statusCode(200);
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
    public void deveConhecerMatchersHamcrest(){
        assertThat("Maria", is("Maria"));
        assertThat(128,isA(Integer.class));
        assertThat(128d,isA(Double.class));
        assertThat(100, greaterThan(99));
        assertThat(100, lessThan(101));

        assertThat("Luiz",not("Maria"));
        assertThat("Joaquina",anyOf(is("Joaquina"),is("Luiz")));
        assertThat("Maria Joaquina",allOf(startsWith("Maria Joaquina"),endsWith("ina"),containsString("a J")));
    }

    @Test
    public void validacoesHamcrestLista(){

        List<Integer> impares = Arrays.asList(1,3,5,7,9);
        assertThat(impares, hasSize(5));
        //tem que ter todos os elementos da lista, sem repetição, na mesma ordem
        assertThat(impares, contains(1,3,5,7,9));
        //tem que ter TODOS os elementos da lista, sem repetição
        assertThat(impares, containsInAnyOrder(3,5,1,7,9));
        //Verifica se tem o item mencionado
        assertThat(impares,hasItem(1));
        //Verifica se tem so itens mencionados
        assertThat(impares,hasItems(1,5));
    }

    @Test
    public void deveValidarBody(){
        given()
                //Pré condições
                .when()
                //Ações
                .get("http://restapi.wcaquino.me/ola")
                .then()
                //Validações
                .statusCode(200)
                .body(is("Ola Mundo!"))
                .body(containsString("Mundo"))
                .body(is(not(nullValue())));
    }
}
