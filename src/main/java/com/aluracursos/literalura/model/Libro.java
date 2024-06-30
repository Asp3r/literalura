package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")

public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String titulo;
    private String autor;
    private List<String> idiomas;
    private Double numeroDescargas;

    //CONSTRUCTORES -----------------------------------------------------------------------------

    public Libro(DatosLibro datosLibro){

        DatosAutor datosAutor = datosLibro.datosDelAutor().get(0);

        this.titulo = datosLibro.titulo();
        this.autor = datosAutor.nombre();
        this.idiomas = datosLibro.idiomas();
        this.numeroDescargas = datosLibro.numeroDescargas();


    }

    public Libro(){} //el repository necesita un constructor vacio

    //GETTERS Y SETTERS -------------------------------------------------------------------------

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }


    //TO STRING ---------------------------------------------------------------------


    @Override
    public String toString() {
        return "Titulo: " + titulo + '\'' +
                ", Autor: " + autor + '\'' +
                ", Idiomas disponibles: " + idiomas +
                ", numeroDescargas: " + numeroDescargas;
    }
}
