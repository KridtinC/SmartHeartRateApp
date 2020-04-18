var express = require('express')
var app = express()
const bodyParser = require("body-parser");

const PORT = process.env.PORT || 5001
const HOSTNAME = process.env.IP || "localhost"

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/', function (req, res, next) {
    res.send("This authen-services are running on express-node.js");
});

app.use('/api', require('./routes/index'));

app.listen(PORT,()=>console.log(`This app listening at http://${HOSTNAME}:${PORT}`))