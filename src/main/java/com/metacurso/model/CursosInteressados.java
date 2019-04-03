package com.metacurso.model;

import javax.persistence.*;

/**
 * Created by luciano on 16/7/18.
 */

@Entity
@Table(name = "cursos_interessados")
public class CursosInteressados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @ManyToOne
    @JoinColumn(name = "interessado", referencedColumnName = "codigo")
    private Interessados interessado;
    @ManyToOne
    @JoinColumn(name = "curso", referencedColumnName = "codigo")
    private Cursos curso;


    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Interessados getInteressado() {
        return interessado;
    }

    public void setInteressado(Interessados interessado) {
        this.interessado = interessado;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }
}

