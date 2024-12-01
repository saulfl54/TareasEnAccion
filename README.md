
# Tareas en Acción

## Tabla de Contenidos
1. [Descripción General](#descripción-general)
2. [Características](#características)
3. [Prerrequisitos](#prerrequisitos)
4. [Instalación](#instalación)
5. [Uso](#uso)
6. [Contribuciones](#contribuciones)
7. [Licencia](#licencia)

---

## Descripción General

El proyecto **"Tareas en Acción"** tiene como objetivo desarrollar una aplicación que facilite la gestión y visualización de las tareas activas asignadas a los empleados. 
Permite la interacción mediante comentarios, promoviendo una colaboración interna más efectiva.  
Se trata de un proyecto educativo enfocado en entregar una primera versión funcional que cubra las necesidades básicas.

---

## Características

- Gestión de usuarios con roles:
  - Administrador: Control total sobre el sistema.
  - Usuario avanzado: Control sobre la gestión de tareas.
- Operaciones CRUD para usuarios y tareas.
- Interfaz visual para asignación y seguimiento de tareas.
- Integración con bases de datos para persistencia de datos.

---

## Prerrequisitos

Antes de instalar, asegúrate de contar con los siguientes elementos:
- **JDK 17** instalado y configurado.
- **Maven** o el wrapper de Maven (`mvnw`).
- El puerto **8080** debe estar libre.

---

## Instalación

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/usuario/repo-proyecto.git
   cd repo-proyecto
   ```

2. Genera el archivo JAR ejecutando el siguiente comando en la terminal:
   ```bash
   ./mvnw clean package
   ```

3. Lanza la aplicación con el comando:
   ```bash
   java -jar ./target/tareas-en-accion-0.0.1-SNAPSHOT.jar
   ```

4. Accede a la aplicación desde tu navegador en la URL:
   ```
   http://localhost:8080
   ```

---

## Uso

### Credenciales predeterminadas

- **Administrador**:
  - Usuario: `administrador`
  - Contraseña: `1234`
  - Permisos: Control total sobre el sistema.

- **Usuario Avanzado**:
  - Usuario: `antoniojefe`
  - Contraseña: `1234`
  - Permisos: Gestión avanzada de tareas.

### Interacción según roles
Una vez autenticado, los usuarios tendrán acceso a funciones específicas basadas en sus permisos.

---

## Contribuciones

¡Contribuir es muy bienvenido! Sigue estos pasos para participar:

1. Haz un fork del repositorio.
2. Crea una nueva rama para tu funcionalidad o corrección:
   ```bash
   git checkout -b nueva-funcionalidad
   ```

3. Realiza los cambios necesarios y haz un commit descriptivo:
   ```bash
   git commit -m "Agrego nueva funcionalidad"
   ```

4. Haz un push de tu rama al repositorio remoto:
   ```bash
   git push origin nueva-funcionalidad
   ```

5. Abre un **Pull Request** en el repositorio original y explica tus cambios.

---

## Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).  
¡Siéntete libre de usar, modificar y compartir!

---

¡Gracias por tu interés en contribuir a **Tareas en Acción**!
