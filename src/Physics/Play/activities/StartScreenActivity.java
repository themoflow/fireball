package Physics.Play.activities;

import Physics.Play.R;
import Physics.Play.database.DatabaseManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class StartScreenActivity extends Activity {

    private boolean logEnabled = true;
    private DatabaseManager dbm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(":::: StartScreenActivity.java - ", "onCreate() called :::: ");
        super.onCreate(savedInstanceState);
        dbm = new DatabaseManager(this);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start_screen);
        if(getIntent().getExtras() != null)
        {
            if(getIntent().getExtras().getString("gameOver").equals("true")) {
                findViewById(R.id.gameOverText1).setVisibility(View.VISIBLE);
                findViewById(R.id.gameOverText2).setVisibility(View.VISIBLE);
            }
        }
        Button[] buttons =  new Button[2];
        buttons[0] = (Button)findViewById(R.id.startBtn);
        buttons[1] = (Button)findViewById(R.id.continueBtn);
        if(!dbm.isGameStateSaved())
            buttons[1].setVisibility(View.GONE);
        setListeners(buttons, this);
    }

    public void setListeners(Button[] buttons, final StartScreenActivity activity){

        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainGameActivity.class);
                intent.putExtra("gameType","new game");
                startActivity(intent);
                finish();
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainGameActivity.class);
                intent.putExtra("gameType", "continue");
                startActivity(intent);
                finish();
            }
        });

    }//end setListeners().

    private void log(String print) {
        if(logEnabled)
            Log.i(":::: StartScreenActivity.java - ", print + " ::::");
    }
}
