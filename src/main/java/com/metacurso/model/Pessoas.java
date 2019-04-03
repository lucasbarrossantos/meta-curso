package com.metacurso.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by luciano on 13/6/18.
 */

@Entity
@Table(name = "pessoas")
public class Pessoas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_cadastro;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @CPF(message = "Informe um CPF válido")
    private String cpf;
    private String rg;
    private String imagem;
    private String genero;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_nascimento")
    private Date datanascimento;
    @Email(message = "Informe um e-mail válido")
    private String email;
    private String telefone;
    private String celular;
    private String endereco;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private Boolean possui_responsavel;
    private String nome_responsavel;
    @CPF(message = "Informe um CPF válido para o responsável")
    private String cpf_responsavel;
    private String rg_responsavel;
    private String email_responsavel;
    private String telefone_responsavel;
    private String celular_responsavel;
    private Integer tipo;
    @CNPJ(message = "Informe um CNPJ válido")
    private String cnpj;
    private String senha;
    private String disciplinas_atuacao;
    private String pis_cartao_cidadao;
    private String titulo_eleitor;
    private String escolaridade;
    private Integer estado_civil;
    private Integer filhos_zero_sete;
    private Integer filhos_sete_treze;
    private String nome_banco;
    private String agencia;
    private String conta;
    private String operacao;
    private String forma_contratacao;
    private String orgao_expedidor_rg;
    private String uf_rg;
    private String numero;
    private String role;
    private Boolean receberNotificacoes;


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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf != null && cpf.length() == 0) {
            cpf = null;
        }
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Boolean getPossui_responsavel() {
        return possui_responsavel;
    }

    public void setPossui_responsavel(Boolean possui_responsavel) {
        this.possui_responsavel = possui_responsavel;
    }

    public String getNome_responsavel() {
        return nome_responsavel;
    }

    public void setNome_responsavel(String nome_responsavel) {
        this.nome_responsavel = nome_responsavel;
    }

    public String getCpf_responsavel() {
        return cpf_responsavel;
    }

    public void setCpf_responsavel(String cpf_responsavel) {
        if (cpf_responsavel != null && cpf_responsavel.length() == 0) {
            cpf_responsavel = null;
        }
        this.cpf_responsavel = cpf_responsavel;
    }

    public String getRg_responsavel() {
        return rg_responsavel;
    }

    public void setRg_responsavel(String rg_responsavel) {
        this.rg_responsavel = rg_responsavel;
    }

    public String getEmail_responsavel() {
        return email_responsavel;
    }

    public void setEmail_responsavel(String email_responsavel) {
        this.email_responsavel = email_responsavel;
    }

    public String getTelefone_responsavel() {
        return telefone_responsavel;
    }

    public void setTelefone_responsavel(String telefone_responsavel) {
        this.telefone_responsavel = telefone_responsavel;
    }

    public String getCelular_responsavel() {
        return celular_responsavel;
    }

    public void setCelular_responsavel(String celular_responsavel) {
        this.celular_responsavel = celular_responsavel;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDisciplinas_atuacao() {
        return disciplinas_atuacao;
    }

    public void setDisciplinas_atuacao(String disciplinas_atuacao) {
        this.disciplinas_atuacao = disciplinas_atuacao;
    }

    public String getPis_cartao_cidadao() {
        return pis_cartao_cidadao;
    }

    public void setPis_cartao_cidadao(String pis_cartao_cidadao) {
        this.pis_cartao_cidadao = pis_cartao_cidadao;
    }

    public String getTitulo_eleitor() {
        return titulo_eleitor;
    }

    public void setTitulo_eleitor(String titulo_eleitor) {
        this.titulo_eleitor = titulo_eleitor;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Integer getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(Integer estado_civil) {
        this.estado_civil = estado_civil;
    }

    public Integer getFilhos_zero_sete() {
        return filhos_zero_sete;
    }

    public void setFilhos_zero_sete(Integer filhos_zero_sete) {
        this.filhos_zero_sete = filhos_zero_sete;
    }

    public Integer getFilhos_sete_treze() {
        return filhos_sete_treze;
    }

    public void setFilhos_sete_treze(Integer filhos_sete_treze) {
        this.filhos_sete_treze = filhos_sete_treze;
    }

    public String getNome_banco() {
        return nome_banco;
    }

    public void setNome_banco(String nome_banco) {
        this.nome_banco = nome_banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getForma_contratacao() {
        return forma_contratacao;
    }

    public void setForma_contratacao(String forma_contratacao) {
        this.forma_contratacao = forma_contratacao;
    }

    public String getOrgao_expedidor_rg() {
        return orgao_expedidor_rg;
    }

    public void setOrgao_expedidor_rg(String orgao_expedidor_rg) {
        this.orgao_expedidor_rg = orgao_expedidor_rg;
    }

    public String getUf_rg() {
        return uf_rg;
    }

    public void setUf_rg(String uf_rg) {
        this.uf_rg = uf_rg;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getReceberNotificacoes() {
        return receberNotificacoes;
    }

    public void setReceberNotificacoes(Boolean receberNotificacoes) {
        this.receberNotificacoes = receberNotificacoes;
    }
}