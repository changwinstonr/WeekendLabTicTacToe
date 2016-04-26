package ly.generalassemb.drewmahrt.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //message tv findView r.id.game message;
        //message.settext getintnt getextra .getstring

    }
    private static ImageView[] mBlocks;
    public static String mWinner;
    public static int mSet;

    public static final int CIRCLE = 0;
    public static final int CROSS = 1;

    //checking against winning sets
    private static boolean winningSet(int first, int second, int third, int set) {
        boolean value = mBlocks[first - 1].getId() == mBlocks[second - 1].getId() && mBlocks[second - 1].getId() == mBlocks[third - 1].getId();
        if (value) {
            if (mBlocks[first - 1].getId() == CIRCLE) {
                mWinner = "CIRCLE";
            } else {
                mWinner = "CROSS";
                mSet = set;
            }
        }
        return value;
    }

    //check the blocks for purple rain
    public static boolean hasWon(int position, ImageView[] blocks) {
        GameActivity.mBlocks = blocks;
        boolean hasWon = false;
        switch (position) {
            case 1:
                hasWon = winningSet(1, 2, 3, 1) || winningSet(1, 4, 7, 4) || winningSet(1, 5, 9, 7);
                break;
            case 2:
                hasWon = winningSet(1, 2, 3, 1) || winningSet(2, 5, 8, 5);
                break;
            case 3:
                hasWon = winningSet(1, 2, 3, 1) || winningSet(3, 6, 9, 6) || winningSet(3, 5, 7, 8);
                break;
            case 4:
                hasWon = winningSet(4, 5, 6, 2) || winningSet(1, 4, 7, 4);
                break;
            case 5:
                hasWon = winningSet(4, 5, 6, 2) || winningSet(2, 5, 8, 5) || winningSet(1, 5, 9, 7) || winningSet(3, 5, 7, 8);
                break;
            case 6:
                hasWon = winningSet(4, 5, 6, 2) || winningSet(3, 6, 9, 6);
                break;
            case 7:
                hasWon = winningSet(7, 8, 9, 3) || winningSet(1, 4, 7, 4) || winningSet(3, 5, 7, 8);
                break;
            case 8:
                hasWon = winningSet(7, 8, 9, 3) || winningSet(2, 5, 8, 5);
                break;
            case 9:
                hasWon = winningSet(7, 8, 9, 3) || winningSet(3, 6, 9, 6) || winningSet(1, 5, 9, 7);
                break;
        }
        return hasWon;
    }
}
