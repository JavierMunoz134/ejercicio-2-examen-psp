# ejercicio-2-examen-psp
 
Este proyecto es una aplicación Java desarrollada con Maven. La aplicación se encarga de descargar y comprimir archivos desde URLs proporcionadas.  

Características

Descarga de archivos desde URLs.
Compresión de los archivos descargados en un archivo .zip.
Manejo de excepciones para URLs inválidas y archivos no encontrados.

Código Principal

El código principal del proyecto se encuentra en las clases DownloaderAndZipper.java y Listener.java.  DownloaderAndZipper.java es responsable de descargar los archivos desde las URLs proporcionadas y comprimirlos en un archivo .zip. Esta clase maneja las excepciones java.net.MalformedURLException y java.io.FileNotFoundException para asegurar que las URLs sean válidas y que los archivos existan antes de intentar descargarlos o comprimirlos. Listener.java es una interfaz que define un método update que se ejecutará cuando el observable notifique a los observadores.

Cómo Ejecutar

Una vez que la aplicación esté en ejecución, te pedirá que introduzcas una URL. Esta URL se añadirá a una lista de URLs para descargar. Puedes introducir tantas URLs como desees. Cuando hayas terminado de introducir URLs, escribe 'salir' y la aplicación comenzará a descargar los archivos de las URLs proporcionadas y a comprimirlos en un archivo .zip.