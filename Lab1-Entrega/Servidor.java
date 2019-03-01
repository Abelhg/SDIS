package ejemplo.socket;

public class Servidor {
	public static final int PUERTO = 2000;

	public static void main(String[] args) {
		try (java.net.ServerSocket servidor = new java.net.ServerSocket(PUERTO)) {
			while (true) {
				try  {
					System.out.println("---- Servidor esperando al cliente ----");
					java.net.Socket sock = servidor.accept();
					java.io.BufferedReader inred = new java.io.BufferedReader(
						new java.io.InputStreamReader(sock.getInputStream()));
					java.io.PrintStream    outred =
						new java.io.PrintStream(sock.getOutputStream());
			
					Runnable echo = () -> {
						try {
							String linea;
							while ((linea = inred.readLine()) != null) {
								System.out.println("[*] Echoing: "+linea);
								outred.println("[DATA] " + linea);
							}
						} catch (java.io.IOException ioe) {
							System.err.println("Cerrando socket de cliente");
							ioe.printStackTrace(System.err);
						}
					};

					Runnable info = () -> {
						try {
							while (true) {
								String i = java.time.LocalDate.now() + " " + 
									sock.getRemoteSocketAddress().toString().substring(1);
								System.out.println("[*] Enviando informacion: " + i);
								outred.println("[INFO] " + i);
								Thread.sleep(2000);
							}
						} catch (java.lang.Exception e) {
							System.err.println("Error al mostrar la informacion");
							e.printStackTrace();
						}
					};

					Thread t = new Thread(echo, "Echo");
					t.start();
					Thread t2 = new Thread(info, "Info");
					t2.start();
				} catch (java.io.IOException e) {
					System.err.println("Cerrando socket de cliente");
					e.printStackTrace(System.err);
				}
			}
		} catch (java.io.IOException e) {
		  	System.err.println("Cerrando socket de servicio");
		 	e.printStackTrace(System.err);
		}
	}
}