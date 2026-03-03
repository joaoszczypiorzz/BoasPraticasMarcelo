import Acervo.Biblioteca;
import Menu.Menu;

public class MainTudoEmUm {
    static void main() {
        Biblioteca biblioteca = new Biblioteca();
        Menu menu = new Menu(biblioteca);
        menu.iniciar();
    }
}
