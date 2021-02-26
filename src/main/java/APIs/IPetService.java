package APIs;

import POJO.Pet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface IPetService {

    @GET
    @Path("/pet/{petId}")
    @Produces(MediaType.APPLICATION_JSON)
    Pet GetPet(@PathParam("petId") int petId);

    @POST
    @Path("/pet")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Pet CreatePet(Pet newPet);
}
