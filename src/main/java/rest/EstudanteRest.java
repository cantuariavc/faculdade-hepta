package rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dto.EstudanteDTO;
import service.EstudanteService;

@Path("/estudantes")
public class EstudanteRest {

    private EstudanteService estudanteService;

    public EstudanteRest() {
        estudanteService = new EstudanteService();
    }

//    @Path("/")
//    @Produces(MediaType.TEXT_PLAIN)
//    @GET
//    public String helloWorld() {
//        return "Hello World";
//    }

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response listar() {
        List<EstudanteDTO> listaEstudanteDTO = new ArrayList<>();

        try {
            listaEstudanteDTO = estudanteService.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro_ao_buscar_estudantes").build();
        }

        GenericEntity<List<EstudanteDTO>> entity = new GenericEntity<List<EstudanteDTO>>(listaEstudanteDTO) {
        };

        return Response.status(Status.OK).entity(entity).build();
    }

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response salvar(EstudanteDTO estudanteDTO) {
        try {
            estudanteDTO = estudanteService.salvar(estudanteDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro_ao_criar_estudante").build();
        }

        return Response.status(Status.OK).entity(estudanteDTO).build();
    }

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public Response atualizar(EstudanteDTO estudanteDTO) {
        try {
            estudanteService.atualizar(estudanteDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro_ao_atualizar_estudante").build();
        }

        return Response.status(Status.OK).build();
    }

}
