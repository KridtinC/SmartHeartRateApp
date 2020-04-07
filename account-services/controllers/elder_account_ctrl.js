const elderAccountModel = require('../models/elder_account_model')

exports.addElder = async function (req, res) {
    try {
        results = await elderAccountModel.addElder(req.body.firstName, req.body.lastName, req.body.age, req.body.lat, req.body.lng, req.body.email)
        res.send(results)
    } catch (error) {
        console.log(error)
        res.status(500)
        res.send(error)
    }
}

exports.getElderList = async function (req, res) {
    try {
        results = await elderAccountModel.getElderList(req.body.email)
        res.send(results)
    } catch (error) {
        console.log(error)
        res.status(500)
        res.send(error)
    }
}