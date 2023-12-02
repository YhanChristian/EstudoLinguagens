package com.example.tasks.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tasks.R;
import com.example.tasks.service.listener.Feedback;
import com.example.tasks.service.model.PriorityModel;
import com.example.tasks.service.model.TaskModel;
import com.example.tasks.viewmodel.TaskViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private SimpleDateFormat mFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private ViewHolder mViewHolder = new ViewHolder();
    private TaskViewModel mViewModel;
    private List<Integer> mListPriorityId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        // Botão de voltar nativo
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Seta Elementos ViewHolder
        this.mViewHolder.editDescription = findViewById(R.id.edit_description);
        this.mViewHolder.spinnerPriority = findViewById(R.id.spinner_priority);
        this.mViewHolder.checkComplete = findViewById(R.id.check_complete);
        this.mViewHolder.buttonDate = findViewById(R.id.button_date);
        this.mViewHolder.buttonSave = findViewById(R.id.button_save);

        // ViewModel
        this.mViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        //Cria Eventos
        this.createEvents();

        // Cria observadores
        this.loadObservers();

        // Carrega dados
        this.mViewModel.getList();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_date) {
            /*Evento de click do botão de data*/
            this.showDatePicker();

        } else if (id == R.id.button_save) {
            /*Evento de click do botão de salvar*/
            this.handleSave();
        }
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        /*Evento de seleção de data*/
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        String date = this.mFormat.format(calendar.getTime());
        this.mViewHolder.buttonDate.setText(date);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, this, year, month, day).show();
    }

    private void handleSave() {
        TaskModel task = new TaskModel();
        task.setmDescription(this.mViewHolder.editDescription.getText().toString());
        task.setmComplete(this.mViewHolder.checkComplete.isChecked());
        task.setmDueDate(this.mViewHolder.buttonDate.getText().toString());
        task.setmPriorityId(this.mListPriorityId.get(this.mViewHolder.spinnerPriority.getSelectedItemPosition()));

        this.mViewModel.save(task);
    }


    /**
     * Observadores
     */
    private void loadObservers() {
        this.mViewModel.listPriority.observe(this, new Observer<List<PriorityModel>>() {
            @Override
            public void onChanged(List<PriorityModel> list) {
                loadSpinner(list);
            }
        });

        this.mViewModel.taskSave.observe(this, new Observer<Feedback>() {
            @Override
            public void onChanged(Feedback feedback) {
                if (feedback.isSuccess()) {
                } else {
                    Toast.makeText(getApplicationContext(), feedback.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadSpinner(List<PriorityModel> list) {
        List<String> listPriorities = new ArrayList<>();
        for (PriorityModel priority : list) {
            listPriorities.add(priority.getDescription());
            mListPriorityId.add(priority.getId());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listPriorities);
        this.mViewHolder.spinnerPriority.setAdapter(adapter);
    }

    private void createEvents() {
        this.mViewHolder.buttonDate.setOnClickListener(this);
        this.mViewHolder.buttonSave.setOnClickListener(this);
    }


    /**
     * ViewHolder
     */
    private static class ViewHolder {
        EditText editDescription;
        Spinner spinnerPriority;
        CheckBox checkComplete;
        Button buttonDate;
        Button buttonSave;
    }
}