package com.metacurso.model.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TurmaDTO {

    private String nome_turma;
    private Date data_inicio;
    private Integer n_vagas;
    private BigDecimal valor_curso;
    private Integer curso_id;

    public TurmaDTO(String nome_turma, Date data_inicio, Integer n_vagas, BigDecimal valor_curso, Integer curso_id) {
        this.nome_turma = nome_turma;
        this.data_inicio = data_inicio;
        this.n_vagas = n_vagas;
        this.valor_curso = valor_curso;
        this.curso_id = curso_id;
    }

    public String getNome_turma() {
        return nome_turma;
    }

    public void setNome_turma(String nome_turma) {
        this.nome_turma = nome_turma;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Integer getN_vagas() {
        return n_vagas;
    }

    public void setN_vagas(Integer n_vagas) {
        this.n_vagas = n_vagas;
    }

    public BigDecimal getValor_curso() {
        return valor_curso;
    }

    public void setValor_curso(BigDecimal valor_curso) {
        this.valor_curso = valor_curso;
    }

    public Integer getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(Integer curso_id) {
        this.curso_id = curso_id;
    }
}
