let alumnos = null;

const cargarUsuAlumnos = () =>
        {
            
            let data = {
                "rol": "2"
            };
            
            $.ajax(
                    {
                        "url": "api/user/getAll",
                        "type": "GET",
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
                        contenido += "<td> <button class='btn btn-outline-danger' onclick='eliminarLibro(" + i + ");'><i class='fa fa-trash'></i></button> </td>";
                        contenido += "<td> <button class='btn btn-outline-primary' onclick='mostrarLibro(" + i + ")'><i class='fa fa-pencil-alt'></i></button> </td>";
                        contenido += "</tr>";
                    }
                    $("#tbodyLibro").html(contenido);
                }
            }
            );
        };
        
const cargarUsuAdministradores = () =>
        {
            let data = {
                "rol": "1"
                
            };
            
            $.ajax(
                    {
                        "url": "api/user/getAll",
                        "type": "GET",
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
                        contenido += "<td> <button class='btn btn-outline-danger' onclick='eliminarLibro(" + i + ");'><i class='fa fa-trash'></i></button> </td>";
                        contenido += "<td> <button class='btn btn-outline-primary' onclick='mostrarLibro(" + i + ")'><i class='fa fa-pencil-alt'></i></button> </td>";
                        contenido += "</tr>";
                    }
                    $("#tbodyLibro").html(contenido);
                }
            }
            );
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
                "apellidos" : apellidos,
                "nombreUsuario": nombreUsuario,
                "contrasennia": contrasennia,
                "rol" : 1
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

const validarNombre =() =>
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