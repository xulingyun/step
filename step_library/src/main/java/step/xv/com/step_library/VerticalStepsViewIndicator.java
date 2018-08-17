package step.xv.com.step_library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 * Created by Cantaloupe--郓 on 2018/8/15.
 * 描述：
 */
public class VerticalStepsViewIndicator extends StepsViewIndicator{

    float centerX;
    float leftTopX;
    float rightBottomX;
    ArrayList<Float> lineLengthList;

    StaticLayout layout;
    float textWidth;
    Rect tempRect1 = new Rect();

    public VerticalStepsViewIndicator(Context context) {
        this(context,null);
    }

    public VerticalStepsViewIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VerticalStepsViewIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        lineLengthList = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if(MeasureSpec.UNSPECIFIED!=widthMode){
            width = widthSize;
        }
        textWidth = width- iconAndTextGap-leftGap-2*mCircleRadius-getPaddingLeft()-getPaddingRight();

        lineLengthList.clear();
        int size = mInfoArrayList.size();
        height = (int) (mCircleRadius*2*size)+getPaddingTop()+getPaddingBottom()+topGap*2;
        for (int i = 0; i < size; i++) {
            Info lInfo = mInfoArrayList.get(i);
            String temp = mInfoArrayList.get(i).getName();
            if (lInfo.getState()== Info.STEP_COMPLETED) {
                completedTextPaint.getTextBounds(temp, 0, temp.length(), tempRect1);
            }else if (lInfo.getState()==Info.STEP_COMPLETEING){
                completeingTextPaint.getTextBounds(temp, 0, temp.length(), tempRect1);
            }else{
                unCompleteTextPaint.getTextBounds(temp, 0, temp.length(), tempRect1);
            }

            int l = (int) Math.ceil(tempRect1.width()*1.0f/textWidth)+1;

            float max = Math.max(mLineLength, l * tempRect1.height());
            lineLengthList.add(max);
            height+=max;

        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = mCircleRadius+leftGap+getPaddingLeft();
        leftTopX = centerX-completedLineHeight/2;
        rightBottomX = centerX+completedLineHeight/2;
        centerPointList.clear();
        float allLineLength = 0;
        for (int i = 0; i < lineLengthList.size(); i++) {
            allLineLength+=lineLengthList.get(i);
        }
        float startY = (height-(mCircleRadius*mInfoArrayList.size()*2+allLineLength))/2;
        float tempLineLength = 0;
        for (int i = 0; i < mInfoArrayList.size(); i++) {
            if(i!=0) {
                tempLineLength += lineLengthList.get(i-1);
            }
            centerPointList.add(startY+mCircleRadius+i*mCircleRadius*2+tempLineLength);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int size = mInfoArrayList.size();
        //-----------------画线开始-------------------//
        float first = 0;
        float second = 0;
        for (int i = 0; i < size - 1; i++) {
            first = centerPointList.get(i);
            second = centerPointList.get(i+1);
            if(i<completeingPosition) {
                canvas.drawRect(leftTopX,first+mCircleRadius-5,rightBottomX,second-mCircleRadius+5,completedLinePaint);
            }else{
                mPath.moveTo(centerX,first+mCircleRadius-5);
                mPath.lineTo(centerX,second-mCircleRadius+5);
                canvas.drawPath(mPath,unCompleteLinePaint);
            }
        }
        //-----------------画线结束-------------------//

        //-----------------画图标,文字开始-------------------//
        for (int i = 0; i < size; i++) {
            float startX = centerX-mCircleRadius;
            first = centerPointList.get(i);
            Rect rect = new Rect((int) startX,(int)(first-mCircleRadius),(int)(startX+2*mCircleRadius),(int)(first+mCircleRadius));
            Info lInfo = mInfoArrayList.get(i);
            String temp = mInfoArrayList.get(i).getName();
            Rect tempRect = new Rect();
            if (lInfo.getState()== Info.STEP_COMPLETED){
                completedIcon.setBounds(rect);
                completedIcon.draw(canvas);
                layout = new StaticLayout(temp, completedTextPaint, (int) textWidth, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                canvas.save();
                canvas.translate(startX+2*mCircleRadius+iconAndTextGap,first-mCircleRadius);
                layout.draw(canvas);
                canvas.restore();
            }else if (lInfo.getState()==Info.STEP_COMPLETEING){
                completeingIcon.setBounds(rect);
                completeingIcon.draw(canvas);
                layout = new StaticLayout(temp, completeingTextPaint, (int) textWidth, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                canvas.save();
                canvas.translate(startX+2*mCircleRadius+iconAndTextGap,first-mCircleRadius);
                layout.draw(canvas);
                canvas.restore();
            }else{
                unCompleteIcon.setBounds(rect);
                unCompleteIcon.draw(canvas);
                layout = new StaticLayout(temp, unCompleteTextPaint, (int) textWidth, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                canvas.save();
                canvas.translate(startX+2*mCircleRadius+iconAndTextGap,first-mCircleRadius);
                layout.draw(canvas);
                canvas.restore();
            }
        }
        //-----------------画图标,文字结束-------------------//
    }
}
