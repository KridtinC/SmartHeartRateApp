const express = require("express")
const router = express.Router()
const elderAccountCtrl = require("../controllers/elder_account_ctrl")

router.get('/', function (req, res, next) {
    res.send("Elder Account API");
});

router.post('/add-elder', elderAccountCtrl.addElder)
router.post('/get-elderlist', elderAccountCtrl.getElderList)

module.exports = router