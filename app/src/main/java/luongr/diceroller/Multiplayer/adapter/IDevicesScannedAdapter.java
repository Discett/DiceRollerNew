package luongr.diceroller.Multiplayer.adapter;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Luong Randy on 1/10/2018.
 */

public interface IDevicesScannedAdapter {
    void onHostSelected(BluetoothDevice device);
}
