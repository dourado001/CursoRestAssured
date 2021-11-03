package rest.aquino;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APISeuBarriga {

    @Test
    public void naoDeveAcessarSemToken(){
                given()
                        .log().all()
                .when()
                        .get("https://barrigarest.wcaquino.me/contas")
                .then()
                        .log().all()
                        .statusCode(401)
                ;
    }

    //Cenario 2 - Deve incluir uma conta com sucesso
    //post/signin email, senha
    //post/contas
    @Test
    public void deveIncluirConta() {
        HashMap<String, String> login = new HashMap<String, String>();
        login.put("email", "guilherme.dourado95@gmail.com");
        login.put("senha", "123456");

                String token = given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(login)
                .when()
                    .post("https://barrigarest.wcaquino.me/signin")
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().path("token")
        ;

                given()
                        .log().all()
                        .header("Authorization","JWT "+ token)
                        .contentType(ContentType.JSON)
                        .body("{\"nome\" : \"Conta automação 2\"}")
                .when()
                    .post("https://barrigarest.wcaquino.me/contas")
                .then()
                    .log().all()
                    .statusCode(201)
        ;
    }









    //Cenario 3 - Deve alterar uma conta com sucesso
    //PUT/contas/:id

    //Cenario 4 - Não deve incluir conta com nome repetido
    //POST/contas/

    //Cenario 5 - Deve inserir movimetação com sucesso
    //POST/transações
    //conta_id, descricao(descrição basica da transacao), envolvido(Nome da pessoa),
    // tipo(DESP / REC), data_transacao(dd/MM/YYYY), data_pagamento(dd/MM/YYYY),
    // valor(0.00f), status(true/false)

    //Cenario 6 - Deve validar campos obrigatorios na movimentação
    //POST/transações

    //Cenario 7 - Não deve cadastrar movimentação futura
    //POST/transações data futura a data atual

    //Cenario 8 - Não deve remover conta com movimentação
    //DELETE/contas/:id

    //Cenario 9 - Deve calcular saldo contas
    //GET/saldo

    //Cenario 10 - Deve remover movimentação
    //DELETE/transações/:id
}
