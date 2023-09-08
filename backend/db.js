const mongoose = require('mongoose');
const  url = require('./mongourl')
const mongoUri = `mongodb+srv://kiran:${url}@cluster0.np63w4z.mongodb.net/`;
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
