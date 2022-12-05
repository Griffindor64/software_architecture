let nUsuario;
let contrasenna;

function moduloLibros()
{
    $.ajax({
        context: document.body,
        url: "modulos/administrador/libros/libro.html"
    }).done(function (data)
    {
        $("#contenedor").html(data);
        cargarLibros();
        nUsuario = "20001468";
        contrasenna = "20001468";
        generarToken();
    });
}

function moduloPerfil()
{
    
    $.ajax({
        context: document.body,
        url: "modulos/administrador/perfil/miPerfil.html"
    }).done(function (data)
    {
        $("#contenedor").html(data);
        cargarAdministrador();
    });
}

function moduloLogin()
{
    window.location.replace("index.html");
}

function moduloUsuarios()
{
    $.ajax({
        context: document.body,
        url: "modulos/administrador/usuarios/usuario.html"
    }).done(function (data)
    {
        $("#contenedor").html(data);
        cargarUsuAlumnos();
        $('#seccionAdmin').hide();
    });
}
