let libros = null;
let otroslibros = null;

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
                        "type": "GET",
                        "async": true
                    }
            ).done((data) =>
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
                        contenido += "<td> <button class='btn btn-outline-danger' onclick='eliminarLibro(" + i + ");'><i class='fa fa-trash'></i></button> </td>";
                        contenido += "<td> <button class='btn btn-outline-primary' onclick='mostrarLibro(" + i + ")'><i class='fa fa-pencil-alt'></i></button> </td>";
                        contenido += "</tr>";
                    }
                    $("#tbodyLibro").html(contenido);
                }
            }
            );
        };

const cargarOtrosLibros = () =>
        {
            $.ajax(
                    {
                        "url": "api/book/getAllO",
                        "type": "GET",
                        "async": true
                    }
            ).done((data) =>
            {
                if (data.error != null) {
                    alert(data.error);
                } else {
                    $("#btnConsultar").hide();
                    $("#btnInsertarOtro").show();
                    $("#btnInsertar").hide();
                    $("#btnVolver").show();
                    $("#btnActualizarOtro").hide();
                    otroslibros = data;
                    let contenido = "";
                    for (let i = 0; i < otroslibros.length; i++)
                    {
                        contenido += "<tr>";
//                contenido += "<td>" + libros[i].nombre_lbr + "</td>";
                        contenido += "<td>" + otroslibros[i].libro_nombre + "</td>";
                        contenido += "<td>" + otroslibros[i].libro_descripcion + "</td>";
                        contenido += "<td>" + otroslibros[i].libro_tema + "</td>";
                        contenido += "<td> <button class='btn btn-outline-primary' onclick='mostrarOtroLibro(" + i + ")'><i class='fa fa-pencil-alt'></i></button> </td>";
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
                "type": "GET",
                "async": true,
                "data": data
            }
    ).done((data) => {
        if (data.error != null) {
            alert("Error");
        } else {
            alert("libro agregado correctamente");
            cargarLibros();
        }
        limpiar();
    });
};

const insertarOtroLibro = () =>
{
    const nombre = document.getElementById('txtNombre').value;
    const descripcion = document.getElementById('txtDescripcion').value;
    const tema = document.getElementById('txtTema').value;
    let data = {
        "nombreL": nombre,
        "descripcionL": descripcion,
        "temaL": tema
    };
    $.ajax(
            {
                "url": "api/book/insertO",
                "type": "GET",
                "async": true,
                "data": data
            }
    ).done((data) => {
        if (data.error != null) {
            alert("Error");
        } else {
            alert("libro agregado correctamente");
            cargarOtrosLibros();
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
                "type": "GET",
                "async": true,
                "data": data
            }
    ).done((data) =>
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

const actualizarOtroLibro = () =>
{
    let id = $("#txtId").val();
    let descripcion = $("#txtDescripcion").val();
    let nombre = $("#txtNombre").val();
    let tema = $("#txtTema").val();
    let libro = {"libro_id": id,
        "libro_descripcion": descripcion,
        "libro_nombre": nombre,
        "libro_tema": tema};

    let data = {"libro": JSON.stringify(libro)};
    $.ajax(
            {
                "url": "api/book/updateO",
                "type": "GET",
                "async": true,
                "data": data
            }
    ).done((data) =>
    {
        if (data.result != null)
        {
            alert("Modificación exitosa");
            cargarOtrosLibros();
            cargarOtrosLibros();

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

const mostrarOtroLibro = (i) =>
        {
            $('#txtId').val(otroslibros[i].libro_id);
            $('#txtNombre').val(otroslibros[i].libro_nombre);
            $('#txtDescripcion').val(otroslibros[i].libro_descripcion);
            $('#txtTema').val(otroslibros[i].libro_tema);
            $("#btnActualizarOtro").show();
            $("#btnInsertarOtro").hide();


        };

const eliminarLibro = (i) =>
        {
            var data = {"id": libros[i].id};
            $.ajax(
                    {
                        "url": "api/book/delete",
                        "type": "GET",
                        "async": true,
                        "data": data
                    }
            ).done((data) =>
            {
                if (data.result !== null)
                {
                    //Se logró la eliminación
                    alert('¡Movimiento realizado!');
                    cargarLibros();
                } else if (data.error !== null)
                {
                    //No se logró la eliminación
                    alert("Error");
                }
            }
            );
        }

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
