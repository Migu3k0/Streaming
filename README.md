[README.md](https://github.com/user-attachments/files/29319803/README.md)
nombre proyecto: Streaming
Asignatura DSY1103 Desarrollo Fullstack
integantes: Esteban Orellana
            Miguel Gonzalez
            Cristobal Villa

Descripcion del Proyecto:

Sistema de backend diseñado para un streaming, cuya arquitectura esta formada por
distintos microservicios que ayudan a cumplir con las necesidades del sistema.

Este proyecto implementa microservicios que crean, guardan, actualizan, eliminan y ejecutan informacion de distintos
objetos como episodios, series, categorias, estudios, etc. Utilizando pruebas de integración mediante POSTMAN, ademas de compartir
esta misma información entre algunos microservicios mediante Feign Client, con un rastro de versiones por parte Flyway Migrations.

Microservicios implementados:
1. ms-categorias
2. ms-usuario
3. ms-perfil
4. ms-favoritos
5. ms-estudio
6. ms-pelicula
7. ms-series
8. ms-suscripciones
9. ms-historial
10. ms-episodios

Pasos para ejecutar:

Tenga instalado Laragon MySQL y Postman.
Encienda la base de datos en Laragon, unicamente habilitando MySQL en configuraciones.
Inicie todos los microservicios (desde el archivo java Ms[nombre]Application) haciendo uso de su IDE de confianza.
Dentro del respectivo Controller de cada microservicio ubique @RequestMapping, copie su url y peguela en 'Enter request URL' de Postman.
Para cada accion o prueba que quiera realizar mediante Postman, utilice ya sea por ejemplo PUT, GET, DELETE, y escribiendo su respectiva URL del método, o también, entrando a enlaces swagger:
http://localhost:8095/doc/swagger-ui/index.html
http://localhost:8094/doc/swagger-ui/index.html
http://localhost:8093/doc/swagger-ui/index.html
http://localhost:8097/doc/swagger-ui/index.html
http://localhost:8086/doc/swagger-ui/index.html
http://localhost:8090/doc/swagger-ui/index.html
http://localhost:8087/doc/swagger-ui/index.html
http://localhost:8098/doc/swagger-ui/index.html
http://localhost:8096/doc/swagger-ui/index.html
http://localhost:8088/doc/swagger-ui/index.html


