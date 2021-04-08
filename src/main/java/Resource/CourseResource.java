package Resource;


import Domain.Course;
import Repository.CourseRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("course")
public class CourseResource {
    CourseRepo repo = new CourseRepo();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Course> getCourse() {
        return repo.getCourse();
    }
    @GET
    @Path("/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Course getCourse(@PathParam("name") String name){
        return repo.getCourseByName(name);
    }
    @POST
    @Path("set")
    @Consumes({MediaType.APPLICATION_JSON})
    public Course createCourse(Course course){
        return repo.createCourse(course);
    }
    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    public Course updateCourse(Course course){
        return repo.updateCourse(course);
    }
    @DELETE
    @Path("delete")
    @Consumes({MediaType.APPLICATION_JSON})
    public Course deleteCourse(Course course){
        return repo.deleteCourse(course);
    }


}
