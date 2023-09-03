const express = require('express')
const User = require('../models/User')
const router = express.Router()
const { body, validationResult } = require('express-validator');

//Creating the user
router.post('/createUser',[
    body('name','Enter valid name ').isLength({min:3}),
    body('email','Enter valid email').isEmail(),
    body('password','Password must be minimum 5 characters').isLength({min:5})
],async (req,res)=>{
    //If there are error return bad request
    const err = validationResult(req);
    if (!err.isEmpty()) {
    return res.status(400).json({err:err.array()})
    }
    //Check whether user with same email exist or not
    try{
        let user = await User.findOne({email:req.body.email})// findone method returns null if no ele found .
        if(user) {
            return res.status(400).json({error : "This Email is already is been used"})
        }
        user = await User.create({
            name : req.body.name,
            email : req.body.email,
            password : req.body.password
        })
        res.send(user)
    }
    // catching errors
    catch(error){
        res.status(500).send("Error occured")
        console.error(error.message)
    }
    
})

module.exports = router