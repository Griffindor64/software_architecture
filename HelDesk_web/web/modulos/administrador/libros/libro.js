let libros = null;
let usuario = null;
let token = null;

const generarToken = () => {
    $.ajax(
            {
                "url": "api/book/token",
                "type": "POST",
                "async": true
            }
    ).done(data =>
    {
        sessionStorage.setItem("token", JSON.stringify(data));
        token = sessionStorage.getItem("token");
        usuario = sessionStorage.getItem("usuario");
    });


}

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
            let nombre = document.getElementById('txtNombre').value;
            let descripcion = document.getElementById('txtDescripcion').value;
            let tema = document.getElementById('txtTema').value;
            generarToken();
//            usuario = sessionStorage.getItem("usuario");
//            token = sessionStorage.getItem("token");

            let data = {
                "nombre": nombre,
                "descripcion": descripcion,
                "tema": tema,
                "usuario": usuario,
                "token": token
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
            let descripcion = $("#txtDescripcion").val();
            let nombre = $("#txtNombre").val();
            let tema = $("#txtTema").val();
            generarToken();
//            usuario = sessionStorage.getItem("usuario");
//            token = sessionStorage.getItem("token");
            let libro = {"id": id, "descripcion": descripcion, "nombre": nombre, "tema": tema, "usuario": usuario};
            let data = {
                "libro": JSON.stringify(libro),
                "token": token
            };

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
