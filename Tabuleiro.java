public class Tabuleiro {
    private char[][] tabuleiro;

    public Tabuleiro() {
        this.tabuleiro = new char[3][3]; // Cria o tabuleiro
        startTabuleiro();
    }

    private void startTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) { // Inicializa o tabuleiro
                tabuleiro[i][j] = ' ';
            }
        }
    }

    public char[][] getTabuleiro() {
        return tabuleiro; // Retorna o tabuleiro
    }

    public boolean fazerJogada(int linha, int coluna, char jogador) {
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
            return false; // Se estiver dentro do alcance
        }
        if (tabuleiro[linha][coluna] == ' ') {
            tabuleiro[linha][coluna] = jogador; // se estiver vago
            return true;
        }
        return false;
    }

    public boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verifyVencedor() {
      for (int i = 0; i < 3; i++) {
        if (tabuleiro[i][0] != ' '  // Verifica as linhas
        && tabuleiro[i][0] == tabuleiro[i][1] 
        && tabuleiro[i][1] == tabuleiro[i][2]) {
            return true; // Verifica as linhas
        }
        if (tabuleiro[0][i] != ' '  // Verifica as colunas
        && tabuleiro[0][i] == tabuleiro[1][i] 
        && tabuleiro[1][i] == tabuleiro[2][i]) {
            return true; // Verifica as colunas
        }
      }

      if (tabuleiro[0][0] != ' ' // Verifica a diagonal principal
      && tabuleiro[0][0] == tabuleiro[1][1] 
      && tabuleiro[1][1] == tabuleiro[2][2]) {
          return true; // Verifica a diagonal principal
      }
      if (tabuleiro[0][2] != ' ' // Verifica a diagonal secundária
      && tabuleiro[0][2] == tabuleiro[1][1] 
      && tabuleiro[1][1] == tabuleiro[2][0]) {
          return true; // Verifica a diagonal secundária
      }
      return false;
    }
}
