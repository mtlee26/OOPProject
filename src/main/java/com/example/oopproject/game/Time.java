package com.example.oopproject.game;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.oopproject.DictionaryApplication.scene;
import static com.example.oopproject.DictionaryApplication.stage;

public class Time implements Runnable {
    private Timer timer;
    private TimerTask timerTask;
    private Label timerLabel;
    private int totalTime;
    private boolean isEnd;


    public Time(Label timerLabel) {
        timer = new Timer();
        this.timerLabel = timerLabel;
        totalTime = 30;
        isEnd = false;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
        if (isEnd == true) {
            timer.cancel();
        }
    }

    public void run() {
        timerTask = new TimerTask() {
            int secondsLeft = totalTime;
            @Override
            public void run() {
                if (stage.isShowing()) {
                    if (secondsLeft > 0) {
                        secondsLeft--;
                        int minutes = secondsLeft / 60;
                        int seconds = secondsLeft % 60;
                        Platform.runLater(() -> {
                            timerLabel.setText("Time left: " + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                        });
                    } else {
                        isEnd = true;
                        timerTask.cancel();
                    }
                } else {
                    System.out.println("close");
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
}
