package by.madcat.currencyrateapp.recyclerviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.madcat.currencyrateapp.R;
import by.madcat.currencyrateapp.common.Currency;

import static by.madcat.currencyrateapp.ui.PreferencesActivity.APP_PREFERENCES;
import static by.madcat.currencyrateapp.ui.PreferencesActivity.APP_PREFS_SAVED;

public class PrefsRecyclerViewAdapter extends RecyclerView.Adapter<PrefsCurrencyItemViewHolder> implements ItemTouchHelperAdapter{

    private List<Currency> data;
    private Context context;
    private LayoutInflater layoutInflater;

    public PrefsRecyclerViewAdapter(Context context) {
        this.data = new ArrayList<>();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public PrefsCurrencyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.prefs_item, parent, false);
        return new PrefsCurrencyItemViewHolder(itemView, context.getSharedPreferences(APP_PREFERENCES, context.MODE_PRIVATE).contains(APP_PREFS_SAVED));
    }

    @Override
    public void onBindViewHolder(@NonNull PrefsCurrencyItemViewHolder holder, int i) {
        holder.bind(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public void setData(List<Currency> newData) {
        if (data != null) {
            PrefsRecyclerViewAdapter.PostDiffCallback postDiffCallback = new PrefsRecyclerViewAdapter.PostDiffCallback(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);

            data.clear();
            data.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data = newData;
        }
    }

    public List<Currency> getModifyList() {
        return data;
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
