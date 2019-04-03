package com.metacurso.model.vo;

public class MatriculaReciboDTO {

    private String nomeAluno;
    private Double valor;
    private String nomeEmpresa;
    private String cnpjEmpresa;
    private String enderecoEmpresa;
    private String numEndEmpresa;
    private String bairroEmpresa;
    private String cepEmpresa;
    private String telefoneEmpresa;

    public MatriculaReciboDTO(String nomeAluno,
                              Double valor,
                              String nomeEmpresa,
                              String cnpjEmpresa,
                              String enderecoEmpresa,
                              String numEndEmpresa,
                              String bairroEmpresa,
                              String cepEmpresa,
                              String telefoneEmpresa) {
        this.nomeAluno = nomeAluno;
        this.valor = valor;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
        this.enderecoEmpresa = enderecoEmpresa;
        this.numEndEmpresa = numEndEmpresa;
        this.bairroEmpresa = bairroEmpresa;
        this.cepEmpresa = cepEmpresa;
        this.telefoneEmpresa = telefoneEmpresa;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getEnderecoEmpresa() {
        return enderecoEmpresa;
    }

    public void setEnderecoEmpresa(String enderecoEmpresa) {
        this.enderecoEmpresa = enderecoEmpresa;
    }

    public String getNumEndEmpresa() {
        return numEndEmpresa;
    }

    public void setNumEndEmpresa(String numEndEmpresa) {
        this.numEndEmpresa = numEndEmpresa;
    }

    public String getBairroEmpresa() {
        return bairroEmpresa;
    }

    public void setBairroEmpresa(String bairroEmpresa) {
        this.bairroEmpresa = bairroEmpresa;
    }

    public String getCepEmpresa() {
        return cepEmpresa;
    }

    public void setCepEmpresa(String cepEmpresa) {
        this.cepEmpresa = cepEmpresa;
    }

    public String getTelefoneEmpresa() {
        return telefoneEmpresa;
    }

    public void setTelefoneEmpresa(String telefoneEmpresa) {
        this.telefoneEmpresa = telefoneEmpresa;
    }
}
