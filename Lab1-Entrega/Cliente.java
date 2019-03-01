package ejemplo.socket;

public class Cliente {
	public static final int PUERTO = 2000;

	public static void main(String[] args) {
		String linea = null;
		try {
			java.io.BufferedReader tec = new java.io.BufferedReader(
				new java.io.InputStreamReader(System.in));

			java.net.Socket miSocket = new java.net.Socket("localhost", PUERTO);

			java.io.BufferedReader inred = new java.io.BufferedReader(
				new java.io.InputStreamReader(miSocket.getInputStream()));

			java.io.PrintStream outred =
				new java.io.PrintStream(miSocket.getOutputStream());

			Runnable procesaMensajes = () -> {
				try {
					String mensaje;
					while ((mensaje = inred.readLine()) != null) {
						String cabecera = mensaje.substring(0, 6);
						String cuerpo = mensaje.substring(7);
						if(cabecera.equals("[INFO]")){
							System.out.print("Información: ");
						}else if(cabecera.equals("[DATA]")){
							System.out.print("Datos: ");
						}
						System.out.println(cuerpo);
					}
				} catch (java.io.IOException ioe) {
					System.err.println("[!] Error al procesar el mensaje.");
					ioe.printStackTrace(System.err);
				}
			};
			
			Thread t = new Thread(procesaMensajes, "ProcesaMensajes");
			t.start();
			
			// Simplemente lee del teclado y envía los datos
			while ((linea = tec.readLine()) != null) {
				outred.println(linea);
			}

		} catch (Exception e) { e.printStackTrace(); }
	}
}
