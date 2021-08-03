package com.example.minesweeper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Vibrator;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Canvas1 extends View {
    Paint rectangle = new Paint();
    Paint safe = new Paint();
    Paint mine = new Paint();
    Paint text = new Paint();

    float scr_w, scr_h, scr_w1, scr_h1, s1, s2, s3, s4, gap, w_gap, h_gap, left, top, x1, y1;
    Bitmap cup;
    int count = 0, c = 0, score = 0;
    Context context;
    float arr[] = new float[200000];
    boolean touch = false;
    private boolean[][] arr1;
    final Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
    private Dialog dialog;




    public Canvas1(Context context) {
        super(context);
        this.context=context;
        rectangle.setColor(Color.GRAY);
        safe.setColor(Color.GREEN);
        mine.setColor(Color.RED);
        text.setColor(getResources().getColor(R.color.pink));
        text.setTextSize(120);
        text.setTextAlign(Paint.Align.CENTER);
        dialog = new Dialog(context);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        arr1=new boolean[64][64];
        display.getSize(size);
        scr_w = size.x;
        scr_h = size.y;
        scr_w1 = scr_w / 8 - 10;
        scr_h1 = scr_h / 8 - 120;
        s1 = 40;
        s2 = 0;
        s3 = scr_w1 + 40;
        s4 = scr_h1 ;
        gap = 5;
        left = 20;
        top = 200;
        cup = BitmapFactory.decodeResource(getResources(), R.drawable.cup);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.light_purple));
        for (int i = 0; i < 8; i++) {
            for (int z = 0; z < 8; z++) {
                canvas.drawRect(s1 + gap, s2 + gap, s3 - gap, s4 - gap, rectangle);
                s1 += scr_w1;
                s3 += scr_w1;
            }
            s1 = 40;
            s3 = scr_w1 + 40;
            s2 += scr_h1;
            s4 += scr_h1;
        }
        s1 = 40;
        s2 = 0;
        s3 = scr_w1 + 40;
        s4 = scr_h1 ;
        canvas.drawText("SCORE : "+score,scr_w/2,9*scr_h1,text);
        canvas.drawBitmap(cup,(scr_w/2-cup.getWidth()/2),11*scr_h1,null);

        for(int r=0;r<8;r++)
        {
            for(int k=0;k<8;k++)
            {
                if(arr1[r][k])
                {
                    canvas.drawRect((r)*scr_w1+40+gap,(k)*scr_h1+gap,(r+1)*scr_w1+40-gap,(k+1)*scr_h1-gap,safe);
                }
                if(arr1[0][6])
                {
                    canvas.drawRect((0)*scr_w1+40+gap,(6)*scr_h1+gap,(1)*scr_w1+40-gap,(7)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[5][0])
                {
                    canvas.drawRect((5)*scr_w1+40+gap,(0)*scr_h1+gap,(6)*scr_w1+40-gap,(1)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[3][1])
                {
                    canvas.drawRect((3)*scr_w1+40+gap,(1)*scr_h1+gap,(4)*scr_w1+40-gap,(2)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[2][2])
                {
                    canvas.drawRect((2)*scr_w1+40+gap,(2)*scr_h1+gap,(3)*scr_w1+40-gap,(3)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[7][4])
                {
                    canvas.drawRect((7)*scr_w1+40+gap,(4)*scr_h1+gap,(8)*scr_w1+40-gap,(5)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[1][3])
                {
                    canvas.drawRect((1)*scr_w1+40+gap,(3)*scr_h1+gap,(2)*scr_w1+40-gap,(4)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[6][5])
                {
                    canvas.drawRect((6)*scr_w1+40+gap,(5)*scr_h1+gap,(7)*scr_w1+40-gap,(6)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[4][7])
                {
                    canvas.drawRect((4)*scr_w1+40+gap,(7)*scr_h1+gap,(5)*scr_w1+40-gap,(8)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }

            }
        }




    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int c = (int) ((event.getX()-40)/scr_w1) ;
            int r = (int) ((event.getY())/scr_h1 );
            arr1[c][r]=!arr1[c][r];
            if((r*scr_h1)<(9*scr_h1)){
                score++;
            }
            c++;
            invalidate();

        }


        return true;

    }
    private void dialog() {
        dialog.setContentView(R.layout.dialogbox);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button bT=dialog.findViewById(R.id.button);
        dialog.setCancelable(false);
        bT.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MainActivity5.class);
                context.startActivity(intent);
            }
        });
        dialog.show();
    }

}

