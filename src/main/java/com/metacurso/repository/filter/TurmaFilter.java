package com.metacurso.repository.filter;

import java.util.Date;

public class TurmaFilter {

    private String nome;
    private String nomeCurso;
    private Date inicioDe;
    private Date inicioAte;
    private Date terminoDe;
    private Date terminoAte;
    private Integer status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Date getInicioDe() {
        return inicioDe;
    }

    public void setInicioDe(Date inicioDe) {
        this.inicioDe = inicioDe;
    }

    public Date getInicioAte() {
        return inicioAte;
    }

    public void setInicioAte(Date inicioAte) {
        this.inicioAte = inicioAte;
    }

    public Date getTerminoDe() {
        return terminoDe;
    }

    public void setTerminoDe(Date terminoDe) {
        this.terminoDe = terminoDe;
    }

    public Date getTerminoAte() {
        return terminoAte;
    }

    public void setTerminoAte(Date terminoAte) {
        this.terminoAte = terminoAte;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
