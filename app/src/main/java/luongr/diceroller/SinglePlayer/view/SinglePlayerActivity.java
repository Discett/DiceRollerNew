package luongr.diceroller.SinglePlayer.view;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luongr.diceroller.Adapters.Selection.SelectionAdapter;
import luongr.diceroller.Dialogs.Options.view.DialogOptions;
import luongr.diceroller.Dice;
import luongr.diceroller.R;
import luongr.diceroller.Selection;
import luongr.diceroller.SinglePlayer.model.SinglePlayerInteractor;
import luongr.diceroller.SinglePlayer.presenter.ISinglePlayerPresenter;
import luongr.diceroller.SinglePlayer.presenter.SinglePlayerPresenter;

public class SinglePlayerActivity extends AppCompatActivity implements ISinglePlayerActivity {
    @BindView(R.id.btnRoll)
    Button btnRoll;
    @BindView(R.id.btnAddSelection)
    Button btnAddSelection;
    @BindView(R.id.edtSelection)
    EditText edtSelection;
    @BindView(R.id.txtDiceRoll)
    TextView txtDiceRoll;
    @BindView(R.id.rvSelection)
    RecyclerView rvSelection;
    @BindView(R.id.tbToolbar)
    Toolbar tbToolbar;


    ISinglePlayerPresenter presenter;
    SinglePlayerInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        ButterKnife.bind(this);
        setSupportActionBar(tbToolbar);
        setUpRecyclerView();
        interactor = new SinglePlayerInteractor();
        presenter = new SinglePlayerPresenter(this,interactor);
        presenter.checkSelection();
        presenter.checkListEntries();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setUpRecyclerView() {
        //rvSelection.setAdapter(new SelectionAdapter(this,));
        //rvSelection.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.single_player_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                Log.d("SPA","Options clicked");
                presenter.onSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.btnRoll)
    public void onRoll(){
        presenter.onRoll();
    }

    @Override
    public void showSelections() {
        rvSelection.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSelections() {
        rvSelection.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setTxtRoll(int roll) {
        txtDiceRoll.setText(String.valueOf(roll));
    }

    @Override
    public void showSettingsDialog() {
        DialogOptions optionsDialog = new DialogOptions();
        optionsDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                presenter.checkSelection();
                presenter.checkListEntries();
            }
        });
        optionsDialog.show(getSupportFragmentManager(), "options");
    }

    @Override
    public void showEdtSelections() {
        edtSelection.setVisibility(View.VISIBLE);
        btnAddSelection.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEdtSelections() {
        edtSelection.setVisibility(View.INVISIBLE);
        btnAddSelection.setVisibility(View.INVISIBLE);
    }
}
