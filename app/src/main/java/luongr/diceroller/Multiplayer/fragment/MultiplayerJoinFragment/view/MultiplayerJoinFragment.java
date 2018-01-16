package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFragment.view;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Multiplayer.adapter.DevicesScannedAdapter;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFragment.model.MultiplayerJoinInteractor;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFragment.presenter.IMultiplayerJoinPresenter;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFragment.presenter.MutliplayerJoinPresenter;
import luongr.diceroller.Multiplayer.thread.JoinServerThread;
import luongr.diceroller.R;

import static android.app.Activity.RESULT_CANCELED;

/**
 * Created by Luong Randy on 1/2/2018.
 */

public class MultiplayerJoinFragment extends Fragment {
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_ACCESS_COARSE_LOCATION = 2;

    @BindView(R.id.rvJoinSelection)
    RecyclerView rvJoinSelection;

    IMultiplayerJoinListener listener;
    IMultiplayerJoinPresenter presenter;
    DevicesScannedAdapter adapter;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                presenter.onAddDevice(device);
                adapter.notifyDataSetChanged();
                //String deviceName = device.getName();
                //String deviceHardwareAddress = device.getAddress(); // MAC address
                //Log.d("JoinFragment","device: " + deviceName);
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mutliplayer_join,container,false);
        ButterKnife.bind(this,view);
        presenter = new MutliplayerJoinPresenter(new MultiplayerJoinInteractor());
        checkPermissions();
        setUpRV();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        getContext().registerReceiver(receiver, filter);
        return view;
    }

    private void setUpRV() {
        adapter = new DevicesScannedAdapter(getContext(), presenter.getDeviceList(), new DevicesScannedAdapter.Callback() {
            @Override
            public void onHostSelected(BluetoothDevice device) {
                JoinServerThread joinServerThread = new JoinServerThread(device);
                joinServerThread.start();
                listener.onShowJoinRollMenu();
            }
        });
        rvJoinSelection.setAdapter(adapter);
        rvJoinSelection.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(receiver);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (IMultiplayerJoinListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement IMultiplayerHostMenu");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //This is to check if the user will or will not enable bluetooth
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_CANCELED) {
                listener.onShowStartMenu();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("RequestCode","Code: " + requestCode);
        switch(requestCode){
            case REQUEST_ACCESS_COARSE_LOCATION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //We got permission do nothing.
                    Log.d("got permission", "celebrate");
                } else {
                    //User rejected the permission so take him back to the home screen because this
                    //is required
                    listener.onShowStartMenu();
                }
                break;
            default:
                break;
        }
    }

    private void checkPermissions() {
        if(!bluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_ACCESS_COARSE_LOCATION);
        }
    }

    public interface IMultiplayerJoinListener {
        void onShowStartMenu();
        void onShowJoinRollMenu();
    }

    @OnClick(R.id.btnScan)
    public void onScan(){
        bluetoothAdapter.startDiscovery();
    }
}
