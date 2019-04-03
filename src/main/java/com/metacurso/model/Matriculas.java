package com.metacurso.model;

import com.metacurso.model.vo.RascunhoDePagamento;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by luciano on 18/6/18.
 */

@Entity
@Table(name = "matriculas")
public class Matriculas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date data_cadastro;
    @NotNull(message = "Curso é obrigatório")
    @ManyToOne
    @JoinColumn(name = "curso", referencedColumnName = "codigo")
    private Cursos curso;
    @NotNull(message = "Turma é obrigatório")
    @ManyToOne
    @JoinColumn(name = "turma", referencedColumnName = "codigo")
    private Turmas turma;
    @ManyToOne
    @JoinColumn(name = "forma_pagamento", referencedColumnName = "codigo")
    private Categorias forma_pagamento;
    private String observacoes;
    @ManyToOne
    @JoinColumn(name = "aluno", referencedColumnName = "codigo")
    private Pessoas aluno;
    private Integer lingua_estrangeira;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double valor;
    @Column(name = "data_entrada")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataentrada = new Date();
    private Integer n_parcelas;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double valor_parcela;
    @ManyToOne
    @JoinColumn(name = "forma_pagamento_parcela", referencedColumnName = "codigo")
    private Categorias forma_pagamento_parcela;
    @Column(name = "data_primeira_parcela")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataprimeiraparcela;
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL)
    private List<MateriaisMatriculas> materiais;
    @Transient
    private Integer horario;
    private Integer situacao;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double entrada;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double taxa_matricula;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double valor_material;
    @ManyToOne
    @JoinColumn(name = "forma_pagamento_material", referencedColumnName = "codigo")
    private Categorias forma_pagamento_material;
    private Integer n_parcelas_material;
    @Column(name = "data_primeira_parcela_material")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataprimeiraparcelamaterial;
    private Boolean financeiro_gerado;
    @NotNull(message = "Empresa é obrigatório")
    @ManyToOne
    private Empresas empresa;

    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL)
    private List<RascunhoDePagamento> rascunhosDePagamentos;

    @Transient
    private RascunhoDePagamento rascunhoDePagamento;

    @Column(name = "observacao_de_pagamento", length = 2000)
    private String observacaoDePagamento;

    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double valor_curso;

    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double valor_desconto;

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
        this.data_cadastro = new Date();
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

    public Integer getLingua_estrangeira() {
        return lingua_estrangeira;
    }

    public void setLingua_estrangeira(Integer lingua_estrangeira) {
        this.lingua_estrangeira = lingua_estrangeira;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataentrada() {
        return dataentrada;
    }

    public void setDataentrada(Date dataentrada) {
        this.dataentrada = dataentrada;
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

    public Categorias getForma_pagamento_parcela() {
        return forma_pagamento_parcela;
    }

    public void setForma_pagamento_parcela(Categorias forma_pagamento_parcela) {
        this.forma_pagamento_parcela = forma_pagamento_parcela;
    }

    public Date getDataprimeiraparcela() {
        return dataprimeiraparcela;
    }

    public void setDataprimeiraparcela(Date dataprimeiraparcela) {
        this.dataprimeiraparcela = dataprimeiraparcela;
    }

    public List<MateriaisMatriculas> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<MateriaisMatriculas> materiais) {
        this.materiais = materiais;
    }

    public Integer getHorario() {
        return horario;
    }

    public void setHorario(Integer horario) {
        this.horario = horario;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Double getEntrada() {
        return entrada;
    }

    public void setEntrada(Double entrada) {
        this.entrada = entrada;
    }

    public Double getTaxa_matricula() {
        return taxa_matricula;
    }

    public void setTaxa_matricula(Double taxa_matricula) {
        this.taxa_matricula = taxa_matricula;
    }

    public Double getValor_material() {
        return valor_material;
    }

    public void setValor_material(Double valor_material) {
        this.valor_material = valor_material;
    }

    public Categorias getForma_pagamento_material() {
        return forma_pagamento_material;
    }

    public void setForma_pagamento_material(Categorias forma_pagamento_material) {
        this.forma_pagamento_material = forma_pagamento_material;
    }

    public Integer getN_parcelas_material() {
        return n_parcelas_material;
    }

    public void setN_parcelas_material(Integer n_parcelas_material) {
        this.n_parcelas_material = n_parcelas_material;
    }

    public Date getDataprimeiraparcelamaterial() {
        return dataprimeiraparcelamaterial;
    }

    public void setDataprimeiraparcelamaterial(Date dataprimeiraparcelamaterial) {
        this.dataprimeiraparcelamaterial = dataprimeiraparcelamaterial;
    }

    public Boolean getFinanceiro_gerado() {
        return financeiro_gerado;
    }

    public void setFinanceiro_gerado(Boolean financeiro_gerado) {
        this.financeiro_gerado = financeiro_gerado;
    }

    public Empresas getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    public List<RascunhoDePagamento> getRascunhosDePagamentos() {
        return rascunhosDePagamentos;
    }

    public void setRascunhosDePagamentos(List<RascunhoDePagamento> rascunhosDePagamentos) {
        this.rascunhosDePagamentos = rascunhosDePagamentos;
    }

    public RascunhoDePagamento getRascunhoDePagamento() {
        return rascunhoDePagamento;
    }

    public void setRascunhoDePagamento(RascunhoDePagamento rascunhoDePagamento) {
        this.rascunhoDePagamento = rascunhoDePagamento;
    }

    public String getObservacaoDePagamento() {
        return observacaoDePagamento;
    }

    public void setObservacaoDePagamento(String observacaoDePagamento) {
        this.observacaoDePagamento = observacaoDePagamento;
    }

    public Double getValor_curso() {
        return valor_curso;
    }

    public void setValor_curso(Double valor_curso) {
        this.valor_curso = valor_curso;
    }

    public Double getValor_desconto() {
        return valor_desconto;
    }

    public void setValor_desconto(Double valor_desconto) {
        this.valor_desconto = valor_desconto;
    }
}
