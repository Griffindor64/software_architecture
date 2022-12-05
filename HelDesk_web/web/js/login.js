
window.onload = function () {
    $("#error").hide();
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
        sessionStorage.setItem("token", JSON.stringify(data));
    });
};

const loginUsuario = () =>
{
    var nombreUsuario = document.getElementById('txtNUsuario').value;
    var contrasennia = document.getElementById('txtContrasennia').value;

    var data = {
        "nombreUsuario": nombreUsuario,
        "contrasennia": contrasennia
    };

    $.ajax(
            {
                "url": "api/user/login",
                "type": "POST",
                "async": true,
                "data": data
            }
    ).done(function (data) {
        if (data.error != null) {
            $("#error").show();
            $("#error").html("Hubo un error verifica que los datos esten bien e intentalo de nuevo");

        } else {
            generarToken();
            sessionStorage.setItem("usuario", JSON.stringify(data));
                    
            if (data.rol == 1)
                window.location.replace("main.html");
            else
                window.location.replace("mainAlumno.html");
        }
    });

};




