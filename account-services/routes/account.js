const express = require("express")
const router = express.Router()
const accountCtrl = require("../controllers/account_ctrl")

router.get('/', function (req, res, next) {
    res.send("Account API");
});

router.post('/add-elder', accountCtrl.addElder)
// router.post('/get-elderlist', accountCtrl.getElderList)

module.exports = router