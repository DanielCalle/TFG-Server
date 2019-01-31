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
        description: 'Un grupo llamado "Neo Marines" con Z como líder, irrumpen en una fortaleza de la Marina y roban las "Dyna Stone". Kizaru aparece ante ellos y habla con Z. Ambos pelean hasta que Z usa una Dyna Stone, que causa una explosión. Más tarde, Z es rescatado por Luffy y su tripulación, que le acogen en su barco y le curan. Sin embargo, cuando se entera de que son piratas, Z les empieza a atacar, junto a su tripulación. Tras ser derrotados, Luffy y su tripulación van a una isla a conseguir información y se dividen en 2 grupos: Chopper, Robin, Usopp y Nami van al bar de la isla para obtener información de Z; Luffy, Zoro, Sanji y Brook van a unas aguas termales para descansar; Franky se queda en el Sunny para repararlo y prepararse para la batalla. En las aguas termales, se encuentran con el ex-Almirante Aokiji. Él les cuenta a su banda que Z y su plan. Según Aokiji, quiere acabar con la Gran Era de la Piratería disparando a los 3 End Points de ciertas islas para provocar una erupción de magma gigantesca en el Nuevo Mundo. La existencia de los End Points es secreta para la gente normal (una leyenda urbana), sólo los oficiales de alto rango de la Marina y del Gobierno Mundial conocen la verdad.'
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
