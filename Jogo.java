import java.util.Scanner;
import java.util.Random;

public class Jogo {
    private char[][] tabuleiro;
    private char jogadorAtual;
    private boolean jogadorVsComputador;
    private Scanner input;

    public Jogo(boolean jogadorVsComputador) {
        this.tabuleiro = new char[3][3];
        this.jogadorAtual = 'X';
        this.jogadorVsComputador = jogadorVsComputador;
        this.input = new Scanner(System.in);
        inicializarTabuleiro();
    }

    public void jogar() {
        boolean fimDoJogo = false;
        while (!fimDoJogo) {
            exibirTabuleiro();
            realizarJogada();
            fimDoJogo = verificarFimDoJogo();
        }
        exibirTabuleiro();
        exibirResultado();
        input.close();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    private void exibirTabuleiro() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void realizarJogada() {
        int linha, coluna;
        if (jogadorVsComputador && jogadorAtual == 'O') {
            Random rand = new Random();
            linha = rand.nextInt(3);
            coluna = rand.nextInt(3);
        } else {
            System.out.println("Jogador " + jogadorAtual + ", faça sua jogada (linha e coluna):");
            linha = input.nextInt();
            coluna = input.nextInt();
        }

        if (validarJogada(linha, coluna)) {
            tabuleiro[linha][coluna] = jogadorAtual;
            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        } else {
            System.out.println("Jogada inválida. Tente novamente.");
        }
    }

    private boolean validarJogada(int linha, int coluna) {
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
            return false;
        }
        return tabuleiro[linha][coluna] == ' ';
    }

    private boolean verificarFimDoJogo() {
        return verificarVencedor() || tabuleiroCheio();
    }

    private boolean verificarVencedor() {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] != ' ' 
            && tabuleiro[i][0] == tabuleiro[i][1] 
            && tabuleiro[i][1] == tabuleiro[i][2]) {
                return true; // Linhas
            }
            if (tabuleiro[0][i] != ' ' 
            && tabuleiro[0][i] == tabuleiro[1][i] 
            && tabuleiro[1][i] == tabuleiro[2][i]) {
                return true; 
            }
        }

        if (tabuleiro[0][0] != ' ' 
        && tabuleiro[0][0] == tabuleiro[1][1] 
        && tabuleiro[1][1] == tabuleiro[2][2]) {
            return true; // Diagonal principal
        }
        if (tabuleiro[0][2] != ' ' 
        && tabuleiro[0][2] == tabuleiro[1][1] 
        && tabuleiro[1][1] == tabuleiro[2][0]) {
            return true; // Diagonal secundária
        }
        return false;
    }

    private boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void exibirResultado() {
        if (verificarVencedor()) {
            char vencedor = (jogadorAtual == 'X') ? 'O' : 'X';
            System.out.println("Jogador " + vencedor + " venceu!");
        } else {
            System.out.println("O jogo empatou!");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Bem-vindo ao Jogo da Velha!");
        System.out.print("Deseja jogar contra o computador? (S/N): ");
        boolean jogadorVsComputador = input.nextLine().equalsIgnoreCase("S");

        Jogo jogo = new Jogo(jogadorVsComputador);
        jogo.jogar();
    }
}