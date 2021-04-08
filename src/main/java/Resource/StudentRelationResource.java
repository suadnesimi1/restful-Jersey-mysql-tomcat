package Resource;


import Domain.Studentrelation;
import Repository.StudentRelationRepo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("getStudentRelation")
public class StudentRelationResource {
    StudentRelationRepo repo = new StudentRelationRepo();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Studentrelation> getStudentRelation() {
        return repo.getStudentrelation();
    }
}
