package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tasks.service.constants.TaskConstants;
import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.listener.Feedback;
import com.example.tasks.service.model.TaskModel;
import com.example.tasks.service.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskListViewModel extends AndroidViewModel {

    private TaskRepository mTaskRepository;

    private MutableLiveData<List<TaskModel>> mList = new MutableLiveData<>();
    public LiveData<List<TaskModel>> list = this.mList;

    private MutableLiveData<Feedback> mFeedback = new MutableLiveData<>();
    public LiveData<Feedback> feedback = this.mFeedback;

    private int mTaskFilter = TaskConstants.TASKFILTER.NO_FILTER;

    public TaskListViewModel(@NonNull Application application) {
        super(application);
        this.mTaskRepository = new TaskRepository(application);
    }

    public void list(int filter) {

        this.mTaskFilter = filter;

        APIListener<List<TaskModel>> listener = new APIListener<List<TaskModel>>() {
            @Override
            public void onSuccess(List<TaskModel> result) {
                mList.setValue(result);

            }

            @Override
            public void onFailure(String message) {
                mList.setValue(new ArrayList<TaskModel>());
                mFeedback.setValue(new Feedback(message));
            }
        };

        /* Retorna a lista de tarefas */
        switch (filter) {
            case TaskConstants.TASKFILTER.NO_FILTER:
            default:
                this.mTaskRepository.all(listener);
                break;
            case TaskConstants.TASKFILTER.NEXT_7_DAYS:
                this.mTaskRepository.nextWeek(listener);
                break;
            case TaskConstants.TASKFILTER.OVERDUE:
                this.mTaskRepository.overdue(listener);
                break;
        }
    }

    public void delete(int id) {
        this.mTaskRepository.delete(id, new APIListener<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                list(mTaskFilter);
                mFeedback.setValue(new Feedback());
            }

            @Override
            public void onFailure(String message) {
                mFeedback.setValue(new Feedback(message));
            }
        });
    }

    public void updateStatus(int id, boolean isComplete) {
        APIListener<Boolean> listener = new APIListener<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                list(mTaskFilter);
            }
            @Override
            public void onFailure(String message) {
                mFeedback.setValue(new Feedback(message));
            }
        };

        if(isComplete) {
            mTaskRepository.complete(id, listener);
        } else {
            mTaskRepository.undo(id, listener);
        }
    }
}