package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFinalFragment.view;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFinalFragment.presenter.MultiplayerJoinFinalPresenter;
import luongr.diceroller.Multiplayer.service.MultiplayerBluetoothService;
import luongr.diceroller.R;

/**
 * Created by Luong on 5/14/2018.
 */

public class MultiplayerJoinFinalFragment extends Fragment {
    BluetoothSocket socket = null;
    MultiplayerBluetoothService mpBS;
    MultiplayerJoinFinalPresenter presenter;
    @BindView(R.id.txtSelectionWinner)
    TextView txtSelectionWinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplayer_join_final,container,false);
        ButterKnife.bind(this,view);
        presenter = new MultiplayerJoinFinalPresenter(this);
        if(socket == null){
            Log.d("MultiplayerJoinRoll", "Null Socket");
        }
        mpBS.setHandler(mHandler);

        return view;
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case MultiplayerBluetoothService.MessageConstants.MESSAGE_WRITE:
                    break;
                case MultiplayerBluetoothService.MessageConstants.MESSAGE_READ:
                    //when handler reads the 0 which is defined by your constant read the object buffer
                    byte[] readBuffer = (byte[]) msg.obj;
                    //now we can convert the byte to string
                    //set up readMessage like this so we get the appropriate size for string via arg1
                    String readMessage = new String(readBuffer, 0, msg.arg1);
                    //presenter.parseMessageList(readMessage);
                    presenter.onGetSelectionWinner(readMessage);
                    Log.d("HostReadMessages!",readMessage);
                    //Log.d("HostReadMessage",String.valueOf(msg.arg1));
                    //Log.d("HostReadMessage",String.valueOf(msg.arg2));
                    break;
            }
        }
    };

    public void setSocket(BluetoothSocket socket) {
        this.socket = socket;
    }

    public void setService(MultiplayerBluetoothService service) {
        mpBS = service;
    }

    public void setSelectionWinner(String message) {
        txtSelectionWinner.setText(message);
    }
}
