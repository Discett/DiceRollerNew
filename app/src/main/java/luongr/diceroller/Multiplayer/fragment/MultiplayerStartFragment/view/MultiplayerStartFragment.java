package luongr.diceroller.Multiplayer.fragment.MultiplayerStartFragment.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.R;

/**
 * Created by Luong Randy on 12/29/2017.
 */

public class MultiplayerStartFragment extends Fragment {
    IMultiplayerStartMenu listener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplayer_start,container,false);
        ButterKnife.bind(this,view);
        Log.d("MultiplayerStartMenu","onFragmentStart");

        return view;
    }

    public interface IMultiplayerStartMenu {
        void onHost();
        void onJoin();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (IMultiplayerStartMenu) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement IMultiplayerStartMenu");
        }
    }


    @OnClick(R.id.btnHost)
    public void onHost(){
        listener.onHost();
    }
    @OnClick(R.id.btnJoin)
    public void onJoin(){
        listener.onJoin();
    }
}
