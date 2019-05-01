package by.madcat.currencyrateapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import by.madcat.currencyrateapp.repositories.CurrenciesRepository;
import by.madcat.currencyrateapp.common.Currency;
import by.madcat.currencyrateapp.loadtask.CurrencyLoadAsyncTask;
import by.madcat.currencyrateapp.repositories.MessagesRepository;

public class CurrencyViewModel extends AndroidViewModel {

    private LiveData<List<Currency>> currencyLiveData;
    private CurrenciesRepository currenciesRepository;

    private MutableLiveData<String> messagesLiveData;

    public CurrencyViewModel(@NonNull Application application) {
        super(application);

        MessagesRepository repository = MessagesRepository.getInstance();
        messagesLiveData = repository.getMessageData();

        currenciesRepository = CurrenciesRepository.getInstance();
        currencyLiveData = currenciesRepository.getCurrencyData();
        currenciesRepository.setDataLoaded(false);
    }

    public LiveData<List<Currency>> getCurrenciesData(){
        if(!currenciesRepository.isDataLoaded())
            loadData();

        return currencyLiveData;
    }

    public LiveData<String> getMessageData(){
        return messagesLiveData;
    }

    private void loadData(){
        CurrencyLoadAsyncTask.runCurrencyLoadAsyncTask(getApplication());
    }
}
