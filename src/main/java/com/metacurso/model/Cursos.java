package com.metacurso.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @NotNull
    private String nome;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "codigo")
    private Categorias categoria;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @OrderBy(value = "codigo desc")
    @JsonManagedReference
    private List<CursosDisciplinas> disciplinas;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "codigo desc")
    private List<CursosMateriais> materiais;
    @Transient
    private CursosDisciplinas disciplina;
    private String observacoes;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double taxa_matricula;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double total_material;
    @Min(value = 1, message = "Valor mínimo é R$ 1,00")
    @NotNull(message = "Valor do curso deve ser informado")
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    @Column(name = "valor_curso")
    private BigDecimal valorCurso;
    private Integer status;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public List<CursosDisciplinas> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<CursosDisciplinas> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<CursosMateriais> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<CursosMateriais> materiais) {
        this.materiais = materiais;
    }

    public CursosDisciplinas getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(CursosDisciplinas disciplina) {
        this.disciplina = disciplina;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Double getTaxa_matricula() {
        return taxa_matricula;
    }

    public void setTaxa_matricula(Double taxa_matricula) {
        this.taxa_matricula = taxa_matricula;
    }

    public Double getTotal_material() {
        return total_material;
    }

    public void setTotal_material(Double total_material) {
        this.total_material = total_material;
    }

    public BigDecimal getValorCurso() {
        return valorCurso;
    }

    public void setValorCurso(BigDecimal valorCurso) {
        this.valorCurso = valorCurso;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}