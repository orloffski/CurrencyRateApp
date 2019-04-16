package by.madcat.currencyrateapp.recyclerviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.madcat.currencyrateapp.R;
import by.madcat.currencyrateapp.common.Currency;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainCyrrencyViewHolder> {

    private List<Currency> data;
    private Context context;
    private LayoutInflater layoutInflater;

    public MainRecyclerViewAdapter(Context context) {
        this.data = new ArrayList<>();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MainCyrrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.currency_item, parent, false);
        return new MainCyrrencyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainCyrrencyViewHolder mainCyrrencyViewHolder, int i) {
        mainCyrrencyViewHolder.bind(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Currency> newData) {
        if (data != null) {
            PostDiffCallback postDiffCallback = new PostDiffCallback(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);

            data.clear();
            data.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        } else {
            // first initialization
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
