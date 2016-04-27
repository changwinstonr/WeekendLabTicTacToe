package ly.generalassemb.drewmahrt.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public ImageView[] mBlocks = new ImageView[9];
    private TextView mDisplay;
    private ImageView mReplay;
/*    public static int TURNO = 0;
    public static int TURNX = 1;*/
    public enum TURN {CIRCLE, CROSS}
    public TURN mTurn;
    private int mStatusCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            initImage();
        }//end of onCreate

    /*listName.setOnClickListener(new AdapterView.OnClickListener() {
        @Override
        public void onClick(AdapterView<?> parent, View view, int position, long id) {
            Intent todolist = new Intent(MainActivity.this, ToDoActivity.class);
            startActivity(todolist);
        }
    });*/
        //initialize ImageView positions
        public void initImage(){
            mDisplay = (TextView) findViewById(R.id.game_turn_text);
            mReplay = (ImageView) findViewById(R.id.playAgain);
            mReplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent starter = getIntent();
                    finish();

                    startActivity(starter);


                }
            });

            for (int position = 0; position < 9;  position++){
                int board = getResources().getIdentifier("block_" + (position + 1), "id", getPackageName());
                mBlocks[position] = (ImageView) findViewById(board);
                final int finalPosition = position;
                mBlocks[position].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeTurn(finalPosition);
                    }
                });
            }//end of for loop
        }



            //change turns
        private void changeTurn(int position) {
            if (mTurn == TURN.CIRCLE) {
                mBlocks[position].setImageResource(R.drawable.caboose_circle);
                mBlocks[position].setId(GameActivity.CIRCLE);
                mTurn = TURN.CROSS;
                mDisplay.setText(position);
            } else {
                mBlocks[position].setImageResource(R.drawable.sarge_cross);
                mBlocks[position].setId(GameActivity.CROSS);
                mTurn = TURN.CIRCLE;
                mDisplay.setText(position);
            }
                //disable click on ImageViews
                mBlocks[position].setEnabled(false);
                mStatusCounter++;

            if (GameActivity.hasWon(position + 1, mBlocks)) {
                mDisplay.setText(GameActivity.mWinner + " won!");
                displayWin(GameActivity.mSet);
                disableAll();
            }else if (mStatusCounter==9) {
                mDisplay.setText("WORT WORT WORT. Try Again.");
            }
        }

            //displays cross out FTW
            private void displayWin(int win) {
                View view;
                switch (win) {
                    case 1:
                        view = findViewById(R.id.win_top_horizontal);
                        break;
                    case 2:
                        view = findViewById(R.id.win_center_horizontal);
                        break;
                    case 3:
                        view = findViewById(R.id.win_bottom_horizontal);
                        break;
                    case 4:
                        view = findViewById(R.id.win_left_vertical);
                        break;
                    case 5:
                        view = findViewById(R.id.win_center_vertical);
                        break;
                    case 6:
                        view = findViewById(R.id.win_right_vertical);
                        break;
                    case 7:
                        view = findViewById(R.id.win_left_right_diagonal);
                        break;
                    case 8:
                        view = findViewById(R.id.win_right_left_diagonal);
                        break;
                    default:
                        view = findViewById(R.id.win_top_horizontal);
                }
                view.setVisibility(View.VISIBLE); //unhides win Views
            }

            //disable ImageViews once
            private void disableAll() {
                for (int position = 0; position < 9; position++)
                    mBlocks[position].setEnabled(false);
            }
    }
