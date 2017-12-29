package luongr.diceroller.Multiplayer.view;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Multiplayer.fragment.MultiplayerStartFragment.view.MultiplayerStartFragment;
import luongr.diceroller.R;

public class MultiplayerActivity extends AppCompatActivity implements IMultiplayerActivity, MultiplayerStartFragment.IMultiplayerStartMenu{
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        ButterKnife.bind(this);
        Log.d("MultiplayerActivity","onActivityStart");
        //showStartMenu();
    }

    @Override
    public void showStartMenu() {
        MultiplayerStartFragment startFragment = new MultiplayerStartFragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,startFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showJoinMenu() {

    }

    @Override
    public void showHostMenu() {

    }

    @Override
    public void onHost() {
        Log.d("OnHost","Listener");
    }

    @Override
    public void onJoin() {
        Log.d("OnJoin","Listener");
    }
}
