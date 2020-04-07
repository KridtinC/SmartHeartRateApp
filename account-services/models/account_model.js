var connection = require("../mysqlConnection");

exports.addElder = async function (firstName, lastName, age, lat, lng) {
    return new Promise(async function (resolve, reject) {
        connection.getConnection(async function (err, conn) {
            try {
                var sql = "insert into elder (firstName, lastName, age, location)\
                value (?, ?, ?, point(?, ?))"
                var params = [firstName, lastName, age, lat, lng]
                await conn.query(sql, params)
                resolve()
            } catch (error) {
                reject(error)
            } finally {
                conn.release();
            }
        });
    })
}