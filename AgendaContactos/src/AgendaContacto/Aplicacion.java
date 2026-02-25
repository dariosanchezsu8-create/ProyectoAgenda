package AgendaContacto;

import java.util.Scanner;

import Depurador.Depurador;
import Fichero.Ruta;

/**
 * @author dario.sanchez
 * @version v1.0
 * @since 2026-02-17
 */

/**
 * Se crea un scanner Se crea una agenda
 */
public class Aplicacion {
	Scanner sc = new Scanner(System.in);
	Agenda unaAgenda = new Agenda();
	public static void main(String[] args) {
		Aplicacion unApp = new Aplicacion();
	}

	public Aplicacion() {	
		Ruta r=new Ruta("agenda.dat");
		r.recuperar(unaAgenda);
		/**
		 * Metodo para cargar tres contactos,controlado por un try-catch Ocurre nada mas
		 * iniciar la ejecucion
		 */
		try {
			Cargador.cargarContactos(unaAgenda);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		/**
		 * Menu de la agenda con un do-while y dentro un switch
		 */
		int opcion = 0;
		do {
			System.out.println("Bienvenido a nuestra agenda");
			System.out.println("1) Añadir contacto");
			System.out.println("2) Modificar contacto");
			System.out.println("3) Buscar contacto");
			System.out.println("4) Eliminar contacto");
			System.out.println("5) Mostrar agenda completa");
			/**
			 * Se controla la entrada para elegir opcion en el menu
			 */
			try {
				opcion = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("Se debe meter un numero entero. Intentelo de nuevo.");
				e.printStackTrace();
			}
			/**
			 * Switch con todos los distintos metodos Para mejor organizacion se hace un
			 * referencia a cada metodo
			 */
			switch (opcion) {
			case 1:
				mostrarMenuAñadirContacto();
				r.guardar(unaAgenda);
				Depurador.imprimirTraza("Contacto guardado correctamente.");
				break;
			case 2:
				mostrarMenuModificarContacto();
				r.guardar(unaAgenda);
				Depurador.imprimirTraza("Contacto guardado correctamente.");
				break;
			case 3:
				mostrarMenuBuscarContacto();
				r.guardar(unaAgenda);
				break;
			case 4:
				mostrarMenuBorrarContacto();
				r.guardar(unaAgenda);
				Depurador.imprimirTraza("Contacto guardado correctamente.");
				break;
			case 5:
				mostrarMenuMostrarAgenda();
				r.guardar(unaAgenda);
				break;
			default:
				System.out.println("La opción elegida no esta en el menu.");
				System.out.println("Saliendo de la aplicación...");
				break;
			}

		} while (opcion >= 1 && opcion < 6);
		sc.close();
	}

	/**
	 * Metodo para añadir contacto. Importe obtener el nombre porque a partir de
	 * este se puede aplicar los demas metodos.
	 */
	private void mostrarMenuAñadirContacto() {
		String nombre = "";
		System.out.println("AÑADIR CONTACTO");
		System.out.println("Porfavor introduzca el nombre del contacto.");
		/**
		 * Metodo que controlado la entrada del teclado por try-catch
		 */
		try {
			nombre = sc.nextLine();
		} catch (Exception e) {
			System.err.println("Debe ser una palabra.");
		}
		/**
		 * Se controla el metodo addContacto con try-catch
		 */
		try {
			unaAgenda.addContacto(nombre);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		/**
		 * Conjunto de preguntas para recoletar los datos del contacto. Falta por
		 * controlar con try-catch las entradas por teclado
		 */
		System.out.println("Porfavor introduzca el apellido del contacto.");
		String a = sc.nextLine();
		unaAgenda.setApellidos(nombre, a);
		System.out.println("Porfavor introduzca el código postal del contacto");
		String b = sc.nextLine();
		unaAgenda.setCodigoPostal(nombre, b);
		System.out.println("NUMERO DE TELEFONO");
		System.out.println("Porfavor ponga una descripción de una palabra del número de teléfono");
		String descripcion = sc.nextLine();
		System.out.println("Porfavor introduzca el prefijo de su comunidad autónoma");
		/**
		 * Para el telefono hay que transformar la entrada del usuario, es decir, un
		 * String a un int para poder luego implementarlo en el metodo como int
		 */
		int prefijo = Integer.parseInt(sc.nextLine());
		System.out.println("Porfavor introduzca el número del contacto");
		int numero = Integer.parseInt(sc.nextLine());
		unaAgenda.addTelefono(nombre, prefijo, numero, descripcion);
		System.out.println("AÑADIR CORREO");
		System.out.println("Porfavor introduzca una descripción del correo.");
		String descripcion2 = sc.nextLine();
		System.out.println("Porfavor introduzca el correo del contacto.");
		String correo = sc.nextLine();
		unaAgenda.addCorreo(nombre, descripcion2, correo);
	}

	/**
	 * Metodo para modificar contacto
	 */
	private void mostrarMenuModificarContacto() {
		String nombreBuscado = "";
		int opcion = 0;
		System.out.println("Porfavor introduzca el nombre del contacto que quieres cambiar");
		/**
		 * Controla la entrada por teclado por un try-catch
		 */
		try {
			nombreBuscado = sc.nextLine();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Debe ser una palabra.");
			/**
			 * Antes de modificar el contacto hay que usar el metodo buscar para encontrar a
			 * este en la lista de contactos Control del metodo buscarContacto por un
			 * try-catch
			 */
		}
		try {
			unaAgenda.buscarContacto(nombreBuscado);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}
		/**
		 * Submenu con las diferentes opciones para cambiar la informacion del contacto.
		 * Se implementa un switch
		 */
		do {
			System.out.println("Menú de modificación de contacto. Elige una opción");
			System.out.println("1- Modificar nombre del contacto.");
			System.out.println("2- Modificar apellidos del contacto.");
			System.out.println("3- Modificar código postal del contacto.");
			System.out.println("4- Modificar teléfono del contacto.");
			System.out.println("5- Modificar correo del contacto.");
			System.out.println("0- Pasa salir del menú.");
			try {
				opcion = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("Se debe menter un numero entero.");
			}
			switch (opcion) {
			/**
			 * Para caso se cambia un campo del contacto
			 */
			case 1:
				String nombre = "";
				System.out.println("Porfavor introduzca el nuevo nombre.");
				try {
					try {
						nombre = sc.nextLine();
					} catch (Exception e) {
						System.err.println("El nombre debe ser una palabra.");
					}
					unaAgenda.setNombre(nombreBuscado, nombre);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Porfavor introduzca el nuevo apellido del contacto.");
				String apellido = sc.nextLine();
				unaAgenda.setApellidos(nombreBuscado, apellido);
				break;
			case 3:
				System.out.println("Porfavor introduzca el nuevo codigo postal del contacto.");
				String codigoPostal = sc.nextLine();
				unaAgenda.setCodigoPostal(nombreBuscado, codigoPostal);
				break;
			case 4:
				/**
				 * Para la modificacion del telefono se integra otro menu con otro switch
				 */
				int opcion2 = 0;
				do {
					System.out.println("MODIFICACIÓN TELÉFONO");
					System.out.println("1- Modificar prefijo o número de teléfono.");
					System.out.println("2- Modificar por completo teléfono.");
					System.out.println("0- Para salir del menú.");
					try {
						opcion2 = sc.nextInt();
						sc.nextLine();
					} catch (Exception e) {
						System.err.println("Se debe meter un numero entero.");
					}
					/**
					 * Para cada caso se cambia un campo de telefono
					 */
					switch (opcion2) {
					case 1:
						System.out.println("Porfavor introduzca la descripción del teléfono.");
						String descripcion = sc.nextLine();
						System.out.println("Porfavor introduzca el prefijo de su nuevo teléfono.");
						int prefijo = Integer.parseInt(sc.nextLine());
						System.out.println("Porfavor introduzca el número de su nuevo teléfono.");
						int numero = Integer.parseInt(sc.nextLine());
						unaAgenda.setPrefijoNumero(descripcion, numero, prefijo, nombreBuscado);
						break;
					case 2:
						System.out.println("Porfavor introduzca la descripción del teléfono.");
						String descripcion2 = sc.nextLine();
						System.out.println("Porfavor introduzca la nueva descripción del teléfono.");
						String descripcionNueva = sc.nextLine();
						System.out.println("Porfavor introduzca el prefijo de su nuevo teléfono.");
						int prefijo2 = Integer.parseInt(sc.nextLine());
						System.out.println("Porfavor introduzca el número de su nuevo teléfono.");
						int numero2 = Integer.parseInt(sc.nextLine());
						try {
							unaAgenda.setTodoTelefono(nombreBuscado, descripcion2, prefijo2, numero2, descripcionNueva);
						} catch (Exception e) {
							System.err.println(e.getMessage());
							e.printStackTrace();
						}
						break;
					case 0:
						System.out.println("Saliendo del menú ...");
						return;
					default:
						System.out.println("Saliendo del submenú ...");
						break;
					}
				} while (opcion > 0);

				break;
			case 5:
				/**
				 * Para cambiar el correo se ha creado un menú controlado por un switch
				 */
				int opcion22 = 0;
				do {
					System.out.println("MODIFICACIÓN CORREO");
					System.out.println("1- Modificar solo correo.");
					System.out.println("2- Modificar descripción o correo.");
					System.out.println("0- Para salir del menú.");
					try {
						opcion22 = sc.nextInt();
						sc.nextLine();
					} catch (Exception e) {
						System.err.println("Se debe introducir un numero entero");
					}
					/**
					 * Para cada caso se cambia un campo de correo
					 */
					switch (opcion22) {
					case 1:
						System.out.println("Porfavor introduzca la descripción inicial.");
						String descripcion = sc.nextLine();
						System.out.println("Porfavor introduzca el nuevo correo.");
						String correoNuevo = sc.nextLine();
						unaAgenda.setCorreo(descripcion, correoNuevo, nombreBuscado);
						break;
					case 2:
						System.out.println("Porfavor introduzca la descripción inicial.");
						String descripcion2 = sc.nextLine();
						System.out.println("Porfavor introduzca la nueva descripción del correo.");
						String descripcionNueva = sc.nextLine();
						System.out.println("Porfavor introduzca el nuevo correo.");
						String correo = sc.nextLine();
						try {
							unaAgenda.setTodoCorreo(nombreBuscado, descripcionNueva, correo, descripcion2);
						} catch (Exception e) {
							System.err.println(e.getMessage());
							e.printStackTrace();
						}
						break;
					case 0:
						System.out.println("Saliendo del menú ...");
						return;
					default:
						System.out.println("Saliendo del menú ...");
						break;
					}
				} while (opcion > 0);
				break;
			default:
				System.out.println("La opción no se encuentra en el menú. Inténtelo de nuevo.");
				System.out.println("Saliendo de la aplicación ...");
				break;
			}
		} while (opcion > 0);

	}

	/**
	 * Metodo para buscar un contacto poniendo el nombre del contacto deseado
	 */
	private void mostrarMenuBuscarContacto() {
		String nombre = "";
		System.out.println("BUSCAR CONTACTO");
		System.out.println("Ingrese el nombre del contacto que quiera buscar.");
		/**
		 * Se controla el input del teclado con un try-catch
		 */
		try {
			nombre = sc.nextLine();
		} catch (Exception e) {
			System.err.println("Debe ser una palabra.");
		}
		/**
		 * Se controla el metodo buscar con un try-catch por si no encuentra el nombre
		 * en la lista de contactos
		 */
		try {
			System.out.println(unaAgenda.buscarContacto(nombre));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Metodo para borrar contacto buscandolo por el nombre del contacto deseado
	 */
	private void mostrarMenuBorrarContacto() {
		String nombre = "";
		System.out.println("BORRAR CONTACTO");
		System.out.println("Ingrese el nombre del contacto porfavor.");
		try {
			nombre = sc.nextLine();
		} catch (Exception e) {
			System.err.println("Debe ser una palabra.");
		}
		/**
		 * Condicion de confirmacion, es decir se necesita una confirmacion para poder
		 * borrar un contacto
		 */
		if (nombre != null) {
			System.out.println("Porfavor comfirme su operación, poniendo [S/N]");
			String sino = sc.nextLine();
			if (sino.equals("S")) {
				boolean resultado = unaAgenda.eliminarContacto(nombre);
				if (resultado) {
					System.out.println("Se ha borrado " + nombre + " correctamente.");
				} else {
					System.out.println("Error. No se puede borrar de la agenda un usuario que no existe.");
				}
			}
		} else {
			System.out.println("Operación denegada. Intentelo de nuevo.");
		}
	}

	/**
	 * Metodo que imprime la agenda entera con los contactos existentes.
	 */
	private void mostrarMenuMostrarAgenda() {
		System.out.println(unaAgenda.toString());
	}
}
