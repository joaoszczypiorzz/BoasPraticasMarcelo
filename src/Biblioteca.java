
import java.util.*;
import java.util.List;

public class Biblioteca {
    private int totalEmprestimos;
    private List<Livro> acervo;
    private final String ARQUIVO_lIVROS = "livros.txt";

    public int getTotalEmprestimos() {
        return totalEmprestimos;
    }
    public void setTotalEmprestimos(int totalEmprestimos) {
        this.totalEmprestimos = totalEmprestimos;
    }
    public List<Livro> getAcervo() {
        return acervo;
    }
    public void setAcervo(List<Livro> acervo) {
        this.acervo = acervo;
    }

}
