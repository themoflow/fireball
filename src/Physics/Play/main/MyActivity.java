package Physics.Play.main;


import Physics.Play.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.view.*;
import android.widget.Button;



public class MyActivity extends Activity {

    private AlertDialog.Builder builder;
    private Bundle reset;
    private Looper looper;
    private String displayText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        builder = new AlertDialog.Builder(this);
        Button[] b =  new Button[2];
        b[0] = (Button)findViewById(R.id.btnTwo);
        b[1] = (Button)findViewById(R.id.btnThree);
        looper = Looper.getMainLooper();
        setContentView(new MainGamePanel(this));
        //setListeners(b,this);
    }

    public void showDialog(String text){

        displayText = text;

        MyActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                builder.setMessage(displayText).setCancelable(false).setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        setContentView(new MainGamePanel(MyActivity.this));
                    }
                })
                        .setNegativeButton("Exit Game", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MyActivity.this.finish();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });



    }

    public void setListeners(Button[] b,final MyActivity a){

        b[0].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });

        b[1].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainGamePanel game = new MainGamePanel(a);
                setContentView(game);
            }
        });

    }//end setListeners().



    @Override
    public void onPause(){ super.onPause(); }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}