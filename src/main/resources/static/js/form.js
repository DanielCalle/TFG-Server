$(() => {
    $("#send").on("click", (event) => {
        $.ajax({
            url: '/film/save',
            type: 'POST',
            data: JSON.stringify({uuid: $('#uuid').val(), 
            name: $('#name').val(), director: $('#director').val(),
            trailerURL: $('#trailerURL').val(), infoURL: $('#infoURL').val(),
            //image: $('#image').val(),
            genre: $('#genre').val(), duration: $('#duration').val(),
            rating: $('#rating').val(), country: $('#country').val()}),
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