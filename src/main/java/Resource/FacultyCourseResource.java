package Resource;


import Domain.Faculty_Course;
import Repository.FacultyCourseRepo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("getFacultyCourse")
public class FacultyCourseResource {
    FacultyCourseRepo repo = new FacultyCourseRepo();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Faculty_Course> getFacultyCourse() {
        return repo.getFacultyCourse();
    }
}
