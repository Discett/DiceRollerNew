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
import luongr.diceroller.Dice;
import luongr.diceroller.R;
import luongr.diceroller.Selection;

/**
 * Created by Luong Randy on 12/26/2017.
 */

public class SelectionAdapter extends RecyclerView.Adapter<SelectionAdapter.ViewHolder> implements ISelectionAdapter {
    private Context context;
    private List<Selection> selectionList;
    private Callback callback;
    private Dice dice;

    public SelectionAdapter(Context context, List<Selection> selectionList, Callback callback) {
        this.context = context;
        this.callback = callback;
        this.selectionList = selectionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_selection,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        dice = Dice.getInstance();
        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectionList.remove(viewHolder.getAdapterPosition());
                notifyDataSetChanged();
                callback.onRemoved();
            }
        });
        return viewHolder;
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

    @Override
    public void onRoll() {
        for(Selection item:selectionList){
            item.setRollResult(dice.rollDice());
        }
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
    public interface Callback{
        void onRemoved();
    }
}
