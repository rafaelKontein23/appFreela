package com.freela.freelancer.Trabalhadores.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity(name = "trabalhadores")
public class TrabalhadorEntidade {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @Length(min = 5, message = "Seu nome não é valido")
    private String nomeCompleto;

    @Length(min = 1, message = "Seu data de nascimento não é valido")
    private String dataNascimento;
    @Length(min = 11, max = 11, message = "Seu cpf não é valido")
    @Column(name = "cpf")
    private String cpf;

    @Email(message = "Seu email não é valido")
    private String email;

    @Length(min = 1, message = "Seu numero da casa não é valido")
    private String numero;

    @Length(min = 8, message = "Seu cep não é valido")
    private String cep;

    @Length(min = 1, message = "Seu endereço não é valido")
    private String endereco;

    @Length(min = 1, message = "Seu bairro não é valido")
    private String bairro;

    @Length(min = 3, message = "Seu cidade não é valido")
    private String cidade;

    @Length(min = 1, message = "Seu estado não é valido")
    private String estado;

    @Length(min = 3, message = "Seu profissao não é valido")
    private String profissao;

    @Length(min = 1, message = "Seu especialidade não é valido")
    private String especialidade;

    @Length(min = 1, message = "Seu regiaoAtendida não é valido")
    private String regiaoAtendida;


    @Length(min = 1, message = "Seu banco não é valido")
    private String banco;

    @Length(min = 1, message = "Seu agencia não é valido")
    private String agencia;

    @Length(min = 1, message = "Seu conta não é valido")
    private String conta;

    @Length(min = 1, message = "Seu tipoConta não é valido")
    private String tipoConta;

    @Length(min = 3, message = "Sua senha não é valido")
    private String senha;

    public TrabalhadorEntidade() {
    }

    public TrabalhadorEntidade(UUID id, String nomeCompleto, String dataNascimento, String cpf, String email, String numero,
                               String cep, String endereco, String bairro, String cidade, String estado, String profissao,
                               String especialidade, String regiaoAtendida, String banco, String agencia, String conta,
                               String tipoConta, String senha) {
        this.id = id;
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
    }

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
}
