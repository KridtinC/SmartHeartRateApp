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