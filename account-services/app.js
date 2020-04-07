var express = require('express')
var app = express()
var http = require("http")
const bodyParser = require("body-parser");
var mysql = require('mysql')

const PORT = process.env.PORT || 5000

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
// connect with database
// var connectionString = `mysql://${config.username}:${config.password}@${config.hostname}/${config.database}?charset=utf8_general_ci&timezone=-0700`
// var database_connection = mysql.createConnection(connectionString)
// database_connection.connect();

// -----------------------------

app.get('/', function (req, res, next) {
    res.send("This server running on express-node.js");
});

app.use('/api', require('./routes/index'));

app.listen(PORT,()=>console.log(`This app listening at http://localhost:${PORT}`))