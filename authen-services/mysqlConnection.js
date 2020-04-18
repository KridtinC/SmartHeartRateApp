var mysql = require("mysql");
const config = require('./config.js')
var connectionString = `mysql://${config.database.username}:${config.database.password}@${config.database.hostname}/${config.database.database}?charset=utf8_general_ci`
var pool = mysql.createPool(connectionString);

exports.getConnection = function(callback) {
  pool.getConnection(function(err, conn) {
    if(err) {
      return callback(err);
    }
    callback(err, conn);
  });
};