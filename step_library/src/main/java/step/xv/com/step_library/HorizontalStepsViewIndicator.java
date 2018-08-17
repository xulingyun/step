package step.xv.com.step_library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Cantaloupe--郓 on 2018/8/13.
 * 描述：
 */
public class HorizontalStepsViewIndicator extends StepsViewIndicator {

    float centerY;
    float leftTopY;
    float rightBottomY;

    public HorizontalStepsViewIndicator(Context context) {
        this(context,null);
    }

    public HorizontalStepsViewIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorizontalStepsViewIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if(MeasureSpec.AT_MOST==widthMode){
            width = (int) (leftGap*2+mCircleRadius*2*mInfoArrayList.size()+mLineLength*(mInfoArrayList.size()-1)+getPaddingLeft()+getPaddingRight());
        }else if(MeasureSpec.EXACTLY==widthMode){
            width = widthSize;
        }
        if(MeasureSpec.UNSPECIFIED!=heightMode){
            if(mInfoArrayList.size()==0){
                height = (int) (mCircleRadius*2);
            }else{
                Rect lRect = new Rect();
                String name = mInfoArrayList.get(0).getName();
                completedTextPaint.getTextBounds(name,0,name.length(),lRect);
                height = (int) (mCircleRadius*2+lRect.height()+topGap*2+getPaddingTop()+getPaddingBottom()+iconAndTextGap);
            }
        }
        Log.e("TAG", "onMeasure: "+width+","+height );
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerY = mCircleRadius+topGap+getPaddingTop();
        leftTopY = centerY-completedLineHeight/2;
        rightBottomY = centerY+completedLineHeight/2;
        centerPointList.clear();
        if (mInfoArrayList!=null){
            float startX = (width-(mCircleRadius*mInfoArrayList.size()*2+mLineLength*(mInfoArrayList.size()-1)+leftGap*2))/2;
            for (int i = 0; i < mInfoArrayList.size(); i++) {
                centerPointList.add(startX+leftGap+mCircleRadius+i*mCircleRadius*2+i*mLineLength+getPaddingLeft());
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int size = mInfoArrayList.size();
        //-----------------画线开始-------------------//
        float first;
        float second;
        for (int i = 0; i < size - 1; i++) {
            first = centerPointList.get(i);
            second = centerPointList.get(i+1);
            if(i<completeingPosition) {
                canvas.drawRect(first+mCircleRadius-5,leftTopY,second-mCircleRadius+5,rightBottomY,completedLinePaint);
            }else{
                mPath.moveTo(first+mCircleRadius,centerY);
                mPath.lineTo(second-mCircleRadius,centerY);
                canvas.drawPath(mPath,unCompleteLinePaint);
            }
        }
        //-----------------画线结束-------------------//

        //-----------------画图标,文字开始-------------------//
        for (int i = 0; i < size; i++) {
            first = centerPointList.get(i);
            Rect rect = new Rect((int)(first-mCircleRadius),(int)(centerY-mCircleRadius),(int)(first+mCircleRadius),(int)(centerY+mCircleRadius));
            Info lInfo = mInfoArrayList.get(i);
            String temp = mInfoArrayList.get(i).getName();
            int textWidth;
            int textHeight;
            Rect tempRect = new Rect();
            if (lInfo.getState()==Info.STEP_COMPLETED){
                completedIcon.setBounds(rect);
                completedIcon.draw(canvas);
                completedTextPaint.getTextBounds(temp,0,temp.length(), tempRect);
                textWidth = tempRect.width();
                textHeight = tempRect.height();
                canvas.drawText(mInfoArrayList.get(i).getName(),first-textWidth/2,centerY+mCircleRadius+textHeight+iconAndTextGap,completedTextPaint);
            }else if (lInfo.getState()==Info.STEP_COMPLETEING){
                completeingIcon.setBounds(rect);
                completeingIcon.draw(canvas);
                completeingTextPaint.getTextBounds(temp,0,temp.length(), tempRect);
                textWidth = tempRect.width();
                textHeight = tempRect.height();
                canvas.drawText(mInfoArrayList.get(i).getName(),first-textWidth/2,centerY+mCircleRadius+textHeight+iconAndTextGap,completeingTextPaint);
            }else{
                unCompleteIcon.setBounds(rect);
                unCompleteIcon.draw(canvas);
                unCompleteTextPaint.getTextBounds(temp,0,temp.length(), tempRect);
                textWidth = tempRect.width();
                textHeight = tempRect.height();
                canvas.drawText(mInfoArrayList.get(i).getName(),first-textWidth/2,centerY+mCircleRadius+textHeight+iconAndTextGap,unCompleteTextPaint);
            }
        }
        //-----------------画图标,文字结束-------------------//
    }
}
