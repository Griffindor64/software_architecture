let libros = null;
let usuario = null;
let token = null;
let archivo = null;

window.onload = function () {
    generarToken();
};

const generarToken = () => {
    $.ajax(
            {
                "url": "api/book/token",
                "type": "POST",
                "async": true
            }
    ).done(data =>
    {
        console.log(JSON.stringify(data));
        let t = data.replace(/['"]+/g, '');
        sessionStorage.setItem("token", t);
    });
};

const convertirBase64 = () => {
    //Leer File
    var selectedFile = document.getElementById("txtArchivo").files;
    //Checar si el File esta vacio
    if (selectedFile.length > 0) {
        // Seleccionar el primer archico
        var fileToLoad = selectedFile[0];
        // FileReader es una función para leer el file.
        var fileReader = new FileReader();
        var base64;
        // Al cargar file se leera su contenido
        fileReader.onload = function (fileLoadedEvent) {
            base64 = fileLoadedEvent.target.result;
            archivo = base64.substring(28);
            return base64;
        };
        // Convertir  libro a base64
        fileReader.readAsDataURL(fileToLoad);
    }
};

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
    usuario = sessionStorage.getItem("usuario");
    token = sessionStorage.getItem("token");
    console.log(token);
    let data = {
        "nombre": nombre,
        "descripcion": descripcion,
        "tema": tema,
        "archivo": archivo,
        "usuario": usuario,
        "token": token
    }
    alert(JSON.stringify(data));
    $.ajax(
            {
                "url": "api/book/insert",
                "type": "POST",
                "async": true,
                "data": data
            }
    ).done(data => {
        if (data.error != null) {
            alert(data.error);
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
    token = sessionStorage.getItem("token");
    usuario = sessionStorage.getItem("usuario");
    let libro = {"id": id, "descripcion": descripcion, "nombre": nombre, "tema": tema, "archivo": archivo,"usuario": usuario};
    let data = {
        "libro": JSON.stringify(libro),
        "token": token
    };
        alert(JSON.stringify(data));

    $.ajax(
            {
                "url": "api/book/update",
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
