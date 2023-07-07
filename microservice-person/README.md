# Patrones de Arquitectura - UNLP - Grupo 2
## VARIABLES DE ENTORNO

Se debe crear un .env en el root del proyecto con las siguientes variables de entorno
```
SECRET_KEY=MySecretKeyHere
ENVRIOMENT=dev
CART_URL=http://cart:8080
PRODUCT_URL=http://product:8080
DELIVERY_URL=http://delivery:8080
```

## SWAGGER

La url para entrar a swagger es la siguiente:  
_para que esto funcione se debe tener configurado la variable ```ENVIORMENT``` en el ```.env``` como ```dev```_

```sh
http://localhost:8080/swagger-ui.html
```