# Proyecto-Plataformas
Proyectos Plataformas - Fabian Prado y Samuel Mejia
Este proyecto es una aplicación de Android enfocada en mostrar información sobre el turismo en Guatemala. Permite a los usuarios explorar diferentes destinos, conocer detalles de cada ubicación, y acceder a información cultural y turística.

Quinta Entrega:
Se ha utilizado NavHost y Nav Controller para poder navegar entre pantallas (Home, Noticia, Welcome).
Además se ha importado el UI Theme con el que se trabajara el proyecto.



Servicios
API de Turismo Local:

Descripción: Simula la provisión de información sobre destinos turísticos, detalles de atracciones y eventos culturales en Guatemala.
Uso en la app: Obtener información de ubicaciones, destinos, y eventos para mostrar a los usuarios. Permite búsquedas y filtrado de destinos turísticos en la app.

DataStore de Android:

Descripción: Utilizado para el almacenamiento local de las preferencias de usuario y datos de inicio de sesión.
Uso en la app: Mantiene la información de autenticación de usuarios para persistencia de sesiones.

Room Database:

Descripción: Base de datos local en SQLite, implementada con Room, para el almacenamiento de datos sobre destinos.
Uso en la app: Almacena los datos sincronizados de destinos turísticos y facilita el acceso offline.
Librerías

Retrofit:

Descripción: Cliente HTTP para manejar peticiones y recibir respuestas en formato JSON.
Uso en la app: Realizar llamadas a la API de Turismo para obtener datos de destinos y detalles.

Room:

Descripción: Biblioteca de persistencia de datos en Android.
Uso en la app: Gestionar una base de datos local que almacena destinos y eventos turísticos para acceso offline.
DataStore:

Descripción: Almacenamiento seguro y sencillo de datos en preferencias.
Uso en la app: Guardar preferencias de usuario, estado de autenticación y configuraciones personales.

Jetpack Compose:

Descripción: Herramienta para diseñar interfaces de usuario declarativas.
Uso en la app: Crear interfaces de usuario intuitivas y visualmente atractivas para el usuario.
