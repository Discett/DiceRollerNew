package luongr.diceroller.Adapters.Selection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luongr.diceroller.R;
import luongr.diceroller.Selection;

/**
 * Created by Luong Randy on 12/26/2017.
 */

public class SelectionAdapter extends RecyclerView.Adapter<SelectionAdapter.ViewHolder> {
    private Context context;
    private List<Selection> selectionList;

    public SelectionAdapter(Context context, List<Selection> selectionList) {
        this.context = context;
        this.selectionList = selectionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_selection,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtSelectionName.setText(selectionList.get(position).getName());
        if (selectionList.get(position).getRollResult() != null) {
            holder.txtSelectionRoll.setText(String.valueOf(selectionList.get(position).getRollResult()));
        } else {
            holder.txtSelectionRoll.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return selectionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtSelectionName)
        TextView txtSelectionName;
        @BindView(R.id.txtSelectionRoll)
        TextView txtSelectionRoll;
        @BindView(R.id.btnRemove)
        Button btnRemove;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
