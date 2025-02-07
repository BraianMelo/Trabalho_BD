package modelo;

import java.time.LocalDate;

public class CriptideoConfirmado{

    private int idConfirmado;
    private int idCriptideo;
    private String nomeCientifico;
    private LocalDate dataConfirmacao;
    private String fonte;
    private String observacoes;

    public CriptideoConfirmado() {}
    	
    // Construtor
    public CriptideoConfirmado(int idConfirmado, int idCriptideo,String nomeCientifico, LocalDate dataConfirmacao, String fonte, String observacoes) {
        this.idConfirmado = idConfirmado;
        this.idCriptideo = idCriptideo;
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
    
    public int getIdCriptideo() {
    	return idCriptideo;
    }

    public void setIdCriptideo(int idCriptideo) {
    	this.idCriptideo = idCriptideo;
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
        return String.format("CriptideoConfirmado{idConfirmado=%d, idCriptideo=%d, nomeCientifico='%s', dataConfirmacao=%s, " +
                             "fonte='%s', observacoes='%s'}",
                             idConfirmado, getIdCriptideo(), nomeCientifico, dataConfirmacao, fonte, observacoes);
    }
}

