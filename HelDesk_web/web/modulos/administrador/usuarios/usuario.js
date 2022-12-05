let alumnos = null;

function cargarSeccion(opcion) {
    if (opcion) {
        $('#seccionAlumnos').show();
        $('#seccionAdmin').hide();
        cargarUsuAlumnos();
    } else {
        $('#seccionAdmin').show();
        $('#seccionAlumnos').hide();
        cargarUsuAdministradores();
    }
}
;

const cargarUsuAlumnos = () =>
{

    let data = {
        "rol": "2"
    };

    $.ajax(
            {
                "url": "api/user/getAll",
                "type": "POST",
                "async": true,
                "data": data

            }
    ).done((data) =>
    {
        if (data.error != null) {
            alert(data.error);
        } else {
            alumnos = data;
            let contenido = "";
            for (let i = 0; i < alumnos.length; i++)
            {
                contenido += "<tr>";
                contenido += "<td>" + alumnos[i].nombres + "</td>";
                contenido += "<td>" + alumnos[i].apellidos + "</td>";
                contenido += "<td>" + alumnos[i].nombreUsuario + "</td>";
                contenido += "<td> <button class='btn btn-outline-danger' onclick='eliminarUsuario(" + i + ");'><i class='fa fa-trash'></i></button>&nbsp"
                        + "<button class='btn btn-outline-primary' onclick='mostrarAlumno(" + i + ")'><i class='fa fa-pencil-alt'></i></button> </td>";
                contenido += "</tr>";
            }
            $("#tbodyAlumnos").html(contenido);
        }
    }
    );
};

const cargarUsuAdministradores = () =>
{
    $('#btnActualizarAdmin').hide();
    $('#btnInsertarAdmin').show();

    let data = {
        "rol": "1"

    };

    $.ajax(
            {
                "url": "api/user/getAll",
                "type": "POST",
                "async": true,
                "data": data

            }
    ).done((data) =>
    {
        if (data.error != null) {
            alert(data.error);
        } else {
            alumnos = data;
            let contenido = "";
            for (let i = 0; i < alumnos.length; i++)
            {
                contenido += "<tr>";
                contenido += "<td>" + alumnos[i].nombres + "</td>";
                contenido += "<td>" + alumnos[i].apellidos + "</td>";
                contenido += "<td>" + alumnos[i].nombreUsuario + "</td>";
                contenido += "<td> <button class='btn btn-outline-danger' onclick='eliminarUsuario(" + i + ");'><i class='fa fa-trash'></i></button>&nbsp" +
                        "<button class='btn btn-outline-primary' onclick='mostrarAdministrador(" + i + ")'><i class='fa fa-pencil-alt'></i></button> </td>";
                contenido += "</tr>";
            }
            $("#tbodyAdmin").html(contenido);
        }
    }
    );
};

const mostrarAlumno = (i) =>
{
    $('#txtIdU').val(alumnos[i].id);
    $('#txtNombres').val(alumnos[i].nombres);
    $('#txtApellidos').val(alumnos[i].apellidos);
    $('#txtNUsuario').val(alumnos[i].nombreUsuario);
    $('#txtContrasennia').val(alumnos[i].contrasennia);
    $('#txtRolU').val(alumnos[i].rol);
    $("#btnActualizarAlum").show();
    $("#btnActualizarAdmin").show();
    $("#btnInsertarAlum").hide();
    $("#btnInsertarAdmin").hide();
};

const mostrarAdministrador = (i) =>
{
    $('#txtIdA').val(alumnos[i].id);
    $('#txtNombresA').val(alumnos[i].nombres);
    $('#txtApellidosA').val(alumnos[i].apellidos);
    $('#txtNUsuarioA').val(alumnos[i].nombreUsuario);
    $('#txtContrasenniaA').val(alumnos[i].contrasennia);
    $('#txtRolA').val(alumnos[i].rol);
    $("#btnActualizarAlum").show();
    $("#btnActualizarAdmin").show();
    $("#btnInsertarAlum").hide();
    $("#btnInsertarAdmin").hide();
};

const registrarUsuario = () =>
        {
            let nombres = document.getElementById('txtNombres').value;
            let apellidos = document.getElementById('txtApellidos').value;
            let contrasennia = document.getElementById('txtContrasennia').value;
            let nombreUsuario = document.getElementById('txtNUsuario').value;

            if ($("#lblusuario").text() == " Usuario no válido") {
                alert("El usuario ya esta en uso o es nulo ingrese otro");
            } else {
                if (nombreUsuario == null || nombreUsuario == "") {
                    alert("Nombre de usuario vacio es necesario llenar este campo");
                } else {
                    let data = {
                        "nombres": nombres,
                        "apellidos": apellidos,
                        "nombreUsuario": nombreUsuario,
                        "contrasennia": contrasennia,
                        "rol": 1
                    };

                    $.ajax(
                            {
                                "url": "api/user/register",
                                "type": "POST",
                                "async": true,
                                "data": data
                            }
                    ).done(function (data) {
                        if (data.error != null) {
                            alert("Error");
                        } else {
                            alert("Usuario registrado con exito");
                            limpiar();
                        }
                    });
                }
            }
        };

const actualizarUsuario = () =>
        {
            let id = $('#txtIdU').val();
            let nombres = $('#txtNombres').val();
            let apellidos = $('#txtApellidos').val();
            let nombreUsuario = $('#txtNUsuario').val();
            let contrasennia = $('#txtContrasennia').val();
            let rol = $('#txtRolU').val();
            let usuario = {"id": id, "nombres": nombres, "apellidos": apellidos, "nombreUsuario": nombreUsuario, "contrasennia": contrasennia, "rol": rol};

            let data = {"usuario": JSON.stringify(usuario)};

            $.ajax(
                    {
                        "url": "api/user/update",
                        "type": "POST",
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

const validarNombre = () =>
        {

            let nombreUsuario = document.getElementById('txtNUsuario').value;
            if (nombreUsuario == null) {
                alert("Nombre de usuario vacio");
            }
            let data = {
                "nombreUsuario": nombreUsuario
            };

            $.ajax(
                    {
                        "url": "api/user/validateNameUser",
                        "type": "POST",
                        "async": true,
                        "data": data
                    }
            ).done(function (data) {
                if (data.error != null) {
                    $('#divNombre').html('<p id="lblusuario" class="text-danger"> Usuario no válido</p>');
                } else {
                    $('#divNombre').html('<p id="lblusuario" class="text-primary"> Usuario  válido</p>');
                }
            });
        };