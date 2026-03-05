import Acervo.Biblioteca;
import Menu.Menu;

/**
 * Ponto de entrada para o Sistema de Gerenciamento de Biblioteca.
 * <p>
 * Esta aplicação via linha de comando permite o controle básico de um acervo literário,
 * fornecendo funcionalidades para adicionar novos livros, realizar empréstimos e listar
 * o inventário atual salvo no arquivo de texto subjacente. O sistema foi desenvolvido
 * aplicando boas práticas de engenharia de software, foco em código limpo e princípios
 * de orientação a objetos.
 * </p>
 * @author João Novaes, Andrey Marucci, João Szczypior, Enzo Schmidt, João Camargo e Rafael Vasconcelos
 * @version 1.0
 */
public class Main {

    /**
     * Método principal que inicializa as dependências fundamentais do sistema e
     * aciona o menu interativo para o usuário.
     */
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Menu menu = new Menu(biblioteca);
        menu.iniciar();
    }
}