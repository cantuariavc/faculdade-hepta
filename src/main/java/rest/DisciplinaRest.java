package rest;

import java.io.File;
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

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import dto.DisciplinaDTO;
import service.DisciplinaService;

@Path("/disciplinas")
public class DisciplinaRest {

    private DisciplinaService disciplinaService;

    public DisciplinaRest() {
        disciplinaService = new DisciplinaService();
    }
    
    
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response listar() {
        List<DisciplinaDTO> listaDisciplinaDTO = new ArrayList<>();

        try {
            listaDisciplinaDTO = disciplinaService.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro_ao_buscar_disciplinas").build();
        }

        GenericEntity<List<DisciplinaDTO>> entity = new GenericEntity<List<DisciplinaDTO>>(listaDisciplinaDTO) {
        };

        return Response.status(Status.OK).entity(entity).build();
    }

    @Path("/")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response salvar(@FormDataParam("disciplina") FormDataBodyPart disciplinaForm, @FormDataParam("arquivo") File arquivo) {
        DisciplinaDTO disciplinaDTO;
        
        try {            
            disciplinaForm.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            disciplinaDTO = disciplinaForm.getValueAs(DisciplinaDTO.class);
            disciplinaService.salvar(disciplinaDTO, arquivo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro_ao_criar_disciplina").build();
        }

        return Response.status(Status.OK).entity(disciplinaDTO).build();
    }

    @Path("/")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public Response atualizar(@FormDataParam("disciplina") FormDataBodyPart disciplinaForm, @FormDataParam("arquivo") File arquivo) {
        DisciplinaDTO disciplinaDTO;
        
        try {
            disciplinaForm.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            disciplinaDTO = disciplinaForm.getValueAs(DisciplinaDTO.class);
            disciplinaService.atualizar(disciplinaDTO, arquivo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro_ao_atualizar_disciplina").build();
        }

        return Response.status(Status.OK).build();
    }
    
    @Path("/{idDisciplina}")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    public Response deletar(@PathParam("idDisciplina") Integer idDisciplina) {
        try {
            disciplinaService.deletar(idDisciplina);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro_ao_deletar_disciplina").build();
        }

        return Response.status(Status.OK).build();
    }
}
