# PBE_final
Proyecto avanzado de Ingeniería

Descripción del proyecto

Este proyecto consiste en el desarrollo de un sistema completo que integra hardware y software para ofrecer información académica personalizada a través de una tarjeta NFC. El objetivo es permitir que cualquier usuario pueda consultar fácilmente sus datos (como horarios, tareas y calificaciones) simplemente acercando su tarjeta identificadora a un lector conectado a un dispositivo.

Funcionamiento general

El sistema se compone de dos partes principales:
	•	Cliente (dispositivo físico): Utiliza un lector NFC conectado a una Raspberry Pi para leer la identificación única (UID) del usuario. Esta información se muestra inicialmente en una pantalla LCD y se envía al servidor mediante una solicitud HTTP.
	•	Servidor (backend y frontend): Recibe el UID, consulta una base de datos MongoDB y devuelve la información personalizada correspondiente. Estos datos se muestran a través de una interfaz web o una aplicación móvil, que ofrece una experiencia interactiva y accesible.

Tecnologías y herramientas utilizadas
	•	Hardware: Raspberry Pi, lector NFC Elechouse, pantalla LCD.
	•	Cliente: Ruby con GTK para la interfaz gráfica.
	•	Servidor: Node.js, Express y MongoDB para el manejo de la API y los datos.
	•	Interfaz web: HTML, CSS y JavaScript.
	•	Aplicación móvil: Desarrollada con arquitectura Clean Architecture, Jetpack Compose, Retrofit y Hilt (Android).

Mejoras implementadas
	•	Separación del código en clases específicas para la lectura NFC y las peticiones HTTP.
	•	Manejo centralizado de errores y reintentos automáticos para mayor robustez.
	•	Interfaz gráfica adaptable que permite consultar distintas tablas dinámicamente.
	•	Control de sesión mediante temporizador de inactividad y limpieza automática de la interfaz.

Resultado final

El sistema desarrollado permite la conexión exitosa entre cliente y servidor, logrando leer el UID de una tarjeta NFC y mostrar la información académica correspondiente. Aunque ha habido dificultades técnicas durante el desarrollo, el resultado es un prototipo funcional que demuestra la viabilidad del concepto y proporciona una base sólida para futuras mejoras o ampliaciones