package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFinalFragment.view;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import luongr.diceroller.R;

/**
 * Created by Luong on 5/14/2018.
 */

public class MultiplayerJoinFinalFragment extends Fragment {
    BluetoothSocket socket = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplayer_join_final,container,false);
        ButterKnife.bind(this,view);

        return view;
    }

    public void setSocket(BluetoothSocket socket) {
        this.socket = socket;
    }
}
