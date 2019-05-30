$(() => {

    console.log(data);

    data.forEach(element => {
        $.ajax({
            method: "GET",
            url: "/film/" + element,
            success: function (data, textStatus, jqXHR) {
                if (data) {
                    let line = $("<li></li>");
                    let film = $("<div class='film'></div>");
                    Object.keys(data).forEach(key => {
                        film.append("<p>" + key + ": " + data[key] + "</p>");
                    });
                    line.append(film);
                    $(".list").append(line);
                    console.log(data);
                }
            },
            // En caso de error, mostrar el error producido
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Se ha producido un error: " + errorThrown);
            }
        });

    });
});