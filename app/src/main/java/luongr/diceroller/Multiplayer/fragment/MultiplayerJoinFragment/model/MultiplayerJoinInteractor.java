package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFragment.model;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luong Randy on 1/2/2018.
 */

public class MultiplayerJoinInteractor {
    List<BluetoothDevice> bluetoothDeviceList = new ArrayList<>();

    public void onAddDevice(BluetoothDevice device) {
        //TODO: check if device is already in the list
        //bluetoothDeviceList.add(device);
        Log.d("JoinInteractor","OnAddDevice: " + bluetoothDeviceList);
    }
}
