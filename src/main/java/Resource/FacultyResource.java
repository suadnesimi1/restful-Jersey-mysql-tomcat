package Resource;

import Domain.Faculty;
import Repository.FacultyRepo;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/faculty")
public class FacultyResource {
    FacultyRepo repo = new FacultyRepo();


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Faculty> getFaculties() {
        return repo.getFaculties();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Faculty getFaculty(@PathParam("name") String name) {
        return repo.getFacultyByName(name);
    }


    @POST
    @Path("/post")
    @Consumes({MediaType.APPLICATION_JSON})
    public Faculty createFaculty(Faculty faculty){
        System.out.println(faculty);
        return repo.createFaculty(faculty);
    }
    @PUT
    @Path("/update")
    @Consumes({MediaType.APPLICATION_JSON})
    public Faculty updateFaculty(Faculty faculty){
        System.out.println(faculty);
        return repo.updateFaculty(faculty);
    }

    @DELETE
    @Path("/delete")
    @Consumes({MediaType.APPLICATION_JSON})
    public Faculty deleteFaculty(Faculty faculty){
        return repo.deleteFaculty(faculty);
    }

}
