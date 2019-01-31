"use strict"

const express = require("express");
const bodyParser = require("body-parser");
const morgan = require("morgan");
const app = express();

app.use(morgan("dev"));
app.use(bodyParser.urlencoded({ extended: false }));

let movies = {
    "a49581c363b94409badf6bafb4bd15d0": {
        title: "Z",
        description: "hey"
    },
    "e72296b8c7d845c69be9c63bd6a57019": {
        title: "Frozen",
        description: "pelicula"
    },
    "738f2719b6dd4cdf9169fb202fbc7810": {
        title: "El Rey León",
        description: "es un leon"
    }
}

app.get("/", (request, response) => {
    response.json(movies);
});

app.post("/", (request, response) => {
    console.log(request.body);
    response.json(movies[request.body.uuid]);
});

let port = process.env.PORT || 8080;

app.listen(port, (err) => {
    if (err) {
        console.log(err);
    } else {
        console.log("listening at port " + port);
    }
});
