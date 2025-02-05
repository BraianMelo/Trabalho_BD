package model;

import model.enums.Tipo;
import model.enums.StatusCriptideo;

public class Criptideo {
    private int idCriptideo;
    private String nome;
    private String descricao;
    private Tipo tipo; 
    private StatusCriptideo statusCr; 
    private String imagemCaminho; 

    public Criptideo() {}
    
    public Criptideo(int idCriptideo, String nome, String descricao, Tipo tipo, StatusCriptideo statusCr, String imagemCaminho) {
        this.idCriptideo = idCriptideo;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.statusCr = statusCr;
        this.imagemCaminho = imagemCaminho;
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

    public String getImagemCaminho() {
        return imagemCaminho;
    }

    public void setImagemCaminho(String imagemCaminho) {
        this.imagemCaminho = imagemCaminho;
    }

    @Override
    public String toString() {
        return String.format("%d - %s: %s | Tipo: %s | Status: %s | Imagem: %s", 
                             idCriptideo, nome, descricao, tipo, statusCr, imagemCaminho);
    }
}
