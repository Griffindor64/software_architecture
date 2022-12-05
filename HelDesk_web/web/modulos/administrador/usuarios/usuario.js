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
    $('#btnActualizarAlum').hide();
    $('#btnInsertarAlum').show();
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

const registrarUsuario = (rol) =>
        {
            let nombres, apellidos, contrasennia, nombreUsuario;
            if (rol == 2) {
                nombres = document.getElementById('txtNombres').value;
                apellidos = document.getElementById('txtApellidos').value;
                contrasennia = document.getElementById('txtContrasennia').value;
                nombreUsuario = document.getElementById('txtNUsuario').value;
            } else {
                nombres = document.getElementById('txtNombresA').value;
                apellidos = document.getElementById('txtApellidosA').value;
                contrasennia = document.getElementById('txtContrasenniaA').value;
                nombreUsuario = document.getElementById('txtNUsuarioA').value;
            }

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
                        "rol": rol
                    };

                    $.ajax(
                            {
                                "url": "api/user/register",
                                "type": "POST",
                                "async": true,
                                "data": data
                            }
                    ).done(data =>
                    {
                        if (data.error != null) {
                            alert("Error");
                        } else {
                            alert("Usuario registrado con exito");
                            cargarUsuAlumnos();
                            cargarUsuAdministradores();
                            limpiarUsuario();
                        }
                    });
                }
            }
        };

const actualizarUsuario = (rol) =>
        {
            let id, nombres, apellidos, contrasennia, nombreUsuario;
            if (rol == 2) {
                id = $('#txtIdU').val();
                nombres = document.getElementById('txtNombres').value;
                apellidos = document.getElementById('txtApellidos').value;
                contrasennia = document.getElementById('txtContrasennia').value;
                nombreUsuario = document.getElementById('txtNUsuario').value;
            } else {
                id = $('#txtIdA').val();
                nombres = document.getElementById('txtNombresA').value;
                apellidos = document.getElementById('txtApellidosA').value;
                contrasennia = document.getElementById('txtContrasenniaA').value;
                nombreUsuario = document.getElementById('txtNUsuarioA').value;
            }
            let data = {
                "id": id,
                "nombres": nombres,
                "apellidos": apellidos,
                "nombreUsuario": nombreUsuario,
                "contrasennia": contrasennia,
                "rol": rol
            };
            alert(JSON.stringify(data));
            $.ajax(
                    {
                        "url": "api/user/update",
                        "type": "POST",
                        "async": true,
                        "data": data
                    }
            ).done(data =>
            {
                if (data.error == null)
                {
                    alert("Modificación exitosa");
                    cargarUsuAlumnos();
                } else if (data.error !== null)
                {
                    alert(data.error);
                }
                limpiarUsuario();
            }
            );
        };

const validarNombreU = (rol) =>
        {
            let nombreUsuario;
            if (rol == 2) {
                nombreUsuario = document.getElementById('txtNUsuario').value;
            } else {
                nombreUsuario = document.getElementById('txtNUsuarioA').value;
            }
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

const limpiarUsuario = () => {
    $('#txtIdU').val("");
    $('#txtNombres').val("");
    $('#txtApellidos').val("");
    $('#txtNUsuario').val("");
    $('#txtContrasennia').val("");
    $('#txtRolU').val("");
    $('#txtIdA').val("");
    $('#txtNombresA').val("");
    $('#txtApellidosA').val("");
    $('#txtNUsuarioA').val("");
    $('#txtContrasenniaA').val("");
    $('#txtRolA').val("");
    
    $('#btnActualizarAlum').hide();
    $('#btnInsertarAlum').show();
    $('#btnActualizarAdmin').hide();
    $('#btnInsertarAdmin').show();

};