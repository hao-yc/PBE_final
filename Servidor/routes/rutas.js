const express = require('express');
const router = express.Router();
const controller = require('../controllers/controllers');

// Rutas públicas
router.get('/timetables', controller.getTimetables);
router.get('/tasks', controller.getTasks);
router.get('/user/:uid', controller.getUserByUid); // Obtener nombre públicamente

// Autenticación para las siguientes rutas
router.use(controller.authMiddleware);

// Rutas protegidas
router.get('/marks', controller.getMarks);
router.get('/me', controller.getMe); // Obtener UID y nombre del usuario autenticado

module.exports = router;
