package au.edu.federation.game_1530304594;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import au.edu.federation.game_1530320375.R;


public class MainActivity extends AppCompatActivity {

    TableLayout tableLayout;
    Model15 _model15;
    TextView _header_text, _footer_text;
    Button _new_game, _clear_result;
    table_utils tableUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Display Metrics of a device



//          getting reference of the widget


        _header_text = (TextView)findViewById(R.id.header);
        _footer_text = (TextView)findViewById(R.id.footer);
        _new_game=(Button)findViewById(R.id.btn_new_game);
        _clear_result=(Button)findViewById(R.id.btn_clear_best_result) ;



        /**
         * define objects that will be used later
         * */
        _model15 = new Model15();
        tableLayout = (TableLayout) findViewById(R.id.gameBoard);
        tableUtils = new table_utils(getApplicationContext(), this, _model15, tableLayout);

        /**
         * create the board initially
         * */
        tableUtils.createTable();

        /**
         * show the high scores if there are any stored in the shared preference
         * */
        Integer hScore = preference_Handler._getHighScore(getApplicationContext());
        String hScorer = preference_Handler._getHighScorer(getApplicationContext());
        if(hScore > 0){
            _footer_text.setText(hScorer + " - " + hScore);
        }



//        Set On click Listeners for Buttons


        //         OnClick Listener for New Game Button
        _new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        _header_text.setTextColor(0xFFFFFFFF);
                        _model15.startNewBoard();
                        
//                        Clear child Views
                
                        int counter = tableLayout.getChildCount();
                        for (int i = 0; i < counter; i++) {
                            View child = tableLayout.getChildAt(i);
                            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
                        }
                        
//                        Recreating a new board

                    tableUtils.createTable();
                        
    //                  check if this is solvable

                    if(_model15.isSolvable()) {
                        _header_text.setText(R.string.problemIsSolvable);
                    }else{
                        _header_text.setText(R.string.problemIsUnsolvable);
                    }
            }
        });

        //  OnClick Listener for clear button.

        _clear_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference_Handler._clearResult(getApplicationContext());
                _footer_text.setText(R.string.noResultSaved);
            }
        });

    }



    // function to ask user input when high score is achieved
    public void ask_Input() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.pop_up_highscore);

//        Button Setup
        Button _btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        final EditText input = (EditText) dialog.findViewById(R.id.ipRecordName);



        Button _btnSaveRecord = (Button) dialog.findViewById(R.id.btn_save_record);
        _btnSaveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = input.getText().toString();

                //save the result
                preference_Handler._setNewScore(getApplicationContext(), name,_model15.moves);

                //change the display board
                _footer_text.setText(String.format("%s - %d", name, _model15.moves));
                dialog.dismiss();
            }
        });

        _btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }
}
