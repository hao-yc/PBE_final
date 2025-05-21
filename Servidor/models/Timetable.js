const mongoose = require('mongoose');

const TimetableSchema = new mongoose.Schema({
  day: String,
  hour: String,
  subject: String,
  room: String,
  teacher: String
});

module.exports = mongoose.model('Timetable', TimetableSchema);
