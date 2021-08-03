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
import android.os.Vibrator;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Canvas2 extends View {
    Paint rectangle = new Paint();
    Paint safe = new Paint();
    Paint mine = new Paint();
    Paint text = new Paint();

    float scr_w, scr_h, scr_w1, scr_h1, s1, s2, s3, s4, gap, w_gap, h_gap, left, top, x1, y1;
    Bitmap cup;
    int count = 0, c = 0, score = 0;

    Context context;
    int arr[] = new int[20];
    boolean touch = false;
    private boolean[][] arr1;
    Random random;
    final Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
    private Dialog dialog;
    public Canvas2(Context context) {
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
        random=new Random();
        for(int b=0;b<16;b++)
        {
            arr[b]=random.nextInt(8);

        }
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
                if(arr1[arr[0]][0])
                {
                    canvas.drawRect((arr[0])*scr_w1+40+gap,(0)*scr_h1+gap,(arr[0]+1)*scr_w1+40-gap,(1)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[1]][0])
                {
                    canvas.drawRect((arr[1])*scr_w1+40+gap,(0)*scr_h1+gap,(arr[1]+1)*scr_w1+40-gap,(1)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[2]][1])
                {
                    canvas.drawRect((arr[2])*scr_w1+40+gap,(1)*scr_h1+gap,(arr[2]+1)*scr_w1+40-gap,(2)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[3]][1])
                {
                    canvas.drawRect((arr[3])*scr_w1+40+gap,(1)*scr_h1+gap,(arr[3]+1)*scr_w1+40-gap,(2)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[4]][2])
                {
                    canvas.drawRect((arr[4])*scr_w1+40+gap,(2)*scr_h1+gap,(arr[4]+1)*scr_w1+40-gap,(3)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[5]][2])
                {
                    canvas.drawRect((arr[5])*scr_w1+40+gap,(2)*scr_h1+gap,(arr[5]+1)*scr_w1+40-gap,(3)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[6]][3])
                {
                    canvas.drawRect((arr[6])*scr_w1+40+gap,(3)*scr_h1+gap,(arr[6]+1)*scr_w1+40-gap,(4)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[7]][3])
                {
                    canvas.drawRect((arr[7])*scr_w1+40+gap,(3)*scr_h1+gap,(arr[7]+1)*scr_w1+40-gap,(4)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[8]][4])
                {
                    canvas.drawRect((arr[8])*scr_w1+40+gap,(4)*scr_h1+gap,(arr[8]+1)*scr_w1+40-gap,(5)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[9]][4])
                {
                    canvas.drawRect((arr[9])*scr_w1+40+gap,(4)*scr_h1+gap,(arr[9]+1)*scr_w1+40-gap,(5)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[10]][5])
                {
                    canvas.drawRect((arr[10])*scr_w1+40+gap,(5)*scr_h1+gap,(arr[10]+1)*scr_w1+40-gap,(6)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[11]][5])
                {
                    canvas.drawRect((arr[11])*scr_w1+40+gap,(5)*scr_h1+gap,(arr[11]+1)*scr_w1+40-gap,(6)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[12]][6])
                {
                    canvas.drawRect((arr[12])*scr_w1+40+gap,(6)*scr_h1+gap,(arr[11]+1)*scr_w1+40-gap,(7)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[13]][6])
                {
                    canvas.drawRect((arr[13])*scr_w1+40+gap,(6)*scr_h1+gap,(arr[13]+1)*scr_w1+40-gap,(7)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[14]][7])
                {
                    canvas.drawRect((arr[14])*scr_w1+40+gap,(7)*scr_h1+gap,(arr[14]+1)*scr_w1+40-gap,(8)*scr_h1-gap,mine);
                    v.vibrate(50);
                    dialog();
                }
                if(arr1[arr[15]][7])
                {
                    canvas.drawRect((arr[15])*scr_w1+40+gap,(7)*scr_h1+gap,(arr[15]+1)*scr_w1+40-gap,(8)*scr_h1-gap,mine);
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
