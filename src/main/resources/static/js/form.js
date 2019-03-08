$(() => {
    $("#send").on("click", (event) => {
        let formData = new FormData();
        let film = {
            uuid: $('#uuid').val(), 
            name: $('#name').val(),
            director: $('#director').val(),
            trailerURL: $('#trailerURL').val(),
            infoURL: $('#infoURL').val(),
            genre: $('#genre').val(),
            duration: $('#duration').val(),
            rating: $('#rating').val(),
            country: $('#country').val()};

        formData.append("film", JSON.stringify(film));
        formData.append("image", $('#image').val());
        
        $.ajax({
            url: '/film/save',
            type: 'POST',
            data: formData,
            contentType: undefined,
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