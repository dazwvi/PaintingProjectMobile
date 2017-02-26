package com.example.zheweidai.cs349_a3;

import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {

    private static Model _instance;

    public List<PaintObject> PArray = new ArrayList<PaintObject>();

    public Tool_enum tool_type;

    public int current_color = Color.RED;

    public boolean changing_color_bar = false;
    public boolean color_bar_changing = false;

    public int thickness = 5;
    public boolean chaning_thickness_bar = false;
    public boolean thickness_bar_changing = false;

    //Constructor
    public Model(){
        tool_type = Tool_enum.Select;
    }

    public synchronized static Model getInstance()
    {
        if (_instance == null)
        {
            _instance = new Model();
        }
        return _instance;
    }

    public void change_tool_type(Tool_enum t){
        this.tool_type = t;
        setChanged();
        notifyObservers();
    }

    public void change_color_bar(int c){
        this.current_color = c;
        changing_color_bar = true;
        setChanged();
        notifyObservers();
    }

    public void color_bar_change(int c){
        this.current_color = c;
        color_bar_changing = true;
        setChanged();
        notifyObservers();
    }

    public void change_thickness_bar(int t){
        this.thickness = t;
        chaning_thickness_bar = true;
        setChanged();
        notifyObservers();
    }

    public void thickness_bar_change(int t){
        this.thickness = t;
        thickness_bar_changing = true;
        setChanged();
        notifyObservers();
    }

    public void clear(){
        PArray.clear();
        setChanged();
        notifyObservers();
    }

    // Observer methods
    @Override
    public void addObserver(Observer observer) {
        Log.d("A3", "Model: Observer added");
        super.addObserver(observer);
    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }

    @Override
    protected void setChanged() {
        super.setChanged();
    }

    @Override
    protected void clearChanged() {
        super.clearChanged();
    }
}
