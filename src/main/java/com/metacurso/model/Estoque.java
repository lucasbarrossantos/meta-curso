package com.metacurso.model;

import com.metacurso.model.enumeration.TipoOperacao;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotNull(message = "Quantidade é obrigatório")
    @Column(scale = 2, precision = 4)
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Integer quantidade;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_entrada")
    private Date dataEntrada;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_saida")
    private Date dataSaida;

    @NotNull(message = "Material é obrigatório")
    @ManyToOne
    private Materiais material;

    private String observacao;

    @NotNull(message = "Tipo é obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoOperacao operacao;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Materiais getMaterial() {
        return material;
    }

    public void setMaterial(Materiais material) {
        this.material = material;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public TipoOperacao getOperacao() {
        return operacao;
    }

    public void setOperacao(TipoOperacao operacao) {
        this.operacao = operacao;
    }
}