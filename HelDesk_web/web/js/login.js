
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
                    console.log(JSON.stringify(data));
                    alert(JSON.stringify(data.JWT.value));                    
                    window.location.replace("main.html");
                }
            });

        };


