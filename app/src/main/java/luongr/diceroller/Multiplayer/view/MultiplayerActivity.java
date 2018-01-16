package luongr.diceroller.Multiplayer.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import luongr.diceroller.Dialogs.Options.view.DialogOptions;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFragment.view.MultiplayerHostFragment;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.view.MultiplayerHostRollFragment;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFragment.view.MultiplayerJoinFragment;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.view.MultiplayerJoinRollFragment;
import luongr.diceroller.Multiplayer.fragment.MultiplayerStartFragment.view.MultiplayerStartFragment;
import luongr.diceroller.R;

public class MultiplayerActivity extends AppCompatActivity implements IMultiplayerActivity,
        MultiplayerStartFragment.IMultiplayerStartMenu, MultiplayerJoinFragment.IMultiplayerJoinListener,
        MultiplayerHostFragment.IMultiplayerHostListener {
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;
    DialogOptions dialogOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        ButterKnife.bind(this);
        Log.d("MultiplayerActivity","onActivityStart");
        showStartMenu();
    }

    @Override
    public void showStartMenu() {
        MultiplayerStartFragment startFragment = new MultiplayerStartFragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,startFragment);
        Log.d("mainactivity","backstack: " + fragmentManager.getBackStackEntryCount());
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.commit();
    }

    @Override
    public void showJoinMenu() {
        MultiplayerJoinFragment joinFragment = new MultiplayerJoinFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,joinFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void showHostMenu() {
        MultiplayerHostFragment hostFragment = new MultiplayerHostFragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,hostFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        dialogOptions = new DialogOptions();
        dialogOptions.show(getSupportFragmentManager(),"options");
        dialogOptions.setCancelable(false);
    }

    @Override
    public void onHost() {
        Log.d("OnHost","Listener");
        showHostMenu();
    }

    @Override
    public void onJoin() {
        Log.d("OnJoin","Listener");
        showJoinMenu();
    }

    @Override
    public void onShowStartMenu() {
        dialogOptions.dismiss();
        showStartMenu();
    }

    @Override
    public void onShowJoinRollMenu() {
        MultiplayerJoinRollFragment joinRollFragment = new MultiplayerJoinRollFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,joinRollFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onShowHostRollMenu() {
        MultiplayerHostRollFragment hostRollFragment = new MultiplayerHostRollFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,hostRollFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
