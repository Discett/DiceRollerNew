package luongr.diceroller.Multiplayer.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luongr.diceroller.R;

/**
 * Created by Luong Randy on 1/5/2018.
 */

public class DevicesScannedAdapter extends RecyclerView.Adapter<DevicesScannedAdapter.ViewHolder> {
    //TODO: replace this with MultiplayerHost object
    List<BluetoothDevice> bluetoothDeviceList;
    Context context;
    Callback callback;

    public DevicesScannedAdapter(Context context,List<BluetoothDevice> bluetoothDeviceList, Callback callback) {
        this.context = context;
        this.bluetoothDeviceList = bluetoothDeviceList;
        this.callback = callback;
    }

    @Override
    public DevicesScannedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_of_hosts,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onHostSelected(bluetoothDeviceList.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DevicesScannedAdapter.ViewHolder holder, int position) {
        holder.txtHostName.setText(context.getResources().getString(R.string.item_list_host,position+1,
                bluetoothDeviceList.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return bluetoothDeviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtHostName)
        TextView txtHostName;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface Callback{
        void onHostSelected(BluetoothDevice device);
    }
}
