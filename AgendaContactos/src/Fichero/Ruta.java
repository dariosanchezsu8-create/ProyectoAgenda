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
		try {
			String fichero = Files.readString(Path.of("C:\\Users\\DARIO\\git\\repository\\nuevoRepositorio\\ProyectoAgenda\\AgendaContactos\\src\\Fichero\\agenda.dat"));
			String[] contactos = fichero.split("\n");
			for (String contacto : contactos) {
				System.out.println(contacto);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
