package luongr.diceroller.Dialogs.Confirmation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import luongr.diceroller.R;

/**
 * Created by Luong Randy on 1/18/2018.
 */
//TODO: finish up the dynamic confirmation
public class DialogConfirmation extends DialogFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm,container,false);

        return view;
    }
}
