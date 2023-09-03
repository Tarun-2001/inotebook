const mongoose = require('mongoose');
const  url = require('./mongourl')
const mongoUri = `mongodb+srv://ftqvwrzrqpeofpwxio:${url}@cluster0.xusiqgr.mongodb.net/?retryWrites=true&w=majority`;
const connectToMongo = async () => {
    try {
        await mongoose.connect(mongoUri, {
            useNewUrlParser: true,
            useUnifiedTopology: true
        });
        console.log("Mongoodb connected successfully");
    } catch (error) {
        console.log("not connected");
        console.log(error)
    }
}

module.exports = connectToMongo;
