package com.metacurso.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by luciano on 18/6/18.
 */

@Entity
@Table(name = "titulos")
public class Titulos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data_cadastro;
    @ManyToOne
    @JoinColumn(name = "matricula", referencedColumnName = "codigo")
    private Matriculas matricula;
    @NotBlank(message = "Titular é obrigatório")
    private String titular;
    private String cpf_cnpj;
    private String telefone;
    private String email;
    @NotNull(message = "Data de Vencimento é obrigatório")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date vencimento;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date pagamento;
    @Min(value = 1, message = "Valor mínimo é R$ 1,00")
    @NotNull(message = "Valor deve ser informado")
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double valor;
    private Integer n_parcelas;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double valor_parcela;
    @NotNull(message = "Forma de pagamento é obrigatório")
    @ManyToOne
    @JoinColumn(name = "forma_pagamento", referencedColumnName = "codigo")
    private Categorias forma_pagamento;
    private String observacoes;
    @ManyToOne
    @JoinColumn(name = "aluno", referencedColumnName = "codigo")
    private Pessoas aluno;
    private Integer tipo;
    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;
    private Integer parcela_atual;
    @ManyToOne
    @JoinColumn(name = "plano_contas", referencedColumnName = "codigo")
    private Categorias plano_contas;

    private Integer situacao;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public Matriculas getMatricula() {
        return matricula;
    }

    public void setMatricula(Matriculas matricula) {
        this.matricula = matricula;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Date getPagamento() {
        return pagamento;
    }

    public void setPagamento(Date pagamento) {
        this.pagamento = pagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getN_parcelas() {
        return n_parcelas;
    }

    public void setN_parcelas(Integer n_parcelas) {
        this.n_parcelas = n_parcelas;
    }

    public Double getValor_parcela() {
        return valor_parcela;
    }

    public void setValor_parcela(Double valor_parcela) {
        this.valor_parcela = valor_parcela;
    }

    public Categorias getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(Categorias forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Pessoas getAluno() {
        return aluno;
    }

    public void setAluno(Pessoas aluno) {
        this.aluno = aluno;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getParcela_atual() {
        return parcela_atual;
    }

    public void setParcela_atual(Integer parcela_atual) {
        this.parcela_atual = parcela_atual;
    }


    public Categorias getPlano_contas() {
        return plano_contas;
    }

    public void setPlano_contas(Categorias plano_contas) {
        this.plano_contas = plano_contas;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }
}