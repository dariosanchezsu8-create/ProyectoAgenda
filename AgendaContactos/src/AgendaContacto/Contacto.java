package AgendaContacto;

import java.util.HashMap;

/**
 * Clase contacto. Dentro de esta hay una clase anidadad llamada telono
 */
public class Contacto {
	/**
	 * Clase anidada Telefono Clase privada no nos interesa que se pueda ver ni usar
	 * en otras clases. Solo en necesario para esta clase contacto
	 */
	private class Telefono {
		/**
		 * Atributo numero del numero
		 */
		private int numero;
		/**
		 * Atributo prefijo del numero
		 */
		private int prefijo;

		/**
		 * Constructor de telefono
		 * 
		 * @param numero
		 * @param prefijo
		 */
		private Telefono(int numero, int prefijo) {
			this.numero = numero;
			this.prefijo = prefijo;
		}

		/**
		 * Metodo que devuelve un numero
		 * 
		 * @return numero telefono
		 */
		private int getNumero() {
			return numero;
		}

		/**
		 * Metodo que cambia el numero por otro
		 * 
		 * @param numero nuevo
		 */
		private void setNumero(int numero) {
			this.numero = numero;
		}

		/**
		 * Metodo que muestra el prefijo del telefono
		 * 
		 * @return prefijo del telefono
		 */
		private int getPrefijo() {
			return prefijo;
		}

		/**
		 * Metodo que cambia el prefijo por otro nuevo
		 * 
		 * @param prefijo nuevo
		 */
		private void setPrefijo(int prefijo) {
			this.prefijo = prefijo;
		}

		/**
		 * Metodo para imprimir el prefijo y el numero Se usa un parseInteger para
		 * cambiar los dos atributos a Strings Y luego se hace un merge de ellos
		 */
		@Override
		public String toString() {
			String prefijo = Integer.toString(this.prefijo);
			String numero = Integer.toString(this.numero);
			return numero + prefijo;
		}

	}

	/**
	 * Atributos de la clase contacto Atributos String nombre,
	 * apellidos,codigoPostal del contacto
	 */
	private String nombre, apellidos, codigoPostal;
	/**
	 * Implementacion de un hashMap denominado telefonos. Como clave tiene una
	 * descripción del numero de telefono Como valor tiene la clase anidada telefono
	 */
	private HashMap<String, Telefono> telefonos;
	/**
	 * Implementacion de un hashMap demoninado email. Como clave tenemos una
	 * descripcion del correo Como valor el correo electronico del contacto
	 */
	private HashMap<String, String> email;

	/**
	 * Constructo de contacto
	 * 
	 * @param nombre Se ha decido poner solo el nombre porque el valor mas
	 *               importante para la aplicacion es el nombre sabiendo el nombre
	 *               del contacto se puede llegar a todo lo demás. En clase agenda
	 *               se verá
	 */
	public Contacto(String nombre) {
		this.apellidos = apellidos;
		this.codigoPostal = codigoPostal;
		this.nombre = nombre;
		telefonos = new HashMap<String, Telefono>();
		// metodo nuevo del hashMap keyset para imprimir las claves
		email = new HashMap<String, String>();

	}

	/**
	 * Metodo que devuelve el nombre contacto
	 * 
	 * @return nombre del contacto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para cambiar el nombre del contacto
	 * 
	 * @param nombre nuevo
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que devuelve los apellidos del contacto
	 * 
	 * @return apellidos del contacto
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Metodo que cambiar los apellidos del contacto
	 * 
	 * @param apellidos nuevo
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Metodo que devuelve el codigoPostal del contacto
	 * 
	 * @return codigoPostal del contacto
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Metodo que cambia el codigoPostal del contacto
	 * 
	 * @param codigoPostal nuevo
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Metodo para añadir un telefono. Se implementa upperCase para que sea el
	 * nombre el mayusculas siempe y no haya confusión
	 * 
	 * @param descripcion
	 * @param prefijo
	 * @param numero
	 */
	public void añadirTelefono(String descripcion, int prefijo, int numero) {
		this.telefonos.put(descripcion.toUpperCase(), new Telefono(prefijo, numero));

	}

	/**
	 * Metodo para cambiar la clave del hashMap telefonos, es decir la descripcion.
	 * Implementacion del upperCase para que no haya confusión
	 * 
	 * @param desAntigua
	 * @param desNueva
	 * @throws Exception. Lanza una excepción cuando la descripcion antigua es null.
	 */
	public void cambiarDescripcionT(String desAntigua, String desNueva) throws Exception {
		desAntigua = desAntigua.toUpperCase();
		desNueva = desNueva.toUpperCase();

		Telefono t = telefonos.get(desAntigua);
		if (t != null) {
			telefonos.remove(desAntigua);
			telefonos.put(desNueva, t);
		} else {
			throw new Exception("No puede cambiar la descripcion del telefono si no tiene una asignada antes.");
		}
	}

	/**
	 * Metodo para añadir correo. Al ser un hashMap utilizamos .put
	 * 
	 * @param descripcion
	 * @param correo
	 */
	public void añadirCorreo(String descripcion, String correo) {
		this.email.put(descripcion, correo);

	}

	/**
	 * Metodo para cambiar la descripcion de email. Tambien se ha puesto el
	 * upperCase para que no cause ninguna confusión
	 * 
	 * @param desAntigua
	 * @param desNueva
	 * @throws Exception. Lanza la excepcion cuando decripcion antigua sea null.
	 */
	public void cambiarDescripcionE(String desAntigua, String desNueva) throws Exception {
		desAntigua = desAntigua.toUpperCase();
		desNueva = desNueva.toUpperCase();
		String e = email.get(desAntigua);
		// la e es el valor del hashMap que en este caso es la e y es el correo, es
		// decir, el valor y quiero mirar con el get la clave de ese valor
		if (e != null) {
			email.remove(desAntigua);
			email.put(desNueva, e);
		} else {
			throw new Exception("No existe el correo para esa descripción. Crealo primero.");
		}
	}

	/**
	 * Metodo que imprimira todos los atributos de contacto /n son saltos de lineas
	 * Se ha implementado el metodo toString a aquellos atributos que pertenecen a
	 * un hashMap Este metodo toString es un metodo propio del hashMap Linea
	 * separadora para mas claridad para el usuario.
	 */
	// No se puede poner un for en un return
	public String toString() {
		String resultado = "";
		resultado +="Nombre: " + nombre + "\n";
		resultado += "Apellidos: " + this.apellidos + "\n";
		resultado += "Código Postal:" + this.codigoPostal + "\n";
		for (String descripcion : telefonos.keySet())
			resultado += "Telefono: " +descripcion+"="+ telefonos.get(descripcion) + "\n";
		for (String descripcion: email.keySet())
			resultado += "Email: "+descripcion+"=" + email.get(descripcion)+ "\n"
			+"_______________" + "\n";
		return resultado;
	}

}
