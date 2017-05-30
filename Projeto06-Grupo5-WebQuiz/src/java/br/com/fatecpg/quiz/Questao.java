
package br.com.fatecpg.quiz;

/*@author Felipe*/
public class Questao {
        private int idQuestao;
        private String textoQuestao;
        private Teste teste;

    public int getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(int idQuestao) {
        this.idQuestao = idQuestao;
    }

    public String getTextoQuestao() {
        return textoQuestao;
    }

    public void setTextoQuestao(String textoQuestao) {
        this.textoQuestao = textoQuestao;
    }

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }
        
        
        
}
