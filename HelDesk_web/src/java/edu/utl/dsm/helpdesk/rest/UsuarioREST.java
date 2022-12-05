package edu.utl.dsm.helpdesk.rest;

import com.google.gson.Gson;
import edu.utl.dsm.helpdesk.controller.UsuarioController;
import edu.utl.dsm.helpdesk.model.Usuario;
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
                return Response.status(Response.Status.UNAUTHORIZED).build();
            } else {
//                String key = "libros";
//                long fecha = System.currentTimeMillis();
//                String token = Jwts.builder()
//                        .signWith(SignatureAlgorithm.HS256, key)
//                        .setSubject(usuario.getNombreUsuario())
//                        .setIssuedAt(new Date(fecha))
//                        .setExpiration(new Date(fecha + 900000))
//                        .claim("nombreUsu", usuario.getNombreUsuario())
//                        .claim("rol", usuario.getRol())
//                        .compact();
//
//                JsonObject js = Json.createObjectBuilder().add("JWT", token).build();
                Gson gs = new Gson();
                out = gs.toJson(usuario);
                return Response.status(Response.Status.CREATED).entity(out).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Ocurrió una falla , "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
            return Response.status(Response.Status.OK).entity(out).build();
        }
    }

    @Path("register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(
            @FormParam("nombres") @DefaultValue("") String nombres,
            @FormParam("apellidos") @DefaultValue("") String apellidos,
            @FormParam("nombreUsuario") @DefaultValue("") String nombreUsuario,
            @FormParam("contrasennia") @DefaultValue("") String contrasennia,
            @FormParam("rol") @DefaultValue("") String rol) {

        String out = "";
        try {
            UsuarioController objUsuC = new UsuarioController();

            Gson gs = new Gson();
            Usuario usu = new Usuario(nombres, apellidos, nombreUsuario, contrasennia, Integer.valueOf(rol));
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

    @Path("delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("id") @DefaultValue("") String id) {
        String out = "";
        try {

            UsuarioController objUsuC = new UsuarioController();
            if (objUsuC.eliminarUsuario(Integer.parseInt(id))) {
                out = "{\"result\":\"Se ha eliminado correctamente el usuario\"}";
            } else {
                out = "{\"error\":\"Ocurrió una falla , "
                        + "Recuerda que el usuario debe existir para poder eliminarlo\"}";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Ocurrió una falla , "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @FormParam("id") @DefaultValue("") String id,
            @FormParam("nombres") @DefaultValue("") String nombres,
            @FormParam("apellidos") @DefaultValue("") String apellidos,
            @FormParam("nombreUsuario") @DefaultValue("") String nombreUsuario,
            @FormParam("contrasennia") @DefaultValue("") String contrasennia,
            @FormParam("rol") @DefaultValue("") String rol) {

        String out = "";
        try {
            UsuarioController objUsuC = new UsuarioController();
            Gson gs = new Gson();
            Usuario objUsuario = new Usuario(Integer.valueOf(id), nombres, apellidos, nombreUsuario, contrasennia, Integer.valueOf(rol));
            if (objUsuC.actualizarUsuario(objUsuario)) {
                out = "{\"result\":\"La actualización resultó exitosa\"}";
            } else {
                out = "{\"error\":\"Ocurrió una falla , "
                        + "Recuerda que el nombre de usuario debe ser unico\"}";
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

    
    @Path("getAll")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@FormParam("rol") @DefaultValue("") String rol) {

        String out = "";

        try {
            UsuarioController objUsuC = new UsuarioController();
            List<Usuario> usuarios = new ArrayList<>();
            usuarios = objUsuC.getAllUsuarios(Integer.parseInt(rol));
            Gson objGS = new Gson();
            out = objGS.toJson(usuarios);
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un error al cargar los usuarios"
                    + " vuelve a intentarlo o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
