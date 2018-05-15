package luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.view;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.interactor.MultiplayerHostFinalInteractor;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.presenter.IMultiplayerHostFinalFragmentPresenter;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.presenter.MultiplayerHostFinalFragmentPresenter;
import luongr.diceroller.R;
import luongr.diceroller.Selection;

/**
 * Created by Luong on 5/14/2018.
 */

public class MultiplayerHostFinalFragment extends Fragment {

    BluetoothSocket socket = null;
    IMultiplayerHostFinalFragmentPresenter presenter = new MultiplayerHostFinalFragmentPresenter(new MultiplayerHostFinalInteractor());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplayer_host_final,container,false);
        ButterKnife.bind(this,view);
        Log.d("FinalFragmentSocket",socket.toString());
        Log.d("FinalSelectionList",presenter.getSelectionList().toString());

        return view;
    }

    @OnClick(R.id.btnRoll)
    public void onRoll(){
        //Rolls and sends the winner to everyone.
        //TODO: socket stuff
        Log.d("FinalFragmentSocket",socket.toString());
        Log.d("FinalSelectionList",presenter.getSelectionList().toString());
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

    public interface IMultiplayerHostFinalFragment{

    }
}
