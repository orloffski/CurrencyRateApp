package by.madcat.currencyrateapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import by.madcat.currencyrateapp.repositories.MessagesRepository;

public class MessagesViewModel extends AndroidViewModel {

    private MutableLiveData<String> messagesLiveData;

    public MessagesViewModel(@NonNull Application application) {
        super(application);

        MessagesRepository repository = MessagesRepository.getInstance();
        messagesLiveData = repository.getMessageData();
    }

    public LiveData<String> getMessageData(){
        return messagesLiveData;
    }
}
