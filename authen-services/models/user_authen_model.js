var connection = require("../mysqlConnection");

exports.register = async function (email, password, firstName, lastName) {
    return new Promise(async function (resolve, reject) {
        connection.getConnection(async function (err, conn) {
            try {
                var sql = "insert into user (email, password, firstName, lastName)\
                value (?, ?, ?, ?)"
                var params = [email, password, firstName, lastName]
                await conn.query(sql, params, (err, res) => {
                    if (err != null) {
                        reject({
                            result: "err",
                            error: err
                        })
                    }
                    resolve({ result: "OK" })
                })
            } catch (error) {
                reject({
                    result: "err",
                    error: error
                })
            } finally {
                conn.release();
            }
        });
    })
}

exports.login = async function (email, password) {
    return new Promise(async function (resolve, reject) {
        connection.getConnection(async function (err, conn) {
            try {
                var sql = "select u.email, u.password from user u\
                where email = ?"
                var params = [email]
                await conn.query(sql, params, (err, res) => {
                    if (err != null) {
                        reject({
                            result: "err",
                            error: err
                        })
                    }
                    var results = JSON.parse(JSON.stringify(res))
                    if(results.length == 0){
                        reject({
                            result: "err",
                            error: "No user " + email + " in our database"
                        })
                    } else if (results[0]['password'] != password){
                        reject({
                            result: "err",
                            error: "Password incorrect"
                        })
                    } else{
                        resolve({ result: "OK" })
                    }
                })
            } catch (error) {
                reject({
                    result: "err",
                    error: error
                })
            } finally {
                conn.release();
            }
        });
    })
}