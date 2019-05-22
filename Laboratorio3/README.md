# Servicio RMI Box

Construcción de un servicio que consiste en un almacén (objetos tipo 'Box') de valores simples (objetos tipo 'Accumulator') mediante RMI.

1. Interfaz 'Box' para que sea un interfaz remota que admita objetos de tipo 'Accumulator'.
2. Clase 'Accumulator' que sea adecuada para ser usada como valor en Box.
3. Implementación de la interfaz remota 'Box' dentro del paquete 'server'.
4. Lanzador 'Launcher' que cree un objeto remoto 'RemoteBox' del tipo diseñado anteriormente, y lo ponga en marcha como servicio.
5. 2 clientes unitarios que muestren el uso de los dos métodos del servicio.
	- Programa 'Put' que guarda ('put()') un elemento de tipo 'Accumulator' inicializado con el valor 8.
	- Programa 'Take' que los recupera ('take()') de la caja ('Box') y los imprime en consola. Si el objeto de tipo 'Box' está vacío, lo notifica con un mensaje.