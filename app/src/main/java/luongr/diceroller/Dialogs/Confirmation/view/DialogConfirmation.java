package luongr.diceroller.Dialogs.Confirmation.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
    TextView txtConfirmationMesage;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    IDialogConfirmation listener;

    public static DialogConfirmation dialogInstance(String message, String btn1, String bt2){
        DialogConfirmation dialog = new DialogConfirmation();
        Bundle args = new Bundle();
        args.putString("message",message);
        args.putString("btn1",btn1);
        args.putString("btn2",bt2);
        dialog.setArguments(args);
        dialog.setCancelable(false);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm, container,false);
        ButterKnife.bind(this,view);
        txtConfirmationMesage.setText(getArguments().getString("message"));
        btn1.setText(getArguments().getString("btn1"));
        btn2.setText(getArguments().getString("btn2"));
        return view;
    }
//TODO: fix on attach
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            // Instantiate the IDialogConfirmation so we can send events to the host
            listener = (IDialogConfirmation) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement IDialogConfirmation");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @OnClick(R.id.btn1)
    public void onBtn1click(){
        if(listener != null){
            listener.onBtn1(this);
            Log.d("listener", "is not null");
        } else {
            Log.d("listener", "is null");
        }
    }

    @OnClick(R.id.btn2)
    public void onBtn2click(){

    }

    public interface IDialogConfirmation{
        public void onBtn1(DialogFragment dialog);
        public void onBtn2(DialogFragment dialog);
    }


}
