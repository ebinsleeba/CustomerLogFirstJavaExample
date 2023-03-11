package resources;

import dao.CustomerDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import modal.Customer;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HEAD;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author Ebin
 */
@Path("customer")
public class CustomerResource {

    @Context
    private UriInfo context;

    private CustomerDAO dao = new CustomerDAO();

    @OPTIONS
    public Response doOptionsCollection() {
        Set<String> api = new TreeSet<>();
        api.add("GET");
        api.add("POST");
        api.add("DELETE");
        api.add("HEAD");

        return Response
                .noContent()
                .allow(api)
                .build();
    }

    @HEAD
    public Response doHeadCollection() {
        return Response
                .noContent()
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllCustomers() {

        List<Customer> lstCustomers = dao.getAllCustomers();

        GenericEntity<List<Customer>> entity;
        entity = new GenericEntity<List<Customer>>(lstCustomers) {
        };

        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .build();
    }

    /**
     * ** ID based filter ***
     */
    @OPTIONS
    @Path("{id}")
    public Response doOptionsCollectionForId() {
        Set<String> api = new TreeSet<>();
        api.add("GET");
        api.add("DELETE");
        api.add("HEAD");
        api.add("PUT");

        return Response
                .noContent()
                .allow(api)
                .build();

    }

    @HEAD
    @Path("{id}")
    public Response doHeadCollectionForId() {
        return Response
                .noContent()
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCustomerById(@PathParam("id") String id) {

        Customer objCustomer = dao.getCustomerById(id);

        GenericEntity<Customer> entity;
        entity = new GenericEntity<Customer>(objCustomer) {
        };

        if (objCustomer == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("<Customer Not Found />")
                    .build();
        } else {
            return Response
                    .status(Response.Status.OK)
                    .entity(entity)
                    .build();
        }

    }

    @DELETE
    @Path("{id}")
    public Response deleteCustomerById(@PathParam("id") String id) {

        dao.deleteCustomerById(id);

        return Response
                .status(Response.Status.OK)
                .build();
    }

    @POST
    @Path("group-delete")
    public Response deleteCustomersById(List<Customer> lstCustomers) {

        if (lstCustomers.isEmpty()) {
            return Response
                    .status(Response.Status.OK)
                    .entity("<Empty List />")
                    .build();
        } else {

            List<String> Ids = new ArrayList<>();

            for (Customer objCustomer : lstCustomers) {
                Ids.add(objCustomer.getId());
            }

            dao.deleteCustomers(Ids);
            
            return Response
                    .status(Response.Status.OK)
                    .entity("<Customers Deleted Successfully />")
                    .build();
        }

    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addCustomer(Customer objCustomer) {

        if (dao.addCustomer(objCustomer) < 0) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity("<duplicateNameError />")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity(objCustomer)
                .build();
    }

    @POST
    @Path("add-customers")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addCustomers(List<Customer> lstCustomers) {

        for (Customer objCustomer : lstCustomers) {
            dao.addCustomer(objCustomer);
        }

        return Response
                .status(Response.Status.OK)
                .entity("<Customers Added />")
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editCustomerById(Customer objCustomer,
            @PathParam("id") String id) {

        if (objCustomer.getId().equalsIgnoreCase(id)) {

            if (dao.getCustomerById(id) != null) {

                dao.updateCustomer(objCustomer);

                return Response
                        .status(Response.Status.OK)
                        .entity(objCustomer)
                        .build();
            } else {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("<Customer Not Found />")
                        .build();
            }
        } else {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity("<mismatchError />")
                    .build();
        }
    }

    @PUT
    @Path("edit-customers")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editCustomersById(List<Customer> lstCustomers) {

        for (Customer objCustomer : lstCustomers) {
            dao.updateCustomer(objCustomer);
        }

        return Response
                .status(Response.Status.OK)
                .entity("<Customers Updated />")
                .build();
    }

}
