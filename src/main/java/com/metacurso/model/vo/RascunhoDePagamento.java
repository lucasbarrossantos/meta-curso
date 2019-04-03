package com.metacurso.model.vo;

import com.metacurso.model.Categorias;
import com.metacurso.model.Matriculas;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "rascunhos_de_pagamentos")
public class RascunhoDePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    private String rascunho_descricao;

    @ManyToOne
    @JoinColumn(name = "rascunho_forma_de_pagamento", referencedColumnName = "codigo")
    private Categorias rascunho_forma_de_pagamento;

    @Min(value = 1, message = "Valor mínimo de parcelas é 1")
    private Integer rascunho_numero_da_parcelas;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date rascunho_data_de_vencimento;

    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private BigDecimal rascunho_valor;

    @ManyToOne
    @JoinColumn(name = "matricula", referencedColumnName = "codigo", nullable = false)
    private Matriculas matricula;

    public String getRascunho_descricao() {
        return rascunho_descricao;
    }

    public void setRascunho_descricao(String rascunho_descricao) {
        this.rascunho_descricao = rascunho_descricao;
    }

    public Categorias getRascunho_forma_de_pagamento() {
        return rascunho_forma_de_pagamento;
    }

    public void setRascunho_forma_de_pagamento(Categorias rascunho_forma_de_pagamento) {
        this.rascunho_forma_de_pagamento = rascunho_forma_de_pagamento;
    }

    public Integer getRascunho_numero_da_parcelas() {
        return rascunho_numero_da_parcelas;
    }

    public void setRascunho_numero_da_parcelas(Integer rascunho_numero_da_parcelas) {
        this.rascunho_numero_da_parcelas = rascunho_numero_da_parcelas;
    }

    public Date getRascunho_data_de_vencimento() {
        return rascunho_data_de_vencimento;
    }

    public void setRascunho_data_de_vencimento(Date rascunho_data_de_vencimento) {
        this.rascunho_data_de_vencimento = rascunho_data_de_vencimento;
    }

    public BigDecimal getRascunho_valor() {
        return rascunho_valor;
    }

    public void setRascunho_valor(BigDecimal rascunho_valor) {
        this.rascunho_valor = rascunho_valor;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Matriculas getMatricula() {
        return matricula;
    }

    public void setMatricula(Matriculas matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "RascunhoDePagamento{" +
                "codigo=" + codigo +
                ", rascunho_descricao='" + rascunho_descricao + '\'' +
                ", rascunho_forma_de_pagamento=" + rascunho_forma_de_pagamento +
                ", rascunho_numero_da_parcelas=" + rascunho_numero_da_parcelas +
                ", rascunho_data_de_vencimento=" + rascunho_data_de_vencimento +
                ", rascunho_valor=" + rascunho_valor +
                '}';
    }
}
