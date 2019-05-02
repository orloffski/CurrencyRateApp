package by.madcat.currencyrateapp.recyclerviews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import by.madcat.currencyrateapp.R;
import by.madcat.currencyrateapp.common.Currency;

public class MainCyrrencyItemViewHolder extends RecyclerView.ViewHolder {

    private TextView currCode;
    private TextView currDescription;
    private TextView lastDateTextView;
    private TextView prevDateTextView;

    public MainCyrrencyItemViewHolder(@NonNull View itemView) {
        super(itemView);

        currCode = itemView.findViewById(R.id.currCode);
        currDescription = itemView.findViewById(R.id.currDescription);
        lastDateTextView = itemView.findViewById(R.id.lastDateTextView);
        prevDateTextView = itemView.findViewById(R.id.prevDateTextView);
    }

    void bind(final Currency currency) {
        if (currency != null) {
            currCode.setText(currency.getCurrencyCode());
            currDescription.setText(currency.getCurrencyDescription());
            lastDateTextView.setText(currency.getCurrencyLastRate());
            prevDateTextView.setText(currency.getCurrencyPrevRate());
        }
    }
}
