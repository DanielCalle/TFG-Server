$(() => {

    data.forEach(element => {
        $.ajax({
            method: "GET",
            url: "/film/" + element,
            success: function (data, textStatus, jqXHR) {
                console.log(data);
            },
            // En caso de error, mostrar el error producido
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Se ha producido un error: " + errorThrown);
            }
        });

    });
});