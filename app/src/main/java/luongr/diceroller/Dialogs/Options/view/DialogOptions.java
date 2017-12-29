package luongr.diceroller.Dialogs.Options.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Dialogs.Options.model.DialogOptionsInteractor;
import luongr.diceroller.Dialogs.Options.presenter.DialogOptionsPresenter;
import luongr.diceroller.Dialogs.Options.presenter.IDialogOptionsPresenter;
import luongr.diceroller.R;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public class DialogOptions extends DialogFragment implements IDialogOptions {
    @BindView(R.id.edtDiceRange1)
    EditText edtDiceRange1;
    @BindView(R.id.edtDiceRange2)
    EditText edtDiceRange2;
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
        presenter.setUpSeekBar();
        presenter.getDiceInfo();
        setUpCustomRange();
        return view;
    }

    private void setUpCustomRange() {
        edtDiceRange1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onCustomDiceFieldOne(editable.toString());
                Log.d("onTextChange",editable.toString());
            }
        });
        edtDiceRange2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onCustomDiceFieldTwo(editable.toString());
                Log.d("onTextChange",editable.toString());
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
                clearEditTexts();
                presenter.onPresetSelection(2);
                txtDiceSelectedHeader.setText(getString(R.string.current_dice_selected,2));
                break;
            case R.id.btn4:
                clearEditTexts();
                presenter.onPresetSelection(4);
                txtDiceSelectedHeader.setText(getString(R.string.current_dice_selected,4));
                break;
            case R.id.btn6:
                clearEditTexts();
                presenter.onPresetSelection(6);
                txtDiceSelectedHeader.setText(getString(R.string.current_dice_selected,6));
                break;
            case R.id.btn8:
                clearEditTexts();
                presenter.onPresetSelection(8);
                txtDiceSelectedHeader.setText(getString(R.string.current_dice_selected,8));
                break;
            case R.id.btn12:
                clearEditTexts();
                presenter.onPresetSelection(12);
                txtDiceSelectedHeader.setText(getString(R.string.current_dice_selected,12));
                break;
            case R.id.btn20:
                clearEditTexts();
                presenter.onPresetSelection(20);
                txtDiceSelectedHeader.setText(getString(R.string.current_dice_selected,20));
                break;
            default:
                break;
        }
    }

    @Override
    public void setDiceInfoRange(int selections, int diceSides, int diceRange) {
        txtDiceSelectedHeader.setText(getString(R.string.current_dice_range_selected,diceRange,diceSides));
        txtSelectionHeader.setText(getString(R.string.current_number_of_selections,selections));
        sbSelection.setProgress(selections);
    }

    @Override
    public void setDiceInfo(int selections, int diceSides) {
        txtDiceSelectedHeader.setText(getString(R.string.current_dice_selected,diceSides));
        txtSelectionHeader.setText(getString(R.string.current_number_of_selections,selections));
        sbSelection.setProgress(selections);
    }

    @Override
    public void setUpSeekBar(int maxSelections) {
        sbSelection.setMax(maxSelections);
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

    @Override
    public void onDiceRangeChange(int sides, int range) {
        txtDiceSelectedHeader.setText(getString(R.string.current_dice_range_selected,range,sides));
    }

    @Override
    public void onDiceSidesChange(int sides) {
        txtDiceSelectedHeader.setText(getString(R.string.current_dice_selected,sides));
    }

    private void clearEditTexts(){
        edtDiceRange1.setText("");
        edtDiceRange2.setText("");
    }
}
