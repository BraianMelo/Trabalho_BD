package model;

import java.time.LocalDate;
import model.enums.Tipo;
import model.enums.StatusCriptideo;

public class CriptideoConfirmado extends Criptideo {

    private int idConfirmado;
    private String nomeCientifico;
    private LocalDate dataConfirmacao;
    private String fonte;
    private String observacoes;

    // Construtor
    public CriptideoConfirmado(int idConfirmado, int idCriptideo, String nome, String descricao, Tipo tipo, StatusCriptideo statusCr, String imagemCaminho, String nomeCientifico, LocalDate dataConfirmacao, String fonte, String observacoes) {
        // Chama o construtor da classe pai Criptideo
        super(idCriptideo, nome, descricao, tipo, statusCr, imagemCaminho);
        this.idConfirmado = idConfirmado;
        this.nomeCientifico = nomeCientifico;
        this.dataConfirmacao = dataConfirmacao;
        this.fonte = fonte;
        this.observacoes = observacoes;
    }

    // Getters e Setters
    public int getIdConfirmado() {
        return idConfirmado;
    }

    public void setIdConfirmado(int idConfirmado) {
        this.idConfirmado = idConfirmado;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public LocalDate getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(LocalDate dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    // toString
    @Override
    public String toString() {
        return String.format("CriptideoConfirmado{idConfirmado=%d, idCriptideo=%d, nome='%s', descricao='%s', " +
                             "tipo=%s, status=%s, imagemCaminho='%s', nomeCientifico='%s', dataConfirmacao=%s, " +
                             "fonte='%s', observacoes='%s'}",
                             getIdCriptideo(), idConfirmado, getNome(), getDescricao(), getTipo(), getStatusCr(), 
                             getImagemCaminho(), nomeCientifico, dataConfirmacao, fonte, observacoes);
    }
}

