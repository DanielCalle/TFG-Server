"use strict"

$(() => {
    $.ajax({
        method: "GET",
        url: "/factorial",
        data: {
            num: "hell"
        },
        success: function (data, textStatus, jqXHR) {
            console.log(data);
            console.log(textStatus);
            console.log(jqXHR);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
});