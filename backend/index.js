const connectToMongoo = require("./db")
const express = require('express')

connectToMongoo();  
const app = express()
const port = 5000
app.use(express.json())
app.get('/',(req,res)=>{
    res.send(`Successfully connected to  ${port}`)
})

app.use('/api/auth',require('./routes/auth.js'))
app.use('/api/note',require('./routes/notes.js'))

app.listen(port,()=>{
    console.log("listening")
})