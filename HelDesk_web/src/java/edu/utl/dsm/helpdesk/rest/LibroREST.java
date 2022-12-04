package edu.utl.dsm.helpdesk.rest;

import com.google.gson.Gson;
import edu.utl.dsm.helpdesk.apiservice.ApiService;
import edu.utl.dsm.helpdesk.controller.LibroController;
import edu.utl.dsm.helpdesk.model.Libro;
import edu.utl.dsm.helpdesk.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
            @FormParam("usuario") @DefaultValue("") String usuario,
            @FormParam("token") @DefaultValue("") String token) {
        String out = "";
        if (token != null) {
            try {
                LibroController objLibC = new LibroController();
                ApiService objAS = new ApiService();
                Gson gs = new Gson();
                Usuario objU = gs.fromJson(usuario, Usuario.class);
                Libro l = new Libro(nombre, descripcion, tema, objU);
                int id = objLibC.registrarLibro(l);
                l.setId(id);
                if (id != 0) {
                    System.out.println(id);
                    System.out.println(l.getId());
                    objAS.guardarLibroCentralizado(l, token);
                    out = String.valueOf(l.getId());
                } else {
                    out = "{\"error\":\"El libro ya existe , "
                            + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                out = "{\"error\":\"Ocurrió una falla , "
                        + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
            }
        } else {
            out = "{\"error\":\"El token no puede estar vacio "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("libro") @DefaultValue("") String libro,
            @FormParam("token") @DefaultValue("") String token) {

        String out = "";
        try {
            Gson objGS = new Gson();
            Libro objLibro = objGS.fromJson(libro, Libro.class);
            LibroController objLibC = new LibroController();
            if (objLibC.actualizarLibro(objLibro)) {
                ApiService objAS = new ApiService();
                objAS.guardarLibroCentralizado(objLibro, token);
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

    @Path("getAll")
    @POST
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

    @Path("search")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@FormParam("filtro") @DefaultValue("") String filtro) {
        String out = "";

        try {
            LibroController objLibC = new LibroController();
            List<Libro> libros = new ArrayList<>();
            libros = objLibC.consultarLibros(filtro);
            Gson objGS = new Gson();
            out = objGS.toJson(libros);

        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un error al cargar los libros"
                    + " vuelve a intentarlo o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("searchCentralizado")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCentralizado(@FormParam("filtro") @DefaultValue("") String filtro,
            @FormParam("token") @DefaultValue("") String token) {
        String out = "";
        try {
            ApiService objApis = new ApiService();
            out = objApis.buscar(filtro, token);
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un error al cargar los libros"
                    + " vuelve a intentarlo o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("token")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response token() {
        String out = "";
        try {
            ApiService objApis = new ApiService();
            out = objApis.getToken().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un error al cargar los libros"
                    + " vuelve a intentarlo o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("getLibro")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLibro(@FormParam("universidad_id") @DefaultValue("") String universidad_id,
            @FormParam("universidad_libro_id") @DefaultValue("") String universidad_libro_id,
            @FormParam("token") @DefaultValue("") String token) {
        String out = "";
        try {
            ApiService objApis = new ApiService();
            out = objApis.obtenerLibro(universidad_libro_id, universidad_id, token);
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un error al cargar los libros"
                    + " vuelve a intentarlo o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("recuperar-libro")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperarLibro(@FormParam("universidad_libro_id") @DefaultValue("") String universidad_libro_id) {
        String out = "";
        try {
            
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un error al cargar los libros"
                    + " vuelve a intentarlo o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
}
