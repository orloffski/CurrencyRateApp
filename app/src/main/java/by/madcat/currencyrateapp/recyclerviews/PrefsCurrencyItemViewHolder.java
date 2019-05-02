package by.madcat.currencyrateapp.recyclerviews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import by.madcat.currencyrateapp.R;
import by.madcat.currencyrateapp.common.Currency;
import by.madcat.currencyrateapp.common.CurrencyAppConstants;

public class PrefsCurrencyItemViewHolder extends RecyclerView.ViewHolder {

    private TextView currCode;
    private TextView currDescription;
    private Switch currSwitch;
    private boolean prefsSaved;

    public PrefsCurrencyItemViewHolder(@NonNull View itemView, boolean prefsSaved) {
        super(itemView);

        this.prefsSaved = prefsSaved;
        currCode = itemView.findViewById(R.id.currCode);
        currDescription = itemView.findViewById(R.id.currDescription);
        currSwitch = itemView.findViewById(R.id.currSwitch);
    }

    void bind(final Currency currency) {
        if (currency != null) {
            currCode.setText(currency.getCurrencyCode());
            currDescription.setText(currency.getCurrencyDescription());
            currSwitch.setChecked(currency.isInclude());

            if(!prefsSaved && CurrencyAppConstants.DEFAULT_CURRENCY.contains(currency.getCurrencyCode()))
                currSwitch.setChecked(true);
        }
    }
}
