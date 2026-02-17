package AgendaContacto;

import java.util.ArrayList;

import Depurador.Depurador;

public class Agenda<Telefono> {
	private ArrayList<Contacto> contactos;

	public Agenda() {
		Depurador.imprimirTraza("Creado lista de contactos");
		contactos = new ArrayList<>();
	}

	public boolean setApellidos(String nombreContacto, String aNuevo) {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().toUpperCase().equals(nombreContacto.toUpperCase())) {
				contactos.get(i).setApellidos(aNuevo);
				Depurador.imprimirTraza("El apellido es " + aNuevo);
				// uppercase ignora minusculas y mayusculas
				return true;// este metodo si recorre todo la lista de contacto y si encuentra uno que tenga
							// el mismo nombre que se busca
			} // entonces si es true el programa entendera que estas cambiando los apellidos
				// de un contacto existente
				// por el contrario estarias creando un usuario nuevo
		}
		return false;
	}

	public boolean setCodigoPostal(String nombreContacto, String cNuevo) {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().toUpperCase().equals(nombreContacto.toUpperCase())) {
				contactos.get(i).setCodigoPostal(cNuevo);// uppercase ignora minusculas y mayusculas
				Depurador.imprimirTraza("El código postal es " + cNuevo);
				return true;
			}
		}
		return false;
	}

	public boolean addCorreo(String nombreContacto, String descripcionCorreo, String correo) {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().toUpperCase().equals(nombreContacto.toUpperCase())) {
				contactos.get(i).añadirCorreo(descripcionCorreo, correo);
				Depurador.imprimirTraza("Añadiendo correo.");
				System.err.println("La descripción es " + descripcionCorreo);
				System.err.println("El correo es " + correo);
				Depurador.imprimirTraza("Operación realizada con éxito.");
				return true;
			}
		}

		return false;

	}

	public boolean addTelefono(String nombreContacto, int prefijo, int numero, String descripcionTelefono) {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().toUpperCase().equals(nombreContacto.toUpperCase())) {
				contactos.get(i).añadirTelefono(descripcionTelefono, prefijo, numero);
				Depurador.imprimirTraza("Añadiendo telefono.");
				System.err.println("La descripción del telefono es " + descripcionTelefono);
				System.err.println("El prefijo del numero es " + prefijo);
				System.err.println("El numero de teléfono es " + numero);
				Depurador.imprimirTraza("Operación realizada con éxito.");
				System.out.println("--------------");
				return true;
			}
		}
		return false;
	}

	public void addContacto(String nombre) throws Exception {
		if (nombre.length() == 0 | nombre == null) {
			throw new Exception("El nombre es un campo obligatorio. Es necesario rellenarlo.");
		} else if (nombre.matches("[0-9]+")) {
			throw new Exception("El nombre debe estar compuesto por letra.");
		} else {
			Contacto c = new Contacto(nombre);
			contactos.add(c);
			Depurador.imprimirTraza("Añadiendo " + nombre + " a la agenda.");

		}

	}

	public void addContacto(String nombre, String apellidos) throws Exception {// DELEGACION
		addContacto(nombre);
		setApellidos(nombre, apellidos);
	}

	public String buscarContacto(String nombre) throws Exception {

		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().equalsIgnoreCase(nombre)) {
				Depurador.imprimirTraza("Resultados de la búsqueda:");
				return contactos.get(i).toString();
			}
		}

		throw new Exception("Este contacto no se encuentra en la lista.");

	}

	public boolean eliminarContacto(String nombre) {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				contactos.remove(i);
				return true;
			}

		}
		return false;

	}

	public boolean setNombre(String nombreContacto, String nNuevo) throws Exception {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().toUpperCase().equals(nombreContacto.toUpperCase())) {
				contactos.get(i).setNombre(nNuevo);// uppercase ignora minusculas y mayusculas
				Depurador.imprimirTraza("Se ha cambiado el nombre correctamente");
				Depurador.imprimirTraza("Ahora el nombre es " + nNuevo);
				return true;

			}
		}
		return false;
	}

	public boolean setPrefijoNumero(String descripcion, int numero, int prefijo, String nombre) {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				contactos.get(i).añadirTelefono(descripcion, prefijo, numero);
				System.err.println("La descripción del telefono es " + descripcion);
				System.err.println("El prefijo del numero es " + prefijo);
				System.err.println("El numero de teléfono es " + numero);
				Depurador.imprimirTraza("Añadiendo teléfono.");
				Depurador.imprimirTraza("Operación realizada con éxito.");
				return true;
			}
		}
		Depurador.imprimirTraza("No se ha encontrado ninguno contacto con el nombre " + nombre);
		return false;
	}

	public boolean setTodoTelefono(String nombre, String descripcion, int prefijo, int numero,
			String descripcionNueva) {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				try {
					contactos.get(i).cambiarDescripcionT(descripcion, descripcionNueva);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
				contactos.get(i).añadirTelefono(descripcionNueva, prefijo, numero);
				Depurador.imprimirTraza("Cambiando " + descripcion + " por " + descripcionNueva);
				System.err.println("La descripción del teléfono ahora es " + descripcionNueva);
				System.err.println("El prefijo ahora es " + prefijo);
				System.err.println("El numero ahora es " + numero);
				Depurador.imprimirTraza("Añadiendo correo.");
				Depurador.imprimirTraza("Operación realizada con éxito.");

				return true;
			}
		}
		Depurador.imprimirTraza("No se ha encontrado ninguno contacto con el nombre " + nombre);
		return false;

	}

	public boolean setCorreo(String descripcion, String correo, String nombre) {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				contactos.get(i).añadirCorreo(descripcion, correo);
				System.err.println("La descripción es " + descripcion);
				System.err.println("El correo es " + correo);
				Depurador.imprimirTraza("Añadiendo correo.");
				Depurador.imprimirTraza("Operación realizada con éxito.");
				return true;
			}
		}
		Depurador.imprimirTraza("No se ha encontrado ninguno contacto con el nombre " + nombre);
		return false;
	}

	public boolean setTodoCorreo(String nombre, String descripcionNueva, String nuevoCorreo, String descripcionVieja)
			throws Exception {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				try {
					contactos.get(i).cambiarDescripcionE(descripcionVieja, descripcionNueva);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
				;
				if (descripcionVieja == descripcionNueva) {
					throw new Exception(
							"La descripción nueva no puede ser igual que la vieja. Porfavor intentelo de nuevo.");
				}
				contactos.get(i).añadirCorreo(descripcionNueva, nuevoCorreo);
				Depurador.imprimirTraza("Cambiando descripción del correo a " + descripcionNueva);
				System.err.println("La descripción ahora es " + descripcionNueva);
				System.err.println("El correo ahora es " + nuevoCorreo);
				Depurador.imprimirTraza("Añadiendo correo.");
				Depurador.imprimirTraza("Operación realizada con éxito.");
				return true;
			}
		}
		Depurador.imprimirTraza("No se ha encontrado ninguno contacto con el nombre " + nombre);
		return false;
	}

	public void excepcion(String nombreBuscado) throws Exception {
		for (int i = 0; i < contactos.size(); i++) {
			if (!contactos.get(i).getNombre().toUpperCase().equals(nombreBuscado.toUpperCase())) {
				throw new Exception("Ese nombre no se encuentra en la lista. Intentelo de nuevo.");
			}

		}

	}

	public String toString() {
		Depurador.imprimirTraza("Imprimiendo lista actual de contactos:");
		System.out.println("____________");
		return contactos.toString();
		// esto es para imprimir la agenda completa. Porque cuando se pone el objeto
	}// te sale tambien ese metodo pero lo unico que te imprimira es la direccion de
		// ese objeto no lo que contiene
// entonces para solucionar esto tienes que añadir a la clase de ese objeto un
	// metodo toString como este.

}