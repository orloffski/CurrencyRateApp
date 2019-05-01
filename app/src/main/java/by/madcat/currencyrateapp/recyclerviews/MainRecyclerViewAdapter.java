package by.madcat.currencyrateapp.recyclerviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.madcat.currencyrateapp.R;
import by.madcat.currencyrateapp.common.Currency;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Currency> data;
    private Context context;
    private LayoutInflater layoutInflater;

    public MainRecyclerViewAdapter(Context context) {
        this.data = new ArrayList<>();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            View itemView = layoutInflater.inflate(R.layout.currency_item, parent, false);
            return new MainCyrrencyItemViewHolder(itemView);
        }else if(viewType == TYPE_HEADER){
            View headerView = layoutInflater.inflate(R.layout.currency_header, parent, false);
            return new MainCurrencyHeaderViewHolder(headerView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if(holder instanceof MainCyrrencyItemViewHolder)
            ((MainCyrrencyItemViewHolder)holder).bind(data.get(i - 1));
        else if(holder instanceof  MainCurrencyHeaderViewHolder)
            ((MainCurrencyHeaderViewHolder)holder).bind(data.get(0));
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private Currency getItem(int position) {
        return data.get(position);
    }

    public void setData(List<Currency> newData) {
        if (data != null) {
            PostDiffCallback postDiffCallback = new PostDiffCallback(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);

            data.clear();
            data.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data = newData;
        }
    }

    class PostDiffCallback extends DiffUtil.Callback {

        private final List<Currency> oldCurrencies, newCurrencies;

        public PostDiffCallback(List<Currency> oldCurrencies, List<Currency> newCurrencies) {
            this.oldCurrencies = oldCurrencies;
            this.newCurrencies = newCurrencies;
        }

        @Override
        public int getOldListSize() {
            return oldCurrencies.size();
        }

        @Override
        public int getNewListSize() {
            return newCurrencies.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldCurrencies.get(oldItemPosition).getPosition() == newCurrencies.get(newItemPosition).getPosition();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldCurrencies.get(oldItemPosition).equals(newCurrencies.get(newItemPosition));
        }
    }
}
