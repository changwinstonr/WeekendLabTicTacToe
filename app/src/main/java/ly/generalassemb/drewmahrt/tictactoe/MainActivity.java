package ly.generalassemb.drewmahrt.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView[] mBlocks = new ImageView[9];
    private TextView mDisplay; //maybe add Toast just below game_turn_text on player change
    private enum TURN {CIRCLE, CROSS}
    private TURN mTurn;
    private ImageView mReplay;
    private int mStatusCounter = 0;

/*

p1 et fv rid p1n;
    "" +
            "" +
            int int = new int maincthis gameactclass;
            inten.putE "pa",pa.getT().getStr();
            "p1gt new string p1.gettext.tostroing" +;
            "" +
            ""

            */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mDisplay = (TextView) findViewById(R.id.game_turn_text);
        mReplay = (ImageView) findViewById(R.id.playAgain);
        mReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starter = getIntent();
                finish();
                //starter.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(starter);
            }

            for(int position = 0; position < 9; position++) {
                int resId = getResources().getIdentifier("block_" + (position + 1), "id", getPackageName());
                mBlocks[position] = (ImageView) findViewById(resId);
                final int finalPosition = position;
                mBlocks[position].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchTurn(finalPosition);
                    }
                });
            }
            private void switchTurn(int position) {
                if (mTurn == TURN.CIRCLE) {
                    mBlocks[position].setImageResource(R.drawable.caboose_circle);
                    mBlocks[position].setId(GameActivity.CIRCLE);
                    mTurn = TURN.CROSS;
                    mDisplay.setText("CROSS's turn");
                } else {
                    mBlocks[position].setImageResource(R.drawable.sarge_cross);
                    mBlocks[position].setId(GameActivity.CROSS);
                    mTurn = TURN.CIRCLE;
                    mDisplay.setText("CIRCLE's turn");
                }
                mBlocks[position].setEnabled(false);
                mStatusCounter++;
                if (GameActivity.hasWon(position + 1, mBlocks)) {
                    mDisplay.setText(GameActivity.mWinner + " won");
                    displayStick(GameActivity.mSet);
                    disableAll();
                }else if (mStatusCounter==9){
                    mDisplay.setText("WORT WORT WORT. Try Again.");
                }
            }
            //displays cross out
            private void displayStick(int stick) {
                View view;
                switch (stick) {
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
                view.setVisibility(View.VISIBLE);
            }

            private void disableAll() {
                for (int i = 0; i < 9; i++)
                    mBlocks[i].setEnabled(false);
            }


        });
    }




}
