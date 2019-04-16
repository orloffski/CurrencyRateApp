package by.madcat.currencyrateapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import by.madcat.currencyrateapp.datarepository.CurrenciesRepository;
import by.madcat.currencyrateapp.datarepository.Currency;
import by.madcat.currencyrateapp.loadtask.CurrencyLoadAsyncTask;

public class CurrencyViewModel extends AndroidViewModel {

    private CurrenciesRepository repository = CurrenciesRepository.getInstance();

    private LiveData<List<Currency>> currencyLiveData;

    public CurrencyViewModel(@NonNull Application application) {
        super(application);

        currencyLiveData = repository.getData();
        loadData();
    }

    public LiveData<List<Currency>> getData(){
        return currencyLiveData;
    }

    private void loadData(){
        CurrencyLoadAsyncTask t = new CurrencyLoadAsyncTask();
        t.execute();
    }
}
