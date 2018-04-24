package luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.view;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Adapters.Selection.SelectionAdapter;
import luongr.diceroller.Dice;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.model.MultiplayerHostRollFragmentInteractor;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.presenter.IMultiplayerHostRollFragmentPresenter;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.presenter.MultiplayerHostRollFragmentPresenter;
import luongr.diceroller.Multiplayer.service.MultiplayerBluetoothService;
import luongr.diceroller.R;

/**
 * Created by Luong Randy on 1/16/2018.
 */

public class MultiplayerHostRollFragment extends Fragment {

    @BindView(R.id.btnLockSelection)
    ToggleButton btnLockSelection;
    @BindView(R.id.rvSelection)
    RecyclerView rvSelection;
    @BindView(R.id.edtSelection)
    EditText edtSelection;

    MultiplayerBluetoothService mpBluetoothService;
    Dice dice;
    BluetoothSocket socket = null;
    IMultiplayerHostRollFragmentPresenter presenter;
    //TODO: limit this fragment to number of available user inputs

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mutliplayer_host_roll,container,false);
        ButterKnife.bind(this,view);
        if(socket == null){
            Log.d("MultiplayerJoinRoll", "Null Socket");
        }
        mpBluetoothService = new MultiplayerBluetoothService(socket,mHandler);
        presenter = new MultiplayerHostRollFragmentPresenter(new MultiplayerHostRollFragmentInteractor());
        dice = Dice.getInstance();
        //sends dice information
        Log.d("MultiplayerJoinRoll", "Send Dice Info");
        mpBluetoothService.write(diceInfo());
        return view;
    }

    private byte[] diceInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(MultiplayerBluetoothService.MessageConstants.DICE_NUMBER_OF_SELECTION);
        sb.append(dice.getDiceSides());
        sb.append(':');
        sb.append(dice.getDiceSidesRange());
        sb.append(':');
        sb.append(dice.getNumberOfSelections());
        return sb.toString().getBytes();
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
                    presenter.parseMessageList(readMessage);
                    //Log.d("HostReadMessage",readMessage);
                    //Log.d("HostReadMessage",String.valueOf(msg.arg1));
                    //Log.d("HostReadMessage",String.valueOf(msg.arg2));
                    break;
            }
        }
    };

    @OnClick(R.id.btnAddSelection)
    public void addSelection(){
        presenter.addSelection(edtSelection.getText().toString());
    }

    private void setUpRV() {
        SelectionAdapter adapter = new SelectionAdapter(getContext(), presenter.getSelectionList(), new SelectionAdapter.Callback() {
            @Override
            public void onRemoved() {
                //might need to run a check to see the number of available selections
            }
        });
        rvSelection.setAdapter(adapter);
        rvSelection.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void setSocket(BluetoothSocket socket) {
        this.socket = socket;
    }

    @OnClick(R.id.btnLockSelection)
    public void onLockToggle(){

    }
}
