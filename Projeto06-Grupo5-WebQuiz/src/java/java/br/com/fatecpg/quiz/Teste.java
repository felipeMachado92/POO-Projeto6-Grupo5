package java.br.com.fatecpg.quiz;

/* @author Felipe*/
public class Teste {
        private int idTeste;
        private String nomeTeste;
        private String descTeste;
        private Questao questao;

    public int getIdTeste() {
        return idTeste;
    }

    public void setIdTeste(int idTeste) {
        this.idTeste = idTeste;
    }

    public String getNomeTeste() {
        return nomeTeste;
    }

    public void setNomeTeste(String nomeTeste) {
        this.nomeTeste = nomeTeste;
    }

    public String getDescTeste() {
        return descTeste;
    }

    public void setDescTeste(String descTeste) {
        this.descTeste = descTeste;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }
        
}
