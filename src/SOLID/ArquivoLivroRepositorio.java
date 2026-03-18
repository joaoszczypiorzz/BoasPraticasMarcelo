package SOLID;

import Acervo.Livro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Implementação do repositório de livros que utiliza arquivos de texto para persistência.
 *
 */
public class ArquivoLivroRepositorio implements LivroRepositorio {
    private static final String arquivoLivros = "livros.txt";

    /**
     * Salva a lista de livros em um arquivo de texto. Cada título é escrito em uma nova linha.
     * @param livros A lista de livros a ser salva.
     *
     */
    @Override
    public void salvar(List<Livro> livros) {
        try (PrintWriter escritor = new PrintWriter(arquivoLivros)) {
            for (Livro l : livros) {
                escritor.println(l.getTitulo());
            }
        } catch (Exception e) {
            System.err.println("ERRO: não foi possivel salvar " + e.getMessage());
        }
    }

    /**
     * Carrega os livros a partir do arquivo de texto. Lê cada linha do arquivo como um novo título de livro.
     * @return Uma lista de livros carregada do arquivo. Retorna uma lista vazia se o arquivo não existir.
     *
     */
    @Override
    public List<Livro> carregar() {
        List<Livro> livros = new ArrayList<>();
        File f = new File(arquivoLivros);

        if (!f.exists()) {
            return livros;
        }

        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                livros.add(new Livro(linha));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        }

        return livros;
    }
}