const mongoose = require('mongoose');

module.exports = new mongoose.Schema({
    author: String,
    content: String,
    title: String,
    imageurl: String,
    created_at: Date,
    id: mongoose.Schema.ObjectId
})