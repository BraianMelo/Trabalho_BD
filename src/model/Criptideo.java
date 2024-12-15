package model;

import model.enums.Tipo;
import model.enums.StatusCriptideo;

public class Criptideo {
    private int idCriptideo;
    private String nome;
    private String descricao;
    private Tipo tipo; // Enum para "Terrestre", "Aquatico", "Voador"
    private StatusCriptideo statusCr; // Enum para "Avistado" e "Confirmado"

    // Construtor
    public Criptideo(int idCriptideo, String nome, String descricao, Tipo tipo, StatusCriptideo statusCr) {
        this.idCriptideo = idCriptideo;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.statusCr = statusCr;
    }

    // Getters e Setters
    public int getIdCriptideo() {
        return idCriptideo;
    }

    public void setIdCriptideo(int idCriptideo) {
        this.idCriptideo = idCriptideo;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public StatusCriptideo getStatusCr() {
        return statusCr;
    }

    public void setStatusCr(StatusCriptideo statusCr) {
        this.statusCr = statusCr;
    }

    // Método para exibir informações
    public void exibirInformacoes() {
        System.out.println("ID: " + idCriptideo);
        System.out.println("Nome: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Tipo: " + tipo);
        System.out.println("Status: " + statusCr);
    }

    // toString
    @Override
    public String toString() {
        return idCriptideo + " - " + nome + " : " + descricao +" | " + tipo+ " | " + statusCr;
    }
}
