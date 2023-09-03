const express = require('express')
const User = require('../models/User')
const router = express.Router()
const { body, validationResult } = require('express-validator');

router.get('/',[
    body('name','Enter valid name ').isLength({min:3}),
    body('email','Enter valid email').isEmail(),
    body('password','Password must be minimum 5 characters').isLength({min:5})
],async (req,res)=>{
    const err = validationResult(req);
    if (!err.isEmpty()) {
    return res.status(400).json({err:err.array()})
    }
  const user = new User(req.body)
  user.save()
  res.send(user)
})


module.exports = router