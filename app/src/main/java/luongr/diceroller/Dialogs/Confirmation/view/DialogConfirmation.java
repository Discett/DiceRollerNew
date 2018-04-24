package luongr.diceroller.Dialogs.Confirmation.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Dialogs.Confirmation.model.DialogConfirmationInteractor;
import luongr.diceroller.Dialogs.Confirmation.presenter.DialogConfirmationPresenter;
import luongr.diceroller.Dialogs.Confirmation.presenter.IDialogConfirmationPresenter;
import luongr.diceroller.R;

/**
 * Created by Luong Randy on 1/18/2018.
 */
//TODO: finish up the dynamic confirmation
public class DialogConfirmation extends DialogFragment {
    @BindView(R.id.txtConfirmationMessage)
    TextView txtConfirmationMessage;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    IDialogConfirmationPresenter presenter;
    IDialogConfirmation listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm,container,false);
        ButterKnife.bind(this,view);
        txtConfirmationMessage.setText("hello");
        return view;
    }

    public interface IDialogConfirmation{
        public void onBtn1(DialogFragment dialog);
        public void onBtn2(DialogFragment dialog);
    }
    //TODO: Fix the onAttach and childFragment
    //TODO: Also find a way to change the text dynamically
    
    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        try {
            // Instantiate the IDialogConfirmation so we can send events to the host
            listener = (IDialogConfirmation) childFragment;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(childFragment.toString()
                    + " must implement IDialogConfirmation");
        }
    }

}
