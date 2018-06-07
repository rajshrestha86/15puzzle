package au.edu.federation.game_1530304594;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import au.edu.federation.game_1530320375.R;


public class table_utils {
    Model15 _model15;
    Context applicationContext;
    TableLayout tableLayout;
    MainActivity mainActivity;

    public table_utils(Context applicationContext, MainActivity mainActivity, Model15 _model15, TableLayout tableLayout){
        this._model15 = _model15;
        this.applicationContext = applicationContext;
        this.tableLayout = tableLayout;
        this.mainActivity = mainActivity;
    }

    public void createTable()  {

        for (int r = 0; r < 4; r++) {
            TableRow row = new TableRow(applicationContext);
            for (int c = 0; c < 4; c++) {
                final int pos = r * 4 + c;
                int cellValue = _model15.cells[r * 4 + c];
                TextView cell = new TextView(applicationContext);
                cell.setWidth(200);
                cell.setHeight(200);
                cell.setGravity(Gravity.CENTER);

                if (! _model15.newBoardCreated){
                    cell.setBackgroundResource(R.drawable.cell_shape);
                }

                else if ( cellValue != 0){
                    cell.setBackgroundResource(R.drawable.cell_shape);
                    cell.setText(String.valueOf(cellValue));
                    cell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cell_click_handler(pos);
                        }
                    });
                }
                row.addView(cell);
            }
            tableLayout.addView(row);
        }
    }

    private void cell_click_handler(int pos) {


        _model15.moveCellAt(pos);
        int count = tableLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = tableLayout.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }

        createTable();

        if (_model15.isSolved()){

            Integer high_score = preference_Handler._getHighScore(applicationContext);
            if ((high_score==0) || (high_score > _model15.moves)) {
                mainActivity.ask_Input();  }
            mainActivity._header_text.setTextColor(0xFF9CCC65);
            mainActivity._header_text.setText(String.format("Congratulations.You have solved the puzzle in %d moves", _model15.moves));

        }

        else{
            mainActivity._header_text.setText("Moves: "+_model15.moves);

        }
    }


}
