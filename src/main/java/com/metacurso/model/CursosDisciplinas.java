package com.metacurso.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by luciano on 11/6/18.
 */

@Entity
@Table(name = "cursos_disciplinas")
public class CursosDisciplinas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @ManyToOne
    @JoinColumn(name = "curso", referencedColumnName = "codigo", nullable = false)
    @JsonBackReference
    private Cursos curso;
    @ManyToOne
    @JoinColumn(name = "disciplina", referencedColumnName = "codigo")
    private Disciplinas disciplina;
    private Double valor;
    @Transient
    private Boolean selecionada;

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

    public Disciplinas getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplinas disciplina) {
        this.disciplina = disciplina;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getSelecionada() {
        return selecionada;
    }

    public void setSelecionada(Boolean selecionada) {
        this.selecionada = selecionada;
    }
}