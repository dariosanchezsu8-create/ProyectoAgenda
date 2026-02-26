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
		String descripcion="";
		String correo1="";
		try {
			String fichero = Files.readString(Path.of("D:\\usud\\Dario SS\\PROGRAMACION\\eclipse-workspace2\\ProyectoAgenda\\AgendaContactos\\src\\Fichero\\agenda.dat"));
			String[] contactos = fichero.split("_______________");
			for (int i=0;i<contactos.length;i++) {
				String[] contacto=contactos[i].trim().split("\n");
				String nombre=contacto[0].replace("Nombre:", "").trim();
				String apellidos=contacto[1].replace("Apellidos:", "").trim();
				String codigoPostal=contacto[2].replace("CódigoPostal:", "").trim();
				String telefono=contacto[3].replace("Telefono:", "").trim();
				if(i==2) {
					arrayCorreo(contacto, descripcion, correo1);
				String correo=contacto[4].replace("Email:", "").trim();
				}
				try {
					unaAgenda.addContacto(nombre);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
				unaAgenda.setApellidos(nombre, apellidos);
				unaAgenda.setCodigoPostal(nombre, codigoPostal);
			//	unaAgenda.addTelefono(nombre, "TODO", i, telefono);
				unaAgenda.addCorreo(nombre, descripcion, correo1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void arrayTelefono(String array[],String descripcion,int numero, int prefijo) {
		for (int i = 0; i < array.length; i++) {
			if (i == 3) {
				String[] telefono = array[i].split("=");
				descripcion = telefono[0];
				String numeroTelefono = telefono[1];
			    numero = Integer.parseInt(numeroTelefono);
			}
		}
	}
	public void arrayNumero(String[] array) {
		
	}

	public void arrayCorreo(String array[], String descripcion , String correo) {
		for (int i = 0; i < array.length; i++) {
			if (i == 4) {
				String[] telefono = array[i].split("=");
				descripcion = telefono[0];
			    correo = telefono[1];
			}
		}
	}
}
