### Base de datos
Para ejecutar el proyecto correctamente, primero debemos crear una base de datos en MySQL llamada techniciandb.

### Endpoints
### Crear technician
### Método: POST
URL: http://localhost:8020/technician
### Body: JSON
{
"name": "Técnico 1"
}
### Crear Servicio
### Método: POST
URL: http://localhost:8020/api
### Body: JSON

{
"adress": "Servicio 1",
"startDate": "06/07/2024-11:30",
"endDate": "06/07/2024-13:00",
"technician": {
"technician_id": 1
}
}
### Consultar todos los servicios
### Método: GET
URL: http://localhost:8020/api
### Consultar servicios por ID
### Método: GET
URL: http://localhost:8020/api/1
### Consultar servicios por Técnico
### Método: GET
URL: http://localhost:8020/api/filters?technicianId=1
### Consultar servicios por fecha inicial
### Método: GET
URL: http://localhost:8020/api/filters?startDate=06/07/2024-11:30
### Consultar servicios por fecha final
### Método: GET
URL: http://localhost:8020/api/filters?endDate=06/07/2024-13:00