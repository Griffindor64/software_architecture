let libros = null;

const cargarLibros = () =>
        {
            $.ajax(
                    {
                        "url": "api/book/getAll",
                        "type": "POST",
                        "async": true
                    }
            ).done(data =>
            {
                if (data.error != null) {
                    alert(data.error);
                } else {
                    libros = data;
                    let contenido = "";
                    for (let i = 0; i < libros.length; i++)
                    {
                        contenido += "<tr>";
                        contenido += "<td>" + libros[i].nombre + "</td>";
                        contenido += "<td>" + libros[i].descripcion + "</td>";
                        contenido += "<td>" + libros[i].tema + "</td>";
                        contenido += "<td> <button class='btn btn-outline-primary' onclick='mostrarLibro(" + i + ")'><i class='fa fa-pencil-alt'></i></button> </td>";
                        contenido += "</tr>";
                    }
                    $("#tbodyLibroA").html(contenido);
                }
            }
            );
        };

const volver = () =>
{
    $("#btnConsultar").show();
    cargarLibros();
    limpiar();
};
