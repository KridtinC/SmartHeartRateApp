const express = require("express")
const router = express.Router()
const userAuthenCtrl = require("../controllers/user_authen_ctrl")

router.get('/', function (req, res, next) {
    res.send("User Authentication API");
});

router.post('/register', userAuthenCtrl.register)

module.exports = router