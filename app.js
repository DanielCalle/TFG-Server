"use strict"

const express = require("express");
const bodyParser = require("body-parser");
const morgan = require("morgan");
const app = express();

app.use(morgan("dev"));
app.use(bodyParser.urlencoded({ extended: false }));

let op = {
    "8e4115c194c545c991b6fea51581cfa8": "Golden Pound Usopp"
}

app.get("/", (request, response) => {
    response.json({
        data: op
    });
});

app.post("/", (request, response) => {
    console.log(request.body);
    response.json({
        data: op[request.body.uuid]
    });
});

let port = process.env.PORT || 8080;

app.listen(3000, (err) => {
    if (err) {
        console.log(err);
    } else {
        console.log("listening at port 3000");
    }
});