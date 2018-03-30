package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.view;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Dice;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.model.MultiplayerJoinRollFragmentInteractor;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.presenter.MultiplayerJoinRollFragmentPresenter;
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
    @BindView(R.id.txtUserSelectionHeader)
    TextView txtUserSelectionHeader;
    BluetoothSocket socket = null;
    MultiplayerBluetoothService mpBluetoothService;
    MultiplayerJoinRollFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplayer_join_roll,container,false);
        presenter = new MultiplayerJoinRollFragmentPresenter(this,new MultiplayerJoinRollFragmentInteractor(getContext()));
        if(socket == null){
            Log.d("MultiplayerJoinRoll", "Null Socket");
        }
        mpBluetoothService = new MultiplayerBluetoothService(socket, mHandler);
        ButterKnife.bind(this,view);
        return view;
    }

    Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case MultiplayerBluetoothService.MessageConstants.MESSAGE_READ:
                    //TODO: get the string and decide if it's winner or setup
                    byte[] readBuffer = (byte[]) msg.obj;
                    //set up readMessage like this so we get the appropriate size for string via arg1
                    String readMessage = new String(readBuffer, 0, msg.arg1);
                    presenter.parseMessage(readMessage);
                    Log.d("HostReadMessage",readMessage);
                    break;
            }
        }
    };

    @OnClick(R.id.btnSendSelections)
    public void onSendSelection(){
        String test = "hello";
        Log.d("BtnSendSelections",test.getBytes().toString());
        mpBluetoothService.write(test.getBytes());
        Dice dice = Dice.getInstance();
    }

    public void setSocket(BluetoothSocket socket){
        this.socket = socket;
    }

    public void joinRollDisplayInfo(String info) {
        //TODO: needs to update the UI when this is called
        txtUserSelectionHeader.setText(info);
    }
}
