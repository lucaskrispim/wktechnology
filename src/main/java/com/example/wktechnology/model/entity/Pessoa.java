package com.example.wktechnology.model.entity;


import com.example.wktechnology.utils.enums.EstadoBrasileiro;
import com.example.wktechnology.utils.enums.Sexo;
import com.example.wktechnology.utils.enums.TipoSanguineo;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;



    @Column(unique = true)
    private String cpf;


    private String rg;


    private LocalDate dataNasc;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;


    private String mae;


    private String pai;


    private String email;


    private String cep;


    private String endereco;


    private int numero;


    private String bairro;


    private String cidade;

    @Enumerated(EnumType.STRING)
    private EstadoBrasileiro estado;


    private String telefoneFixo;


    private String celular;


    private double altura;


    private int peso;

    @Enumerated(EnumType.STRING)
    private TipoSanguineo tipoSanguineo;

    // Getters e Setters para todos os campos

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getDataNascimento() {
        return dataNasc;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNasc = dataNascimento;
    }

    public String getSexo() {
        return sexo.toString();
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
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

    public EstadoBrasileiro getEstado() {
        return estado;
    }

    public void setEstado(EstadoBrasileiro estado) {
        this.estado = estado;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo.toString();
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", dataNasc='" + dataNasc + '\'' +
                ", sexo='" + sexo + '\'' +
                ", mae='" + mae + '\'' +
                ", pai='" + pai + '\'' +
                ", email='" + email + '\'' +
                ", cep='" + cep + '\'' +
                ", endereco='" + endereco + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", telefoneFixo='" + telefoneFixo + '\'' +
                ", celular='" + celular + '\'' +
                ", altura=" + altura +
                ", peso=" + peso +
                ", tipoSanguineo='" + tipoSanguineo + '\'' +
                '}';
    }
}

