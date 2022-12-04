let libros = null;

const cargarLibros = () =>
        {
            $("#btnInsertar").show();
            $("#btnActualizar").hide();
            $("#btnActualizarOtro").hide();
            $("#btnVolver").hide();
            $("#btnInsertarOtro").hide();
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
                    $("#tbodyLibro").html(contenido);
                }
            }
            );
        };

const insertarLibro = () =>
{
    const nombre = document.getElementById('txtNombre').value;
    const descripcion = document.getElementById('txtDescripcion').value;
    const tema = document.getElementById('txtTema').value;
    let data = {
        "nombre": nombre,
        "descripcion": descripcion,
        "tema": tema
    };
    $.ajax(
            {
                "url": "api/book/insert",
                "type": "POST",
                "async": true,
                "data": data
            }
    ).done(data => {
        if (data.error != null) {
            alert("Error");
        } else {
            alert("libro agregado correctamente");
            cargarLibros();
        }
        limpiar();
    });
};

const actualizarLibro = () =>
{
    let id = $("#txtId").val();
    alert($("#txtId").val());
    let descripcion = $("#txtDescripcion").val();
    let nombre = $("#txtNombre").val();
    let tema = $("#txtTema").val();
    let libro = {"id": id, "descripcion": descripcion, "nombre": nombre, "tema": tema};

    let data = {"libro": JSON.stringify(libro)};

    $.ajax(
            {
                "url": "../api/book/update",
                "type": "POST",
                "async": true,
                "data": data
            }
    ).done(data =>
    {
        if (data.result != null)
        {
            alert("Modificación exitosa");
            cargarLibros();
        } else if (data.error !== null)
        {
            alert("Error");
        }
        limpiar();
    }
    );
};

const mostrarLibro = (i) =>
        {
            $('#txtId').val(libros[i].id);
            $('#txtNombre').val(libros[i].nombre);
            $('#txtDescripcion').val(libros[i].descripcion);
            $('#txtTema').val(libros[i].tema);
            $("#btnActualizar").show();
            $("#btnInsertar").hide();


        };

const limpiar = () =>
{
    $('#txtId').val("");
    $('#txtNombre').val("");
    $('#txtDescripcion').val("");
    $('#txtTema').val("");
};

const volver = () =>
{
    $("#btnConsultar").show();
    cargarLibros();
    limpiar();
};
