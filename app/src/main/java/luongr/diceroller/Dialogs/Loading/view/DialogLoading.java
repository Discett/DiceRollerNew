package luongr.diceroller.Dialogs.Loading.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import luongr.diceroller.R;

/**
 * Created by Luong on 4/18/2018.
 */

public class DialogLoading extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_loading,container,false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().setCancelable(false);
    }
}
