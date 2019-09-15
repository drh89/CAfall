/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MembersDTO;
import entities.Members;
import facades.MembersFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Dennis
 */
@Path("groupmembers")
public class MembersResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CAfall",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final MembersFacade FACADE =  MembersFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of MembersResource
     */
    public MembersResource() {
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @GET
    @Path("/all")
    public Response getAllMembers(){
        List<Members> members = FACADE.getAllMembers();
        List<MembersDTO> membersDTO = new ArrayList();
        for (Members member : members) {
            membersDTO.add(new MembersDTO(member));
        }
        return Response.ok().entity(GSON.toJson(membersDTO)).build();
    }
    
    @GET
    @Path("/populate")
    public String populate(){
        Members drh89 = new Members(1L, "drh89", "cph-dh153", "red");
        Members rangerRyge = new Members(1L, "rangerRyge", "cph-jrXXX", "red");
        FACADE.addMember(drh89);
        FACADE.addMember(rangerRyge);
        return "Success!!!";
    }
}
