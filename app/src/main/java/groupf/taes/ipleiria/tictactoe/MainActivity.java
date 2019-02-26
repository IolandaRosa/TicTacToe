package groupf.taes.ipleiria.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Button> buttons = new ArrayList<>();
    private boolean gameOver;
    private int playerTurn;
    private static String X_SYMBOL = "X";
    private static String O_SYMBOL = "O";
    private String playerOne;
    private String playerTwo;
    private TextView infoTextView;
    private Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adicionar botões à lista
        buttons.add((Button) findViewById(R.id.button));
        buttons.add((Button) findViewById(R.id.button2));
        buttons.add((Button) findViewById(R.id.button3));
        buttons.add((Button) findViewById(R.id.button4));
        buttons.add((Button) findViewById(R.id.button5));
        buttons.add((Button) findViewById(R.id.button6));
        buttons.add((Button) findViewById(R.id.button7));
        buttons.add((Button) findViewById(R.id.button8));
        buttons.add((Button) findViewById(R.id.button9));

        this.gameOver=false;
        this.playerTurn=1;

        //this.playerOne="Player 1";
        //this.playerTwo="Player 2";
        this.playerOne = getIntent().getStringExtra(WelcomeActivity.PLAYER_ONE);
        this.playerTwo = getIntent().getStringExtra(WelcomeActivity.PLAYER_TWO);

        this.infoTextView=findViewById(R.id.txtGameInfo);
        this.infoTextView.setText(playerOne.concat("'s turn"));
        this.restartButton = findViewById(R.id.btnRestart);

        for (Button b : buttons) {
            //Limpar texto dos botões
            b.setText("");

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Verificar se o botão já tem símbolo.
                    if (canClick((Button) view)) {
                        //Se o botão não tem texto, preencher o botão com o símbolo do jogador “X” ou “O”
                        if (playerTurn == 1) {
                            ((Button) view).setText(X_SYMBOL);
                        } else {
                            ((Button) view).setText(O_SYMBOL);
                        }

                        //Verificar se o jogo acabou.
                        isGameOver();

                        //Se o jogo não tiver acabado, mudar a vez do jogador.
                        if (!gameOver) {
                            switchPlayerTurn();
                        }

                    }

                }
            });

        }

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
    }

    private boolean canClick(Button button) {
        return button.getText().toString().equals("") && !gameOver;
    }

    private void isGameOver() {
        //O jogo quando estiverem todos os simbolos iguais numa linha / coluna / diagonal / ou todas as casas preenchidas
        
        String[][] symbolsMatrix = new String[3][3];
        int index = 0;
        //Copiar o texto que esta nos botões para a matriz
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                symbolsMatrix[i][j] = buttons.get(index).getText().toString();
                index++;
            }
        }

        //Fazer verificação de simbolos iguais nas linhas e colunas
        for (int i = 0; i < 3; i++) {

            //Verificar linhas
            if (!symbolsMatrix[i][0].equals("") && symbolsMatrix[i][0].equals(symbolsMatrix[i][1]) && symbolsMatrix[i][0].equals(symbolsMatrix[i][2])) {
                String playerName = this.playerTurn == 1 ? playerOne : playerTwo;
                infoTextView.setText(playerName.concat(" is the winner"));
                gameOver = true;
                return;
            }

            //Verificar colunas
            if (!symbolsMatrix[0][i].equals("") && symbolsMatrix[0][i].equals(symbolsMatrix[1][i]) && symbolsMatrix[0][i].equals(symbolsMatrix[2][i])) {
                String playerName = this.playerTurn == 1 ? playerOne : playerTwo;
                infoTextView.setText(playerName.concat(" is the winner"));
                gameOver = true;
                return;
            }
        }

        //verificar diagonais
        if (!symbolsMatrix[0][0].equals("") && symbolsMatrix[0][0].equals(symbolsMatrix[1][1]) && symbolsMatrix[0][0].equals(symbolsMatrix[2][2])) {
            String playerName = this.playerTurn == 1 ? playerOne : playerTwo;
            infoTextView.setText(playerName.concat(" is the winner"));
            gameOver = true;
            return;
        }

        if (!symbolsMatrix[0][2].equals("") && symbolsMatrix[0][2].equals(symbolsMatrix[1][1]) && symbolsMatrix[0][2].equals(symbolsMatrix[2][0])) {
            String playerName = this.playerTurn == 1 ? playerOne : playerTwo;
            infoTextView.setText(playerName.concat(" is the winner"));
            gameOver = true;
            return;
        }

        //Verificar se todas as celulas preenchidas
        for (Button b : buttons) {
            if (b.getText().toString().equals("")) {
                //Se não estiverem e ninguem tiver ganho é possivel continuar a jogar
                gameOver = false;
                return;
            }
        }

        //Se estiverem o jogo termina com game over
        gameOver = true;
        infoTextView.setText("Game Over");
    }

    private void switchPlayerTurn() {
        playerTurn = (playerTurn == 1) ? 2 : 1;
        setCurrentPlayer();
    }

    //Mudar a vez do jogador
    private void setCurrentPlayer() {
        String playerName = this.playerTurn == 1 ? playerOne : playerTwo;
        infoTextView.setText(playerName.concat("'s turns"));
    }

    //Reiniciar o jogo
    private void restartGame() {
        for (Button b : buttons) {
            b.setText("");
        }

        this.gameOver = false;
        this.playerTurn = 1;
        this.setCurrentPlayer();
    }



}
