package model;

import java.time.LocalDate;

public class Avistamento {

    private int idAvistamento;
    private String local;
    private String pais;
    private LocalDate data;

    public Avistamento() {}
    
    public Avistamento(int idAvistamento, String local, String pais, LocalDate data) {
        this.idAvistamento = idAvistamento;
        this.local = local;
        this.pais = pais;
        this.data = data;
    }

	// Getters e Setters
    public int getIdAvistamento() {
        return idAvistamento;
    }

    public void setIdAvistamento(int idAvistamento) {
        this.idAvistamento = idAvistamento;
    }
    
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    // toString
    @Override
    public String toString() {
        return "Avistamento{" +
                "idAvistamento=" + idAvistamento +
                ", local='" + local + '\'' +
                ", pais='" + pais + '\'' +
                ", data=" + data +
                '}';
    }
}
