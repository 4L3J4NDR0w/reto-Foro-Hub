# # reto-Foro-Hub

ForoHub es una aplicación de foro desarrollada con Spring Boot y Spring Security, que utiliza JWT para la autenticación. La aplicación permite a los usuarios registrarse, iniciar sesión, y participar en discusiones de diferentes tópicos.

## Características

- Registro de usuarios
- Autenticación con JWT
- Creación y gestión de tópicos
- Publicación de respuestas en los tópicos
- Administración de usuarios y roles

## Tecnologías

- Java 17
- Spring Boot
- Spring Security
- JWT (Json Web Token)
- JPA/Hibernate
- MySQL

## Requisitos previos

- JDK 17 o superior
- Maven
- MySQL

## Configuración del proyecto

1. **Clonar el repositorio**

    ```bash
    git clone https://github.com/tu-usuario/foro-hub.git
    cd foro-hub
    ```

2. **Configuración de la Base de Datos**

    1. Crea una base de datos en MySQL:

        ```sql
        CREATE DATABASE nombre_de_tu_base_de_datos;
        ```

    2. Configura la conexión a la base de datos en `application.properties`:

        ```properties
        # Configuración de la base de datos
        spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos
        spring.datasource.username=tu_usuario
        spring.datasource.password=tu_contraseña

        # Configuración de Hibernate
        spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
        spring.jpa.hibernate.ddl-auto=update
        ```

3. **Ejecutar la aplicación**

    ```bash
    mvn spring-boot:run
    ```

## Endpoints de la API

### Autenticación

#### `POST /login`

Este endpoint se utiliza para iniciar sesión y obtener un token JWT válido.

##### Parámetros de entrada

```json
{
  "correoElectronico": "usuario@example.com",
  "contrasena": "********"
}




