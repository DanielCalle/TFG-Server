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
        description: "Un ex-almirante de la Marina roba el Dyna Stones, y se cruza en el camino de los Piratas de Sombrero de Paja."
    },
    "e72296b8c7d845c69be9c63bd6a57019": {
        title: "Frozen",
        description: "Una profecía condena al reino de Arandelle a vivir en un invierno eterno. La joven Anna, el temerario montañero Kristoff y el reno Sven deben emprender un viaje épico y lleno de aventuras en busca de Elsa, la hermana de Anna y Reina de las Nieves. Ella es la única que puede poner fin al gélido hechizo."
    },
    "738f2719b6dd4cdf9169fb202fbc7810": {
        title: "El Rey León",
        description: "Tras la muerte de su padre, Simba vuelve a enfrentar a su malvado tío, Scar, y reclamar el trono de rey."
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
