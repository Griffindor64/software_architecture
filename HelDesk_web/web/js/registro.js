const registrarUsuario = () =>
{
    let nombres = document.getElementById('txtNombres').value;
    let apellidos = document.getElementById('txtApellidos').value;
    let contrasennia = document.getElementById('txtContrasennia').value;
<<<<<<< HEAD
    let nombreUsuario = document.getElementById('txtNUsuario').value;
=======
    let nombreUsuario = document.getElementById('txtContrasennia').value;
>>>>>>> 35123ea89056266a7b584197088d2e24aa2649ab

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

const limpiar = () =>
{
    document.getElementById("txtNUsuario").value = "";
    document.getElementById("txtContrasennia").value = "";
    $('#divNombre').html('<p id="lblusuario" class="text-danger"> Usuario no válido</p>');
};