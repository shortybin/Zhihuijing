package com.bibi.wisdom.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.bibi.wisdom.bean.CircleBean;
import com.bibi.wisdom.utils.LogUtils;
import com.bibi.wisdom.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleView extends View {

    public static final int DEFAULT_SIZE = 500;

    private Paint circlePaint;

    private float width;

    private float height;

    //环的宽度
    private float circleWidth;


    //中间点
    private float centerPointX;

    private float centerPointY;

    private RectF rectF;    //矩形

    private int rotateDegree=0;

    //起始点-重点位置坐标
//    private float[] points=new float[4];


    private List<CircleBean> circleList=new ArrayList<>();



    public CircleView(Context context) {
        super(context);
        initView();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.BLUE);
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(3);
        circlePaint.setStyle(Paint.Style.STROKE);
        rectF=new RectF();
        setData();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = getMySize(widthMeasureSpec);
        final int height = getMySize(heightMeasureSpec);
        final int min = Math.min(width, height);//保证控件为方形
        setMeasuredDimension(min, min);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        circleWidth = width - 40;

        centerPointX = width / 2;

        centerPointY = height / 2;

        startRotate();
    }


    /**
     * 获取测量大小
     */
    private int getMySize(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;//确切大小,所以将得到的尺寸给view
        } else if (specMode == MeasureSpec.AT_MOST) {
            //默认值为450px,此处要结合父控件给子控件的最多大小(要不然会填充父控件),所以采用最小值
            result = Math.min(DEFAULT_SIZE, specSize);
        } else {
            result = DEFAULT_SIZE;
        }
        return result;
    }


    private void setData(){
        for (int i = 0; i <= 315; i+=45) {
            CircleBean bean=new CircleBean();
            bean.setName(i+"");
            bean.setFromDegree(i);
            bean.setToDegree(45);
            bean.setColor(randomColor());
            circleList.add(bean);
        }
    }

    public  int randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return  Color.rgb(r, g, b);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawCircle(width / 2, height / 2, circleWidth / 2, circlePaint);

//        drawLines(canvas);

        canvas.rotate(rotateDegree,centerPointX,centerPointY);

        drawArc(canvas);
    }


//    private void drawLines(Canvas canvas) {
//        float[] points = new float[4];
//
//        for (int i = 0; i <= 315; i+=45) {
//            getPointFromDegree(points,circleWidth / 2, i);
//            canvas.drawLines(points,circlePaint);
//        }
//
//    }

    private void drawArc(Canvas canvas){
        float[] points = new float[4];
        rectF.set(20, centerPointY-circleWidth/2, circleWidth+20, centerPointY+circleWidth/2);
        circlePaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < circleList.size(); i++) {

            //画扇形
            CircleBean bean=circleList.get(i);
            circlePaint.setColor(bean.getColor());
            canvas.drawArc(rectF, bean.getFromDegree(), bean.getToDegree(), true, circlePaint);

            int textDegree=bean.getFromDegree()+(bean.getFromDegree()+bean.getToDegree()-bean.getFromDegree())/2;
            getPointFromDegree(points,circleWidth/3,textDegree);
            circlePaint.setColor(Color.BLACK);
            circlePaint.setTextSize(45);
            canvas.save();
//            canvas.drawLines(points,circlePaint);
            canvas.rotate(textDegree+90,points[2],points[3]);
            canvas.drawText(bean.getName(), points[2], points[3], circlePaint);
            canvas.restore();
        }

    }


    private float[] getPointFromDegree(float[] points,float radius, int degree) {


        points[0] = centerPointX;
        points[1] = centerPointY;

//        float radius = circleWidth / 2;

        points[2] = (float) (Math.cos(Math.toRadians(degree)) * radius + width / 2);
        points[3] = (float) (Math.sin(Math.toRadians(degree)) * radius + height / 2);

        return points;

    }


    public void startRotate(){
        final int degree=1600+new Random().nextInt(1000);
        ValueAnimator animatorY = ValueAnimator.ofFloat(0, degree);
        animatorY.setDuration(3*1000);
        animatorY.setInterpolator(new DecelerateInterpolator());
        animatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float curValue= (float) animation.getAnimatedValue();

                rotateDegree= (int) curValue;
                postInvalidate();
            }

        });

        animatorY.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                int realDegree=rotateDegree%360;

                getCurrentItem(rotateDegree);
//                LogUtils.e("degree---"+realDegree);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorY.start();
    }


    private void getCurrentItem(float degree){
        degree=(degree)%360;
//        if(degree<0) degree+=360;


        LogUtils.e("degree---"+degree);
        for (int i = 0; i < circleList.size(); i++) {
            CircleBean bean=circleList.get(i);
            float fromDegree=bean.getFromDegree();
            float toDegree=bean.getToDegree()+fromDegree;
            float offsetFrom=(fromDegree+degree)%360;
            float offsetTo=(toDegree+degree)%360;
            if(offsetFrom<=360-90&&360-90<offsetTo){
                ToastUtil.showToast(getContext(),bean.getName());
                break;
            }

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getActionMasked()==MotionEvent.ACTION_UP){
            getItemFromPoints(event);
        }
        return super.onTouchEvent(event);
    }


    private void getItemFromPoints(MotionEvent event){
        float x=event.getX();
        float y=event.getY();


        float xToCenter=x-centerPointX;
        float yToCenter=y-centerPointY;
        //三角函数计算半径
        float pointRadius= (float) Math.sqrt(xToCenter*xToCenter+yToCenter*yToCenter);

        if(pointRadius<=circleWidth/2){

//            ToastUtil.showToast(getContext(),"在圈内");

            float degree= (float) Math.toDegrees(Math.atan2(yToCenter,xToCenter));
            if(degree<0) degree+=360;
//            float degree2= (float) Math.toDegrees(Math.asin(yToCenter/pointRadius));

//            LogUtils.e("角度："+degree);

            float realDegree=degree-rotateDegree%360;
            if(realDegree<0) realDegree+=360;

            LogUtils.e("角度："+degree+">>rotateDegree"+rotateDegree%360);

            for (int i = 0; i < circleList.size(); i++) {
                CircleBean bean=circleList.get(i);
                float fromDegree=bean.getFromDegree();
                float toDegree=bean.getToDegree()+fromDegree;

                if(realDegree>=fromDegree&&realDegree<toDegree){
                    ToastUtil.showToast(getContext(),bean.getName());
                    break;
                }

            }

//            degree= (float) (180*degree/Math.PI);
//            ToastUtil.showToast(getContext(),"角度："+degree+":"+degree2);
        }else {
            ToastUtil.showToast(getContext(),"在圈外");
        }

    }
}
