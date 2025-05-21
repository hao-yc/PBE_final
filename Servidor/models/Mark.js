const mongoose = require('mongoose'); 

const MarkSchema = new mongoose.Schema({
  uid: { type: String, required: true, index: true },
  subject: { type: String, required: true },
  value: { type: Number, required: true },
  date: { type: Date, default: Date.now }
});

module.exports = mongoose.model('Mark', MarkSchema);
