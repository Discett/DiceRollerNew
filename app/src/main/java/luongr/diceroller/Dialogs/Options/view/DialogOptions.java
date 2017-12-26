package luongr.diceroller.Dialogs.Options.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Dialogs.Options.model.DialogOptionsInteractor;
import luongr.diceroller.Dialogs.Options.presenter.DialogOptionsPresenter;
import luongr.diceroller.Dialogs.Options.presenter.IDialogOptionsPresenter;
import luongr.diceroller.Dice;
import luongr.diceroller.R;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public class DialogOptions extends DialogFragment implements IDialogOptions {
    static int SEEKBAR_CHOICE_MAX = 25;
    @BindView(R.id.txtDiceSelectedHeader)
    TextView txtDiceSelectedHeader;
    @BindView(R.id.txtDiceRangeHeader)
    TextView txtDiceRangeHeader;
    @BindView(R.id.txtSelectionHeader)
    TextView txtSelectionHeader;
    @BindView(R.id.sbSelection)
    SeekBar sbSelection;
    private DialogInterface.OnDismissListener dismissListener;

    IDialogOptionsPresenter presenter;
    DialogOptionsInteractor interactor;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(dismissListener != null){
            dismissListener.onDismiss(dialog);
        }
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener dismissListener){
        this.dismissListener = dismissListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_options,container,false);
        ButterKnife.bind(this,view);
        interactor = new DialogOptionsInteractor();
        presenter = new DialogOptionsPresenter(this,interactor);
        setUpSeekBar();

        return view;
    }

    private void setUpSeekBar() {
        sbSelection.setMax(SEEKBAR_CHOICE_MAX);
        sbSelection.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtSelectionHeader.setText(getString(R.string.current_number_of_selections,i));
                presenter.setMaxSelection(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick(R.id.btnDone)
    void onDone(){
        dismiss();
    }

    @OnClick({R.id.btn2, R.id.btn4, R.id.btn6, R.id.btn8, R.id.btn12, R.id.btn20})
    void onBtnPressed(View id){
        switch (id.getId()){
            case R.id.btn2:
                presenter.onPresetSelection(2);
                break;
            case R.id.btn4:
                presenter.onPresetSelection(4);
                break;
            case R.id.btn6:
                presenter.onPresetSelection(6);
                break;
            case R.id.btn8:
                presenter.onPresetSelection(8);
                break;
            case R.id.btn12:
                presenter.onPresetSelection(12);
                break;
            case R.id.btn20:
                presenter.onPresetSelection(20);
                break;
            default:
                break;
        }
    }
}
