const mongoose = require('mongoose');
const Timetable = require('./models/Timetable');
const Task = require('./models/Task');
const Mark = require('./models/Mark');
const Student = require('./models/Student');

mongoose.connect('mongodb://localhost:27017/pbe', {
  useNewUrlParser: true,
  useUnifiedTopology: true
}).then(() => {
  console.log('Conectado a MongoDB');
  seedData();
}).catch(err => console.log('Error al conectar con MongoDB', err));

async function seedData() {
  // Limpiar datos anteriores
  await Student.deleteMany({});
  await Timetable.deleteMany({});
  await Task.deleteMany({});
  await Mark.deleteMany({});

  // Crear estudiantes
  const students = await Student.insertMany([
    { uid: '5A5BC301', name: 'Adriana' },
    { uid: 'stu002', name: 'Carlos' },
    { uid: 'stu003', name: 'Júlia' }
  ]);

  // Crear horarios (añadiendo más asignaturas de telecos 3º)
  await Timetable.insertMany([
    { day: 'Mon', hour: '08:00', subject: 'IPAV', room: 'A3001', teacher: 'Olga' },
    { day: 'Mon', hour: '10:00', subject: 'ICOM', room: 'A3002', teacher: 'Toni Pascual' },
    { day: 'Tue', hour: '08:00', subject: 'PBE', room: 'A3201', teacher: 'Francesc Oller' },
    { day: 'Wed', hour: '12:00', subject: 'RP', room: 'A3102', teacher: 'Merce' },
    { day: 'Thu', hour: '14:00', subject: 'DSBM', room: 'A3305', teacher: 'Jordi Salazar' },
    { day: 'Fri', hour: '09:00', subject: 'IXT', room: 'A3004', teacher: 'Marc Rosa' }
  ]);

  // Crear tareas (incluyendo nuevas asignaturas)
  await Task.insertMany([
    { title: 'Ejercicios', description: 'Resolver 10 problemas', date: new Date(), subject: 'IPAV', student_uid: '5A5BC301' },
    { title: 'Lectura cap. 3', description: 'Resumen del capítulo', date: new Date(), subject: 'ICOM', student_uid: '5A5BC301' },
    { title: 'Preparar control', date: new Date(), subject: 'PBE', student_uid: 'stu002' },
    { title: 'Proyecto de red', description: 'Diseñar topología', date: new Date(), subject: 'IXT', student_uid: 'stu003' },
    { title: 'Hacer exmaen de 2020 problema 3', description: 'Ejercicio de codificación', date: new Date(), subject: 'DSBM', student_uid: 'stu002' },
    { title: 'Ejercicio de antenas', description: 'Definir tema y objetivos', date: new Date(), subject: 'RP', student_uid: 'stu003' }
  ]);

  // Crear notas (más asignaturas y estudiantes)
  await Mark.insertMany([
    { subject: 'IPAV', value: 8.5, uid: '5A5BC301' },
    { subject: 'ICOM', value: 7.0, uid: '5A5BC301' },
    { subject: 'PBE', value: 10.0, uid: 'stu002' },
    { subject: 'IXT', value: 9.0, uid: 'stu003' },
    { subject: 'DSBM', value: 6.5, uid: 'stu002' },
    { subject: 'RP', value: 9.5, uid: 'stu003' }
  ]);

  console.log('Datos insertados correctamente');
  mongoose.disconnect();
}
