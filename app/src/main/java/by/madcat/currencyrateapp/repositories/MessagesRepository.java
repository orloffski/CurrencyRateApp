package by.madcat.currencyrateapp.repositories;

import android.arch.lifecycle.MutableLiveData;

import by.madcat.currencyrateapp.common.SingleLiveEvent;

public class MessagesRepository {

    private static MessagesRepository instance;

    private MutableLiveData<String> messageLiveData;

    private MessagesRepository(){
        messageLiveData = new SingleLiveEvent<>();
    }

    public static MessagesRepository getInstance(){
        synchronized (CurrenciesRepository.class) {
            if (instance == null)
                instance = new MessagesRepository();
        }

        return instance;
    }

    public MutableLiveData<String> getMessageData(){
        return messageLiveData;
    }

    public void updateMessage(String message){
        messageLiveData.postValue(message);
    }

    public void clearMessage(){
        messageLiveData.postValue(null);
    }
}
