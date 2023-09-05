const jwt = require('jsonwebtoken')
const JWT_SECRET = "THIS$IS&SIGNATURE_KEY"

const fetchuser= (req,res,next) =>{
    const token = req.header('auth-token')
    if(!token){
        res.status(401).send({error:"Please provide valid auth-token"})
    }
    try{
        const data = jwt.verify(token,JWT_SECRET) //validating the token provided by user
        req.user = data.user;
        next()
    }catch(Error){
        res.status(401).send({error:"Please please provide valid auth-token"})
    }
}

module.exports = fetchuser;