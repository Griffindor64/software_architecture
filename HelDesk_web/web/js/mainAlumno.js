
function moduloLibrosA()
{
    $.ajax({
        context: document.body,
        url: "modulos/alumnos/libros/libro.html"
    }).done(function (data)
    {
        $("#contenedor").html(data);
        cargarLibrosA();
        nUsuario = "20001468";
        contrasenna = "20001468";
        generarTokenA();
    });
}

function moduloLogin()
{
    window.location.replace("index.html");
}

function moduloPerfilA()
{
    
    $.ajax({
        context: document.body,
        url: "modulos/alumnos/perfil/miPerfil.html"
    }).done(function (data)
    {
        $("#contenedor").html(data);
        cargarAlumno();
    });
}
