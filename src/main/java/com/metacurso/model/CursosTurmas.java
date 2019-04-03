package com.metacurso.model;

import javax.persistence.*;

/**
 * Created by luciano on 11/6/18.
 */

@Entity
@Table(name = "cursos_turmas")
public class CursosTurmas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @ManyToOne
    @JoinColumn(name = "curso", referencedColumnName = "codigo")
    private Cursos curso;
    @ManyToOne
    @JoinColumn(name = "turma", referencedColumnName = "codigo")
    private Turmas turma;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

    public Turmas getTurma() {
        return turma;
    }

    public void setTurma(Turmas turma) {
        this.turma = turma;
    }
}