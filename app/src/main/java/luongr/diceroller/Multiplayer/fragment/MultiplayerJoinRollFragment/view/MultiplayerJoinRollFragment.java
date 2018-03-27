package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.view;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Multiplayer.service.MultiplayerBluetoothService;
import luongr.diceroller.Multiplayer.thread.JoinServerThread;
import luongr.diceroller.R;

/**
 * Created by Luong Randy on 1/16/2018.
 */

public class MultiplayerJoinRollFragment extends Fragment {

    @BindView(R.id.rvUserSelections)
    RecyclerView rvSelection;
    @BindView(R.id.edtSelection)
    EditText edtSelection;
    BluetoothSocket socket = null;
    MultiplayerBluetoothService mpBluetoothService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplayer_join_roll,container,false);
        if(socket == null){
            Log.d("MultiplayerJoinRoll", "Null Socket");
        }
        mpBluetoothService = new MultiplayerBluetoothService(socket, mHandler);
        ButterKnife.bind(this,view);
        return view;
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){

        }
    };

    @OnClick(R.id.btnSendSelections)
    public void onSendSelection(){
        String test = "hello";
        Log.d("BtnSendSelections",test.getBytes().toString());
        mpBluetoothService.write(test.getBytes());
    }

    public void setSocket(BluetoothSocket socket){
        this.socket = socket;
    }
}
