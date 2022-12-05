let usuarioB = null;

const cargarAlumno = () => {
    usuarioB = JSON.parse(sessionStorage.getItem("usuario"));
    $('#txtIdA').val(usuarioB.id);
    $('#txtNombresA').val(usuarioB.nombres);
    $('#txtApellidosA').val(usuarioB.apellidos);
    $('#txtNUsuarioA').val(usuarioB.nombreUsuario);
    $('#txtContrasenniaA').val(usuarioB.contrasennia);
    $('#txtRolA').val(usuarioB.rol);

};

const actualizarAlumno = () =>
        {
            usuarioB.id = $('#txtIdA').val();
            usuarioB.nombres = document.getElementById('txtNombresA').value;
            usuarioB.apellidos = document.getElementById('txtApellidosA').value;
            usuarioB.contrasennia = document.getElementById('txtContrasenniaA').value;
            usuarioB.nombreUsuario = document.getElementById('txtNUsuarioA').value;
            usuarioB.rol = document.getElementById('txtRolA').value;

            let data = {
                "id": usuarioB.id,
                "nombres": usuarioB.nombres,
                "apellidos": usuarioB.apellidos,
                "nombreUsuario": usuarioB.nombreUsuario,
                "contrasennia": usuarioB.contrasennia,
                "rol": usuarioB.rol
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
                    sessionStorage.setItem("usuario", JSON.stringify(usuarioA));
                    cargarAlumno();

                } else if (data.error !== null)
                {
                    alert(data.error);
                }

            }
            );

        };
