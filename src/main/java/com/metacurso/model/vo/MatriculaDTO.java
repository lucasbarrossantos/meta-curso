package com.metacurso.model.vo;

import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

public class MatriculaDTO {

    //Dados da Matrícula
    private Integer codigo;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double valor;
    private String observacoes;
    private String observacaoDePagamento;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double taxa_matricula;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double valor_material;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double valor_curso;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double valor_desconto;

    // Dados do Aluno
    private String nome;
    private String email;
    private Date dataDeNascimento;
    private String cpf;
    private String rg;
    private String orgaoExpedidor;
    private String ufRg;
    private String telefone;
    private String celular;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String nomeResponsavel;
    private String cpfResponsavel;
    private String rgResponsavel;
    private String celularResponsavel;
    private Boolean possui_responsavel;

    //Dados do curso
    private String nomeCurso;

    //Dados da turma
    private String nomeTurma;
    private Date dataDeInicio;
    private Date dataDeTermino;
    private String turno;

    //Dados da empresa
    private String nomeEmpresa;
    private String cnpjEmpresa;
    private String enderecoEmpresa;
    private String numeroEndEmpresa;
    private String bairroEndEmpresa;
    private String cidadeEndEmpresa;
    private String ufEndEmpresa;
    private String cepEndEmpresa;
    private String telefoneEmpresa;
    private String celularEmpresa;

    public MatriculaDTO(Integer codigo,
                        Double valor,
                        String observacaoDePagamento,
                        String observacoes,
                        Double taxa_matricula,
                        Double valor_material,
                        Double valor_curso,
                        Double valor_desconto,
                        String nome,
                        String email,
                        Date dataDeNascimento,
                        String cpf,
                        String rg,
                        String orgaoExpedidor,
                        String ufRg,
                        String telefone,
                        String celular,
                        String endereco,
                        String numero,
                        String bairro,
                        String cidade,
                        String uf,
                        String nomeResponsavel,
                        String cpfResponsavel,
                        String rgResponsavel,
                        String celularResponsavel,
                        Boolean possui_responsavel,
                        String nomeCurso,
                        String nomeTurma,
                        Date dataDeInicio,
                        Date dataDeTermino,
                        String turno,
                        String nomeEmpresa,
                        String cnpjEmpresa,
                        String enderecoEmpresa,
                        String numeroEndEmpresa,
                        String bairroEndEmpresa,
                        String cidadeEndEmpresa,
                        String ufEndEmpresa,
                        String cepEndEmpresa,
                        String telefoneEmpresa,
                        String celularEmpresa) {
        this.codigo = codigo;
        this.valor = valor;
        this.observacoes = observacoes;
        this.observacaoDePagamento = observacaoDePagamento;
        this.taxa_matricula = taxa_matricula;
        this.valor_material = valor_material;
        this.valor_curso = valor_curso;
        this.valor_desconto = valor_desconto;
        this.nome = nome;
        this.email = email;
        this.dataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.orgaoExpedidor = orgaoExpedidor;
        this.ufRg = ufRg;
        this.telefone = telefone;
        this.celular = celular;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.nomeResponsavel = nomeResponsavel;
        this.cpfResponsavel = cpfResponsavel;
        this.rgResponsavel = rgResponsavel;
        this.celularResponsavel = celularResponsavel;
        this.possui_responsavel = possui_responsavel;
        this.nomeCurso = nomeCurso;
        this.nomeTurma = nomeTurma;
        this.dataDeInicio = dataDeInicio;
        this.dataDeTermino = dataDeTermino;
        this.turno = turno;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
        this.enderecoEmpresa = enderecoEmpresa;
        this.numeroEndEmpresa = numeroEndEmpresa;
        this.bairroEndEmpresa = bairroEndEmpresa;
        this.cidadeEndEmpresa = cidadeEndEmpresa;
        this.ufEndEmpresa = ufEndEmpresa;
        this.cepEndEmpresa = cepEndEmpresa;
        this.telefoneEmpresa = telefoneEmpresa;
        this.celularEmpresa = celularEmpresa;
    }

    public String getObservacaoDePagamento() {
        return observacaoDePagamento;
    }

    public void setObservacaoDePagamento(String observacaoDePagamento) {
        this.observacaoDePagamento = observacaoDePagamento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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

    public Double getValor_material() {
        return valor_material;
    }

    public void setValor_material(Double valor_material) {
        this.valor_material = valor_material;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoExpedidor() {
        return orgaoExpedidor;
    }

    public void setOrgaoExpedidor(String orgaoExpedidor) {
        this.orgaoExpedidor = orgaoExpedidor;
    }

    public String getUfRg() {
        return ufRg;
    }

    public void setUfRg(String ufRg) {
        this.ufRg = ufRg;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    public String getRgResponsavel() {
        return rgResponsavel;
    }

    public void setRgResponsavel(String rgResponsavel) {
        this.rgResponsavel = rgResponsavel;
    }

    public String getCelularResponsavel() {
        return celularResponsavel;
    }

    public void setCelularResponsavel(String celularResponsavel) {
        this.celularResponsavel = celularResponsavel;
    }

    public Boolean getPossui_responsavel() {
        return possui_responsavel;
    }

    public void setPossui_responsavel(Boolean possui_responsavel) {
        this.possui_responsavel = possui_responsavel;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public Date getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(Date dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public Date getDataDeTermino() {
        return dataDeTermino;
    }

    public void setDataDeTermino(Date dataDeTermino) {
        this.dataDeTermino = dataDeTermino;
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

    public String getNumeroEndEmpresa() {
        return numeroEndEmpresa;
    }

    public void setNumeroEndEmpresa(String numeroEndEmpresa) {
        this.numeroEndEmpresa = numeroEndEmpresa;
    }

    public String getBairroEndEmpresa() {
        return bairroEndEmpresa;
    }

    public void setBairroEndEmpresa(String bairroEndEmpresa) {
        this.bairroEndEmpresa = bairroEndEmpresa;
    }

    public String getCidadeEndEmpresa() {
        return cidadeEndEmpresa;
    }

    public void setCidadeEndEmpresa(String cidadeEndEmpresa) {
        this.cidadeEndEmpresa = cidadeEndEmpresa;
    }

    public String getUfEndEmpresa() {
        return ufEndEmpresa;
    }

    public void setUfEndEmpresa(String ufEndEmpresa) {
        this.ufEndEmpresa = ufEndEmpresa;
    }

    public String getCepEndEmpresa() {
        return cepEndEmpresa;
    }

    public void setCepEndEmpresa(String cepEndEmpresa) {
        this.cepEndEmpresa = cepEndEmpresa;
    }

    public String getTelefoneEmpresa() {
        return telefoneEmpresa;
    }

    public void setTelefoneEmpresa(String telefoneEmpresa) {
        this.telefoneEmpresa = telefoneEmpresa;
    }

    public String getCelularEmpresa() {
        return celularEmpresa;
    }

    public void setCelularEmpresa(String celularEmpresa) {
        this.celularEmpresa = celularEmpresa;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
