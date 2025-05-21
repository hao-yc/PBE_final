const mongoose = require('mongoose');

const TaskSchema = new mongoose.Schema({
  title: { type: String, required: true },
  description: String,
  date: { type: Date, required: true, get: v => v.toISOString().split('T')[0]  }, // hemos añadido la fecha
  subject: String
});

module.exports = mongoose.model('Task', TaskSchema);


