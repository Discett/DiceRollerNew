package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFragment.presenter;

import android.bluetooth.BluetoothDevice;

import java.util.List;

/**
 * Created by Luong Randy on 1/2/2018.
 */

public interface IMultiplayerJoinPresenter {
    void onAddDevice(BluetoothDevice device);
    void onRemoveDevice();

    List<BluetoothDevice> getDeviceList();

}
