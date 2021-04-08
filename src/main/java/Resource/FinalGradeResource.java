package Resource;

import Domain.Final_Grade;
import Repository.FinalGradeRepo;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("finalgrade")
public class FinalGradeResource {
    FinalGradeRepo repo = new FinalGradeRepo();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Final_Grade> getFinalGrade() {
        return repo.getFinalGrade();
    }
    @POST
    @Path("set")
    @Consumes({MediaType.APPLICATION_JSON})
    public Final_Grade createFinalGrade(Final_Grade finalGrade){
        return repo.createFinalGrade(finalGrade);
    }
    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    public Final_Grade updateFinalGrade(Final_Grade finalGrade){
        return repo.updateFinalGrade(finalGrade);
    }
    @DELETE
    @Path("delete")
    @Consumes({MediaType.APPLICATION_JSON})
    public Final_Grade deleteFinalGrade(Final_Grade finalGrade){
        return repo.deleteFinalGrade(finalGrade);
    }

}
