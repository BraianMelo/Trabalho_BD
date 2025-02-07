package modelo;

import modelo.enums.Genero;

public class Pesquisador extends Testemunha {

    private int idPesquisador;
    private int idTestemunha;
    private String areaAtuacao;
    private String instituicao;

    public Pesquisador(int idPesquisador, int idTestemunha, String nome, String sobrenome, int idade, Genero genero, String email, String telefone, String areaAtuacao, String instituicao) {
        super(idTestemunha, nome, sobrenome, idade, genero, email, telefone);
        this.idPesquisador = idPesquisador;
        this.idTestemunha = idTestemunha;
        this.areaAtuacao = areaAtuacao;
        this.instituicao = instituicao;
    }
    
    public Pesquisador(int idPesquisador, int idTestemunha, String areaAtuacao, String instituicao) {
        this.idPesquisador = idPesquisador;
        this.idTestemunha = idTestemunha;
        this.areaAtuacao = areaAtuacao;
        this.instituicao = instituicao;
    }

    public int getIdPesquisador() {
        return idPesquisador;
    }

    public void setIdPesquisador(int idPesquisador) {
        this.idPesquisador = idPesquisador;
    }

    public int getIdTestemunha() {
        return idTestemunha;
    }

    public void setIdTestemunha(int idTestemunha) {
        this.idTestemunha = idTestemunha;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    // toString
    @Override
    public String toString() {
        return "Pesquisador{" +
                "idPesquisador=" + idPesquisador +
                ", idTestemunha=" + idTestemunha +
                ", areaAtuacao='" + areaAtuacao + '\'' +
                ", instituicao='" + instituicao + '\'' +
                ", nome='" + getNome() + '\'' +  // Usando o getter de Testemunha para acessar nome
                ", sobrenome='" + getSobrenome() + '\'' +  // Usando o getter de Testemunha para acessar sobrenome
                ", idade=" + getIdade() +  // Usando o getter de Testemunha para acessar idade
                ", genero=" + getGenero() +  // Usando o getter de Testemunha para acessar genero
                ", email='" + getEmail() + '\'' +  // Usando o getter de Testemunha para acessar email
                ", telefone='" + getTelefone() + '\'' +  // Usando o getter de Testemunha para acessar telefone
                '}';
    }
}

