var express = require('express')
var app = express()
var http = require("http")
var mysql = require('mysql')
const config = require('./config.js')
const PORT = process.env.PORT || 3000


// connect with database
var database_connection = mysql.createConnection({
    host : config.database.hostname,
    user : config.database.username,
    password : config.database.password,
    database : config.database.database,
    port : config.database.port
})
database_connection.connect();
// -----------------------------

app.use('/api')

app.listen(PORT,()=>console.log(`This app listerning at http://localhost:${PORT}`))