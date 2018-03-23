package luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.view;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
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
        mpBluetoothService = new MultiplayerBluetoothService(socket);
        presenter = new MultiplayerHostRollFragmentPresenter(new MultiplayerHostRollFragmentInteractor());
        setUpRV();
        return view;
    }

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
