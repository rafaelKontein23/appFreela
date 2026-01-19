package com.freela.freelancer.infrastructure.persistence.entity.workers;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity(name = "trabalhadores")
public class WorkersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(min = 5, message = "Seu nome não é válido")
    private String nomeCompleto;

    @Length(min = 1, message = "Sua data de nascimento não é válida")
    private String dataNascimento;

    @Length(min = 11, max = 11, message = "Seu CPF não é válido")
    @Column(unique = true, nullable = false)
    private String cpf;

    @Email(message = "Seu email não é válido")
    @Column(unique = true, nullable = false)
    private String email;

    @Length(min = 1, message = "Seu número da casa não é válido")
    private String numero;

    @Length(min = 8, message = "Seu CEP não é válido")
    private String cep;

    @Length(min = 1, message = "Seu endereço não é válido")
    private String endereco;

    @Length(min = 1, message = "Seu bairro não é válido")
    private String bairro;

    @Length(min = 3, message = "Sua cidade não é válida")
    private String cidade;

    @Length(min = 1, message = "Seu estado não é válido")
    private String estado;

    @Length(min = 3, message = "Sua profissão não é válida")
    private String profissao;

    @Length(min = 1, message = "Sua especialidade não é válida")
    private String especialidade;

    @Length(min = 1, message = "Sua região atendida não é válida")
    private String regiaoAtendida;

    @Length(min = 1, message = "Seu banco não é válido")
    private String banco;

    @Length(min = 1, message = "Sua agência não é válida")
    private String agencia;

    @Length(min = 1, message = "Sua conta não é válida")
    private String conta;

    @Length(min = 1, message = "Seu tipo de conta não é válido")
    private String tipoConta;

    @Length(min = 3, message = "Sua senha não é válida")
    private String senha;

    @Version
    @Column(nullable = false) // Garante que version nunca seja null no banco
    private Integer version = 0; // Valor inicial padrão

    // Construtor padrão exigido pelo Hibernate
    public WorkersEntity() {
        this.version = 0; // Inicializa explicitamente
    }

    // Construtor parametrizado
    public WorkersEntity(String nomeCompleto, String dataNascimento, String cpf, String email, String numero,
                         String cep, String endereco, String bairro, String cidade, String estado,
                         String profissao, String especialidade, String regiaoAtendida, String banco,
                         String agencia, String conta, String tipoConta, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.numero = numero;
        this.cep = cep;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.profissao = profissao;
        this.especialidade = especialidade;
        this.regiaoAtendida = regiaoAtendida;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.tipoConta = tipoConta;
        this.senha = senha;
        this.version = 0; // Inicializa version no construtor
    }

    // Getters e Setters (mantidos como antes, exceto setVersion removido)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getRegiaoAtendida() {
        return regiaoAtendida;
    }

    public void setRegiaoAtendida(String regiaoAtendida) {
        this.regiaoAtendida = regiaoAtendida;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
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

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getVersion() {
        return version;
    }

    // Removido setVersion para evitar manipulação manual
    // public void setVersion(Integer version) {
    //     this.version = version;
    // }
}