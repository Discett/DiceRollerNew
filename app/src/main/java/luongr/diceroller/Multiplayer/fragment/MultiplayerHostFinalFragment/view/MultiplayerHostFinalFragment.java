package luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.view;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Adapters.Selection.SelectionAdapter;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.interactor.MultiplayerHostFinalInteractor;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.presenter.IMultiplayerHostFinalFragmentPresenter;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.presenter.MultiplayerHostFinalFragmentPresenter;
import luongr.diceroller.Multiplayer.service.MultiplayerBluetoothService;
import luongr.diceroller.R;
import luongr.diceroller.Selection;

/**
 * Created by Luong on 5/14/2018.
 */

public class MultiplayerHostFinalFragment extends Fragment implements IMultiplayerHostFinal {

    MultiplayerBluetoothService mpBS;
    BluetoothSocket socket = null;
    IMultiplayerHostFinalFragmentPresenter presenter = new MultiplayerHostFinalFragmentPresenter(new MultiplayerHostFinalInteractor());
    @BindView(R.id.btnRoll)
    Button btnRoll;
    @BindView(R.id.rvSelectionList)
    RecyclerView rvSelectionList;
    SelectionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplayer_host_final,container,false);
        ButterKnife.bind(this,view);
        if(socket == null){
            Log.d("MultiplayerJoinRoll", "Null Socket");
        }
        mpBS.setHandler(mHandler);
        setUpRV();
        //Log.d("FinalFragmentSocket",socket.toString());
        //Log.d("FinalSelectionList",presenter.getSelectionList().toString());

        return view;
    }

    private void setUpRV() {
        adapter = new SelectionAdapter(getContext(), presenter.getSelectionList(), false, new SelectionAdapter.Callback() {
            @Override
            public void onRemoved() {
                //not necessary at this point
            }
        });
        rvSelectionList.setAdapter(adapter);
        rvSelectionList.setLayoutManager(new LinearLayoutManager(getContext()));
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
                    Log.d("HostReadMessageFinal",readMessage);
                    presenter.parseMessageList(readMessage);
                    adapter.notifyDataSetChanged();
                    //Log.d("HostReadMessage",String.valueOf(msg.arg1));
                    //Log.d("HostReadMessage",String.valueOf(msg.arg2));
                    break;
            }
        }
    };

    @OnClick(R.id.btnRoll)
    public void onRoll(){
        //Rolls and sends the winner to everyone.
        //TODO: socket stuff
        mpBS.write("Verify That This Works".getBytes());
        //Log.d("FinalFragmentSocket",socket.toString());
        adapter.notifyDataSetChanged();
        //Log.d("FinalSelectionList",presenter.getSelectionList().toString());
    }

    public void setSocket(BluetoothSocket socket) {
        //We still need the socket in order to send to join players
        this.socket = socket;
    }

    public void setSelectionList(List<Selection> selectionList) {
        //We get our selection list from the HostRollFragment which sends it to the actvitiy which
        //sends it to our fragment.
        presenter.setSelectionList(selectionList);
    }

    @Override
    public void onShowRollButton() {
        btnRoll.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideRollButton() {
        btnRoll.setVisibility(View.INVISIBLE);
    }

    public void setService(MultiplayerBluetoothService service) {
        mpBS = service;
    }

    public interface IMultiplayerHostFinalFragment{
        //might not need this
    }
}
