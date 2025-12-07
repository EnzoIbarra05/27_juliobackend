# 🚀 Backend – Sistema de Control de KPIs  
### Empresa **27 de Julio S.R.L.**

Este proyecto corresponde al backend desarrollado para la empresa **27 de Julio S.R.L.**, destinado al **control y la gestión de KPIs operativos**, accesos de usuarios y generación de reportes internos.  
Está construido con **Java + Spring Boot**, conectado a una base de datos **MySQL**, y se comunica con un frontend web realizado en **React**.

---

## 🧩 Funcionalidades principales

### 🔐 Autenticación de usuarios
- Login vía endpoint `/usuarios/login`
- Validación de credenciales contra base de datos
- Manejo de sesiones desde el frontend  
- Respuestas JSON estandarizadas  

### 📊 Gestión de KPIs
- Carga de datos mediante formularios del frontend
- Lectura de reportes por fecha, usuario o categoría
- Generación y actualización automática del tablero de KPIs  
- Endpoints para:
  - Crear registros  
  - Modificar registros  
  - Listar información  
  - Descargar reportes (CSV/PDF desde el frontend)

### 👥 Gestión de empleados
- Alta, baja y modificación de usuarios del sistema  
- Campos: `id`, `nombre`, `apellido`, `usuario`, `dni`, `sector`, `fechaIngreso`, `activo`

### 🗂️ Reportes
- Endpoints para consultar información consolidada  
- Generación de resúmenes internos  
- Lógica de ordenamiento y paginado  
- Integración con tablas dinámicas en el frontend  

---

## 🛠️ Tecnologías utilizadas

### 🖥️ Backend
- **Java 17+**
- **Spring Boot**
- Spring Web
- Spring Data JPA
- MySQL Connector

### 🗄️ Base de datos
- **MySQL**
- Estructura normalizada para KPIs y usuarios

### 🌐 Comunicación
- API REST en formato **JSON**
- CORS configurado para permitir comunicación con el frontend hospedado externamente

---

## ⚙️ Estructura del proyecto

📁 27dejulio-backend
├── src
│ ├── main
│ │ ├── java/com/empresa/...
│ │ │ ├── controllers
│ │ │ ├── services
│ │ │ ├── repository
│ │ │ └── models
│ │ └── resources
│ │ └── application.properties
└── Dockerfile


---

## 🐳 Docker (Producción)

El backend puede ejecutarse en un servidor con Docker mediante:

**Dockerfile**
```dockerfile
FROM openjdk:22-jdk-slim
ARG JAR_FILE=target/27dejulio_srl-0.0.1.jar
COPY ${JAR_FILE} app_27dejulio.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_27dejulio.jar"]
```
---
## 🎥 Mira la demo completa del proyecto

[![Ver video](https://img.youtube.com/vi/CkTQhXJ32TM/0.jpg)](https://www.youtube.com/watch?v=CkTQhXJ32TM)



