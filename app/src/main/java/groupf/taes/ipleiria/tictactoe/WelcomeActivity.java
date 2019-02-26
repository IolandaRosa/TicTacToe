package groupf.taes.ipleiria.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    private ImageButton btnPlay;
    public static String PLAYER_ONE = "player1";
    public static String PLAYER_TWO = "player2";
    private EditText nameOne;
    private EditText nameTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnPlay = findViewById(R.id.btnPlay);
        nameOne = findViewById(R.id.editTextNameOne);
        nameTwo = findViewById(R.id.editTextNameTwo);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = nameOne.getText().toString().trim();
                String name2 = nameTwo.getText().toString().trim();

                if (name1.isEmpty() || name2.isEmpty()) {
                    Toast.makeText(WelcomeActivity.this, "The players names can't be empty!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //Start the MainActivity passing the players names

                //1º - Criar o intent
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);

                //Pass the players names to a new activity
                //Colocar informação extra no intent e passar este intent à nova atividade
                intent.putExtra(PLAYER_ONE, name1);
                intent.putExtra(PLAYER_TWO, name2);
                startActivity(intent);
            }
        });
    }
}
