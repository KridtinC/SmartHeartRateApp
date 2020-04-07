// create new Router instance for api routes
var router = require('express').Router();

router.use('/elder-account', require('./elder_account'));
// router.use('/user-account', require('./user_account'));

module.exports = router;