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

	}

}
