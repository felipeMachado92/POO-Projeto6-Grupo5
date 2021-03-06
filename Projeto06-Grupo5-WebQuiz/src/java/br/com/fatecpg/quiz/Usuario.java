package br.com.fatecpg.quiz;

/* @author Felipe*/
public class Usuario {
    private int idUsuario;
    private String nome;
    private String login;
    private String senha;
    private TipoUsuario tpUsuario;
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id) {
        this.idUsuario = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTpUsuario() {
        return tpUsuario;
    }

    public void setTpUsuario(TipoUsuario tpUsuario) {
        this.tpUsuario = tpUsuario;
    }
    
}
