package Resource;

import Domain.Faculty;
import Domain.Student;
import Repository.StudentRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("student")
public class StudentResource {
    StudentRepo repo = new StudentRepo();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Student> getStudent() {
        return repo.getStudent();
    }

    @GET
    @Path("/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Student getStudentByName(@PathParam("name") String name ){
        return repo.getStudentByName(name);
    }


    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Student getStudentById(@PathParam("id")String id){
        return repo.getStudentById(id);
    }

    @GET
    @Path("/grade/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Student getStudentGrade(@PathParam("id")String id){
        return repo.getStudentGrade(id);
    }

    @GET
    @Path("/full")
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Student> getStudentsFull(){
        return repo.getStudentsFull();
    }

    @GET
    @Path("/full-v2")
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Student> getStudentsFullV2(){
        return repo.getStudentsFullV2();
    }

    @GET
    @Path("/full-v3")
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Student> getStudentsFullV3(){
        return repo.getStudentsFullV3();
    }

    @POST
    @Path("/set")
    @Consumes({MediaType.APPLICATION_JSON})
    public Student createStudent(Student student){
        System.out.println(student);
        return repo.createStudent(student);
    }
    @GET
    @Path("/examgrade")
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Student>getStudentsExam(){
        return repo.getStudentExam();
    }
    @GET
    @Path("getAll")
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Student>getAll(){
        return repo.getAll();
    }
}
