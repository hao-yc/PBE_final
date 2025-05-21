const express = require('express');
const cors = require('cors');
const mongoose = require('mongoose');

// Conexión a MongoDB 
mongoose.connect('mongodb://localhost:27017/pbe', { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => console.log('Conectado a MongoDB'))
  .catch(err => console.log('Error al conectar con MongoDB', err));

const app = express();
const PORT = 3000;

// Middleware
app.use(cors({
  origin: '*',
  allowedHeaders: ['Content-Type', 'uid']
}));

app.use(express.json()); // Para que el backend entienda las peticiones con JSON

// Rutas
const apiRoutes = require('./routes/rutas');
app.use('/', apiRoutes);

// Arrancar servidor
app.listen(PORT, '0.0.0.0', () => {
  console.log(`Servidor escuchando en http://0.0.0.0:${PORT}`);
});

