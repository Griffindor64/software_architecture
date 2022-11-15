package edu.utl.dsm.helpdesk.rest;
import com.google.gson.Gson;
import edu.utl.dsm.helpdesk.controller.UsuarioController;
import edu.utl.dsm.helpdesk.model.Usuario;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UsuarioREST {
 
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("nombreUsuario") @DefaultValue("") String nombreUsuario,
            @FormParam("contrasennia") @DefaultValue("") String contrasennia) {

        String out = "";
        try {
            UsuarioController objUsuC = new UsuarioController();
            Usuario usuario = new Usuario();
            usuario = objUsuC.iniciarSesion(nombreUsuario, contrasennia);
            if (usuario.getId() == 0) {
                out = "{\"error\":\"El usuario no existe, "
                        + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
            } else {
                Gson gs = new Gson();
                out = gs.toJson(usuario);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Ocurrió una falla , "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();

    }
    
    @Path("register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@FormParam("nombreUsuario") @DefaultValue("") String nombreUsuario,
            @FormParam("contrasennia") @DefaultValue("") String contrasennia) {

        String out = "";
        try {
            UsuarioController objUsuC = new UsuarioController();
            
            Gson gs = new Gson();
            Usuario usu = new Usuario(nombreUsuario, contrasennia);
            int nU = objUsuC.registrarUsuario(usu);
            if (nU == 0) {
                out = "{\"error\":\"Error , "
                        + " El usuario ya existe\"}";
            } else {
                usu.setId(nU);
                out = gs.toJson(usu);
            }

            
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Ocurrió una falla , "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();

    }

    @Path("validateNameUser")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateNameUser(@FormParam("nombreUsuario") @DefaultValue("") String nombreUsuario) {

        String out = "";
        try {
            UsuarioController objUsuC = new UsuarioController();
            if (objUsuC.validarNombreUsuario(nombreUsuario)) {
                out = "{\"error\":\"Usuario no disponible\"}";
            } else {
                out = "{\"result\":\"Usuario disponible\"}";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Ocurrió una falla , "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();

    }
}
