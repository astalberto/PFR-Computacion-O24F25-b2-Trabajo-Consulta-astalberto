## B2 - Trabajo Consulta 2: Conexión base de datos relacional
### **¿Qué es JDBC?**
JDBC es una especificación desarrollada por JavaSoft que define una interfaz de programación de aplicaciones (API) estándar diseñada para facilitar el acceso a sistemas de gestión de bases de datos (DBMS) desde programas Java. La API JDBC se compone de un conjunto de interfaces y clases escritas en el lenguaje de programación Java, lo que proporciona una base uniforme para interactuar con diferentes DBMS.

Gracias a estas interfaces y clases, los desarrolladores pueden implementar aplicaciones capaces de establecer conexiones con bases de datos, ejecutar consultas estructuradas en lenguaje SQL y procesar los resultados obtenidos. Una de las principales ventajas de JDBC es su naturaleza como estándar, lo que garantiza que cualquier programa Java que utilice esta API pueda comunicarse con diversos DBMS, siempre que exista un controlador compatible con el sistema de base de datos en cuestión.

### **Controladores JDBC y su funcionamiento**

-   **Tipos de controladores JDBC**: Los controladores JDBC actúan como intermediarios entre las aplicaciones Java y las bases de datos, permitiendo realizar consultas SQL y manejar datos. Existen cuatro tipos principales de controladores:
    
    1.  **Puente JDBC-ODBC (Tipo 1)**: Traduce las llamadas JDBC en llamadas ODBC y las envía al controlador ODBC. Es necesario cargar las bibliotecas cliente de la base de datos en cada máquina cliente.
        
    2.  **API nativa/Controlador parcial de Java (Tipo 2)**: Convierte las llamadas JDBC en llamadas específicas de la base de datos, comunicándose directamente con el servidor de la base de datos. Requiere bibliotecas específicas en el cliente.
        
    3.  **Controlador Java puro (Tipo 3)**: Utiliza una arquitectura de tres niveles, donde las llamadas JDBC se transmiten a un servidor intermedio que traduce las solicitudes a la interfaz nativa de la base de datos.
        
    4.  **Controlador Java de protocolo nativo (Tipo 4)**: Es un controlador completamente Java que convierte directamente las llamadas JDBC en llamadas específicas de la base de datos. Las aplicaciones cliente pueden comunicarse directamente con el servidor de la base de datos.
        
-   **Driver JDBC y URL de conexión**: Los drivers JDBC son esenciales para garantizar la compatibilidad entre la aplicación Java y la base de datos que se está utilizando. Cada sistema de gestión de bases de datos tiene su propio driver JDBC. Para identificar la base de datos y establecer una conexión, se utiliza una **URL de conexión**, que incluye:
    
    -   El tipo de base de datos.
        
    -   La dirección del servidor.
        
    -   El puerto.
        
    -   Otros detalles necesarios para la conexión.
        
-   **API JDBC**: Proporciona un conjunto de clases e interfaces que permiten ejecutar consultas SQL, gestionar transacciones y manipular datos en las bases de datos. Algunas de las clases clave incluyen:
    
    -   **Connection**: Representa la conexión con una base de datos específica.
        
    -   **Statement**: Permite ejecutar consultas SQL simples.
        
    -   **ResultSet**: Se utiliza para manejar los resultados obtenidos de las consultas.

### Librerías de Scala que permitan conectarse a una base de datos relacional
#### Comparación entre **Doobie** y **Slick**

| **Aspecto** | **Slick** | **Doobie** |
|---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Modelado de datos** | Usa **case classes** y tuplas con su sistema **Functional Relational Mapping (FRM)** para mapear tablas a modelos en Scala. | Trabaja con case classes, tipos básicos (como `Int`, `String`) o tuplas, escribiendo directamente las consultas SQL. |
| **Uso de SQL** | Opcional: puedes usar su **DSL integrado** o escribir SQL directamente (Plain SQL). | SQL es la base: escribes consultas directamente con interpoladores, manteniendo control total. |
| **Consultas seguras** | **Sí**: Verifica consultas en tiempo de compilación, evitando errores comunes. | **Parcial**: Aunque ofrece herramientas para evitar problemas, el programador tiene más responsabilidad para garantizar seguridad. |
| **Estilo de programación**| **Reactivo y no bloqueante**, ideal para aplicaciones que usan Akka o Play. | Muy flexible: permite integrar con diferentes librerías funcionales como Cats o Scalaz. |
| **Manejo de resultados** | Los resultados están envueltos en un **Future**, lo que facilita la programación reactiva. | Usa mónadas como **IO**, **Task**, o similares, dando más opciones para manejar efectos secundarios. |
| **Transacciones** | Composición de consultas mediante `flatMap`, manteniendo un flujo funcional. | Se describen explícitamente como `ConnectionIO` y se ejecutan con `.transact`, ofreciendo control detallado. |
| **Streaming de datos** | Soporte para **Reactive Streams**, ideal para manejar grandes volúmenes de datos en tiempo real. | Compatible con **fs2**, lo que es útil en sistemas funcionales. |
| **Configuración** | Configuración sencilla mediante perfiles predefinidos, como `PostgresProfile`, para manejar métodos y funcionalidades específicas de la base de datos. | Configuración manual mediante un `Transactor`, que requiere definir conexión, credenciales, y pooling (si se usa). |
| **Casos ideales** | Cuando necesitas simplicidad, consultas composicionales, y soporte robusto para aplicaciones reactivas. | Si prefieres un control total sobre SQL y mayor flexibilidad funcional en proyectos complejos.
### Documentación de como establecer una conexión a una base de datos relacional
Genere una base de datos en mysql <br>
![image](https://github.com/user-attachments/assets/bc7da9cc-973c-44d6-a511-7e76106df688)<br>
Genere una tabla con datos de prueba <br>
![image](https://github.com/user-attachments/assets/5c2e7a76-03cf-48fe-ad26-e7caaed1f489) <br>
Desde Scala establezca la conexión a la base datos, se utilizo la librerira mysql-connector para esta consulta <br>
![image](https://github.com/user-attachments/assets/949c0cb9-6791-46b6-8005-a77c08175357) <br>
Desde Scala realice la consulta de todos los datos de la tabla de prueba.  <br>
![image](https://github.com/user-attachments/assets/becdfa45-2e73-485a-8384-6ac8bd0405c5)


### Bibliografía 
https://www.ibm.com/docs/es/informix-servers/12.10?topic=started-what-is-jdbc-driver
https://www.ibm.com/docs/es/informix-servers/12.10?topic=started-what-is-jdbc
https://www.tokioschool.com/noticias/jdbc/
https://scala-slick.org
https://typelevel.org/doobie/index.html
https://softwaremill.com/comparing-scala-relational-database-access-libraries/
https://www.baeldung.com/scala/slick-intro
https://scala-slick.org/doc/3.3.3/introduction.html
https://softwaremill.com/comparing-scala-relational-database-access-libraries/
https://scala-slick.org/doc/3.3.3/supported-databases.html
https://stackoverflow.com/questions/19528104/how-to-create-a-connection-between-scala-and-mysql-using-jdbc
