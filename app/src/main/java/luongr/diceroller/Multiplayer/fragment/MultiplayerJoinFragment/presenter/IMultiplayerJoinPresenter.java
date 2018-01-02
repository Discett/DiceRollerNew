package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFragment.presenter;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Luong Randy on 1/2/2018.
 */

public interface IMultiplayerJoinPresenter {
    void onAddDevice(BluetoothDevice device);
    void onRemoveDevice();
}
