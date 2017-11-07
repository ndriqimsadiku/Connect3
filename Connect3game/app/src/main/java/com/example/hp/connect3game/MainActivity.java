package com.example.hp.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText playerOne;
    EditText playerTwo;

    ImageView playerOneImage;
    ImageView playerTwoImage;

    String king;
    String queen;
    String winner;

    TextView someoneHasWon;
    LinearLayout linearLayout;
    Button playAgainButton;
    Button playNow;
    GridLayout gridLayout;
    //0-king
    //1-queen
    int activePlayer=0;

    boolean gameisActive=true;

    int[] gameState={2,2,2,2,2,2,2,2,2};

    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void PlayNOW(View view){

        playerOne=(EditText)findViewById(R.id.player1);
        playerTwo=(EditText)findViewById(R.id.player2);

        playerOneImage=(ImageView)findViewById(R.id.player1_image);
        playerTwoImage=(ImageView)findViewById(R.id.player2_image);


         king=playerOne.getText().toString();
        queen=playerTwo.getText().toString();
        winner=king;
        Toast.makeText(getApplicationContext(),king+" is the first one to play.",Toast.LENGTH_LONG).show();



        playNow=(Button)findViewById(R.id.playNowButton);
        gridLayout=(GridLayout)findViewById(R.id.grid_Layout);

        playNow.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        playerOne.setVisibility(View.INVISIBLE);
        playerTwo.setVisibility(View.INVISIBLE);

        playerOneImage.setVisibility(View.INVISIBLE);
        playerTwoImage.setVisibility(View.INVISIBLE);




    }

    public void DropIn(View view){

        ImageView imageView=(ImageView) view;

        int tappedCounter=Integer.parseInt(imageView.getTag().toString());


        linearLayout=(LinearLayout)findViewById(R.id.linear_layout_playAgain);
        someoneHasWon=(TextView)findViewById(R.id.winnerSomeone);
        linearLayout.setTranslationY(-1000f);




        if (gameState[tappedCounter]==2 && gameisActive) {

            gameState[tappedCounter]=activePlayer;

            imageView.setTranslationY(-1000f);


            if (activePlayer == 0) {
                imageView.setImageResource(R.drawable.king1);
                activePlayer = 1;
            } else {

                imageView.setImageResource(R.drawable.king);
                activePlayer = 0;
            }

            imageView.animate().translationYBy(1000f).rotationYBy(1080f).setDuration(800);

            for (int[] winningPosition:winningPositions){

                if (gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                        gameState[winningPosition[0]]==gameState[winningPosition[2]]&&
                        gameState[winningPosition[0]]!=2){

                    linearLayout.setVisibility(View.VISIBLE);
                    gameisActive=false;

                    linearLayout.animate().translationYBy(1000f).rotationYBy(360).setDuration(2000);




                    if (gameState[winningPosition[0]]==1) {
                        winner=queen;
                    }


                    gridLayout.setVisibility(View.INVISIBLE);

                    someoneHasWon.setText(winner+" has won!");




                }
                else {
                    boolean gameisOver=true;

                    for (int counterState:gameState){
                        if(counterState==2) gameisOver=false;
                    }

                    if (gameisOver){
                        linearLayout.setVisibility(View.VISIBLE);
                        gameisActive=false;

                        linearLayout.animate().translationYBy(1000f).rotationYBy(360).setDuration(2000);

                        gridLayout.setVisibility(View.INVISIBLE);

                        someoneHasWon.setText(" Game Over!\n it's a draw ");




                    }


                }
            }

        }

    }

    public void PlayAgain(View view){

        activePlayer=0;
        gameisActive=true;
        linearLayout=(LinearLayout)findViewById(R.id.linear_layout_playAgain);

        linearLayout.animate().translationYBy(-1000f).setDuration(1500);






        gridLayout.setVisibility(View.VISIBLE);
       // linearLayout.setVisibility(View.INVISIBLE);
       //playerOne.setText(queen);
        //playerTwo.setText(king);




        for (int i=0;i<9;i++){
            gameState[i]=2;
        }

        for (int i=0;i<gridLayout.getChildCount();i++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }






    }


}
