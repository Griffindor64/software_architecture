let usuarioA;

const cargarAdministrador = () => {
    usuarioA = JSON.parse(sessionStorage.getItem("usuario"));
    $('#txtIdA').val(usuarioA.id);
    $('#txtNombresA').val(usuarioA.nombres);
    $('#txtApellidosA').val(usuarioA.apellidos);
    $('#txtNUsuarioA').val(usuarioA.nombreUsuario);
    $('#txtContrasenniaA').val(usuarioA.contrasennia);
    $('#txtRolA').val(usuarioA.rol);

};

const actualizarte = () =>
        {
            usuarioA.id = $('#txtIdA').val();
            usuarioA.nombres = document.getElementById('txtNombresA').value;
            usuarioA.apellidos = document.getElementById('txtApellidosA').value;
            usuarioA.contrasennia = document.getElementById('txtContrasenniaA').value;
            usuarioA.nombreUsuario = document.getElementById('txtNUsuarioA').value;
            usuarioA.rol = document.getElementById('txtRolA').value;

            let data = {
                "id": usuarioA.id,
                "nombres": usuarioA.nombres,
                "apellidos": usuarioA.apellidos,
                "nombreUsuario": usuarioA.nombreUsuario,
                "contrasennia": usuarioA.contrasennia,
                "rol": usuarioA.rol
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
                    cargarAdministrador();

                } else if (data.error !== null)
                {
                    alert(data.error);
                }

            }
            );

        };

const actualizarUniversidad = () => {

    let nUniversidad = document.getElementById('txtUniversidad');
    let contrasenna = document.getElementById('nueva_contrasena');
    let grupo = document.getElementById('txtGrupo');
    let metodo = document.getElementById('txtMetodo');
    let url = document.getElementById('txtURL');
    token = sessionStorage.getItem("token");
    let data = {
        "nombre_universidad": nUniversidad,
        "nueva_contrasena": contrasenna,
        "grupo": grupo,
        "metodo": metodo,
        "url": url,
        "token": token
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

        console.log(JSON.stringify(data));
        let t = data.replace(/['"]+/g, '');
        sessionStorage.setItem("token", t);
    });
};