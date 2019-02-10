$(() => {
    $("#send").on("click", (event) => {
        $.ajax({
            url: '/film/save',
            type: 'POST',
            data: JSON.stringify({uuid: $('#uuid').val(), 
                name: $('#name').val(), idDirector: $('#idDirector').val(),
            trailer: $('#trailer').val(), description: $('#description').val(),
            genre: $('#genre').val(), duration: $('#duration').val(),
            valoration: $('#valoration').val(), country: $('#country').val()}),
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