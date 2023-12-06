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

    private void save(Call<Boolean> call, final APIListener<Boolean> listener) {
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

    public void create(TaskModel taskModel, final APIListener<Boolean> listener) {
        Call<Boolean> call = mTaskservice.create(
                taskModel.getmPriorityId(),
                taskModel.getmDescription(),
                taskModel.getmDueDate(),
                taskModel.getmComplete()
        );
        this.save(call, listener);
    }

    public void update(TaskModel task, final APIListener<Boolean> listener) {
        Call<Boolean> call = this.mTaskservice.update(
                task.getmId(),
                task.getmPriorityId(),
                task.getmDescription(),
                task.getmDueDate(),
                task.getmComplete()
        );
        this.save(call, listener);
    }

    public void delete(int id, final APIListener<Boolean> listener) {
        Call<Boolean> call = this.mTaskservice.delete(id);
        this.save(call, listener);
    }

    private void list(Call<List<TaskModel>> call, final APIListener<List<TaskModel>> listener) {
        call.enqueue(new Callback<List<TaskModel>>() {
            @Override
            public void onResponse(Call<List<TaskModel>> call, Response<List<TaskModel>> response) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
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

    public void load(int id, final APIListener<TaskModel> listener) {
        final Call<TaskModel> call = this.mTaskservice.load(id);
        call.enqueue(new Callback<TaskModel>() {
            @Override
            public void onResponse(Call<TaskModel> call, Response<TaskModel> response) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(handleFailure(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<TaskModel> call, Throwable t) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
            }
        });
    }


}
