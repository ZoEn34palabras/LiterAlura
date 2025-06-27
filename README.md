# LiterAlura 📚

Proyecto Java + Spring Boot que permite buscar libros desde la API de Gutendex, guardarlos en base de datos y consultarlos desde un menú por consola.

---

## 🔧 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- API REST Gutendex
- Maven

---

## 📦 Funcionalidades

- Buscar y guardar libros por título desde la API de Gutendex
- Consultar todos los libros guardados
- Filtrar libros por idioma (solo inglés `en` o español `es`)
- Mostrar autores que estaban vivos en un año específico
- Ver el Top 10 de libros más descargados

---

## 🚀 Requisitos

- Java 17+
- PostgreSQL
- Maven

---

## 🔌 Configuración

1. **Base de datos:** crea una base de datos llamada `literalura`.

2. **Archivo de configuración:** crea o edita el archivo `src/main/resources/application.properties` con el siguiente contenido (no lo subas a GitHub):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3. **Ejecuta la aplicación:**  
Usa tu IDE o terminal con:

```bash
./mvnw spring-boot:run
```

---

## 💻 Uso

Desde la consola se mostrará un menú interactivo.  
Ejemplo de opciones:

```
*** Menú LiterAlura ***
1. Buscar y guardar libro por título
2. Mostrar todos los libros
3. Mostrar libros por idioma (en/es)
4. Mostrar autores vivos en un año
5. Mostrar los 10 libros más descargados
0. Salir
```

---

## 🌐 Fuente de datos

Los libros se obtienen desde [Gutendex API](https://gutendex.com/).

---

## 🧑‍💻 Autor

Enzo Flores – [enzo.umaguma@gmail.com](https://github.com/ZoEn34palabras)

---

## ⚠️ Notas

- El archivo `application.properties` está excluido mediante `.gitignore` por seguridad.
- Asegúrate de que los títulos de libros buscados estén en inglés o español dependiendo del idioma original en Gutendex.

---

¡Feliz lectura y programación! 📖