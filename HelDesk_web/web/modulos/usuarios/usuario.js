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
alert();
                
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

