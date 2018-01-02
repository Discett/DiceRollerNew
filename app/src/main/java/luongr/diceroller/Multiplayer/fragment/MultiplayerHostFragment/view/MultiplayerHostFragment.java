package luongr.diceroller.Multiplayer.fragment.MultiplayerHostFragment.view;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.R;

/**
 * Created by Luong Randy on 1/1/2018.
 */

public class MultiplayerHostFragment extends Fragment {
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int RESULT_CANCELED = 0;

    //IMultiplayerHostMenu listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mutliplayer_host,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btnStart)
    public void onStartPressed(){
        startDiscovery();
    }


    private void startDiscovery() {
        Log.d("MultiplayerHost","Start Discovery");
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
    }

    public interface IMultiplayerHostMenu{
        void onBlueToothCancel();
    }
}
