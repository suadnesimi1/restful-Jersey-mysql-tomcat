package Resource;

import Domain.University;
import Repository.UniversityRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("universities")
public class UniversityResource {
    UniversityRepo repo = new UniversityRepo();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<University> getUniversity() {
        return repo.getUniversity();
    }
    @POST
    @Path("/set")
    @Consumes({MediaType.APPLICATION_JSON})
    public University createUni(University university){
        return repo.createUni(university);
    }
    @DELETE
    @Path("/delete")
    @Consumes({MediaType.APPLICATION_JSON})
    public University deleteUni(University university){
        return repo.deleteUni(university);
    }
}

