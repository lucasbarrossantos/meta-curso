package com.metacurso.model.vo;

import java.util.Date;

public class HorarioDTO {

    private Integer codigo;
    private String inicio;
    private String fim;
    private Integer dia;
    private Date dataAula;
    private String nomeProfessor;
    private Integer codigoProfessor;
    private String nomeDisciplina;
    private Integer codigoDisciplina;

    public HorarioDTO(Integer codigo,
                      String inicio,
                      String fim,
                      Integer dia,
                      Date dataAula,
                      String nomeProfessor,
                      Integer codigoProfessor,
                      String nomeDisciplina,
                      Integer codigoDisciplina) {
        this.codigo = codigo;
        this.inicio = inicio;
        this.fim = fim;
        this.dia = dia;
        this.dataAula = dataAula;
        this.nomeProfessor = nomeProfessor;
        this.codigoProfessor = codigoProfessor;
        this.nomeDisciplina = nomeDisciplina;
        this.codigoDisciplina = codigoDisciplina;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Date getDataAula() {
        return dataAula;
    }

    public void setDataAula(Date dataAula) {
        this.dataAula = dataAula;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public Integer getCodigoProfessor() {
        return codigoProfessor;
    }

    public void setCodigoProfessor(Integer codigoProfessor) {
        this.codigoProfessor = codigoProfessor;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public Integer getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(Integer codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }
}
