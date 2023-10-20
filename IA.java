public class IA {
  public int[] fazerJogada(char[][] tabuleiro) {
      int melhorValor = Integer.MIN_VALUE;
      int[] melhorJogada = {-1, -1};

      for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
              if (tabuleiro[i][j] == ' ') {
                  tabuleiro[i][j] = 'O';
                  int valor = minimax(tabuleiro, 0, false);
                  tabuleiro[i][j] = ' ';

                  if (valor > melhorValor) {
                      melhorValor = valor;
                      melhorJogada[0] = i;
                      melhorJogada[1] = j;
                  }
              }
          }
      }

      return melhorJogada;
  }

  private int minimax(char[][] tabuleiro, int profundidade, boolean maximizando) {
    if (tabuleiroCheio(tabuleiro)) {
        return 0; // Se estiver cheio, retorne 0
    }

    if (verificarVencedor(tabuleiro)) {
        if (maximizando) {
            return -1;
        } else {
            return 1;
        }
    }

    if (maximizando) {
      int melhorValor = Integer.MIN_VALUE;
      for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
              if (tabuleiro[i][j] == ' ') {
                  tabuleiro[i][j] = 'X';
                  int valor = minimax(tabuleiro, profundidade + 1, false);
                  tabuleiro[i][j] = ' ';
                  melhorValor = Math.max(melhorValor, valor);
              }
          }
      }
      return melhorValor;
    } else {
      int melhorValor = Integer.MAX_VALUE;
      for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
              if (tabuleiro[i][j] == ' ') {
                  tabuleiro[i][j] = 'O';
                  int valor = minimax(tabuleiro, profundidade + 1, true);
                  tabuleiro[i][j] = ' ';
                  melhorValor = Math.min(melhorValor, valor);
              }
          }
      }
      return melhorValor;
    }
  }

  private boolean verificarVencedor(char[][] tabuleiro) {
    for (int i = 0; i < 3; i++) {
        if (tabuleiro[i][0] != ' ' && tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
            return true; // Verifica as linhas
        }
        if (tabuleiro[0][i] != ' ' && tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
            return true; // Verifica as colunas
        }
    }

    if (tabuleiro[0][0] != ' ' && tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
        return true; // Verifica a diagonal principal se 
    }
    if (tabuleiro[0][2] != ' ' && tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
        return true; // Verifica a diagonal secundária
    }
    return false;
  } 

  private boolean tabuleiroCheio(char[][] tabuleiro) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (tabuleiro[i][j] == ' ') {
                return false;
            }
        }
    }
    return true;
  }
}
