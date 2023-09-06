const express = require('express'); // Importing express
const Notes = require('../models/Notes');
const fetchuser = require('../middleware/fetchuser')
const { body, validationResult } = require('express-validator'); //Importing validator dependencies
const router = express.Router()

//ROUTE 1 : Fetching all notes by user token
router.get('/fetchNotes',fetchuser,async (req,res)=>{
    try{
        const notes = await Notes.find({user:req.user.id}) // fetching all notes by user id 
        res.send(notes)
    }//Catching errors
    catch(error){
        res.status(500).send("Error occured") 
    }
})

//ROUTE 2 : Add notes by user 

router.post('/addNotes',fetchuser,[
    body('title','Enter valid title name').isLength({min:3}),
    body('description','Description should be more').exists({min:5})
],async (req,res)=>{

    //If there are error return bad request
    const err = validationResult(req);
    if (!err.isEmpty()) {
    return res.status(400).json({err:err.array()})
    }
    try{
        const {title,description,tag,data} =req.body
        const notes = new Notes({
            title:title,
            description:description,
            user:req.user.id
        })
        notes.save()
        res.send(notes)
    }//Catching errors
    catch(error){
        res.status(500).send("Error occured") 
    }
})

//ROUTE 3 : Update the notes by taking id

router.put('/updateNotes/:id',fetchuser,async (req,res)=>{

    try{
        
        const usernotes = await Notes.findById(req.params.id)
        if(!usernotes) return res.status(404).send({error:"Please provide the valid id"})
        if(usernotes.user.toString()!==req.user.id){
            return res.status(404).send({error:"Operation not Allowed"})
        }
        const {title,description,tag} = req.body
        const newnote = {}
        if(title) {newnote.title=title}
        if(description){newnote.description=description}
        if(tag){newnote.tag=tag}
        let result = await Notes.findByIdAndUpdate(req.params.id,{$set:newnote},{new:true})
        res.json(result)
    }//Catching errors
    catch(error){
        res.status(500).send("Error occured in updating") 
    }
})


module.exports = router