const accountModel = require('../models/elder_account_model')

exports.addElder = async function (req, res) {
    try {
        results = await accountModel.addElder(req.body.firstName, req.body.lastName, req.body.age, req.body.lat, req.body.lng)
        res.send("OK")
    } catch (error) {
        console.log(error)
        res.send("Error")
    }
}