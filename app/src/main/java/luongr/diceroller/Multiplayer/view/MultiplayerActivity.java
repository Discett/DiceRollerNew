package luongr.diceroller.Multiplayer.view;

import android.bluetooth.BluetoothSocket;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luongr.diceroller.Dialogs.Options.view.DialogOptions;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.view.MultiplayerHostFinalFragment;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFragment.view.MultiplayerHostFragment;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.view.MultiplayerHostRollFragment;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFinalFragment.view.MultiplayerJoinFinalFragment;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFragment.view.MultiplayerJoinFragment;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.view.MultiplayerJoinRollFragment;
import luongr.diceroller.Multiplayer.fragment.MultiplayerStartFragment.view.MultiplayerStartFragment;
import luongr.diceroller.Multiplayer.interactor.MultiplayerActivityInteractor;
import luongr.diceroller.Multiplayer.presenter.IMultiplayerActivityPresenter;
import luongr.diceroller.Multiplayer.presenter.MultiplayerActivityPresenter;
import luongr.diceroller.R;
import luongr.diceroller.Selection;

public class MultiplayerActivity extends AppCompatActivity implements IMultiplayerActivity,
        MultiplayerStartFragment.IMultiplayerStartMenu, MultiplayerJoinFragment.IMultiplayerJoinListener,
        MultiplayerHostFragment.IMultiplayerHostListener, MultiplayerHostFinalFragment.IMultiplayerHostFinalFragment,
        MultiplayerHostRollFragment.IMultiplayerHostRollFragment, MultiplayerJoinRollFragment.IMultiplayerJoinRollFragment{
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;
    DialogOptions dialogOptions;
    IMultiplayerActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        ButterKnife.bind(this);
        presenter = new MultiplayerActivityPresenter(new MultiplayerActivityInteractor());

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
    public void onShowJoinRollMenu(BluetoothSocket socket) {
        MultiplayerJoinRollFragment joinRollFragment = new MultiplayerJoinRollFragment();
        joinRollFragment.setSocket(socket);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,joinRollFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onShowHostRollMenu(BluetoothSocket socket) {
        MultiplayerHostRollFragment hostRollFragment = new MultiplayerHostRollFragment();
        hostRollFragment.setSocket(socket);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,hostRollFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onShowMultiplayerHostFinal(BluetoothSocket socket) {
        MultiplayerHostFinalFragment hostFinalFragment = new MultiplayerHostFinalFragment();
        hostFinalFragment.setSocket(socket);
        hostFinalFragment.setSelectionList(presenter.getSelectionList());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,hostFinalFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onFinalizedSelectionList(List<Selection> selectionList) {
        presenter.setSelectionList(selectionList);
    }

    @Override
    public void onShowMultiplayerJoinFinalFragment(BluetoothSocket socket) {
        MultiplayerJoinFinalFragment joinFinalFragment = new MultiplayerJoinFinalFragment();
        joinFinalFragment.setSocket(socket);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,joinFinalFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
