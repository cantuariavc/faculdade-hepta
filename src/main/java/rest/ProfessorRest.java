package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dto.DisciplinaDTO;
import dto.ProfessorDTO;
import service.ProfessorService;

@Path("/professores")
public class ProfessorRest {

    private ProfessorService professorService;

    public ProfessorRest() {
        professorService = new ProfessorService();
    }
    
    
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response listar() {
        List<ProfessorDTO> listaProfessorDTO = new ArrayList<>();

        try {
            listaProfessorDTO = professorService.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro_ao_buscar_professores").build();
        }

        GenericEntity<List<ProfessorDTO>> entity = new GenericEntity<List<ProfessorDTO>>(listaProfessorDTO) {
        };

        return Response.status(Status.OK).entity(entity).build();
    }

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response salvar(ProfessorDTO professorDTO) {
        try {
            professorDTO = professorService.salvar(professorDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro_ao_criar_professor").build();
        }

        return Response.status(Status.OK).entity(professorDTO).build();
    }

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public Response atualizar(ProfessorDTO professorDTO) {
        try {
            professorService.atualizar(professorDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro_ao_atualizar_professor").build();
        }

        return Response.status(Status.OK).build();
    }
    
    @Path("/{idProfessor}")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    public Response deletar(@PathParam("idProfessor") Integer idProfessor) {
        try {
            professorService.deletar(idProfessor);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro_ao_deletar_professor").build();
        }

        return Response.status(Status.OK).build();
    }
    
    @Path("/{idProfessor}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response detalhar(@PathParam("idProfessor") Integer idProfessor) {
        ProfessorDTO professorDTO;
        
        try {
            professorDTO = professorService.detalhar(idProfessor);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro_ao_detalhar_professor").build();
        }

        return Response.status(Status.OK).entity(professorDTO).build();
    }
}
