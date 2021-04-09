package Resource;

import Domain.Exam;
import Repository.ExamRepo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class ExamResource {

@Path("/exam")
    public class examResource {
    ExamRepo repo = new ExamRepo();

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Exam> getExam() {
        return repo.getExam();
    }
}
}
