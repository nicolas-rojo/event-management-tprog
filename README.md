# MisEventos — Sistema de Gestión de Eventos

Aplicación de gestión de eventos desarrollada como proyecto grupal para la materia
**Taller de Programación** de la **Facultad de Ingeniería, Universidad de la República (UdelaR)**.

El sistema permite administrar eventos, sus ediciones, categorías, tipos de registro,
patrocinios e instituciones, así como el registro de usuarios (organizadores y asistentes)
a las distintas ediciones. Está construido sobre una arquitectura **MVC** con una capa de
lógica de negocio independiente, expuesta mediante **servicios web SOAP** y consumida por
clientes web basados en **Java Servlets, JSP y Tomcat**.

---

## Tecnologías utilizadas

Relevadas directamente del código y los descriptores de build (`pom.xml`, `web.xml`, `persistence.xml`):

| Área | Tecnología |
|------|------------|
| Lenguaje | Java 21 |
| Build / dependencias | Apache Maven |
| Capa web (MVC) | Jakarta Servlets 6.0, Jakarta Server Pages (JSP) 3.1, JSTL 3.0 |
| Contenedor | Apache Tomcat (Jakarta EE 10 / Servlet 6.x) |
| Servicios web | JAX-WS (SOAP), JAXB — Jakarta XML Web Services 4.0 |
| Persistencia | Jakarta Persistence (JPA) 3.0 con EclipseLink 4.0.2 (aplicada a las entidades de usuarios, ediciones y registros) |
| Base de datos | HSQLDB (embebida, en archivo) |
| Interfaz de escritorio | Java Swing + JCalendar (JDateChooser) |
| Testing | JUnit 4 / JUnit 5 |
| Vistas | HTML, CSS y JavaScript sobre JSP |

---

## Arquitectura del proyecto

El repositorio está organizado en tres módulos Maven que reflejan la evolución del sistema
a lo largo del curso. La lógica de negocio se implementa una sola vez (en `tarea1`) y se
reutiliza desde los clientes web a través de servicios SOAP.

```
event-management-tprog/
├── tarea1/   → Lógica de negocio + modelo de dominio + servicios web SOAP + app de escritorio (JAR)
├── tarea2/   → Aplicación web MVC completa (WAR)
└── tarea3/   → Cliente web reducido / versión "móvil" (WAR)
```

### `tarea1` — Núcleo de dominio y servicios (packaging `jar`)

Contiene el corazón del sistema, siguiendo un patrón **Controlador / Manejador** por sobre
el modelo de dominio:

- `logica` — modelo de dominio: `Evento`, `EdicionEvento`, `Categoria`, `TipoRegistro`,
  `Usuario`, `Organizador`, `Asistente`, `Institucion`, `Patrocinio`, `Registro`.
- `logica.interfaces` — contratos de la lógica (`IEventos`, `IUsuario`, `IInstituciones`).
- `logica.ctrlmanejador` — controladores (`ControladorEventos`, `ControladorUsuario`,
  `ControladorInstituciones`) y sus manejadores (colecciones en memoria).
- `logica.datatypes` — objetos de transferencia de datos (DTO / *DataTypes*).
- `excepciones` — excepciones de dominio propias.
- `webservices` — publicación de los servicios SOAP con JAX-WS (`PublicadorWS`).
- `presentacion` — cliente de escritorio en Java Swing.
- `persistencia` — JPA/EclipseLink sobre HSQLDB (`persistence.xml`, unidad `MisEventos-PU`).
  La persistencia se aplica a un subconjunto del dominio: `Usuario`, `Asistente`,
  `Organizador`, `EdicionEvento` y `Registro` (las entidades mapeadas en `persistence.xml`,
  con generación automática de tablas vía EclipseLink). El resto del dominio se maneja en
  memoria a través de los manejadores.

Los servicios web se publican con `PublicadorWS` en:

```
http://localhost:8081/eventos?wsdl
http://localhost:8081/usuarios?wsdl
http://localhost:8081/instituciones?wsdl
```

### `tarea2` — Aplicación web MVC (packaging `war`)

Frontend web principal, con separación clara **Modelo–Vista–Controlador**:

- **Controlador:** Servlets en `com.miseventos.controllers` (por ejemplo `AltaEvento`,
  `ConsultaEvento`, `ListarEventos`, `AltaEdicion`, `Login`, `Register`, `Buscar`,
  `ComprobantePDF`, etc.). Cada servlet consume los servicios SOAP mediante los
  *stubs* generados en `cliente.ws.eventos`, `cliente.ws.usuarios` y `cliente.ws.instituciones`.
