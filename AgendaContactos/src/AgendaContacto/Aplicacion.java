package AgendaContacto;

import java.util.Scanner;

import Depurador.Depurador;
/**
 * @author DARIO
 * @version v1.0
 * @since 2026-02-17
 */


public class Aplicacion {
	Scanner sc = new Scanner(System.in);
	Agenda unaAgenda = new Agenda();

	public static void main(String[] args) {
		Aplicacion unApp = new Aplicacion();
	}

	public Aplicacion() {
		try {
			Cargador.cargarContactos(unaAgenda);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		int opcion = 0;
		do {
			System.out.println("Bienvenido a nuestra agenda");
			System.out.println("1) Añadir contacto");
			System.out.println("2) Modificar contacto");
			System.out.println("3) Buscar contacto");
			System.out.println("4) Eliminar contacto");
			System.out.println("5) Mostrar agenda completa");
			try {
				opcion = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("Se debe meter un numero entero. Intentelo de nuevo.");
				e.printStackTrace();
			}
			switch (opcion) {
			case 1:
				mostrarMenuAñadirContacto();
				break;
			case 2:
				mostrarMenuModificarContacto();
				break;
			case 3:
				mostrarMenuBuscarContacto();
				break;
			case 4:
				mostrarMenuBorrarContacto();
				break;
			case 5:
				mostrarMenuMostrarAgenda();
				break;
			default:
				System.out.println("La opción elegida no esta en el menu.");
				System.out.println("Saliendo de la aplicación...");
				break;
			}

		} while (opcion >= 1 && opcion < 6);
		sc.close();
	}

	private void mostrarMenuAñadirContacto() {
		String nombre = "";
		System.out.println("AÑADIR CONTACTO");
		System.out.println("Porfavor introduzca el nombre del contacto.");
		try {
			nombre = sc.nextLine();
		} catch (Exception e) {
			System.err.println("Debe ser una palabra.");
		}
		try {
			unaAgenda.addContacto(nombre);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
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

	private void mostrarMenuModificarContacto() {
		String nombreBuscado = "";
		int opcion;
		System.out.println("Porfavor introduzca el nombre del contacto que quieres cambiar");
		try {
			nombreBuscado = sc.nextLine();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Debe ser una palabra.");
		}
		try {
			unaAgenda.buscarContacto(nombreBuscado);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}

		do {
			System.out.println("Menú de modificación de contacto. Elige una opción");
			System.out.println("1- Modificar nombre del contacto.");
			System.out.println("2- Modificar apellidos del contacto.");
			System.out.println("3- Modificar código postal del contacto.");
			System.out.println("4- Modificar teléfono del contacto.");
			System.out.println("5- Modificar correo del contacto.");
			System.out.println("0- Pasa salir del menú.");
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {

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
				do {
					System.out.println("MODIFICACIÓN TELÉFONO");
					System.out.println("1- Modificar prefijo o número de teléfono.");
					System.out.println("2- Modificar por completo teléfono.");
					System.out.println("0- Para salir del menú.");
					int opcion2 = sc.nextInt();
					sc.nextLine();
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
						unaAgenda.setTodoTelefono(nombreBuscado, descripcion2, prefijo2, numero2, descripcionNueva);
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
				do {
					System.out.println("MODIFICACIÓN CORREO");
					System.out.println("1- Modificar solo correo.");
					System.out.println("2- Modificar descripción o correo.");
					System.out.println("0- Para salir del menú.");
					int opcion2 = sc.nextInt();
					sc.nextLine();
					switch (opcion2) {
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

	private void mostrarMenuBuscarContacto() {
		String nombre = "";
		System.out.println("BUSCAR CONTACTO");
		System.out.println("Ingrese el nombre del contacto que quiera buscar.");
		try {
			nombre = sc.nextLine();
		} catch (Exception e) {
			System.err.println("Debe ser una palabra.");
		}
		try {
			System.out.println(unaAgenda.buscarContacto(nombre));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private void mostrarMenuBorrarContacto() {
		String nombre = "";
		System.out.println("BORRAR CONTACTO");
		System.out.println("Ingrese el nombre del contacto porfavor.");
		try {
			nombre = sc.nextLine();
		} catch (Exception e) {
			System.err.println("Debe ser una palabra.");
		}
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

	private void mostrarMenuMostrarAgenda() {
		System.out.println(unaAgenda.toString());
	}
}
