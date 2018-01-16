package luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mutliplayer_host_roll,container,false);
        ButterKnife.bind(this,view);

        return view;
    }
}
