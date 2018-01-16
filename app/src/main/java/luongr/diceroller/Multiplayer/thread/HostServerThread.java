package luongr.diceroller.Multiplayer.thread;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

import static android.content.ContentValues.TAG;

/**
 * Created by Luong Randy on 1/10/2018.
 */

public class HostServerThread extends Thread {
    private final BluetoothServerSocket mmServerSocket;
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private final UUID MY_UUID = UUID.nameUUIDFromBytes(mBluetoothAdapter.getName().getBytes());
    private final String NAME = "DiceRoller";

    public HostServerThread() {
        // Use a temporary object that is later assigned to mmServerSocket
        // because mmServerSocket is final.
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code.
            tmp = mBluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(NAME, MY_UUID);
        } catch (IOException e) {
            Log.e(TAG, "Socket's listen() method failed", e);
        }
        mmServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned.
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                Log.e(TAG, "Socket's accept() method failed", e);
                break;
            }
/* //We want multiple connections so we don't do this
            if (socket != null) {
                // A connection was accepted. Perform work associated with
                // the connection in a separate thread.

                //manageMyConnectedSocket(socket);

                try {
                    mmServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
*/
        }
    }

    // Closes the connect socket and causes the thread to finish.
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the connect socket", e);
        }
    }
}
