var connection = require("../mysqlConnection");

exports.addElder = async function (firstName, lastName, age, lat, lng, email) {
    return new Promise(async function (resolve, reject) {
        connection.getConnection(async function (err, conn) {
            try {
                var sql = "insert into elder (firstName, lastName, age, location)\
                value (?, ?, ?, point(?, ?))"
                var params = [firstName, lastName, age, lat, lng]
                await conn.query(sql, params, async (err, res) => {
                    if (err != null) {
                        reject({
                            result: "err",
                            error: err
                        })
                    }
                    sql = "insert into User_Elder_List (email_user, deviceID_elder)\
                    value (?, ?)"
                    params = [email, res.insertId]
                    await conn.query(sql, params, (err, res) => {
                        if (err != null) {
                            reject({
                                result: "err",
                                error: err
                            })
                        }
                        resolve({ result: "OK" })
                    })
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

exports.getElderList = async function (email) {
    return new Promise(async function (resolve, reject) {
        connection.getConnection(async function (err, conn) {
            try {
                var sql = "select e.deviceID, e.firstName, e.lastName, e.age, ST_X(e.location) as lat, ST_Y(e.location) as lng \
                from elder e inner join user_elder_list ue on e.deviceID = ue.deviceID_elder\
                where ue.Email_User = ?"
                var params = [email]
                await conn.query(sql, params, (err, res) => {
                    if (err != null) {
                        reject({
                            result: "err",
                            error: err
                        })
                    }
                    var results = JSON.parse(JSON.stringify(res))
                    resolve({ result: results })
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