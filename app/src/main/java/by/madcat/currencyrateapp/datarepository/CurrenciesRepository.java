package by.madcat.currencyrateapp.datarepository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class CurrenciesRepository {

    private static CurrenciesRepository instance;

    private List<Currency> currencies;
    private MutableLiveData<List<Currency>> currencyLiveData;

    private CurrenciesRepository() {
        currencies = new ArrayList<>();
        currencyLiveData = new MutableLiveData<>();

        currencyLiveData.postValue(currencies);
    }

    public static CurrenciesRepository getInstance(){
        synchronized (CurrenciesRepository.class) {
            if (instance == null)
                instance = new CurrenciesRepository();
        }

        return instance;
    }

    public LiveData<List<Currency>> getData(){
        return currencyLiveData;
    }

    public void addCurrency(Currency currency){
        currencies.add(currency);
        currencyLiveData.postValue(currencies);
    }
}
