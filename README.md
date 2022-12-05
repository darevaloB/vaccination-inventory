# vaccination-inventory
_El proyecto detalla una aplicación para llevar un registro del inventario del estado de vacunación de los empleados._

### Pre-requisitos 📋
El presente proyecto esta creado en Java 11 utilizando la version 2.7.4 de Spring Boot

## Ejecución del proyecto
* Paso 1: Clonar el repositorio de Git: git clone https://github.com/darevaloB/vaccination-inventory.git
* Paso 2: Ingresar a la carpeta del proyecto y encontramos el .jar

### Ejecución
```
$ cd target/ $ java -jar vaccination-inventory-1.jar
```
## Base de datos 📦
* Persistencia de datos: Base de datos Postgresql
* Nombre de la base de datos: vaccination_db
* El archivo vaccination_db.sql se lo ejecuta para crear las tablas de la base de datos
* Adicionalmente se debe modificar el archivo application.properties en el proyecto para indicar el acceso a la base de datos

![foto 4](https://user-images.githubusercontent.com/43796299/205544805-ba830658-31b1-4c9a-aac3-16642d21b42a.PNG)


## Inicio 🚀
_Para el ingreso del login y obtener el token se debe utilizar la siguiente url:_

* http://localhost:8080/api/login?username=[usuario]&password=[contraseña]
* Por ejemplo: http://localhost:8080/api/login?username=admin123&password=rooti123

## Ejecución de las pruebas ⚙️
Ya que se ha logrado obtener el token lo ingresamos en http://localhost:8080/swagger-ui/ como se visualiza en la siguiente figura:

![foto 3](https://user-images.githubusercontent.com/43796299/205544050-49e05589-8f5b-4e4a-b502-b4ff8dc6ac8c.PNG)


## Swagger UI ⌨️
A continuación ya se puede hacer uso de la Api Documentation de swagger:

![foto 1](https://user-images.githubusercontent.com/43796299/205543832-2557ee93-0030-4ccf-a356-c5f7c33ad53f.PNG)


### Pruebas de Funcionamiento  📄
Al ejecutar el método para obtener todos los empleados se nos muestra la información que se tiene de la base y esta funcionando correctamente:

![foto 2](https://user-images.githubusercontent.com/43796299/205543779-89eef2f4-00db-42b0-a29e-7195c448a6d7.PNG)



