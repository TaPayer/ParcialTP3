# Rick & Morty

Rick & Morty es una aplicación Android que te permite ver la información detallada de los personajes de la serie, y además poder agregar tus favoritos a una lista. 

## Integrantes
- Ferrari Verónica
- Capua Camila
- Osses Macchia Germán
- Payer Tomás
- Da Costa Sofía

## Pre requisitos

Para poder iniciar la aplicación Rick & Morty, es necesario contar con el entorno de desarrollo Android Studio y la versión de Gradle 7.0.2.
(Para consultar su versión de Gradle solo deberá ir a "File - Project Structure" en Android Studio).

La aplicación utiliza la autenticación con Google a través de Firebase Auth para funcionar correctamente.
Para poder configurar Firebase es necesario seguir algunos pasos:

### Configuración de la cuenta de Google en Firebase

1. Acceder a la web de Firebase en "https://firebase.google.com/" e ingresar con su cuenta de Google.
2. Ir a la consola de Firebase en "https://console.firebase.google.com/" o haciendo click en "Ir a la consola".
3. Hacer click en "Agregar proyecto".
4. Ingresar "Rick and Morty" como nombre.
5. Deshabilitar Google Analytics y Crear proyecto.

### Configuración de la app y extracción del certificado de firma SHA-1

1. Dentro de la consola de Firebase agregue una app Android.
2. Como nombre del paquete de Android ingresar "com.ar.ort.rickmorty".
3. Como sobre nombre de la app ingresar "Rick and Morty".
4. Para obtener el certificado de firma SHA-1 dirigirse a Android Studio.
5. Hacer click en el tab "Gradle" y en "Tasks".
6. Dentro buscar "android - signingReport" y hacer click derecho "Run".
7. En la consola se debería haber generado el certificado de firma SHA-1, copiar y pegar el mismo dentro de Firebase.

### Reemplazar el archivo google-services.json

1. Descargar el archivo google-services.json.
2. Cambiar a la vista de Proyecto en Android Studio.
3. Pegar dicho archivo reemplazando el que se encuentra en la carpeta raíz del proyecto.

### Agregar SDK de Firebase

El SDK de Firebase ya debería estar agregado al proyecto, pero en caso de necesitar actualizar la versión deberá seguir las instrucciones propuestas por Google en la siguiente pagina "https://firebase.google.com/docs/admin/setup/". También puede ser de utilidad la información de este link "https://developer.android.com/studio/write/firebase/".

## Instalación

Puedes instalarlo de la siguiente manera:

`git clone https://github.com/TaPayer/ParcialTP3.git`.
