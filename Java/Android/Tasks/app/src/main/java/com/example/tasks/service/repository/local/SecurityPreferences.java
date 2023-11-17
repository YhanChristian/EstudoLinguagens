package com.example.tasks.service.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences
 */
public class SecurityPreferences {

        private final SharedPreferences mSharedPreferences;

        public SecurityPreferences(Context context) {
            this.mSharedPreferences = context.getSharedPreferences("TasksShared", Context.MODE_PRIVATE);
        }

        public void storeString(String key, String value) {
            this.mSharedPreferences.edit().putString(key, value).apply();
        }

        public String getStoredString(String key) {
            return this.mSharedPreferences.getString(key, "");
        }

        public void removeStoredString(String key) {
            this.mSharedPreferences.edit().remove(key).apply();
        }
}
