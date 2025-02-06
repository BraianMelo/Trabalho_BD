package modelo;

import modelo.enums.Genero;

public class Testemunha {

    private int idTestemunha;
    private String nome;
    private String sobrenome;
    private int idade;
    private Genero genero;
    private String email;
    private String telefone;
    
    public Testemunha() {}


    public Testemunha(int idTestemunha, String nome, String sobrenome, int idade, Genero genero, String email, String telefone) {
        this.idTestemunha = idTestemunha;  
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.genero = genero;
        this.email = email;
        this.telefone = telefone;
    }

	public int getIdTestemunha() {
        return idTestemunha;
    }

    public void setIdTestemunha(int idTestemunha) {
        this.idTestemunha = idTestemunha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Testemunha{" +
                "idTestemunha=" + idTestemunha + 
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", idade=" + idade +
                ", genero=" + genero +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}

