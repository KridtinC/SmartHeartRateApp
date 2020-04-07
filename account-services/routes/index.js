// create new Router instance for api routes
var router = require('express').Router();

router.use('/account', require('./account'));
// router.use('/monitoring', require('./monitoring'));

module.exports = router;