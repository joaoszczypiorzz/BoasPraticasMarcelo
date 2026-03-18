package SOLID;

import SOLID.Biblioteca;
import SOLID.LivroRepositorio;


public class Main {
    public static void main(String[] args) {
        // 1. Define onde os dados moram
        LivroRepositorio repo = new ArquivoLivroRepositorio();

        // 2. Cria o motor de regras (negócio)
        Biblioteca b = new Biblioteca(repo);

        // 3. Cria a interface de usuário e inicia
        Menu menu = new Menu(b);
        menu.iniciar();
    }
}