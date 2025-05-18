
Creado por **Angel Perez Cristobal** para **Inditex**.   
Si deseas discutir el proyecto o tienes alguna pregunta, no dudes en contactarme.
Puedes contactarme a través de LinkedIn: [Angel Marcel Perez Cristóbal](https://www.linkedin.com/in/angel-marcel-perez-crist%C3%B3bal-1b80a8277/)
# 🧾 Inditex Core Platform – Servicio de Precios

Microservicio REST que permite obtener el precio aplicable de un producto según la fecha, marca y prioridad, construido con Java 17 y Spring Boot 3 con `ApiFirst`. 
Aplica arquitectura **hexagonal**, **DDD**, buenas prácticas de código, validación de estilo con **Checkstyle**, manejo de error con **Controller Advice**, documentación con **Swagger,OpenApi**, y reportes de cobertura con **JaCoCo**.  
Ademas opcional ejecucion de `Docker` y `GitHub Actions` para `CI/CD`, tambien se dejo implementado Logstash para afuturo integrarse con `ELK`.

---

## Arquitectura

- ✅ Api First
- ✅ Hexagonal
- ✅ DDD
- ✅ Clean Code
- ✅ SOLID


![Arquitectura hegagonal](/docs/images/hexagonal-architcture.png)

## Tecnologías

- ✅ Api First
- ✅ Java 17
- ✅ Spring Boot 3
- ✅ Spring Data JPA
- ✅ H2 Database
- ✅ OpenApi
- ✅ Lombok
- ✅ JUnit 5 / MockMvc
- ✅ JaCoCo
- ✅ Logstash logback Spring
- ✅ Docker
- ✅ Docker Compose
- ✅ GitHub Actions

⚠️ IMPORTANTE : *Se esta dejando fuera de alcance la securizacion de la api con **Spring Security**, ya que no es parte del alcance de la prueba técnica.
Para un escenario real deberiamos usar `**JWT**` con `**OAuth2**` por ejemplo o exponer como primera capa un `ApiaGateway` qu valide la identidad*

## Despliegue

Se ha configurado en esta prueba las siguientes formas de pruebas y/o despliegue

1.- ✅ Ejecución de api modo local  
2.- ✅ Ejecución en Docker local (Docker Compose) **Pre requisito tener instalado Docker Desktop*  
3.- ✅ Ejecucion en Render (cloud) CI/CD GitHub Actions configurado por ambiente

## Estructura clave del proyecto 

```
├── .github/
│   ├── workflows/ (Ci/CD GitHuub Actions)
│   ├── ci-develop.yml
│   ├── ci-cert.yml
│   ├── ci-prod.yml
├── docs/
│   ├── images/
│   ├── postman/
├── api-price/ (api de prueba técnica)
│   ├── src/
│   ├── pom.xml
│   ├── Dockerfile (Uso de docker)
│   ├── checkstyle.xml
│   └── ...
├── README.md (Documentación)
├── .gitignore

```
## Ejecución del proyecto (Local)

```bash
mvn clean install
mvn spring-boot:run
```

La base de datos H2 se inicializa automáticamente en memoria con los datos definidos en `data.sql` y `schema.sql`.

---

## Swagger UI

Disponible en:

```
http://localhost:8080/swagger-ui/index.html
```

Evidencia de doumentación de pruebas:

![Cobertura de pruebas](/docs/images/swagger.png)

---
## 🗄️ Base de Datos H2 (In-Memory)

Este proyecto utiliza una base de datos H2 en memoria para entornos locales y de desarrollo. Esta configuración es ideal para pruebas rápidas y para mantener el entorno ligero durante la integración continua y despliegue en ambientes temporales como `develop`, `cert` y `main`.

### 🔧 Configuración por perfil

Dependiendo del perfil activo (dev, cert, prod), se utilizan variables de entorno para definir los parámetros de conexión. Por ejemplo, en `application-prod.yaml`:

```yaml
spring:
  datasource:
    url: ${DB_URL}
    driver-class-name: ${DB_DRIVER}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  h2:
    console:
      enabled: true
```

Estas variables se configuran en `docker-compose.yml` y también pueden ser inyectadas como secrets desde GitHub Actions.

---

### 📊 Estructura de Tablas

#### Tabla: `price_rate`

| Campo           | Tipo         | Descripción                     |
|----------------|--------------|----------------------------------|
| price_rate_id  | BIGINT       | Clave primaria                   |
| code           | VARCHAR(3)   | Código de tarifa (ej: 'PRE')     |
| description    | VARCHAR(255) | Descripción de la tarifa         |
| price          | DECIMAL(18,4)| Precio asociado a la tarifa      |
| currency       | VARCHAR(3)   | Moneda (ISO 4217)                |
| created_at     | TIMESTAMP    | Fecha de creación                |
| updated_at     | TIMESTAMP    | Última actualización             |

#### Tabla: `price`

| Campo          | Tipo       | Descripción                                     |
|----------------|------------|--------------------------------------------------|
| id             | BIGINT     | Clave primaria                                   |
| brand_id       | INT        | ID de marca                                      |
| start_date     | TIMESTAMP  | Inicio de validez                                |
| end_date       | TIMESTAMP  | Fin de validez                                   |
| price_rate_id  | INT        | FK a `price_rate(price_rate_id)`                |
| product_id     | INT        | ID del producto                                  |
| priority       | INT        | Prioridad en caso de múltiples coincidencias     |
| created_at     | TIMESTAMP  | Fecha de creación                                |
| updated_at     | TIMESTAMP  | Última actualización                             |

---

### 📌 Índices

Para mejorar la eficiencia de búsqueda por filtros, se define el siguiente índice compuesto:

```sql
CREATE INDEX idx_prices_product_brand_date
ON price (product_id, brand_id, start_date, end_date, priority);
```

Este índice es usado en la query principal de negocio para acelerar la consulta de precios válidos según filtros.

---

### 🧪 Acceso a la consola H2

La consola está habilitada por defecto y es accesible en:

> http://localhost:8080/h2-console

- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: *(vacío)*

Evidencia de tablas:

![Cobertura de pruebas](/docs/images/bd.png)

---

## 🪵 Logging y Observabilidad

La aplicación está configurada con **Spring Boot Logging (Logback)** para registrar eventos clave, errores y trazas de ejecución. El sistema genera logs estructurados y claros para facilitar el monitoreo, el debugging y la integración futura con herramientas como **ELK Stack (Elasticsearch, Logstash, Kibana)** o **Grafana + Loki**.

### 🛠️ Configuración por defecto

La configuración predeterminada utiliza el `application-{profile}.yaml` para definir el nivel de logging:

```yaml
logging:
  level:
    root: INFO
  file:
    name: logs/app.log
```

### 📌 Ejemplo de logs generados

```bash
2025-05-18 10:20:01 INFO  o.s.b.StartupInfoLogger - Starting ApiFirstPriceApplication
2025-05-18 10:20:02 INFO  c.i.c.a.s.GetApplicablePriceService - Looking for price for productId=35455, brandId=1, date=2020-06-14T10:00:00
2025-05-18 10:20:02 WARN  c.i.c.c.e.GlobalExceptionHandler - PriceNotFoundException: No price found for given filters
```


### 🔍 Logs HTTP de entrada

Las solicitudes al endpoint principal también son loggeadas:

```bash
INFO  PriceController - getPriceByFilter() | Incoming applicationDate: 2020-06-14T10:00:00, productId: 35455, brandId: 1
```

### 🔄 Integración futura con ELK (opcional)

Puedes exportar estos logs a herramientas de observabilidad:

- **Filebeat** o **Logstash** puede capturar los logs del contenedor Docker.
- **Elasticsearch** para indexarlos.
- **Kibana** para visualizarlos.

---

## Checkstyle

Checkstyle se ejecuta automáticamente en cada build.

Archivo de configuración: `checkstyle.xml`

Si hay violaciones de estilo, el build fallará con un mensaje descriptivo.

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
curl -X GET "http://localhost:8080/api/v1/price/by-filters?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00" -H "accept: application/json"

# Caso 2: 14 de junio a las 16:00
curl -X GET "http://localhost:8080/api/v1/price/by-filters?productId=35455&brandId=1&applicationDate=2020-06-14T16:00:00" -H "accept: application/json"

# Caso 3: 14 de junio a las 21:00
curl -X GET "http://localhost:8080/api/v1/price/by-filters?productId=35455&brandId=1&applicationDate=2020-06-14T21:00:00" -H "accept: application/json"

# Caso 4: 15 de junio a las 10:00
curl -X GET "http://localhost:8080/api/v1/price/by-filters?productId=35455&brandId=1&applicationDate=2020-06-15T10:00:00" -H "accept: application/json"

# Caso 5: 16 de junio a las 21:00
curl -X GET "http://localhost:8080/api/v1/price/by-filters?productId=35455&brandId=1&applicationDate=2020-06-16T21:00:00" -H "accept: application/json"

# Caso 6: Producto no existente
curl -X GET "http://localhost:8080/api/v1/price/by-filters?productId=99999&brandId=1&applicationDate=2020-06-14T10:00:00" -H "accept: application/json"
```

Se adjunta archivo Postman por ambiente **(DEV-CERT-PROD)** para su facil testeo en la ruta :  
Las rutas dev,cert,pord se encuentran actualmente desplegadas en Render con un dominio por ambiente configurado para su facil testeo, se encuentra actualmente integrado el despliegue continuo con Render mediante GitHubActions. 

[🔗 Descargar colección Postman](/docs/postman/inditex.postman_collection.json)

Evidencia de curl de postman:

![Postman evidencia](/docs/images/postman.png)
---



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
Evidencia de contenedor ejcutandose en Docker:  

![Arquitectura hegagonal](/docs/images/docker.png)
Accede a tu API en:

```
http://localhost:8080
```

> La API utiliza una base de datos **H2 en memoria**, por lo que no se requiere levantar un contenedor adicional para la base de datos.

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
## ⚙️Ejecutar/Desplegar mediante CI/CD con GitHub Actions

Este proyecto cuenta con una configuración de CI/CD completamente automatizada usando **GitHub Actions**, con integración hacia **Render** para el despliegue en la nube.

Evidencia de commit en GitHub:

![Cobertura de pruebas](/docs/images/cicd1.png)

Evidencia de CI/CD (DEV) en GitHub Actions:

![Cobertura de pruebas](/docs/images/cicd2.png)
### 🔁 Flujo de Ramas y Entornos

Se han definido tres ramas principales que simulan ambientes reales de despliegue:

- `develop`: entorno de desarrollo.
- `cert`: entorno de certificación o staging.
- `prod`: entorno de producción (`main` o `prod`).

Cada rama está asociada a un archivo `.yml` específico dentro de `.github/workflows/`, que define las tareas CI/CD correspondientes a ese ambiente:

- `ci-develop.yml`: pipeline para `develop`
- `ci-cert.yml`: pipeline para `cert`
- `ci-prod.yml`: pipeline para `prod`

### Secretos del Repositorio

Se han creado secretos a nivel de repositorio en GitHub para proteger credenciales sensibles y permitir su uso dentro de los pipelines:

| Secreto               | Descripción                                     |
|-----------------------|-------------------------------------------------|
| `SONAR_PROJECT_KEY`   | Clave del proyecto SonarCloud                   |
| `SONAR_ORGANIZATION`  | ID de la organización en SonarCloud             |
| `SONAR_HOST_URL`      | URL del servidor SonarCloud                     |
| `SONAR_TOKEN`         | Token de autenticación para análisis SonarCloud |
| `RENDER_HOOK_DEV`     | Webhook de despliegue automático para develop   |
| `RENDER_HOOK_CERT`    | Webhook de despliegue automático para cert      |
| `RENDER_HOOK_PROD`    | Webhook de despliegue automático para prod/main |

Cada uno de estos secretos es consumido por el pipeline de su respectivo entorno, garantizando un despliegue seguro y desacoplado de la configuración.


### 🛡️ Reglas de Protección de Ramas

Cada rama principal (`develop`, `cert`, `prod`) está protegida mediante reglas en GitHub:

- No se permite hacer push directo.
- Todo cambio debe ser aprobado mediante un Pull Request.
- Se han definido validaciones como build, tests, checkstyle, y análisis Sonar para asegurar la calidad antes de permitir un merge.

### 📄 Descripción de los Workflows (`.yml`)

Cada archivo de workflow contiene etapas organizadas para cubrir el ciclo completo de integración continua. Estas etapas son:

#### 1. `build`

- Compila el código con Maven.
- Verifica dependencias.

#### 2. `checkstyle`

- Verifica el cumplimiento de normas de estilo usando Checkstyle.

#### 3. `coverage`

- Ejecuta pruebas con `JaCoCo` para obtener la cobertura de código.

#### 4. `sonarcloud` *(solo en develop)*

- Análisis estático del código fuente con SonarCloud.
- Usa variables `SONAR_TOKEN_DEV` y otras del repositorio.

#### 5. `deploy` *(a Render)*

- Despliega automáticamente la aplicación en Render.
- Utiliza variables de entorno (`RENDER_DEPLOY_HOOK_DEV`, `RENDER_DEPLOY_HOOK_CERT`, `RENDER_DEPLOY_HOOK_PROD`), según el ambiente.

### ☁️ Despliegue con Render

- Se ha realizado el despliegue en Render para los 3 entornos.
- Cada entorno cuenta con su propia aplicación web separada.
- Render se activa desde el último stage del pipeline de cada rama, ejecutando un `curl` al webhook del ambiente correspondiente.

Evidencia de despliegue (DEV) en Render:

![Cobertura de pruebas](/docs/images/deploy.png)

Evidencia de CURL hacia api desplegada en Render (DEV):

![Cobertura de pruebas](/docs/images/render-curl.png)

### 🛠️ Tecnologías Usadas

- **GitHub Actions** para integración y despliegue continuo.
- **SonarCloud** para análisis de calidad de código.
- **Docker** para empaquetar la aplicación.
- **Render** como proveedor de despliegue cloud.
- **Checkstyle + JaCoCo** para código limpio y probado.
Puedes ver los resultados desde la pestaña **Actions** en tu repositorio GitHub.

---



## Contacto y consideraciones
* Este proyecto fue desarrollado por Angel Marcel Perez Cristóbal como parte de una prueba técnica para Inditex Core Platform.  
* Considerar que e esta dejando fuera de alcance la securizacion de la api con **Spring Security**, ya que no es parte del alcance de la prueba técnica.
Para un escenario real deberiamos usar `**JWT**` con `**OAuth2**` por ejemplo, adicionalmente exponer una Capa como Apigateway que maneje la seguridad y no golpear a las apis de negocio directamente*  
* Considerar que no se han incluido pruebas de integración para la base de datos H2, ya que no se consideraron necesarias para el alcance del proyecto.
Sin embargo, se pueden agregar fácilmente utilizando la configuración de Spring Data JPA.    
* Además, se ha utilizado un enfoque de arquitectura hexagonal y DDD para garantizar la escalabilidad y mantenibilidad del código.  
* Faltaria para injectar las credenciales de base de datos en un archivo de propiedades o en algun servicio de nube como Aws Secret Manager o Azure keyVault o HashicorpVault, en lugar de tenerlas codificadas en el código fuente.  

* Si deseas discutir el proyecto o tienes alguna pregunta, no dudes en contactarme.  
Puedes contactarme a través de LinkedIn: [Angel Marcel Perez Cristóbal](https://www.linkedin.com/in/angel-marcel-perez-crist%C3%B3bal-1b80a8277/)
```
