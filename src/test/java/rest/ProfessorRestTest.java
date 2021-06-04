package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dto.ProfessorDTO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfessorRestTest {

    private String urlBase = "http://localhost:8080/Faculdade-Hepta/rest/professores/";
    private static int idProfessor;
    private String nomeCompleto = "Vinícius Cantuária";
    private String professorJSON = "{\"nomeCompleto\": \""+nomeCompleto+"\" }";
    private String nomeCompletoAtualizado = "Vinícius de Castro";
    private String professorAtualizadoJSON = "{\"idProfessor\": \""+idProfessor+"\", \"nomeCompleto\": \""+nomeCompletoAtualizado+"\" }";
    
    @Order(1)
    @Test
    public void testSalvar() {
        idProfessor = 
            given()
                .contentType("application/json")
                .body(professorJSON)
            .when()
                .post(urlBase)
            .then()
                .contentType("application/json")
                .assertThat()
                .statusCode(200)
                .body(containsString(nomeCompleto))
                .extract()
                .as(ProfessorDTO.class)
                .getIdProfessor();
    }
    
    @Order(2)
    @Test
    public void testDetalhar() {
        given()
        .when()
            .get(urlBase+idProfessor)
        .then()
            .contentType("application/json")
            .assertThat()
            .statusCode(200)
            .body(containsString(String.valueOf(idProfessor)))
            .body(containsString(nomeCompleto))
            .extract()
            .as(ProfessorDTO.class);
    }
    
    @Order(3)
    @Test
    public void testAtualizar() {
        given()
            .contentType("application/json")
            .body(professorAtualizadoJSON)
        .when()
            .put(urlBase)
        .then()
            .assertThat()
            .statusCode(200);
    }
    
    @Order(4)
    @Test
    public void testListar() {
        given()
        .when()
            .get(urlBase)
        .then()
            .contentType("application/json")
            .assertThat()
            .statusCode(200)
            .body(containsString(String.valueOf(idProfessor)))
            .body(containsString(nomeCompletoAtualizado))
            .extract()
            .as(ProfessorDTO[].class);
    }
    
    @Order(5)
    @Test
    public void testExcluir() {
        given()
        .when()
            .delete(urlBase+idProfessor)
        .then()
            .assertThat()
            .statusCode(200);
    }
}
