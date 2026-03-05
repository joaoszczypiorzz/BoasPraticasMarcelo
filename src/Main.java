import Acervo.Biblioteca;
import Menu.Menu;

/**
 * Classe Main onde o sistema irá ser executado
 */
public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Menu menu = new Menu(biblioteca);
        menu.iniciar();
    }
}