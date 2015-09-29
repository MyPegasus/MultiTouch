package com.example.mypegasus.multitouch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = (FrameLayout) findViewById(R.id.container);
        iv = (ImageView) findViewById(R.id.iv);

        root.setOnTouchListener(new View.OnTouchListener() {

            float currentDistance;
            float lastDistance = -1;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        System.out.println("action down");
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        System.out.println("action move");
                        if (event.getPointerCount() >= 2) {
                            float offsetX = event.getX(0) - event.getX(1);
                            float offsetY = event.getY(0) - event.getY(1);

                            currentDistance = (float) Math.sqrt(offsetX * offsetX + offsetY * offsetY);

                            if (lastDistance < 0) {
                                lastDistance = currentDistance;
                            } else {
                                if (currentDistance - lastDistance > 5) {
                                    System.out.println("放大");

                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv.getLayoutParams();
                                    lp.width = (int) (1.1f * iv.getWidth());
                                    lp.height = (int) (1.1f * iv.getHeight());
                                    iv.setLayoutParams(lp);

                                    lastDistance = currentDistance;
                                } else if (lastDistance - currentDistance > 5) {
                                    System.out.println("缩小");
                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv.getLayoutParams();
                                    lp.width = (int) (0.9f * iv.getWidth());
                                    lp.height = (int) (0.9f * iv.getHeight());
                                    iv.setLayoutParams(lp);

                                    lastDistance = currentDistance;
                                }
                            }
                        }


                        /*System.out.println("point count : " + event.getPointerCount());

                        String msg = "";
                        for (int i = 0; i < event.getPointerCount(); i++) {
                            msg += "x" + (i + 1) + ":%f y" + (i + 1) + ":%f\n";
                            msg = String.format(msg, event.getX(i), event.getY(i));
                        }
                        System.out.println(msg);*/
                        /*FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv.getLayoutParams();
                        lp.leftMargin = (int) event.getX();
                        lp.topMargin = (int) event.getY();

                        iv.setLayoutParams(lp);

                        System.out.println(String.format("x:%f, y:%f", event.getX(), event.getY()));*/

                        break;
                    case MotionEvent.ACTION_UP:
//                        System.out.println("action up");
                        break;
                }

                return true;
            }
        });
    }

    private FrameLayout root;
    private ImageView iv;
}
