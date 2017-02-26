package com.example.zheweidai.cs349_a3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

public class MainActivity extends Activity {
    Model model;
    View_Bar view_bar;
    View_Canvas view_canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = Model.getInstance();
        model.deleteObservers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Log.d("A3", "onPostCreate");
        view_bar = new View_Bar(this, model);
        view_canvas = new View_Canvas(this, model);

        model.addObserver(view_bar);
        model.addObserver(view_canvas);

        ViewGroup v1 = (ViewGroup) findViewById(R.id.mainactivity_1);
        v1.addView(view_bar);

        ViewGroup v2 = (ViewGroup) findViewById(R.id.mainactivity_2);
        v2.addView(view_canvas);

        // initialize views
        model.notifyObservers();
    }
}
