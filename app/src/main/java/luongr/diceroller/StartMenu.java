package luongr.diceroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.SinglePlayer.view.SinglePlayerActivity;

public class StartMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSinglePlayer)
    void onSinglePlayer(){
        Intent intent = new Intent(this,SinglePlayerActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btnMultiplayer)
    void onMultiplayer(){

    }
}
