# vaccination-inventory
_El proyecto detalla una aplicaci贸n para llevar un registro del inventario del estado de vacunaci贸n de los empleados de una empresa._

### Pre-requisitos 馃搵
El presente proyecto esta creado en Java 11 utilizando la version 2.7.4 de Spring Boot

## Ejecuci贸n del proyecto
* Paso 1: Clonar el repositorio de Git: git clone https://github.com/darevaloB/vaccination-inventory.git
* Paso 2: Ingresar a la carpeta del proyecto y encontramos el .jar

### Ejecuci贸n
```
$ cd target/ $ java -jar vaccination-inventory-1.jar
```
## Base de datos 馃摝
* Persistencia de datos: Base de datos Postgresql
* Nombre de la base de datos: vaccination_db
* El archivo vaccination_db.sql se lo ejecuta para crear las tablas de la base de datos
* Adicionalmente se debe modificar el archivo application.properties en el proyecto para indicar el acceso a la base de datos

![foto 4](https://user-images.githubusercontent.com/43796299/205544805-ba830658-31b1-4c9a-aac3-16642d21b42a.PNG)


## Inicio 馃殌
_Para el ingreso del login y obtener el token se debe utilizar la siguiente url:_

* http://localhost:8080/login?username=[usuario]&password=[contrase帽a]
* Por ejemplo: http://localhost:8080/login?username=admin&password=admin1997

## Ejecuci贸n de las pruebas 鈿欙笍
Ya que se ha logrado obtener el token lo ingresamos en http://localhost:8080/swagger-ui/ como se visualiza en la siguiente figura:

![foto3](https://user-images.githubusercontent.com/43796299/205742109-2fb884e9-59a2-4498-8c10-73d50598ab9d.PNG)


## Swagger UI 鈱笍
A continuaci贸n ya se puede hacer uso de la Api Documentation de swagger:

![foto1](https://user-images.githubusercontent.com/43796299/205742025-0a16ce3b-cc72-4df5-8550-f9c98f140434.PNG)


### Pruebas de Funcionamiento  馃搫
Al ejecutar el m茅todo para obtener todos los empleados se nos muestra la informaci贸n que se tiene de la base y esta funcionando correctamente:

![foto 2](https://user-images.githubusercontent.com/43796299/205543779-89eef2f4-00db-42b0-a29e-7195c448a6d7.PNG)




