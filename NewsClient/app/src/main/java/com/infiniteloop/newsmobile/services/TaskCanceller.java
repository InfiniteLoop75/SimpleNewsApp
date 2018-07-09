package com.infiniteloop.newsmobile.services;

import android.os.AsyncTask;

/**
 * Created by hp on 7/9/2018.
 */

public class TaskCanceller implements Runnable {
        private AsyncTask task;

        public TaskCanceller(AsyncTask task) {
            this.task = task;
        }

        @Override
        public void run() {
            if (task.getStatus() == AsyncTask.Status.RUNNING )
                task.cancel(true);
        }
    }
