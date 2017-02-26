package com.example.zheweidai.cs349_a3;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Observable;
import java.util.Observer;
import android.view.ScaleGestureDetector;

public class View_Canvas extends View implements Observer{
    public Model model;

    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //initial color
    private int paintColor = 0xFF660000, paintAlpha = 255;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;

    public Tool_enum current_tool;
    public Dot draw_start;
    public Dot drag_start;
    boolean selected;
    boolean is_resizing;
    int selected_index;
    boolean is_drawing;
    boolean is_dragging;
    boolean change_thickness_bar;
    Dot painting_point;
    ScaleGestureDetector scaleGestureDetector;

    // Constructor
    public View_Canvas(Context context, Model m) {
        super(context);
        // save the model reference
        model = m;

        canvasPaint = new Paint(Paint.DITHER_FLAG);
        change_thickness_bar = false;
        this.current_tool = m.tool_type;
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    //size assigned to view
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    //draw the view - will be called after touch event
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);

        for (int i = 0; i < model.PArray.size(); i++){
            int type = model.PArray.get(i).type;
            int color = model.PArray.get(i).color;
            int tickness = model.PArray.get(i).tickness;
            boolean is_filled = model.PArray.get(i).is_filled;
            int filled_color = model.PArray.get(i).fill;
            switch (type){
                case 1:
                    float x1 = model.PArray.get(i).line_end1.x;
                    float y1 = model.PArray.get(i).line_end1.y;
                    float x2 = model.PArray.get(i).line_end2.x;
                    float y2 = model.PArray.get(i).line_end2.y;

                    Paint myPaint = new Paint();
                    myPaint.setColor(color);
                    myPaint.setStrokeWidth(tickness);
                    myPaint.setStyle(Paint.Style.STROKE);
                    canvas.drawLine(x1, y1, x2, y2, myPaint);
                    break;
                case 2:
                    x1 = model.PArray.get(i).Rec_NW.x;
                    y1 = model.PArray.get(i).Rec_NW.y;
                    x2 = model.PArray.get(i).Rec_SE.x;
                    y2 = model.PArray.get(i).Rec_SE.y;
                    float width = x2 - x1;
                    float height = y2 - y1;

                    myPaint = new Paint();
                    if (is_filled){
                        myPaint.setColor(filled_color);
                        canvas.drawRect(x1, y1, x2, y2, myPaint);
                    }
                    myPaint.setColor(color);
                    myPaint.setStrokeWidth(tickness);
                    myPaint.setStyle(Paint.Style.STROKE);
                    canvas.drawRect(x1, y1, x2, y2, myPaint);
                    break;
                case 3:
                    float r = model.PArray.get(i).Cir_r;
                    x1 = model.PArray.get(i).Cir_center.x;
                    y1 = model.PArray.get(i).Cir_center.y;

                    myPaint = new Paint();
                    if (is_filled){
                        myPaint.setColor(filled_color);
                        canvas.drawCircle(x1, y1, r, myPaint);
                    }
                    myPaint.setColor(color);
                    myPaint.setStrokeWidth(tickness);
                    myPaint.setStyle(Paint.Style.STROKE);
                    canvas.drawCircle(x1, y1, r, myPaint);
                    break;
            }
        }

        if (selected){
            PaintObject temp = model.PArray.get(selected_index);
            int type = temp.type;
            Paint myPaint = new Paint();
            myPaint.setColor(Color.rgb(0, 0, 0));
            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(3);
            switch (type){
                case 1:
                    canvas.drawRect(temp.line_end1.x - 5, temp.line_end1.y - 5, temp.line_end1.x + 5, temp.line_end1.y + 5, myPaint);
                    canvas.drawRect(temp.line_end2.x - 5, temp.line_end2.y - 5, temp.line_end2.x + 5, temp.line_end2.y + 5, myPaint);
                    break;
                case 2:
                    canvas.drawRect(temp.Rec_NW.x - 5, temp.Rec_NW.y - 5, temp.Rec_NW.x + 5, temp.Rec_NW.y + 5, myPaint);
                    canvas.drawRect(temp.Rec_NE.x - 5, temp.Rec_NE.y - 5, temp.Rec_NE.x + 5, temp.Rec_NE.y + 5, myPaint);
                    canvas.drawRect(temp.Rec_SW.x - 5, temp.Rec_SW.y - 5, temp.Rec_SW.x + 5, temp.Rec_SW.y + 5, myPaint);
                    canvas.drawRect(temp.Rec_SE.x - 5, temp.Rec_SE.y - 5, temp.Rec_SE.x + 5, temp.Rec_SE.y + 5, myPaint);
                    break;
                case 3:
                    float r = temp.Cir_r;
                    canvas.drawRect(temp.Cir_center.x + r - 5, temp.Cir_center.y - 5, temp.Cir_center.x + r + 5, temp.Cir_center.y + 5, myPaint);
                    canvas.drawRect(temp.Cir_center.x - 5, temp.Cir_center.y + r - 5, temp.Cir_center.x + 5, temp.Cir_center.y + r + 5, myPaint);
                    canvas.drawRect(temp.Cir_center.x - r - 5, temp.Cir_center.y - 5, temp.Cir_center.x - r + 5, temp.Cir_center.y + 5, myPaint);
                    canvas.drawRect(temp.Cir_center.x - 5, temp.Cir_center.y - r - 5, temp.Cir_center.x + 5, temp.Cir_center.y - r + 5, myPaint);
                    break;
            }
        }

