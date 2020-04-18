var jwt = require('jsonwebtoken')
var key = require('../config').jwtKey

let verifyToken = (req, res, next) => {
    let token = req.header['x-access-token']

    if (token) {
        var result = jwt.verify(token, key['key'], {
            expiresIn: "24h",
            algorithm: "RS256"
        })

        if(result){
            return {
                result: "Authorized"
            }
        }
        else{
            return {
                result: "Unauthorized"
            }
        }
    }
    else{
        return{
            result: "Token not found"
        }
    }
}