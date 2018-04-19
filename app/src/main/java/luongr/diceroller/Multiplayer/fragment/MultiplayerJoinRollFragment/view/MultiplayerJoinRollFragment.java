package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.view;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Adapters.Selection.SelectionAdapter;
import luongr.diceroller.Dialogs.Loading.view.DialogLoading;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.model.MultiplayerJoinRollFragmentInteractor;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.presenter.MultiplayerJoinRollFragmentPresenter;
import luongr.diceroller.Multiplayer.service.MultiplayerBluetoothService;
import luongr.diceroller.R;

/**
 * Created by Luong Randy on 1/16/2018.
 */

public class MultiplayerJoinRollFragment extends Fragment {

    @BindView(R.id.rvUserSelections)
    RecyclerView rvSelections;
    @BindView(R.id.edtSelection)
    EditText edtSelection;
    @BindView(R.id.txtUserSelectionHeader)
    TextView txtUserSelectionHeader;
    @BindView(R.id.btnAddSelection)
    Button btnAddSelection;
    BluetoothSocket socket = null;
    DialogFragment loading;
    MultiplayerBluetoothService mpBluetoothService;
    MultiplayerJoinRollFragmentPresenter presenter;
    SelectionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loading = new DialogLoading();
        showLoadingDialog();
        View view = inflater.inflate(R.layout.fragment_multiplayer_join_roll,container,false);
        ButterKnife.bind(this,view);
        presenter = new MultiplayerJoinRollFragmentPresenter(this,new MultiplayerJoinRollFragmentInteractor(getContext()));
        if(socket == null){
            Log.d("MultiplayerJoinRoll", "Null Socket");
        }
        mpBluetoothService = new MultiplayerBluetoothService(socket, mHandler);
        setUpFragment();
        return view;
    }

    public void showEdtSelection() {
        btnAddSelection.setVisibility(View.VISIBLE);
        edtSelection.setVisibility(View.VISIBLE);
    }

    public void hideEdtSelection() {
        btnAddSelection.setVisibility(View.INVISIBLE);
        edtSelection.setVisibility(View.INVISIBLE);
    }

    private void setUpFragment() {
        adapter = new SelectionAdapter(getContext(), presenter.getListOfSelection(), new SelectionAdapter.Callback() {
            @Override
            public void onRemoved() {
                //TODO: set up the removal of a selection and update ui
            }
        });
        rvSelections.setAdapter(adapter);
        rvSelections.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void hideLoadingDialog() {
        presenter.checkMaxSelections();
        loading.dismiss();
    }

    private void showLoadingDialog() {
        loading.show(getFragmentManager(),"Loading");
    }

    Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case MultiplayerBluetoothService.MessageConstants.MESSAGE_READ:
                    byte[] readBuffer = (byte[]) msg.obj;
                    //set up readMessage like this so we get the appropriate size for string via arg1
                    String readMessage = new String(readBuffer, 0, msg.arg1);
                    presenter.parseMessage(readMessage);
                    Log.d("HostReadMessage",readMessage);
                    break;
            }
        }
    };

    @OnClick(R.id.btnAddSelection)
    public void onAddSelection(){
        Log.d("btnAddSelection","adding");
        presenter.addSelectionToList(edtSelection.getText().toString());
        synchronized(adapter){
            adapter.notifyDataSetChanged();
        }
        presenter.checkMaxSelections();
    }

    @OnClick(R.id.btnSendSelections)
    public void onSendSelection(){
        //TODO: setup the confirmation before sending
    }

    public void setSocket(BluetoothSocket socket){
        this.socket = socket;
    }

    public void joinRollDisplayInfo(String info) {
        txtUserSelectionHeader.setText(info);
    }
}
