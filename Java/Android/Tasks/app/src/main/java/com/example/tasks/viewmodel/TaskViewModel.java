package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.listener.Feedback;
import com.example.tasks.service.model.PriorityModel;
import com.example.tasks.service.model.TaskModel;
import com.example.tasks.service.repository.PriorityRepository;
import com.example.tasks.service.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private PriorityRepository mPriorityRepository;
    private TaskRepository mTaskRepository;

    private MutableLiveData<List<PriorityModel>> mListPriority = new MutableLiveData<>();
    public LiveData<List<PriorityModel>> listPriority = this.mListPriority;
    private MutableLiveData<Feedback> mTaskFeedback = new MutableLiveData<>();
    public LiveData<Feedback> taskFeedback = this.mTaskFeedback;

    private MutableLiveData<TaskModel> mTask = new MutableLiveData<>();
    public LiveData<TaskModel> task = this.mTask;


    public TaskViewModel(@NonNull Application application) {
        super(application);
        this.mPriorityRepository = new PriorityRepository(application);
        this.mTaskRepository = new TaskRepository(application);
    }

    public void getList() {
        List<PriorityModel>list = this.mPriorityRepository.getList();
        this.mListPriority.setValue(list);
    }

    public void save(TaskModel task) {
        APIListener<Boolean> listener = new APIListener<Boolean>() {
            @Override
            public void onSuccess(Boolean obj) {
                mTaskFeedback.setValue(new Feedback());

            }
            @Override
            public void onFailure(String msg) {
                mTaskFeedback.setValue(new Feedback(msg));
            }
        };

        if(task.getmId() == 0) {
            this.mTaskRepository.create(task, listener);
        } else {
            this.mTaskRepository.update(task, listener);
        }
    }

    public void load(int id) {
        this.mTaskRepository.load(id, new APIListener<TaskModel>() {
            @Override
            public void onSuccess(TaskModel result) {
                mTask.setValue(result);
            }

            @Override
            public void onFailure(String msg) {
                mTaskFeedback.setValue(new Feedback(msg));
            }
        });
    }
}
