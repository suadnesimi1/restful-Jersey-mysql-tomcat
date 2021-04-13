package Resource;

import Domain.FinalGrade;
import Repository.FinalGradeRepo;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("finalgrade")
public class FinalGradeResource {
    FinalGradeRepo repo = new FinalGradeRepo();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<FinalGrade> getFinalGrade() {
        return repo.getFinalGrade();
    }
    @POST
    @Path("set")
    @Consumes({MediaType.APPLICATION_JSON})
    public FinalGrade createFinalGrade(FinalGrade finalGrade){
        return repo.createFinalGrade(finalGrade);
    }
    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    public FinalGrade updateFinalGrade(FinalGrade finalGrade){
        return repo.updateFinalGrade(finalGrade);
    }
    @DELETE
    @Path("delete")
    @Consumes({MediaType.APPLICATION_JSON})
    public FinalGrade deleteFinalGrade(FinalGrade finalGrade){
        return repo.deleteFinalGrade(finalGrade);
    }

}
