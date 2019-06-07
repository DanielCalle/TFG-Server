# TFG-Server
## Desplegar con Docker
Primero tenemos que tener docker instalado, el cual se puede instalar por el siguiente enlace:
https://www.docker.com/
Tras descargarse el repositorio e iniciado docker, situarse en la carpeta raíz del proyecto mediante el comando cd.
```
# Ir a la raiz del proyecto
cd <path-del-proyecto>
# Despliegue
docker-compose up
```
Con la sentecia docker-compose up se despliega la instancia de docker con las configuraciones que están en el archivo docker-compose.yml. Esperar hasta que en el terminal de comandos aparezcan las líneas:
```
Creating tfg-server_filmar_1 ... done
Attaching to tfg-server_filmar_1
```
Ahora que se ha levantado la instancia de docker y sus respectivos puertos mapeados.
Abrir un nuevo terminal de comandos y conectarse al contenedor mediante ssh con la contraseña "**tfg-ucm**".
```
# El puerto 22022 es el que se habia mapeado
ssh root@localhost -p 22022
```
Una vez conectado al contenedor, hay que ir a la ruta en la que se encuentra el proyecto:
*/usr/src/app*
```
# Ir a ruta
cd /usr/src/app
```
Antes de levantar el servicio, hay que preparar postgresql. Hay que ejecutar el script de configuración llamado **database.sh**. Para ello hace falta primero darle permiso de ejecución al script.
```
# Dar permiso de ejecucion al script
chmod +x database.sh
# Ejecutar el script
./database.sh
```
Una vez configurado postgres, se procede al despliegue del servicio. Con **service.sh**.
```
# Dar permiso de ejecucion al script
chmod +x service.sh
# Para iniciar el servicio
./service.sh start
# Para reiniciar el servicio
./service.sh restart
# Para parar el servicio
./service.sh stop
```
Puede que haya problemas con el formato de los scripts pues los scripts se escribieron
en Windows, para ello hace falta descargarse **vim**, y cambiarle el formato a los scripts.
```
# Descarga de vim
apt-get install -y vim
# Cambiar formato
vim script.sh
:set fileformat=unix
:wq
```
Una vez terminada la ejecución, ya está levantado el servicio en el puerto **8080**, se puede
probar con el siguiente enlace en cualquier navegador *localhost:8080/films*.
Si se quiere ver la estructura de la base de datos se encuentra en el siguiente fichero:
*path-al-proyecto/src/main/webapp/WEB-INF/sql/filmar_low.sql*.
Cuando queramos terminar el proyecto, habríamos que desconectar la conexión de ssh y
apagar la instancia de docker.
```
# Para desconectar la conexion con el ssh
exit
# Para apagar la instancia de docker
docker-compose down
```
