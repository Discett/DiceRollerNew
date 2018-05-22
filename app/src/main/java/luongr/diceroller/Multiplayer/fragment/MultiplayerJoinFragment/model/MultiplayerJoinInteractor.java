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
    boolean isInList = false;

    public void onAddDevice(BluetoothDevice device) {
        //TODO: check if device is already in the list
        Log.d("JoinInteractor","list size: " + bluetoothDeviceList.size());
        for(int i = 0; i < bluetoothDeviceList.size(); i++){
            if(device.getAddress().equals(bluetoothDeviceList.get(i).getAddress())){
                isInList = true;
            }
        }
        if(!isInList){
            bluetoothDeviceList.add(device);
        }
        Log.d("JoinInteractor","OnAddDevice: " + bluetoothDeviceList);
    }

    public List<BluetoothDevice> getDeviceList() {
        return bluetoothDeviceList;
    }

}
