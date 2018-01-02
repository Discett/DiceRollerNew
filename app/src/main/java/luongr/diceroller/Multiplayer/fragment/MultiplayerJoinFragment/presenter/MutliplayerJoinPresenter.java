package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFragment.presenter;

import android.bluetooth.BluetoothDevice;

import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFragment.model.MultiplayerJoinInteractor;

/**
 * Created by Luong Randy on 1/2/2018.
 */

public class MutliplayerJoinPresenter implements IMultiplayerJoinPresenter{

    MultiplayerJoinInteractor interactor;

    public MutliplayerJoinPresenter(MultiplayerJoinInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onAddDevice(BluetoothDevice device) {
        interactor.onAddDevice(device);
    }

    @Override
    public void onRemoveDevice() {

    }
}
