package com.example.tasks.service.repository;

import android.content.Context;

import com.example.tasks.R;
import com.example.tasks.service.constants.TaskConstants;
import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.model.TaskModel;
import com.example.tasks.service.repository.remote.RetrofitClient;
import com.example.tasks.service.repository.remote.TaskService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRepository extends BaseRepository {
    private TaskService mTaskservice;
    public TaskRepository(Context context) {
        super(context);
        this.mTaskservice = RetrofitClient.createService(TaskService.class);
    }

public void save(TaskModel taskModel, final APIListener<Boolean> listener) {
        Call<Boolean> call = mTaskservice.create(
                taskModel.getmPriorityId(),
                taskModel.getmDescription(),
                taskModel.getmDueDate(),
                taskModel.getmComplete()
        );
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.code() == TaskConstants.HTTP.SUCCESS) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(handleFailure(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
            }
        });
    }

    private void list(Call<List<TaskModel>> call, final APIListener<List<TaskModel>> listener) {
        call.enqueue(new Callback<List<TaskModel>>() {
            @Override
            public void onResponse(Call<List<TaskModel>> call, Response<List<TaskModel>> response) {
                if(response.code() == TaskConstants.HTTP.SUCCESS) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(handleFailure(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<TaskModel>> call, Throwable t) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
            }
        });
    }

    public void all(final APIListener<List<TaskModel>> listener) {
        Call<List<TaskModel>> call = this.mTaskservice.all();
        this.list(call, listener);
    }

    public void nextWeek(final APIListener<List<TaskModel>> listener) {
        Call<List<TaskModel>> call = this.mTaskservice.next7Days();
        this.list(call, listener);
    }

    public void overdue(final APIListener<List<TaskModel>> listener) {
        Call<List<TaskModel>> call = this.mTaskservice.overdue();
        this.list(call, listener);
    }
}
