package edu.utl.dsm.helpdesk.rest;

import com.google.gson.Gson;
import edu.utl.dsm.helpdesk.MVVM.LibroViewModel;
import edu.utl.dsm.helpdesk.apiservice.ApiService;
import edu.utl.dsm.helpdesk.controller.LibroController;
import edu.utl.dsm.helpdesk.model.Libro;
import edu.utl.dsm.helpdesk.model.Usuario;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("book")
public class LibroREST {

    @Path("insert")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("nombre") @DefaultValue("") String nombre,
            @FormParam("descripcion") @DefaultValue("") String descripcion,
            @FormParam("tema") @DefaultValue("") String tema,
            @FormParam("usuario") @DefaultValue("") String usuario) {

        String out = "";
        try {
            LibroController objLibC = new LibroController();
            Gson gs = new Gson();
            Usuario objU = gs.fromJson(usuario, Usuario.class);
            Libro l = new Libro(nombre, descripcion, tema, objU);
            int id = objLibC.registrarLibro(l);
            l.setId(id);
            out = gs.toJson(l);
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Ocurrió una falla , "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("insertPublic")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertPublic(@QueryParam("nombreL") @DefaultValue("") String nombre,
            @QueryParam("descripcionL") @DefaultValue("") String descripcion,
            @QueryParam("temaL") @DefaultValue("") String tema) {

        String out = "";
        try {
            LibroController objLibC = new LibroController();
            Gson gs = new Gson();
            LibroViewModel l = new LibroViewModel(nombre, descripcion, tema);
            int id = objLibC.registrarLibroPublic(l);
            l.setId_lbr(id);
            out = gs.toJson(l);
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Ocurrió una falla , "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("insertO")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertO(@QueryParam("nombreL") @DefaultValue("") String nombre,
            @QueryParam("descripcionL") @DefaultValue("") String descripcion,
            @QueryParam("temaL") @DefaultValue("") String tema) {

        String out = "";

        try {
            ApiService objApis = new ApiService();
            StringBuilder response = objApis.insertarOtros(nombre, descripcion, tema);
            out = response.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un error al cargar los libros"
                    + " vuelve a intentarlo o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("delete")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") @DefaultValue("") String id) {
        String out = "";
        try {

            LibroController objLibC = new LibroController();
            if (objLibC.eliminarLibro(Integer.parseInt(id))) {
                out = "{\"result\":\"Se ha eliminado correctamente el registro\"}";
            } else {
                out = "{\"error\":\"Ocurrió una falla , "
                        + "Recuerda que el nlibro debe existir para poder eliminarlo\"}";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Ocurrió una falla , "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("update")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@QueryParam("libro") @DefaultValue("") String libro) {

        String out = "";
        try {
            Gson objGS = new Gson();
            Libro objLibro = objGS.fromJson(libro, Libro.class);
            LibroController objLibC = new LibroController();
            if (objLibC.actualizarLibro(objLibro)) {
                out = "{\"result\":\"La actualización del libro resultó exitosa\"}";
            } else {
                out = "{\"error\":\"Ocurrió una falla , "
                        + "Recuerda que el nombre del libro debe ser unico\"}";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Ocurrió una falla , "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();

    }
    
    @Path("updatePublic")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePublic(@QueryParam("libro") @DefaultValue("") String libro) {

        String out = "";
        try {
            Gson objGS = new Gson();
            LibroViewModel objLibro = objGS.fromJson(libro, LibroViewModel.class);
            LibroController objLibC = new LibroController();
            if (objLibC.actualizarLibroPublic(objLibro)) {
                out = "{\"result\":\"La actualización del libro resultó exitosa\"}";
            } else {
                out = "{\"error\":\"Ocurrió una falla , "
                        + "Recuerda que el nombre del libro debe ser unico\"}";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Ocurrió una falla , "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();

    }
    
    @Path("updateO")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateO(@QueryParam("libro") @DefaultValue("") String libro) {

        String out = "";

        try {
            ApiService objApis = new ApiService();
            StringBuilder response = objApis.actualizarOtros(libro);
            out = response.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un error al cargar los libros"
                    + " vuelve a intentarlo o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        String out = "";

        try {
            LibroController objLibC = new LibroController();
            List<Libro> libros = new ArrayList<>();
            libros = objLibC.mostrarLibros();
            Gson objGS = new Gson();
            out = objGS.toJson(libros);

        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un error al cargar los libros"
                    + " vuelve a intentarlo o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("getAllPublic")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPublic() {
        String out = "";

        try {
            LibroController objLibC = new LibroController();
            List<LibroViewModel> libros = new ArrayList<>();
            libros = objLibC.mostrarLibrosPublic();
            Gson objGS = new Gson();
            out = objGS.toJson(libros);

        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un error al cargar los libros"
                    + " vuelve a intentarlo o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllO")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOthers() throws MalformedURLException,
            ProtocolException, IOException {
        String out = "";

        try {
            ApiService objApis = new ApiService();
            StringBuilder response = objApis.librosCentralizados();
            out = response.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un error al cargar los libros"
                    + " vuelve a intentarlo o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

}
