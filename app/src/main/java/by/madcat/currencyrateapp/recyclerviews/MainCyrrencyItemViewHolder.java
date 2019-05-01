package by.madcat.currencyrateapp.recyclerviews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import by.madcat.currencyrateapp.R;
import by.madcat.currencyrateapp.common.Currency;

public class MainCyrrencyItemViewHolder extends RecyclerView.ViewHolder {

    private TextView currCode;

    public MainCyrrencyItemViewHolder(@NonNull View itemView) {
        super(itemView);

        currCode = itemView.findViewById(R.id.currCode);
    }

    void bind(final Currency currency) {
        if (currency != null) {
            currCode.setText(currency.getCurrencyCode());
        }
    }
}
