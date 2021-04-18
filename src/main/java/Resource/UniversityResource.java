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
}

