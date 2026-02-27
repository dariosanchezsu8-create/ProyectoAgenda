package Fichero;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import AgendaContacto.Agenda;

public class Ruta {
	private Path ruta;

	public Ruta(String nombreFichero) {
		this.ruta = Path.of(nombreFichero);
	}

	public void guardar(Agenda unaAgenda) {
		try {
			Files.writeString(ruta, unaAgenda.toString(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void recuperar(Agenda unaAgenda) {
		String nombre = "";
		try {
			String fichero = Files.readString(Path.of(
					"D:\\usud\\Dario SS\\PROGRAMACION\\eclipse-workspace2\\ProyectoAgenda\\AgendaContactos\\src\\Fichero\\agenda.dat"));
			String[] contactos = fichero.split("_______________");
			for (int i = 0; i < contactos.length; i++) {
				String[] contacto = contactos[i].trim().split("\n");
				
				for (String linea : contacto) {
					if (linea.startsWith("Nombre:")) {
						nombre = linea.replace("Nombre:", "").trim();
						try {
							unaAgenda.addContacto(nombre);
						} catch (Exception e) {
							System.err.println(e.getMessage());
							e.printStackTrace();
						}

					}

				}
				recuperarApellido(contacto, unaAgenda, nombre);
				recuperarCodigoPostal(contacto, unaAgenda, nombre);
				recuperarTelefono(contacto, unaAgenda, nombre);
				recuperarCorreo(contacto, unaAgenda, nombre);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void recuperarApellido(String[] contactos, Agenda unaAgenda, String nombre) {
		String apellido = "";
		for (String contacto : contactos) {
			if (contacto.startsWith("Apellidos:")) {
				apellido = contacto.replace("Apellidos:", "").trim();

				try {
					unaAgenda.addContacto(nombre, apellido);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	public void recuperarCodigoPostal(String[] contactos, Agenda unaAgenda, String nombre) {
		String codigoPostal = "";
		for (String contacto : contactos) {
			if (contacto.startsWith("Código Postal:")) {
				codigoPostal = contacto.replace("Código Postal:", "").trim();
				unaAgenda.setApellidos(nombre, codigoPostal);
			}
		}
	}

	public void recuperarTelefono(String[] contactos, Agenda unaAgenda, String nombre) {
		String telefono = "";
		for (String contacto : contactos) {
			if (contacto.startsWith("Telefono:")) {
				telefono = contacto.replace("Telefono:", "").trim();
				String[] objeto = telefono.split("-");
				String descripcion = objeto[0];
				String numero = objeto[1];
				String prefijo = objeto[2];
				int numeroEntero = Integer.parseInt(numero);
				int prefijoEntero = Integer.parseInt(prefijo);
				unaAgenda.addTelefono(nombre, prefijoEntero, numeroEntero, descripcion);

			}
		}
	}

	public void recuperarCorreo(String[] contactos, Agenda unaAgenda, String nombre) {
		String email = "";
		for (String contacto : contactos) {
			if (contacto.startsWith("Correo:")) {
				email = contacto.replace("Correo:", "").trim();
				String[] objeto = email.split("-");
				String descripcion = objeto[0];
				String correo = objeto[1];
				unaAgenda.addCorreo(nombre, descripcion, correo);

			}
		}
	}
}
