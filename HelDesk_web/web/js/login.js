
window.onload = function () {
    $("#error").hide();
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
                    alert("Iniciaste sesión correctamente");
                    window.location.replace("gestion/libro.html");
                }
            });

        };


