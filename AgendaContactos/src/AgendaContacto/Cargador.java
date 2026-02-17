package AgendaContacto;

public abstract class Cargador {

	/**
	 * Metodo que carga tres contactos con datos predefinidos.
	 * Este metodo se cargara el primero de todos, incluso antes del menu
	 * @param unaAgenda
	 * @throws Exception. Lanza excepciones porque este metodo esta compuesto
	 * por muchos metodos y algunos de ellos lanzan excepciones.
	 * @see Agenda#addContacto (String, String)
	 * @see Agenda#setCodigoPostal (String, String)
	 * @see Agenda#addCorreo (String, String, String)
	 * @see Agenda#addTelefono (String, int, int, String)
	 */
	public static void cargarContactos(Agenda unaAgenda) throws Exception {
		unaAgenda.addContacto("Marco", "Perez");
		unaAgenda.setCodigoPostal("Marco", "Calle descuento");
		unaAgenda.addCorreo("Marco", "Trabajo", "manolo123@gmail.com");
		unaAgenda.addTelefono("Marco", 91, 212463, "Casa");

		unaAgenda.addContacto("Romeo", "Santos");
		unaAgenda.setCodigoPostal("Romeo", "Calle sotana");
		unaAgenda.addCorreo("Romeo", "Personal", "romeojeje@hotmail.es");
		unaAgenda.addTelefono("Romeo", 96, 340623, "Trabajo");

		unaAgenda.addContacto("Sofía", "Castro");
		unaAgenda.setCodigoPostal("Sofía", "Calle Juanchito");
		unaAgenda.addCorreo("Sofía", "Secundario", "sofiagg@educastur.org");
		unaAgenda.addTelefono("Sofía", 956, 850452, "Casa");
	}

}
