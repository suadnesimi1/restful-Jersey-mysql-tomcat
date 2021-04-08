package Resource;


import Domain.StaffCourse;
import Repository.StaffCourseRepo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("getStaffCourse")
public class StaffCourseResource {
    StaffCourseRepo repo = new StaffCourseRepo();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<StaffCourse> getStaffCourse() {
        return repo.getStaffCourse();
    }
}
