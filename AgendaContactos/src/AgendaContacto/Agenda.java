package AgendaContacto;

import java.util.ArrayList;

import Depurador.Depurador;

public class Agenda<Telefono> {
	private ArrayList<Contacto> contactos;

	public Agenda() {
		Depurador.imprimirTraza("Creado lista de contactos");
		contactos = new ArrayList<>();
	}

	/**
	 * Metodo para cambiar el apellido de un contacto Este metodo realiza una
	 * busqueda por nombre en la lista de contacto. Si lo encuentra se sustituirá
	 * pero si no lo encuentra le pone a ese contacto un nuevo apellido Se ha puesto
	 * un upperCase para ignorar las minusculas y mayusculas.
	 * 
	 * @param nombreContacto por el que se busca
	 * @param aNuevo         es el nuevo apellido de adquiere el contacto
	 * @return true/false dependiendo si encuentra el contacto en la lista.
	 */
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

	/**
	 * Metodo que cambia el código postal del contacto, aparte de esto si el usuario
	 * existe sustituye su antiguo codigo postal por el nuevo, si por el contrario
	 * no existe creara un nuevo usuario con el nuevo codigo postal.
	 * 
	 * @param nombreContacto por el que se busca
	 * @param cNuevo,        el nuevo codigo postal
	 * @return true/false
	 */
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

	/**
	 * Metodo para añadir un correo a un contacto. Si encuentra el nombre en la
	 * lista de contactos se añadira el nuevo correo con una descripcion y el
	 * correo.
	 * 
	 * @param nombreContacto
	 * @param descripcionCorreo
	 * @param correo
	 * @return true/false
	 * @see contactos#añadircorreo
	 * 
	 */

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

	/**
	 * Metodo para añadir un telefono a un contacto. Si encuentra el nombre en la
	 * lista de contactos se añadira el nuevo telefono con una descripcion, prefijo
	 * y un numero.
	 * 
	 * @param nombreContacto
	 * @param descripcionCorreo
	 * @param correo
	 * @return true/false
	 * @see contactos#añadirTelefono
	 */
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

	/**
	 * Metodo para añadir el nombre de un contacto. Si no se lanza ninguna de las
	 * excepciones se añadira el contacto en la lista con un nombre
	 * 
	 * @param nombre
	 * @throws Exception. Una de ellas, por si el usuario no mete ninguna palabra
	 *                    por teclado. Y la otra, por si en vez de una palabra mete
	 *                    numeros
	 * @see contactos#add
	 */
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

	/**
	 * Metodo de delegación.Primero se hace el metodo addContacto. Y una vez que se
	 * acabe este se utilizara el nombre para el metodo setApellido
	 * 
	 * @param nombre
	 * @param apellidos
	 * @throws Exception por el metodo addContacto
	 */
	public void addContacto(String nombre, String apellidos) throws Exception {// DELEGACION
		addContacto(nombre);
		setApellidos(nombre, apellidos);
	}

	/**
	 * Metodo para buscar contacto mediante el nombre.
	 * 
	 * @param nombre
	 * @see contactos#toString
	 * @throws Exception si no se encuentra el nombre en la lista de contactos
	 */
	public String buscarContacto(String nombre) throws Exception {

		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().equals(nombre)) {
				Depurador.imprimirTraza("Resultados de la búsqueda:");
				return contactos.get(i).toString();
			}
		}

		throw new Exception("Este contacto no se encuentra en la lista.");

	}

	/**
	 * Metodo para eliminar contacto de la lista de contactos, buscado por el nombre
	 * del cotacto.
	 * 
	 * @param nombre
	 * @return true/falseº
	 */
	public boolean eliminarContacto(String nombre) {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				contactos.remove(i);
				return true;
			}

		}
		return false;

	}

	/**
	 * Metodo para cambiar el nombre de un contacto. Si encuentra el nombre del
	 * contactoç se sustituira por un nuevo nombre, sino se crear un usuario con un
	 * nuevo nombre
	 * 
	 * @param nombreContacto
	 * @param nNuevo
	 * @return true/false
	 * @throws Exception
	 * @see contactos#setNombre
	 */
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

	/**
	 * Metodo para cambiar solo el prefijo y el numero de telefono. Accedemos a
	 * estos mediante la clave que es la descrepción
	 * 
	 * @param descripcion
	 * @param numero
	 * @param prefijo
	 * @param nombre
	 * @return true/false
	 * @see contactos#añadirTelefono
	 */
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

	/**
	 * Metodo cambiar telefono por completo incluso la descripcion
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param prefijo
	 * @param numero
	 * @param descripcionNueva
	 * @return true/false
	 * @throws Exception cuando la descripcion antigua de telefono es igual a la
	 *                   nueva
	 * @see contactos#cambiarDescripcionT
	 * @see contactos#añadirTelefono
	 */
	public boolean setTodoTelefono(String nombre, String descripcion, int prefijo, int numero, String descripcionNueva)
			throws Exception {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				try {
					contactos.get(i).cambiarDescripcionT(descripcion, descripcionNueva);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
				if (descripcion == descripcionNueva) {
					throw new Exception(
							"La descripción nueva no puede ser igual que la vieja. Porfavor intentelo de nuevo.");
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

	/**
	 * Metodo para cambiar solo el correo mediante la clave que es la descripcion,
	 * ya que email es un hashMap
	 * 
	 * @param descripcion
	 * @param correo
	 * @param nombre
	 * @return true/false
	 * @see contactos.añadirCorreo
	 */

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

	/**
	 * Metodo para cambiar correo por completo, incluido la descripcion.
	 * 
	 * @param nombre
	 * @param descripcionNueva
	 * @param nuevoCorreo
	 * @param descripcionVieja
	 * @return true/false
	 * @throws Exception cuando la descripcion antigua de correo es la misma que la
	 *                   nueva.
	 */
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

	/**
	 * Metodo para arreglar una exception en una situacion concreta, que es cuando
	 * no se encuentra el nombre en la lista de contactos
	 * 
	 * @param nombreBuscado
	 * @throws Exception
	 */

	public void excepcion(String nombreBuscado) throws Exception {
		for (int i = 0; i < contactos.size(); i++) {
			if (!contactos.get(i).getNombre().toUpperCase().equals(nombreBuscado.toUpperCase())) {
				throw new Exception("Ese nombre no se encuentra en la lista. Intentelo de nuevo.");
			}

		}

	}

	/**
	 * Metodo para imprimir la lista de contactos
	 * 
	 * @see contactos#toString
	 */
	public String toString() {
		Depurador.imprimirTraza("Imprimiendo lista actual de contactos:");
		System.out.println("____________");
		return contactos.toString().replace(",", "").replace(" ", "");// para remplazar la coma
		// y el espacio al imprimir el ArrayList
		// esto es para imprimir la agenda completa. Porque cuando se pone el objeto
	}// te sale tambien ese metodo pero lo unico que te imprimira es la direccion de
		// ese objeto no lo que contiene
// entonces para solucionar esto tienes que añadir a la clase de ese objeto un
	// metodo toString como este.

}