package com.aluracursos.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")

public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String nombreAutor;
    private Double fechaNacimiento;
    private Double fechaFallecimiento;

    //CONSTRUCTORES --------------------------------------------------------------------------

    public Autor(){} //el repository necesita un constructor vacio

    public Autor(DatosAutor datosAutor){

        this.nombreAutor = datosAutor.nombre();
        this.fechaNacimiento = Double.valueOf(datosAutor.anioNacimiento());
        this.fechaFallecimiento = Double.valueOf(datosAutor.anioFallecimiento());

    }

    //GETTERS Y SETTERS ----------------------------------------------------------------------

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Double getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Double fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Double getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Double fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }


    //TO STRING ------------------------------------------------------------------


    @Override
    public String toString() {
        return "Nombre del Autor: " + nombreAutor + '\'' +
                ", Fecha de nacimiento: " + fechaNacimiento +
                ", Fecha de fallecimiento: " + fechaFallecimiento;
    }
}
