package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner read = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();

    private List<Libro> listaLibros;
    private List<Autor> listaAutores;

    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;

    public Principal(LibroRepository repositoryLibro, AutorRepository repositoryAutor){

        this.repositorioLibro = repositoryLibro;
        this.repositorioAutor = repositoryAutor;

    }



    public void mostrarMenu(){

        /*
        TESTEO DE SI LLEGA EL JSON EN MODO STRING

        var json = consumoAPI.obtenerDatos(URL_BASE);

        System.out.println();
        System.out.println("JSON como String:");
        System.out.println(json);
        System.out.println();

        */
        String opcion = "";

        while (!opcion.equalsIgnoreCase("0")){

            System.out.println();
            System.out.println();
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Bienvenido a Literalura. Tu biblioteca virtual de confianza.");
            System.out.println();
            System.out.println("Ingrese el numero correspondiente a la opcion que desea realizar:");

            System.out.println();
            System.out.println("PULSAR 1: Buscar un libro y añadir sus datos a Literalura.");
            System.out.println("PULSAR 2: Mostrar listado de todos los libros guardados " +
                    "en Literalura.");
            System.out.println("PULSAR 3: Mostrar listado de todos los autores de los cuales" +
                    " hay libros disponibles en Literalura.");
            System.out.println("PULSAR 4: Mostrar listado de todos los autores vivos en un " +
                    "año determinado.");
            System.out.println("PULSAR 5: Mostrar una lista de todos los libros disponibles en" +
                    " un idioma determinado que usted elija.");
            System.out.println("PULSAR 0: Salir del programa.");

            opcion = read.nextLine();

            //verificacion de que el usuario ingreso una opcion correcta:

            while (!opcion.equalsIgnoreCase("1") &&
                    !opcion.equalsIgnoreCase("2") &&
                    !opcion.equalsIgnoreCase("3") &&
                    !opcion.equalsIgnoreCase("4") &&
                    !opcion.equalsIgnoreCase("5") &&
                    !opcion.equalsIgnoreCase("0")){

                System.out.println();
                System.out.println("Usted ingreso un numero o caracter invalido.");
                System.out.println("Por favor, intentelo nuevamente.");

                System.out.println("Ingrese el numero correspondiente a la opcion que desea realizar:");

                System.out.println();
                System.out.println("PULSAR 1: Buscar un libro y añadir sus datos a la biblioteca.");
                System.out.println("PULSAR 2: Mostrar listado de todos los libros guardados " +
                        "en la biblioteca.");
                System.out.println("PULSAR 3: Mostrar listado de todos los autores de los cuales" +
                        " hay libros disponibles en Literalura.");
                System.out.println("PULSAR 4: Mostrar listado de todos los autores vivos en un " +
                        "año determinado.");
                System.out.println("PULSAR 5: Mostrar una lista de todos los libros disponibles en" +
                        " un idioma determinado que usted elija.");
                System.out.println("PULSAR 0: Salir del programa.");

                opcion = read.nextLine();

            }

            switch (opcion){

                case "1":
                    buscarLibro();
                    break;

                case "2":
                    mostrarLibros();
                    break;

                case "3":
                    mostrarAutores();
                    break;

                case "4":
                    autoresVivosDadoUnAnio();
                    break;

                case "5":
                    buscarLibrosPorIdioma();
                    break;

                case "0":
                    System.out.println("Gracias por utilizar Literalura! Esperamos vlver a verte.");
                    System.out.println("Finalizando programa...");
                    break;

            }

            if (opcion.equalsIgnoreCase("0")){
                break;
            }


        }


    }


    public void buscarLibro(){

        //Buscar un libro por titulo:

        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var libroBuscado = read.nextLine();

        //actualizo la direccion porque para buscar por libro es otra URL
        //Ejemplo de URL por libro: https://gutendex.com/books/?search=moby%20dick

        var json = consumoAPI.obtenerDatos(URL_BASE + "?search="
                + libroBuscado.replace(" ", "%20"));

        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroEncontrado = datosBusqueda.listaLibros().stream()
                .filter(t -> t.titulo().toUpperCase().contains(libroBuscado.toUpperCase()))
                .findFirst();

        if (libroEncontrado.isPresent()){

            //Guardando los datos del libro en la base de datos:
            DatosLibro datosLibro = libroEncontrado.get();
            Libro libro = new Libro(datosLibro);
            repositorioLibro.save(libro);

            //guardando los datos del autor del libro en la base de datos:
            DatosAutor datosAutor = libroEncontrado.get().datosDelAutor().get(0);
            Autor autor = new Autor(datosAutor);
            repositorioAutor.save(autor);

            System.out.println("Mejor coincidencia:");
            System.out.println();
            System.out.println(libro);

            /*
            TESTEO DE QUE SE INSTANCIEN BIEN LAS CLASES AUTOR Y LIBRO

            System.out.println("INICIO test abjeto libro:");
            System.out.println();
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Disponible en los siguientes idiomas: " + libro.getIdiomas());
            System.out.println("Numero de veces descargado: " + libro.getNumeroDescargas());
            System.out.println("FIN test abjeto libro:");
            System.out.println("------------------------------------------");
            System.out.println("INICIO test objeto autor:");
            System.out.println();
            System.out.println("Nombre del autor: " + autor.getNombreAutor());
            System.out.println("Fecha de nacimiento: " + autor.getFechaNacimiento());
            System.out.println("Fecha de fallecimiento: " + autor.getFechaFallecimiento());
            System.out.println("FIN test objeto libro");
            */
        } else {
            System.out.println();
            System.out.println("Lo sentimos, no encontramos ningun libro que coincida con" +
                    " su busqueda.");
            System.out.println();
        }

    }

    private void mostrarLibros() {

        listaLibros = repositorioLibro.findAll();

        listaLibros.forEach(System.out::println);
        System.out.println();

    }

    private void mostrarAutores(){

        listaAutores = repositorioAutor.findAll();

        listaAutores.forEach(System.out::println);
        System.out.println();

    }

    private void autoresVivosDadoUnAnio(){

        System.out.println("Ingrese el año en el que desea buscar autores:");
        Double anioAutores = read.nextDouble();
        read.nextLine();

        listaAutores = repositorioAutor.autoresVivosEnAnio(anioAutores);

        System.out.println();
        System.out.println("Autores vivos en el año " + anioAutores + ":");

        for (Autor listaAutore : listaAutores) {

            System.out.println(listaAutore.getNombreAutor());

        }

    }

    private void buscarLibrosPorIdioma(){

        System.out.println("Ingrese el codigo correspondiente al idioma que desea:");
        System.out.println();
        System.out.println("Codigo ES: Español");
        System.out.println("Codigo EN: Ingles");
        System.out.println("Codigo FR: Frances");
        System.out.println("Codigo PT: Portugues");
        System.out.println();
        String idiomaPedido = read.nextLine();

        while (!idiomaPedido.equalsIgnoreCase("es") &&
                !idiomaPedido.equalsIgnoreCase("en") &&
                !idiomaPedido.equalsIgnoreCase("fr") &&
                !idiomaPedido.equalsIgnoreCase("pt")){

            System.out.println("Codigo incorrecto. Por favor, intentelo nuevamente.");
            System.out.println();

            System.out.println("Ingrese el codigo correspondiente al idioma que desea:");
            System.out.println();
            System.out.println("Codigo ES: Español");
            System.out.println("Codigo EN: Ingles");
            System.out.println("Codigo FR: Frances");
            System.out.println("Codigo PT: Portugues");
            System.out.println();
            idiomaPedido = read.nextLine();

        }

        listaLibros = repositorioLibro.findAll();

        //boolean idiomaEncontrado = false;

        for (int i = 0; i < listaLibros.size(); i++) {

            if (listaLibros.get(i).getIdiomas().contains(idiomaPedido)){

                System.out.println(listaLibros.get(i).getTitulo());

            }

        }

    }


}
