# Microservicio candidatos

### Esquema del repositorio

Dentro de las carpetas src/main/java/com/proyectofinalg30/candidatos se encuentra la distribución de los componentes de la API, dividiendo el mismo en capas como buena práctica, teniendo los siguientes paquetes:

i. controller: Paquete de los controladores que cuenta con los endpoint.

ii. service: Paquete de los servicios donde se encuentra las funcionalidades y lógica.

iii. model: Paquete de los modelos de representación de los datos.

iv. respository: Paquete de repositorio el cual establece la comunicación con la base de datos.

![img_1.png](img_1.png)

Para los recursos de pruebas unitarias se podrá encontrar desde la siguiente ruta src/test/java/proyectofinalg30/candidatos allí se listarán las pruebas por cada controller existente, en nuestro caso 1 archivos con 14 escenarios.

### Pruebas unitarias

Para las pruebas unitarias se implementaron para los endpoints con Junit, la ejecución de pruebas se puede realizar ubicándose en la raíz del proyecto y ejecutando el siguiente comando. mvn test.

![img.png](img.png)

### Coverage

En este aparatado se menciona la herramienta utilizada para el análisis de coverage del codigo jacoco, y que una ves ejecutado el comando mvn test podrá acceder al reporte que se muestra a continuación. Esta mimas es utilizada para evaluar el coverage en el Action de GitHub.

![img_2.png](img_2.png)

![img_3.png](img_3.png)
