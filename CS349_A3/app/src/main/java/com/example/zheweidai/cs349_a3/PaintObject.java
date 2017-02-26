package com.example.zheweidai.cs349_a3;

import android.graphics.Color;

public class PaintObject {
    public Boolean is_filled;
    public int fill = Color.BLUE;
    public int type; // 1 - Line, 2 - Rectangle, 3 - Circle
    public int tickness;
    public int real_tickness;
    public int color;

    //Fields for line
    public Dot line_end1;
    public Dot line_end2;

    //Fields for Rectangle;
    public Dot Rec_NW;
    public Dot Rec_NE;
    public Dot Rec_SW;
    public Dot Rec_SE;

    //Fields for Circle
    public Dot Cir_center;
    public float Cir_r;

    //Constructor
    public PaintObject(int tp, int tn, int c){
        this.is_filled = false;
        this.type = tp;
        this.tickness = tn;
        this.color = c;
        this.real_tickness = tn;
    }
}
