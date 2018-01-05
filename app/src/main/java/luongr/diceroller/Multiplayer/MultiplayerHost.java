package luongr.diceroller.Multiplayer;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Luong Randy on 1/5/2018.
 */

public class MultiplayerHost {
    private String name;
    private BluetoothDevice device;

    public MultiplayerHost(String name, BluetoothDevice device) {
        this.name = name;
        this.device = device;
    }
}
