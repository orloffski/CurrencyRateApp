package by.madcat.currencyrateapp.recyclerviews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import by.madcat.currencyrateapp.R;
import by.madcat.currencyrateapp.common.Currency;

public class MainCurrencyHeaderViewHolder extends RecyclerView.ViewHolder  {

    private TextView lastDate;
    private TextView prevDate;

    public MainCurrencyHeaderViewHolder(@NonNull View itemView) {
        super(itemView);

        lastDate = itemView.findViewById(R.id.lastDateTextView);
        prevDate = itemView.findViewById(R.id.prevDateTextView);
    }

    void bind(Currency currency) {
        if (currency != null) {
//            lastDate.setText(currency.getLastDate());
//            lastDate.setText(currency.getPrevDate());
        }
    }
}
