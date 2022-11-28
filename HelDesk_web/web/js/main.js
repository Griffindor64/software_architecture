
function moduloLibros()
{
    $.ajax({
        context: document.body,
        url: "modulos/libros/libro.html"
    }).done(function (data)
    {
        $("#contenedor").html(data);
        cargarLibros();
    });
}

function moduloUsuarios()
{
    $.ajax({
        context: document.body,
        url: "modulos/usuarios/usuario.html"
    }).done(function (data)
    {
        $("#contenedor").html(data);
        cargarUsuarios();
    });
}