        if (is_dragging){
            float x1 = draw_start.x;
            float y1 = draw_start.y;
            float x2 = painting_point.x;
            float y2 = painting_point.y;
            float width = x2 - x1;
            float height = y2 - y1;
            float r;
            if (width > height){
                r = width;
            }
            else {
                r = height;
            }
            Paint myPaint = new Paint();
            myPaint.setColor(model.current_color);
            myPaint.setStrokeWidth(model.thickness);
            myPaint.setStyle(Paint.Style.STROKE);

            if (current_tool == Tool_enum.Line){
                canvas.drawLine(x1, y1, x2, y2, myPaint);
            }
            else if (current_tool == Tool_enum.Rectangle) {
                canvas.drawRect(x1, y1, x2, y2, myPaint);
            } else if (current_tool == Tool_enum.Circle) {
                canvas.drawCircle(x1, y1, r, myPaint);
            }
        }

    }

    public int is_select(float x, float y){
        for (int i  = 0; i < model.PArray.size(); i++){
            if (is_contain(model.PArray.get(i), x, y)){
                selected = true;
                return i;
            }
        }
        selected = false;
        return -1;
    }

    public boolean is_contain(PaintObject p, float x, float y){
        switch (p.type){
            case 2:
                float x1 = p.Rec_NW.x-7;
                float y1 = p.Rec_NW.y-7;
                float x2 = p.Rec_SE.x+7;
                float y2 = p.Rec_SE.y+7;

                if (x1 <= x && x <= x2 && y1 <= y && y <= y2){
                    return true;
                }
                break;
            case 3:
                float r = p.Cir_r;
                float xc = p.Cir_center.x;
                float yc = p.Cir_center.y;
                if ((xc - x)*(xc - x) + (yc - y)*(yc - y) <= r * r){
                    return true;
                }
                break;
            case 1:
                x1 = p.line_end1.x;
                y1 = p.line_end1.y;
                x2 = p.line_end2.x;
                y2 = p.line_end2.y;

                return is_insect_line(x1, y1, x2, y2, x, y);
        }

        return false;
    }

    public boolean is_insect_line(float x1, float y1, float x2, float y2, float x, float y){
        if (Math.abs(x1 - x2) <= 10){
            if (x <= x1 + 20.0 && x1 - 20 <= x ){
                if (y1 <= y2){
                    if (y1 <= y && y <= y2){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else{
                    if (y2 <= y && y <= y1){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
            else {
                return false;
            }
        }
        else{
            if (x1 <= x2){
                if (x < x1 || x > x2){
                    return false;
                }
            }
            else {
                if (x < x2 || x > x1){
                    return false;
                }
            }
            if (y1 <= y2){
                if (y < y1 || y > y2){
                    return false;
                }
            }
            else {
                if (y > y1 || y < y2){
                    return false;
                }
            }
            float k =  (y2 - y1) / (x2 - x1);
            float b = (x2*y1 - y2*x1) / (x2 - x1);
            double d = Math.abs((k*x - y + b) / (Math.sqrt(k*k + 1)));
            if (d <= 20.0){
                return true;
            }
            else {
                return false;
            }
        }
    }

    //register user touches as drawing action
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);

        // Action Down
        if (motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
            drag_start = new Dot(motionEvent.getX(), motionEvent.getY());

            if (current_tool == Tool_enum.Line || current_tool == Tool_enum.Rectangle || current_tool == Tool_enum.Circle) {
                draw_start = new Dot(motionEvent.getX(), motionEvent.getY());
                is_drawing = true;
            } else {
                selected_index = is_select(motionEvent.getX(), motionEvent.getY());
                setFocusable(true);
            }
            if (current_tool == Tool_enum.Eraser && selected) {
                model.PArray.remove(selected_index);
                selected = false;
            }
            if (current_tool == Tool_enum.Fill && selected) {
                model.PArray.get(selected_index).fill = model.current_color;
                model.PArray.get(selected_index).is_filled = true;
                selected = false;
            }
            if (selected) {
                change_thickness_bar = true;
                model.color_bar_change(model.PArray.get(selected_index).color);
                model.thickness_bar_change(model.PArray.get(selected_index).tickness);
            }

            invalidate();
            return true;

        // Action Up
        }
        else if (motionEvent.getAction() == android.view.MotionEvent.ACTION_UP) {
            is_resizing = false;
            if (current_tool == Tool_enum.Rectangle) {
                PaintObject rec = new PaintObject(2, -1, -1);
                float x1 = draw_start.x;
                float x2 = motionEvent.getX();
                float y1 = draw_start.y;
                float y2 = motionEvent.getY();
                rec.Rec_NW = new Dot(x1, y1);
                rec.Rec_NE = new Dot(x2, y1);
                rec.Rec_SW = new Dot(x1, y2);
                rec.Rec_SE = new Dot(x2, y2);

                rec.color = model.current_color;
                rec.tickness = model.thickness;

                model.PArray.add(0, rec);
                is_drawing = false;
                is_dragging = false;
            }
            if (current_tool == Tool_enum.Line) {
                PaintObject line = new PaintObject(1, -1, -1);
                float x1 = draw_start.x;
                float x2 = motionEvent.getX();
                float y1 = draw_start.y;
                float y2 = motionEvent.getY();
                line.line_end1 = new Dot(x1, y1);
                line.line_end2 = new Dot(x2, y2);

                line.color = model.current_color;
                line.tickness = model.thickness;

                model.PArray.add(0, line);
                is_drawing = false;
                is_dragging = false;
            }
            if (current_tool == Tool_enum.Circle) {
                PaintObject circle = new PaintObject(3, -1, -1);
                float x1 = draw_start.x;
                float y1 = draw_start.y;
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                float width = Math.abs(x2 - x1);
                float height = Math.abs(y2 - y1);
                circle.Cir_center = new Dot(x1, y1);
                if (height > width) {
                    circle.Cir_r = height;
                } else {
                    circle.Cir_r = width;
                }

                circle.color = model.current_color;
                circle.tickness = model.thickness;

                model.PArray.add(0, circle);
                is_drawing = false;
                is_dragging = false;
            }
            invalidate();
            return true;
        } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            //Drawing
            if (is_drawing) {
                painting_point = new Dot(motionEvent.getX(), motionEvent.getY());
                is_dragging = true;
            }

            //Move
            if (selected){
                float updated_x = motionEvent.getX() - drag_start.x;
                float updated_y = motionEvent.getY() - drag_start.y;

                drag_start.x = motionEvent.getX();
                drag_start.y = motionEvent.getY();
                PaintObject temp = model.PArray.get(selected_index);
                int type = temp.type;
                switch (type){
                    case 1:
                        model.PArray.get(selected_index).line_end1.x += updated_x;
                        model.PArray.get(selected_index).line_end1.y += updated_y;
                        model.PArray.get(selected_index).line_end2.x += updated_x;
                        model.PArray.get(selected_index).line_end2.y += updated_y;
                        break;
                    case 2:
                        model.PArray.get(selected_index).Rec_SE.x += updated_x;
                        model.PArray.get(selected_index).Rec_SE.y += updated_y;
                        model.PArray.get(selected_index).Rec_NW.x += updated_x;
                        model.PArray.get(selected_index).Rec_NW.y += updated_y;
                        model.PArray.get(selected_index).Rec_SW.x += updated_x;
                        model.PArray.get(selected_index).Rec_SW.y += updated_y;
                        model.PArray.get(selected_index).Rec_NE.x += updated_x;
                        model.PArray.get(selected_index).Rec_NE.y += updated_y;
                        break;
                    case 3:
                        model.PArray.get(selected_index).Cir_center.x += updated_x;
                        model.PArray.get(selected_index).Cir_center.y += updated_y;
                        break;
                }
            }

            invalidate();
            return true;
        }

        return false;
    }

    // the model call this to update the view
    public void update(Observable observable, Object data){
        if (current_tool != model.tool_type){
            current_tool = model.tool_type;
            selected = false;
        }
        if (selected) {
            if (model.PArray.get(selected_index).color != model.current_color){
                model.PArray.get(selected_index).color = model.current_color;
            }
            if (change_thickness_bar){
                change_thickness_bar = false;
            }
            else {
                if (model.PArray.get(selected_index).tickness != model.thickness) {
                    model.PArray.get(selected_index).tickness = model.thickness;
                }
            }
        }

        invalidate();
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            if (selected){
                //Resize
                PaintObject temp = model.PArray.get(selected_index);
                int type = temp.type;
                switch (type) {
                    case 2:
                        float width = Math.abs(model.PArray.get(selected_index).Rec_SE.x - model.PArray.get(selected_index).Rec_SW.x);
                        float height = Math.abs(model.PArray.get(selected_index).Rec_SE.y - model.PArray.get(selected_index).Rec_NE.y);
                        float new_width = width * scaleFactor;
                        float new_height = height * scaleFactor;
                        float width_change = new_width - width;
                        float height_change = new_height - height;
                        model.PArray.get(selected_index).Rec_SE.x += width_change;
                        model.PArray.get(selected_index).Rec_SE.y += height_change;
                        model.PArray.get(selected_index).Rec_NW.x -= width_change;
                        model.PArray.get(selected_index).Rec_NW.y -= height_change;
                        model.PArray.get(selected_index).Rec_SW.x -= width_change;
                        model.PArray.get(selected_index).Rec_SW.y += height_change;
                        model.PArray.get(selected_index).Rec_NE.x += width_change;
                        model.PArray.get(selected_index).Rec_NE.y -= height_change;
                        break;
                    case 3:
                        model.PArray.get(selected_index).Cir_r *= scaleFactor;
                        break;
                }
            }
            invalidate();
            return true;
        }
    }
}


