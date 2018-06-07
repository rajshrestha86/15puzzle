package au.edu.federation.game_1530304594;

import java.util.ArrayList;
import java.util.Random;


public class Model15 {

    int [] cells;
    int moves;
    boolean newBoardCreated;
    int [] possibleDigits = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};

    public Model15(){
        cells = possibleDigits;
        newBoardCreated = false;
    }

    public void startNewBoard(){
        if (! newBoardCreated) {
            cells = possibleDigits;
            newBoardCreated = true;

        }

        shuffle();
        moves = 0;
    }

    public void moveCellAt(int position) {

        boolean begpos = (position%4 == 0);
        boolean endpos = (position%4 == 3);
        boolean toppos = (position < 4);
        boolean bottompos = (position > 11);

        //left
        if ( !begpos && cells[position-1] == 0)
            swap(position, position -1);
        //right
        else if ( !endpos && cells[position +1] ==0)
            swap(position, position+1);
        //up
        else if ( !toppos && cells[position-4] == 0)
            swap(position,position-4);
        //down
        else if( !bottompos && cells[position+4]==0)
            swap(position, position+4);
        else return;

        moves++;

    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 16; i > 1; i--) {
            int j = random.nextInt(i);
            swap(i - 1, j);
        }
    }

    public boolean isSolvable() {
        int[] temp = {cells[0], cells[1], cells[2], cells[3],
                cells[7], cells[6], cells[5], cells[4],
                cells[8], cells[9], cells[10], cells[11],
                cells[15], cells[14], cells[13], cells[12]};
        ArrayList<Integer> temp1 = new ArrayList();
        for (int k = 0; k < 16; k++) {
            if (temp[k] != 0) {
                temp1.add(temp[k]);
            }
        }
        int parity = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = i + 1; j < 15; j++) {
                if (temp1.get(i) > temp1.get(j)) {
                    parity++;
                }
            }
        }
        if (parity % 2 == 1) {
            return true;
        }
        return false;
    }

    public boolean isSolved(){
        for (int i=0; i<15; i++){
            if(cells[i] != (i+1))
                return false;
        }
        return true;
    }

    private void swap(int i, int j) {
        int temp = cells[i];
        cells[i] = cells[j];
        cells[j] = temp;
    }
}
