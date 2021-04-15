package Resource;

import Domain.Staff;
import Repository.StaffRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("staff")
public class StaffResource {
    StaffRepo repo = new StaffRepo();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Staff> getStaff() {
        return repo.getStaff();
    }
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Staff getStaff(@PathParam("name") String name) {
        return repo.getStaffByName(name);
    }
    @POST
    @Path("set")
    @Consumes({MediaType.APPLICATION_JSON})
    public Staff createStaff(Staff staff){
        return repo.createStaff(staff);
    }
    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    public Staff updateStaff(Staff staff){
        return repo.updateStaff(staff);
    }
    @DELETE
    @Path("delete")
    @Consumes({MediaType.APPLICATION_JSON})
    public Staff deleteStaff(Staff staff){
        return repo.deleteStaff(staff);
    }

}

