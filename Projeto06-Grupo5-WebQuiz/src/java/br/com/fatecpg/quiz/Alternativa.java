package br.com.fatecpg.quiz;

/* @author Felipe*/
public class Alternativa {
        private int idAlternativa;
        private String textoAlternativa;
        private boolean tipo;
        private Questao questao;

    public String getTextoAlternativa() {
        return textoAlternativa;
    }

    public void setTextoAlternativa(String texto) {
        this.textoAlternativa = texto;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public int getIdAlternativa() {
        return idAlternativa;
    }

    public void setIdAlternativa(int idAlternativa) {
        this.idAlternativa = idAlternativa;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }
        
}
