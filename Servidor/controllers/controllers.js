const Timetable = require('../models/Timetable');
const Task = require('../models/Task');
const Mark = require('../models/Mark');
const Student = require('../models/Student');

// Middleware de autenticación
exports.authMiddleware = async (req, res, next) => {
  const uid = req.headers['uid'];
  if (!uid) return res.status(401).json({ error: 'Falta UID' });

  const student = await Student.findOne({ uid });
  if (!student) return res.status(403).json({ error: 'UID no registrado' });

  req.student = student;
  next();
};

// (público)
exports.getTimetables = async (req, res) => {
  const filter = parseQuery(req.query);
  const limit = parseInt(req.query.limit) || null;
  const data = await Timetable.find(filter)
    .sort({ day: 1, hour: 1 })
    .limit(limit);
  res.json(data);
};

// (público)
exports.getTasks = async (req, res) => {
  const filter = parseQuery(req.query);
  const tasks = await Task.find(filter).sort({ date: 1 });
  res.json(tasks);
};

//  (protegido)
exports.getMarks = async (req, res) => {
  const filter = { student_uid: req.student.uid, ...parseQuery(req.query) };
  const marks = await Mark.find(filter).sort({ subject: 1 });
  res.json(marks);
};

// GET /me (usuario autenticado)
exports.getMe = (req, res) => {
  res.json({ uid: req.student.uid, name: req.student.name });
};

// GET /user/:uid (público)
exports.getUserByUid = async (req, res) => {
  const { uid } = req.params;
  const student = await Student.findOne({ uid });
  if (!student) return res.status(404).json({ error: 'Usuario no encontrado' });
  res.json({ name: student.name });
};

// para convertir parámetros de consulta a formato MongoDB
function parseQuery(query) {
  const result = {};
  for (const key in query) {
    if (key === 'limit') continue;
    if (key.includes('[')) {
      const [field, op] = key.split(/\[|\]/);
      const value = query[key] === 'now'
        ? (field === 'date' ? new Date() : undefined)
        : query[key];
      if (!result[field]) result[field] = {};
      result[field][`$${op}`] = value;
    } else {
      result[key] = query[key];
    }
  }
  return result;
}
