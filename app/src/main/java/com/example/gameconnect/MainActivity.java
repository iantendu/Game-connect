package com.example.gameconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 rep blue ,1 rep red and 2 rep empty
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;
    int[][] winningPosition={{0,1,2},{ 3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        Log.i("Tag", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }


            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPosition) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    gameActive =false;
                    if (activePlayer == 1) {
                        winner = "blue";
                    } else {
                        winner = "red";
                    }

                    TextView textView=(TextView)findViewById(R.id.textView);
                    Button btn=(Button) findViewById(R.id.button);
                    textView.setText("The winner is "+winner);
                    textView.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.VISIBLE);
                    GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
                    for(int i=0; i<gridLayout.getChildCount(); i++) {
                        ImageView child = (ImageView) gridLayout.getChildAt(i);
                        child.setImageDrawable(null);
                    }
                    for(int i=0;i<gameState.length;i++){
                      gameState[i]=2;
                    }
                    int activePlayer=0;
                    gameActive=true;

                }


            }
        }

    }
    public void playAgain(View view){
        TextView textView=(TextView)findViewById(R.id.textView);
        Button btn=(Button) findViewById(R.id.button);

        textView.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}