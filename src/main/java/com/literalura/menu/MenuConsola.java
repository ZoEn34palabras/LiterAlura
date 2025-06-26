package com.literalura.menu;

import com.literalura.gutendex.GutendexClient;
import com.literalura.gutendex.dto.LibroDTO;
import com.literalura.model.Autor;
import com.literalura.model.Libro;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LibroRepository;
import com.literalura.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MenuConsola implements CommandLineRunner {

    private final GutendexClient gutendexClient = new GutendexClient();
    private final LibroService libroService;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public MenuConsola(LibroService libroService, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroService = libroService;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n      *** Menú LiterAlura ***");
            System.out.println("1. Buscar y guardar libro por título");
            System.out.println("2. Mostrar todos los libros");
            System.out.println("3. Mostrar libros por idioma (en/es)");
            System.out.println("4. Mostrar autores vivos en un año");
            System.out.println("5. Mostrar los 10 libros más descargados");
            System.out.println("0. Salir");
            System.out.print("\n Ingrese una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n *** Opcion Inválida ***");
                continue;
            }
            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingrese el título del libro: ");
                    String titulo = sc.nextLine();
                    LibroDTO libroDTO = gutendexClient.buscarLibroPorTitulo(titulo);

                    if (libroDTO != null) {
                        Libro libroGuardado = libroService.guardarLibroDesdeDTO(libroDTO);
                        if (libroGuardado != null) {
                            System.out.println("Libro Guardado:\n " + libroGuardado);
                        } else {
                            System.out.println("No se puede guardar el libro");
                        }
                    } else {
                        System.out.println("No se encontró el libro en la API");
                    }
                }
                case 2 -> {
                    List<Libro> libros = libroRepository.findAll();
                    if (libros.isEmpty()) {
                        System.out.println("No hay libros guardados");
                    } else {
                        libros.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.println("Idiomas disponibles");
                    System.out.println("1.Inglés (en)");
                    System.out.println("2.Español (es)");
                    System.out.println("Seleccione una opción");
                    String idiomaSeleccionado = sc.nextLine().trim();

                    String idioma = switch (idiomaSeleccionado) {
                        case "1", "en" -> "en";
                        case "2", "es" -> "es";
                        default -> null;
                    };

                    if (idioma == null) {
                        System.out.println("Idioma inválido. Elige 1 o 2");
                        break;
                    }

                    List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma);
                    if (librosPorIdioma.isEmpty()) {
                        System.out.println("No hay libros en el idioma '" + idioma + "'");
                    } else {
                        System.out.println("Libros en el idioma '" + idioma + "':");
                        librosPorIdioma.forEach(System.out::println);
                    }
                }
                case 4 -> {
                    System.out.println("Ingrese el año en el que desea buscar autores vivos");
                    String entrada = sc.nextLine().trim();

                    int año;
                    try {
                        año = Integer.parseInt(entrada);
                    } catch (NumberFormatException e) {
                        System.out.println("Año inválido. Debe ingresar un número");
                        break;
                    }

                    List<Autor> autoresVivos = autorRepository.findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(año, año);
                    if (autoresVivos.isEmpty()) {
                        System.out.println("No se encontraron autores vivos en el año " + año);
                    } else {
                        System.out.println("Autores vivos en " + año + ":");
                        autoresVivos.forEach(System.out::println);
                    }
                }
                case 5 -> {
                    List<Libro> topLibros = libroRepository.findTop10ByOrderByDescargasDesc();
                    if (topLibros.isEmpty()) {
                        System.out.println("No hay libros en la base de datos");
                    } else {
                        System.out.println("Top 10 libros más descargados:");
                        topLibros.forEach(libro -> System.out.println(
                                libro.getTitulo() + " - " + libro.getDescargas() + " descargas"));
                    }

                }
                case 0 -> System.out.println("Gracias por usar la aplicación");
                default -> System.out.println("Opción inválida");

            }
       }
    }
}
