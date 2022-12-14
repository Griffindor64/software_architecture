let libros = null;

const cargarLibrosA = () =>
        {
            $("#librosCentralizados").hide();
            $("#seccionLibro").hide();
            $("#libroslocales").show();
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
                    libros = null;
                    libros = data;
                    let contenido = "";
                    for (let i = 0; i < libros.length; i++)
                    {
                        contenido += "<tr>";
                        contenido += "<td>" + libros[i].nombre + "</td>";
                        contenido += "<td>" + libros[i].descripcion + "</td>";
                        contenido += "<td>" + libros[i].tema + "</td>";
                        contenido += "<td> <button class='btn btn-outline-primary' onclick='abrirLibro(" + i + ")'><i class='far fa-eye'></i></button> </td>";
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


const recuperarLibro = (i) => {
    let data = {
        "universidad_libro_id": libros[i].universidad_libro_id,
        "universidad_id": libros[i].universidad_id,
        "token": sessionStorage.getItem('token')
    };

    $.ajax(
            {
                "url": "api/book/getLibro",
                "type": "POST",
                "async": true,
                "data": data
            }
    ).done(data =>
    {
        $("#seccionLibro").show();
        $("#pdfHolderA").show();
        var src = "data:application/pdf;base64,";
        src += data.libro_base64;
        document.getElementById("pdfHolderA").data = src;
        alert( document.getElementById("pdfHolderA").data);

    });

};

const abrirLibro = (i) => {
    $("#seccionLibro").show();
    $("#pdfHolderA").show();
    var src = "data:application/pdf;base64,";
    src += libros[i].archivo;
    document.getElementById("pdfHolderA").data = src;
};

const generarTokenA = () => {
    let data = {
        "nUsuario": nUsuario,
        "contrasenna": contrasenna
    };

    $.ajax(
            {
                "url": "api/book/token",
                "type": "POST",
                "async": true,
                "data": data
            }
    ).done(data =>
    {
        let t = JSON.stringify(data).replace(/['"]+/g, '');
        console.log(t);
        sessionStorage.setItem("token", t);
    });
};

const buscarCentralizados = () =>
        {
            $("#seccionLibro").hide();
            $("#pdfHolderA").hide();
            $("#librosCentralizados").show();
            $("#libroslocales").hide();
            let filtro = $("#txtfiltroA").val();
            token = sessionStorage.getItem("token");
            let data = {
                "filtro": filtro,
                "token": token
            };
            $.ajax(
                    {
                        "url": "api/book/searchCentralizado",
                        "type": "POST",
                        "async": true,
                        "data": data
                    }
            ).done(data =>
            {
                if (data.error != null) {
                    alert(data.error);
                } else {
                    libros = null;
                    libros = data;
                    let contenido = "";
                    for (let i = 0; i < libros.length; i++)
                    {
                        contenido += "<tr>";
                        contenido += "<td>" + libros[i].libro_nombre + "</td>";
                        contenido += "<td>" + libros[i].tema + "</td>";
                        contenido += "<td>" + libros[i].nombre_universidad + "</td>";
                        contenido += "<td> <button class='btn btn-outline-primary' onclick='recuperarLibro(" + i + ")'><i class='far fa-eye'></i></button> </td>";
                        contenido += "</tr>";
                    }
                    $("#tbodyLibroAC").html(contenido);
                }
            }
            );
        };