- **Vista:** JSP en `src/main/webapp/WEB-INF` con plantillas reutilizables
  (`template/topbar.jsp`, `template/sidebar.jsp`).
- **Modelo:** el dominio de `tarea1`, accedido a través de los web services.

### `tarea3` — Cliente web reducido (packaging `war`)

Segundo cliente web (`miseventos-movil`), más acotado, orientado a la consulta y listado
de eventos, ediciones y usuarios. Comparte el mismo backend de servicios SOAP que `tarea2`.

---

## Cómo correr el proyecto localmente

### Requisitos previos

- **JDK 21** (con `JAVA_HOME` correctamente configurado).
- **Apache Maven** — el repositorio incluye una copia (`apache-maven-3.9.11/`), o se puede
  usar una instalación propia.
- **Apache Tomcat 10.1+** (compatible con Jakarta EE 10 / Servlet 6) para desplegar los `.war`.

### 1. Compilar e instalar la lógica (`tarea1`)

`tarea1` se empaqueta como JAR y es dependencia de los módulos web, por lo que debe
instalarse primero en el repositorio Maven local:

```bash
cd tarea1
mvn clean install
```

### 2. Levantar los servicios web SOAP

Desde `tarea1`, ejecutar el publicador (clase `webservices.PublicadorWS`, configurada como
`mainClass` del `exec-maven-plugin`):

```bash
cd tarea1
mvn exec:java
```

Esto deja los servicios de eventos, usuarios e instituciones publicados en
`http://localhost:8081`. La base de datos HSQLDB se crea automáticamente en archivo
(`MisEventos-DB`) mediante la generación de tablas de EclipseLink.

### 3. Compilar y desplegar los clientes web

Con los servicios levantados, generar los `.war`:

```bash
cd tarea2
mvn clean package
# el artefacto queda en tarea2/target/tarea2.war
```

```bash
cd tarea3
mvn clean package
# el artefacto queda en tarea3/target/tarea3.war
```

Desplegar los `.war` resultantes en Tomcat (copiándolos al directorio `webapps/` o desde
el IDE) y acceder a la aplicación desde el navegador. La página de inicio configurada es
la de login (`welcome-file: login`).

> Nota: `tarea2` y `tarea3` requieren que los servicios web de `tarea1` estén en ejecución,
> ya que toda la lógica se consume vía SOAP.

---

## Equipo

Proyecto grupal desarrollado por:

- **Luca Scaboni Morales** — líder del equipo
- **Facundo Lane Díaz** — integrante
- **Agustín Cabrera Bertucci** — integrante
- **Ignacio Boix Piccardo** — integrante
- **Nicolás Rojo Ferraro** — integrante

El trabajo se dividió por módulos funcionales, asignando un módulo a cada integrante.

---

## Mi contribución — Nicolás Rojo Ferraro

Trabajé principalmente sobre el **módulo de Eventos**, participando en distintas capas de la
arquitectura para esa funcionalidad. El desarrollo fue **colaborativo**: varias partes las
implementé en conjunto con compañeros del equipo, por lo que la división no fue estrictamente
tajante entre integrantes.

Dentro del módulo de Eventos aporté en:

- **Modelo de dominio y lógica de negocio** (`tarea1/src/logica`): clases del dominio de
  eventos (`Evento`, `EdicionEvento`, `Categoria`, `TipoRegistro`) y la lógica asociada
  (`ControladorEventos` / `ManejadorEvento`, sobre la interfaz `IEventos`) — alta de eventos
  y ediciones, categorías, tipos de registro, consultas y listados.
- **Servicio web** (`webservices`): las operaciones de Eventos expuestas como servicio SOAP
  (`ControladorEventoWS` / `IControladorEventoWS`).
- **Capa web (Servlets y JSP):** los controladores y vistas del módulo de Eventos
  (por ejemplo `AltaEvento`, `ConsultaEvento`, `ListarEventos`, `AltaEdicion` y sus JSP
  correspondientes), incluyendo formularios, carga de imágenes y presentación de la información.

También participé en la implementación de la **capa de persistencia** del sistema
(JPA/EclipseLink sobre HSQLDB), que abarca las entidades de usuarios, ediciones y registros.

Aclaración: por tratarse de un trabajo en equipo y por el tiempo transcurrido, no puedo
atribuirme con exactitud cada archivo — algunas partes fueron desarrolladas mano a mano con
compañeros.

---

## Supervisión docente

Proyecto desarrollado bajo la supervisión del docente **Leonel Peña**, en el marco de la
materia Taller de Programación de la Facultad de Ingeniería (UdelaR).
