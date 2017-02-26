package com.example.zheweidai.cs349_a3;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.Observable;
import java.util.Observer;

public class View_Bar extends LinearLayout implements Observer{
    private Model model;
    private ImageButton bt_select;
    private ImageButton bt_rec;
    private ImageButton bt_cir;
    private ImageButton bt_line;
    private ImageButton bt_remove;
    private ImageButton bt_fill;
    private ImageButton bt_red;
    private ImageButton bt_blue;
    private ImageButton bt_green;
    private ImageButton bt_line1;
    private ImageButton bt_line2;
    private ImageButton bt_line3;

    // Constructor
    public View_Bar(Context context, Model m){
        super(context);

        // get the xml description of the view and "inflate" it
        // into the display (kind of like rendering it)
        View.inflate(context, R.layout.view_bar, this);

        // save the model reference
        model = m;

        bt_select = (ImageButton) findViewById(R.id.imageButton1);
        bt_rec = (ImageButton) findViewById(R.id.imageButton2);
        bt_cir = (ImageButton) findViewById(R.id.imageButton3);
        bt_line = (ImageButton) findViewById(R.id.imageButton4);
        bt_remove = (ImageButton) findViewById(R.id.imageButton5);
        bt_fill = (ImageButton) findViewById(R.id.imageButton6);
        bt_red = (ImageButton) findViewById(R.id.imageButton7);
        bt_blue = (ImageButton) findViewById(R.id.imageButton8);
        bt_green = (ImageButton) findViewById(R.id.imageButton9);
        bt_line1 = (ImageButton) findViewById(R.id.imageButton10);
        bt_line2 = (ImageButton) findViewById(R.id.imageButton11);
        bt_line3 = (ImageButton) findViewById(R.id.imageButton12);

        if (model.tool_type == Tool_enum.Select){
            bt_select.setImageResource(R.drawable.select_s);
            bt_rec.setImageResource(R.drawable.rec);
            bt_cir.setImageResource(R.drawable.cir);
            bt_line.setImageResource(R.drawable.line);
            bt_remove.setImageResource(R.drawable.remove);
            bt_fill.setImageResource(R.drawable.fill);
        }
        if (model.tool_type == Tool_enum.Rectangle){
            bt_select.setImageResource(R.drawable.select);
            bt_rec.setImageResource(R.drawable.rec_s);
            bt_cir.setImageResource(R.drawable.cir);
            bt_line.setImageResource(R.drawable.line);
            bt_remove.setImageResource(R.drawable.remove);
            bt_fill.setImageResource(R.drawable.fill);
        }
        if (model.tool_type == Tool_enum.Circle){
            bt_select.setImageResource(R.drawable.select);
            bt_rec.setImageResource(R.drawable.rec);
            bt_cir.setImageResource(R.drawable.cir_s);
            bt_line.setImageResource(R.drawable.line);
            bt_remove.setImageResource(R.drawable.remove);
            bt_fill.setImageResource(R.drawable.fill);
        }
        if (model.tool_type == Tool_enum.Eraser){
            bt_select.setImageResource(R.drawable.select);
            bt_rec.setImageResource(R.drawable.rec);
            bt_cir.setImageResource(R.drawable.cir);
            bt_line.setImageResource(R.drawable.line);
            bt_remove.setImageResource(R.drawable.remove_s);
            bt_fill.setImageResource(R.drawable.fill);
        }
        if (model.tool_type == Tool_enum.Fill){
            bt_select.setImageResource(R.drawable.select);
            bt_rec.setImageResource(R.drawable.rec);
            bt_cir.setImageResource(R.drawable.cir);
            bt_line.setImageResource(R.drawable.line);
            bt_remove.setImageResource(R.drawable.remove);
            bt_fill.setImageResource(R.drawable.fill_s);
        }
        if (model.tool_type == Tool_enum.Line){
            bt_select.setImageResource(R.drawable.select);
            bt_rec.setImageResource(R.drawable.rec);
            bt_cir.setImageResource(R.drawable.cir);
            bt_line.setImageResource(R.drawable.line_s);
            bt_remove.setImageResource(R.drawable.remove);
            bt_fill.setImageResource(R.drawable.fill);
        }
        if (model.current_color == Color.RED){
            bt_red.setImageResource(R.drawable.red_s);
            bt_blue.setImageResource(R.drawable.blue);
            bt_green.setImageResource(R.drawable.green);
        }
        if (model.current_color == Color.GREEN){
            bt_red.setImageResource(R.drawable.red);
            bt_blue.setImageResource(R.drawable.blue);
            bt_green.setImageResource(R.drawable.green_s);
        }
        if (model.current_color == Color.BLUE){
            bt_red.setImageResource(R.drawable.red);
            bt_blue.setImageResource(R.drawable.blue_s);
            bt_green.setImageResource(R.drawable.green);
        }
        if (model.thickness == 3){
            bt_line1.setImageResource(R.drawable.line1_s);
            bt_line2.setImageResource(R.drawable.line2);
            bt_line3.setImageResource(R.drawable.line3);
        }
        if (model.thickness == 5){
            bt_line1.setImageResource(R.drawable.line1);
            bt_line2.setImageResource(R.drawable.line2_s);
            bt_line3.setImageResource(R.drawable.line3);
        }
        if (model.thickness ==7){
            bt_line1.setImageResource(R.drawable.line1);
            bt_line2.setImageResource(R.drawable.line2);
            bt_line3.setImageResource(R.drawable.line3_s);
        }

        // create a controller for the button
        bt_select.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.change_tool_type(Tool_enum.Select);
                bt_select.setImageResource(R.drawable.select_s);
                bt_rec.setImageResource(R.drawable.rec);
                bt_cir.setImageResource(R.drawable.cir);
                bt_line.setImageResource(R.drawable.line);
                bt_remove.setImageResource(R.drawable.remove);
                bt_fill.setImageResource(R.drawable.fill);
            }
        });
        bt_rec.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.change_tool_type(Tool_enum.Rectangle);
                bt_select.setImageResource(R.drawable.select);
                bt_rec.setImageResource(R.drawable.rec_s);
                bt_cir.setImageResource(R.drawable.cir);
                bt_line.setImageResource(R.drawable.line);
                bt_remove.setImageResource(R.drawable.remove);
                bt_fill.setImageResource(R.drawable.fill);
            }
        });
        bt_cir.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.change_tool_type(Tool_enum.Circle);
                bt_select.setImageResource(R.drawable.select);
                bt_rec.setImageResource(R.drawable.rec);
                bt_cir.setImageResource(R.drawable.cir_s);
                bt_line.setImageResource(R.drawable.line);
                bt_remove.setImageResource(R.drawable.remove);
                bt_fill.setImageResource(R.drawable.fill);

            }
        });
        bt_line.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.change_tool_type(Tool_enum.Line);
                bt_select.setImageResource(R.drawable.select);
                bt_rec.setImageResource(R.drawable.rec);
                bt_cir.setImageResource(R.drawable.cir);
                bt_line.setImageResource(R.drawable.line_s);
                bt_remove.setImageResource(R.drawable.remove);
                bt_fill.setImageResource(R.drawable.fill);
            }
        });
        bt_remove.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.change_tool_type(Tool_enum.Eraser);
                bt_select.setImageResource(R.drawable.select);
                bt_rec.setImageResource(R.drawable.rec);
                bt_cir.setImageResource(R.drawable.cir);
                bt_line.setImageResource(R.drawable.line);
                bt_remove.setImageResource(R.drawable.remove_s);
                bt_fill.setImageResource(R.drawable.fill);
            }
        });
        bt_remove.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.change_tool_type(Tool_enum.Eraser);
                bt_select.setImageResource(R.drawable.select);
                bt_rec.setImageResource(R.drawable.rec);
                bt_cir.setImageResource(R.drawable.cir);
                bt_line.setImageResource(R.drawable.line);
                bt_remove.setImageResource(R.drawable.remove_s);
                bt_fill.setImageResource(R.drawable.fill);
                model.clear();
                return true;
            }
        });

        bt_fill.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.change_tool_type(Tool_enum.Fill);
                bt_select.setImageResource(R.drawable.select);
                bt_rec.setImageResource(R.drawable.rec);
                bt_cir.setImageResource(R.drawable.cir);
                bt_line.setImageResource(R.drawable.line);
                bt_remove.setImageResource(R.drawable.remove);
                bt_fill.setImageResource(R.drawable.fill_s);
            }
        });

        bt_red.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.change_color_bar(Color.RED);
                bt_red.setImageResource(R.drawable.red_s);
                bt_blue.setImageResource(R.drawable.blue);
                bt_green.setImageResource(R.drawable.green);
            }
        });
        bt_blue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.change_color_bar(Color.BLUE);
                bt_red.setImageResource(R.drawable.red);
                bt_blue.setImageResource(R.drawable.blue_s);
                bt_green.setImageResource(R.drawable.green);
            }
        });
        bt_green.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.change_color_bar(Color.GREEN);
                bt_red.setImageResource(R.drawable.red);
                bt_blue.setImageResource(R.drawable.blue);
                bt_green.setImageResource(R.drawable.green_s);
            }
        });

        bt_line1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.change_thickness_bar(5);
                bt_line1.setImageResource(R.drawable.line1_s);
                bt_line2.setImageResource(R.drawable.line2);
                bt_line3.setImageResource(R.drawable.line3);
            }
        });
        bt_line2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.change_thickness_bar(7);
                bt_line1.setImageResource(R.drawable.line1);
                bt_line2.setImageResource(R.drawable.line2_s);
                bt_line3.setImageResource(R.drawable.line3);
            }
        });
        bt_line3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.change_thickness_bar(9);
                bt_line1.setImageResource(R.drawable.line1);
                bt_line2.setImageResource(R.drawable.line2);
                bt_line3.setImageResource(R.drawable.line3_s);
            }
        });
    }

    // the model call this to update the view
    public void update(Observable observable, Object data){
        if (model.current_color == Color.RED){
            bt_red.setImageResource(R.drawable.red_s);
            bt_blue.setImageResource(R.drawable.blue);
            bt_green.setImageResource(R.drawable.green);
        }
        else if (model.current_color == Color.GREEN){
            bt_red.setImageResource(R.drawable.red);
            bt_blue.setImageResource(R.drawable.blue);
            bt_green.setImageResource(R.drawable.green_s);
        }
        else if (model.current_color == Color.BLUE){
            bt_red.setImageResource(R.drawable.red);
            bt_blue.setImageResource(R.drawable.blue_s);
            bt_green.setImageResource(R.drawable.green);
        }
        if (model.thickness == 5){
            bt_line1.setImageResource(R.drawable.line1_s);
            bt_line2.setImageResource(R.drawable.line2);
            bt_line3.setImageResource(R.drawable.line3);
        } else if (model.thickness == 7){
            bt_line1.setImageResource(R.drawable.line1);
            bt_line2.setImageResource(R.drawable.line2_s);
            bt_line3.setImageResource(R.drawable.line3);
        } else if (model.thickness ==9){
            bt_line1.setImageResource(R.drawable.line1);
            bt_line2.setImageResource(R.drawable.line2);
            bt_line3.setImageResource(R.drawable.line3_s);
        }
    }
}


