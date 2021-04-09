package Resource;

import Domain.Exam;
import Repository.ExamRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("exam")
public class ExamResource {
    ExamRepo repo = new ExamRepo();

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Exam> getExam() {
        return repo.getExam();
    }
    @POST
    @Path("set")
    @Consumes({MediaType.APPLICATION_JSON})
    public Exam createExam(Exam exam){
        return repo.createExam(exam);
    }
    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    public Exam updateExam(Exam exam){
        return repo.updateExam(exam);
    }
    @DELETE
    @Path("delete")
    @Consumes({MediaType.APPLICATION_JSON})
    public Exam deleteExam(Exam exam){
        return repo.deleteExam(exam);
    }
}

