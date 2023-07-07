# App


Este repositorio va a contener el docker-compose encargado de levantar los 4 microservicios y va a contener documentación general del proyecto.

Para levantar los microservicios deben estar descargado los 4 repositorios y se debe ejecutar el comando ```docker-compose up``` dentro de esta carpeta



## URLS


Cada microservicio va a estar corriendo internamente en la imagen de docker en el puerto 8080 pero a nuestra computadora los vamos a mapear en diferentes puertos

``` localhost:8080``` - Microservicio Delivery


``` localhost:8081``` - Microservicio Person


``` localhost:8082``` - Microservicio Product


``` localhost:8083``` - Microservicio Seller


## Prueba 


Una vez ejecutado el comando ```docker-compose up``` para chequear que los proyectos estén levantamos podemos ir la url ```localhost:808X/api/ping``` donde la ```X``` debe ser reemplazada por el puerto correspondiente.

Si la url nos responde ```pong``` significa que los proyecyos están funcionando correctamente.