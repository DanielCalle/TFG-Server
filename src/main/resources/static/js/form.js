$(() => {
    $("#send").on("click", (event) => {
        let film = {
            uuid: $('#uuid').val(), 
            name: $('#name').val(),
            director: $('#director').val(),
            trailerURL: $('#trailerURL').val(),
            infoURL: $('#infoURL').val(),
            imageURL: $('#imageURL').val(),
            genre: $('#genre').val(),
            duration: $('#duration').val(),
            rating: $('#rating').val(),
            country: $('#country').val()};

        $.ajax({
            url: '/films',
            type: 'POST',
            data: film,
            processData: false,
            contentType: "application/json",
            success: function(response) {
                alert(response);
            },
            error: function(response) {
                console.log(response)
            },
            fail: function(response) {
                console.log(response)
            }
        })
        event.preventDefault();
    });
});