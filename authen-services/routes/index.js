// create new Router instance for api routes
var router = require('express').Router();

router.use('/user-authen', require('./user_authen'));
// router.use('/elder-authen', require('./elder_authen'));

module.exports = router;