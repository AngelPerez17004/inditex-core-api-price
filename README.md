
Creado por **Angel Perez** Cristobal para **Inditex**.   
Si deseas discutir el proyecto o tienes alguna pregunta, no dudes en contactarme.
Puedes contactarme a través de LinkedIn: [Angel Marcel Perez Cristóbal](https://www.linkedin.com/in/angel-marcel-perez-crist%C3%B3bal-1b80a8277/)
# 🧾 Inditex Core Platform – Servicio de Precios

Microservicio REST que permite obtener el precio aplicable de un producto según la fecha, marca y prioridad, construido con Java 17 y Spring Boot 3 con **ApiFirst**. 
Aplica arquitectura **hexagonal**, **DDD**, buenas prácticas de código, validación de estilo con **Checkstyle**, manejo de error con **Controller Advice**, documentación con **Swagger,OpenApi**, y reportes de cobertura con **JaCoCo**.  
Ademas opcional ejecucion de **Docker** y **GitHub Actions** para CI/CD, tambien se dejo implementado Logstash para afuturo integrarse con **ELK**.

---

## Arquitectura

- Api First
- Hexagonal
- DDD
- Clean Code
- SOLID


![Arquitectura hegagonal](/docs/images/hexagonal-architcture.png)

## Tecnologías

- Api First
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database
- OpenApi
- Lombok
- JUnit 5 / MockMvc
- JaCoCo
- Logstash logback Spring
- Docker
- Docker Compose
- GitHub Actions

⚠️ IMPORTANTE : *Se esta dejando fuera de alcance la securizacion de la api con **Spring Security**, ya que no es parte del alcance de la prueba técnica.
Para un sistema real deberiamos usar **JWT** con **OAuth2** minimo.*

---

## Ejecución del proyecto

```bash
mvn clean install
mvn spring-boot:run
```

La base de datos H2 se inicializa automáticamente en memoria con los datos definidos en `data.sql`.

---

## Swagger UI

Disponible en:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Cómo ejecutar las pruebas y ver cobertura

### Ejecutar pruebas con cobertura:

```bash
mvn clean verify
```

Esto ejecuta:
- ✔️ Pruebas unitarias y de integración
- ✔️ Validación de estilo con Checkstyle
- ✔️ Generación de reporte JaCoCo

### Ver reporte de cobertura:

Ejecutar el comando en el terminal en la raiz del proyecto y abrirá el navegador con el reporte de cobertura:

```
start target/site/jacoco/index.html
```
Evidencia de cobertura de pruebas:

![Cobertura de pruebas](/docs/images/coverage.png)
---

## Probar el endpoint con `curl`

Formato:

```bash
curl -X GET "http://localhost:8080/api/v1/prices?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00" -H "accept: application/json"
```

### 🔁 Casos de prueba solicitados:

```bash
# Caso 1: 14 de junio a las 10:00
curl -X GET "http://localhost:8080/inditex-core-platform/v1/price/by-filters?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00" -H "accept: application/json"

# Caso 2: 14 de junio a las 16:00
curl -X GET "http://localhost:8080/inditex-core-platform/v1/price/by-filters?productId=35455&brandId=1&applicationDate=2020-06-14T16:00:00" -H "accept: application/json"

# Caso 3: 14 de junio a las 21:00
curl -X GET "http://localhost:8080/inditex-core-platform/v1/price/by-filters?productId=35455&brandId=1&applicationDate=2020-06-14T21:00:00" -H "accept: application/json"

# Caso 4: 15 de junio a las 10:00
curl -X GET "http://localhost:8080/inditex-core-platform/v1/price/by-filters?productId=35455&brandId=1&applicationDate=2020-06-15T10:00:00" -H "accept: application/json"

# Caso 5: 16 de junio a las 21:00
curl -X GET "http://localhost:8080/inditex-core-platform/v1/price/by-filters?productId=35455&brandId=1&applicationDate=2020-06-16T21:00:00" -H "accept: application/json"

# Caso 6: Producto no existente
curl -X GET "http://localhost:8080/inditex-core-platform/v1/price/by-filters?productId=99999&brandId=1&applicationDate=2020-06-14T10:00:00" -H "accept: application/json"
```

Se adjunta archivo Postman para su facil testeo en la ruta :
[🔗 Descargar colección Postman](/docs/postman/inditex.postman_collection.json)

Evidencia de curl de postman:

![Postman evidencia](/docs/images/postman.png)
---

## Logs

Por defecto se esta configurando la dependencia Logstash logback Spring para enviar logs a la consola.

Archivo de configuración: `resources/logback-spring.xml`

Dado que la idea a futuro es integrarte con ELK.

---

## Checkstyle

Checkstyle se ejecuta automáticamente en cada build.

Archivo de configuración: `checkstyle.xml`

Si hay violaciones de estilo, el build fallará con un mensaje descriptivo.

---

## Estructura clave del proyecto

```
├── api-price/
│   ├── src/
│   ├── pom.xml
│   ├── checkstyle.xml
│   └── ...
├── README.md
```

## 🐳 Ejecutar la aplicación con Docker

### ✅ Requisitos previos

- Tener instalado [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- Verificar que el demonio de Docker esté corriendo
- Haber generado el artefacto `.jar` con Maven (`target/*.jar`)

---

### 1. Construir el JAR del proyecto

```bash
mvn clean package -DskipTests
```

Esto generará el archivo `target/app.jar` (o similar) necesario para contenerizar tu aplicación.

---

### 2. Construir la imagen Docker

```bash
docker-compose build
```

Este comando usará el `Dockerfile` y construirá una imagen de tu API llamada `inditex-core-app`.

---

### 3. Levantar el contenedor

```bash
docker-compose up
```

Accede a tu API en:

```
http://localhost:8080
```

> La API utiliza una base de datos **H2 en memoria**, por lo que no se requiere levantar un contenedor adicional para la base de datos.

---

### 4. Acceder a la consola H2

```
http://localhost:8080/h2-console
```

Valores por defecto:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **User**: `sa`
- **Password**: *(dejar en blanco)*

---

### 5. Detener los contenedores

Presiona `Ctrl + C` o ejecuta:

```bash
docker-compose down
```

---

### Archivos Docker relacionados

| Archivo               | Propósito                                                                 |
|-----------------------|--------------------------------------------------------------------------|
| `Dockerfile`          | Define cómo construir la imagen de la API usando Java 17                |
| `docker-compose.yml`  | Orquesta la ejecución del contenedor y expone el puerto 8080            |

---

## 🔁 CI/CD con GitHub Actions

Este proyecto incluye una integración continua completa con GitHub Actions para asegurar la calidad del código en cada push o pull request.

### Stages configuradas

- **Build & Verify:** Compila el proyecto y ejecuta los tests con Maven.
- **Checkstyle:** Verifica el estilo de código según las reglas definidas.
- **JaCoCo Coverage:** Genera reporte de cobertura de tests unitarios.
- **Docker Build:** Construye la imagen Docker del proyecto.

### Requisitos

- Tener definido el plugin de Checkstyle en `pom.xml`.
- Tener el archivo `Dockerfile` en la raíz del proyecto.
- El proyecto debe estar correctamente estructurado con Maven.

### Archivo de configuración

La configuración del pipeline se encuentra en:

```
.github/workflows/ci.yml
```

### Ejecución

Cada vez que haces `push` o `pull_request` sobre la rama `main`, GitHub Actions:

1. Clona el código
2. Configura Java 17
3. Ejecuta `mvn clean verify`
4. Valida reglas de estilo
5. Genera el reporte de cobertura con JaCoCo
6. (Opcional) Construye imagen Docker

Puedes ver los resultados desde la pestaña **Actions** en tu repositorio GitHub.

---



## Contacto y consideraciones

Este proyecto fue desarrollado por Angel Marcel Perez Cristóbal como parte de una prueba técnica para Inditex Core Platform.
Considerarm que no se han incluido pruebas de integración para la base de datos H2, ya que no se consideraron necesarias para el alcance del proyecto.
Sin embargo, se pueden agregar fácilmente utilizando la configuración de Spring Data JPA.
Además, se ha utilizado un enfoque de arquitectura hexagonal y DDD para garantizar la escalabilidad y mantenibilidad del código.
Faltaria para injectar las credenciales de base de datos en un archivo de propiedades o en algun servicio de nube como Aws Secret Manager o Azure keyVault o HashicorpVault, en lugar de tenerlas codificadas en el código fuente.
Si deseas discutir el proyecto o tienes alguna pregunta, no dudes en contactarme.
Puedes contactarme a través de LinkedIn: [Angel Marcel Perez Cristóbal](https://www.linkedin.com/in/angel-marcel-perez-crist%C3%B3bal-1b80a8277/)
```
