import java.util.Scanner;

public class Jogo {
    private Tabuleiro tabuleiro;
    private char jogadorAtual;
    private boolean VsCpu;
    private Scanner input;
    private IA ia; // Adicionado um objeto da AdvancedAI

    public Jogo(boolean VsCpu) {
        this.tabuleiro = new Tabuleiro();
        this.jogadorAtual = 'X';
        this.VsCpu = VsCpu;
        this.input = new Scanner(System.in);

        if (VsCpu) {
            this.ia = new IA(); // Inicializando a IA se for um jogo contra o computador
        }
    }

    public void jogar() {
        boolean fimDoJogo = false;
        while (!fimDoJogo) {
            showTabuleiro();
            makePlay();
            fimDoJogo = verifyEnd();
        }
        showTabuleiro();
        exibirResultado();
        input.close();
    }

    private void showTabuleiro() {
        char[][] estadoAtual = tabuleiro.getTabuleiro();
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i+1) + "|");
            for (int j = 0; j < 3; j++) {
                System.out.print(estadoAtual[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void makePlay() {
        int linha, coluna;
        if (VsCpu && jogadorAtual == 'O') {
            int[] jogadaIA = ia.fazerJogada(tabuleiro.getTabuleiro());
            linha = jogadaIA[0];
            System.out.print(linha);
            coluna = jogadaIA[1];
        } else {
            System.out.println(jogadorAtual + ", faca sua jogada [Linha -> Coluna]:");
            linha = input.nextInt();
            linha = linha - 1;
            coluna = input.nextInt();
            coluna = coluna - 1;
        }

        if (tabuleiro.fazerJogada(linha, coluna, jogadorAtual)) {
            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        } else {
            System.out.println("Posicao invalida. Tente novamente.");
        }
    }

    private boolean verifyEnd() {
        return tabuleiro.verifyVencedor() || tabuleiro.tabuleiroCheio();
    }

    private void exibirResultado() {
        if (tabuleiro.verifyVencedor()) {
            char vencedor = (jogadorAtual == 'X') ? 'O' : 'X';
            System.out.println(vencedor + " venceu!");
        } else {
            System.out.println("O jogo empatou!");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Traz a utilidade de scanner
        System.out.println("BEM-VINDO AO JOGO DA [[Pessoa de idade avancada]]!");
        System.out.print("Jogar contra o computador? (S/N): ");
        boolean VsCpu = input.nextLine().equalsIgnoreCase("S"); // Se a comparação for igual a S

        Jogo jogo = new Jogo(VsCpu); // Inicializar o jogo
        jogo.jogar();
    }
}
