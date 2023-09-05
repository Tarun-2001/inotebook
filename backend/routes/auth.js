const express = require('express') // Importing express
const User = require('../models/User') // Importing user schema
const bcrypt = require("bcrypt")  // Importing bcrypt for password hashing      
const jwt = require('jsonwebtoken');    // Importng jwt for token type authentication
const { body, validationResult } = require('express-validator'); //Importing validator dependencies
const fetchuser = require('../middleware/fetchuser')

const router = express.Router()
const JWT_SECRET = "THIS$IS&SIGNATURE_KEY"
// ROUTE:1 Creating the user
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
        //Encrypting the passowrd. 
        const salt = await bcrypt.genSalt(10);
        const encryptedPassword = await bcrypt.hash(req.body.password,salt);
        user = await User.create({
            name : req.body.name,
            email : req.body.email,
            password : encryptedPassword
        })
        const data = {
            user:{
                id:user.id
            }
        }
        const authToken = jwt.sign(data,JWT_SECRET);
        res.send(authToken)
    }
    // catching errors
    catch(error){
        res.status(500).send("Error occured")
        console.error(error.message)
    }
    
})
//ROUTE 2 : Authenticating the user with credentitals 
router.post('/login',[
    body('email','Enter valid email').isEmail(),
    body('password','Password should not be blank').exists({min:5})
    
],async (req,res)=>{
    //If there are error return bad request
    const err = validationResult(req);
    if (!err.isEmpty()) {
    return res.status(400).json({err:err.array()})
    }
    try{
        const {email,password} = req.body;
        let user = await User.findOne({email});
        if(!user){
            return res.status(400).json({error:"Inccorect email and password"})
        }
        const pwd = await bcrypt.compare(password,user.password)
        if(!pwd){
            return res.status(400).json({error:"Inccorect email and password"})
        }
        const data = {
            user:{
                id:user.id
            }
        }
        // Generating the auth token
        const authToken = jwt.sign(data,JWT_SECRET);
        res.json({token:authToken})
    }//Catching errors
    catch(error){
        res.status(500).send("Error occured") 
        console.error(error.message)
    }

})

// ROUTE 3 : Get user details if exits
router.get('/fetchUser', fetchuser,async (req,res)=>{

   try{
        let userId = req.user.id // id is from fetchuser file 
        const user = await User.findById(userId).select("-password"); // Finding user by id 
        res.send(user)
    }//Catching errors
    catch(error){
        res.status(500).send("Error occured") 
    }

})
module.exports = router