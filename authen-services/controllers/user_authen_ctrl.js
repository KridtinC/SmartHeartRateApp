const userAuthenModel = require('../models/user_authen_model')

exports.register = async function (req, res) {
    try {
        results = await userAuthenModel.register(req.body.email, req.body.password, req.body.firstName, req.body.lastName)
        res.send(results)
    } catch (error) {
        console.log(error)
        res.status(403)
        res.send(error)
    }
}

exports.login = async function (req, res) {
    try {
        results = await userAuthenModel.login(req.body.email, req.body.password)
        res.send(results)
    } catch (error) {
        console.log(error)
        res.status(403)
        res.send(error)
    }
}