package step.xv.com.step_library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Cantaloupe--郓 on 2018/8/15.
 * 描述：
 */
public class StepsViewIndicator extends LinearLayout {

    Drawable defaultUnCompleteIcon = getResources().getDrawable(R.drawable.default_icon);
    Drawable defaultCompletedTextIcon = getResources().getDrawable(R.drawable.complted);
    Drawable defaultCompleteingTextIcon = getResources().getDrawable(R.drawable.attention);
    public final int defaultLeftGap = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
    public final int defaultTopGap = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
    public final int defaultLineLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
    public final int defaultCircleRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());

    int unCompleteTextColor = Color.parseColor("#FFFFFF");
    int completedTextColor = Color.parseColor("#FFFFFF");
    int completeingTextColor = Color.parseColor("#FFFFFF");

    float unCompleteTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, getResources().getDisplayMetrics());
    float completedTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, getResources().getDisplayMetrics());
    float completeingTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, getResources().getDisplayMetrics());

    boolean unCompleteTextBlod = false;
    boolean completedTextBlod = true;
    boolean completeingTextBlod = true;

    Drawable unCompleteIcon;
    Drawable completedIcon;
    Drawable completeingIcon;

    int unCompleteLineColor = Color.parseColor("#FFFFFF");
    int completedLineColor = Color.parseColor("#FFFFFF");

    float unCompleteLineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
    float completedLineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());

    int unCompleteLineKind = 1;
    int completedLineKind = 2;

    TextPaint unCompleteTextPaint;
    TextPaint  completedTextPaint;
    TextPaint  completeingTextPaint;
    Paint unCompleteLinePaint;
    Paint completedLinePaint;
    int width;
    int height;

    ArrayList<Info> mInfoArrayList;
    int completeingPosition;
    ArrayList<Float> centerPointList;
    Path mPath;
    int leftGap = defaultLeftGap;
    int topGap = defaultTopGap;
    float mLineLength = defaultLineLength;
    float mCircleRadius = defaultCircleRadius;
    float iconAndTextGap = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());

    public StepsViewIndicator(Context context) {
        this(context, null);
    }

    public StepsViewIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepsViewIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs, defStyleAttr);
        initPaint();
        mInfoArrayList = new ArrayList<>();
        centerPointList = new ArrayList<>();
        mPath = new Path();
    }


    void initPaint() {
        unCompleteTextPaint = new TextPaint ();
        unCompleteTextPaint.setAntiAlias(true);
        unCompleteTextPaint.setDither(true);
        unCompleteTextPaint.setColor(unCompleteTextColor);
        unCompleteTextPaint.setTextSize(unCompleteTextSize);
        unCompleteTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        if(unCompleteTextBlod){
            unCompleteTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        }

        completedTextPaint = new TextPaint ();
        completedTextPaint.setAntiAlias(true);
        completedTextPaint.setDither(true);
        completedTextPaint.setColor(completedTextColor);
        completedTextPaint.setTextSize(completedTextSize);
        completedTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        if(completedTextBlod){
            completedTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        }

        completeingTextPaint = new TextPaint ();
        completeingTextPaint.setAntiAlias(true);
        completeingTextPaint.setDither(true);
        completeingTextPaint.setColor(completeingTextColor);
        completeingTextPaint.setTextSize(completeingTextSize);
        completeingTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        if(completeingTextBlod){
            completeingTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        }

        unCompleteLinePaint = new Paint();
        unCompleteLinePaint.setAntiAlias(true);
        unCompleteLinePaint.setDither(true);
        unCompleteLinePaint.setColor(unCompleteLineColor);
        unCompleteLinePaint.setStrokeWidth(unCompleteLineHeight);
        unCompleteLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        if(unCompleteLineKind==2) {
            unCompleteLinePaint.setPathEffect(new DashPathEffect(new float[]{6, 6, 6, 6}, 1));
        }

        completedLinePaint = new Paint();
        completedLinePaint.setAntiAlias(true);
        completedLinePaint.setDither(true);
        completedLinePaint.setColor(completedLineColor);
        completedLinePaint.setStrokeWidth(completedLineHeight);
    }

    void initAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.HorizontalStepsViewIndicator);
        unCompleteTextColor = ta.getColor(R.styleable.HorizontalStepsViewIndicator_unCompleteTextColor,unCompleteTextColor);
        completedTextColor = ta.getColor(R.styleable.HorizontalStepsViewIndicator_completedTextColor,completedTextColor);
        completeingTextColor = ta.getColor(R.styleable.HorizontalStepsViewIndicator_completeingTextColor,completeingTextColor);

        unCompleteTextSize = ta.getDimension(R.styleable.HorizontalStepsViewIndicator_unCompleteTextSize,unCompleteTextSize);
        completedTextSize = ta.getDimension(R.styleable.HorizontalStepsViewIndicator_completedTextSize,completedTextSize);
        completeingTextSize = ta.getDimension(R.styleable.HorizontalStepsViewIndicator_completeingTextSize,completeingTextSize);

        unCompleteTextBlod = ta.getBoolean(R.styleable.HorizontalStepsViewIndicator_unCompleteTextBlod,unCompleteTextBlod);
        completedTextBlod = ta.getBoolean(R.styleable.HorizontalStepsViewIndicator_completedTextBlod,completedTextBlod);
        completeingTextBlod = ta.getBoolean(R.styleable.HorizontalStepsViewIndicator_completeingTextBlod,completeingTextBlod);

        unCompleteIcon = ta.getDrawable(R.styleable.HorizontalStepsViewIndicator_unCompleteIcon);
        completedIcon = ta.getDrawable(R.styleable.HorizontalStepsViewIndicator_completedTextIcon);
        completeingIcon = ta.getDrawable(R.styleable.HorizontalStepsViewIndicator_completeingTextIcon);
        if (unCompleteIcon==null){
            unCompleteIcon = defaultUnCompleteIcon;
        }
        if (completedIcon==null){
            completedIcon = defaultCompletedTextIcon;
        }
        if (completeingIcon==null){
            completeingIcon = defaultCompleteingTextIcon;
        }

        unCompleteLineColor = ta.getColor(R.styleable.HorizontalStepsViewIndicator_unCompleteLineColor,unCompleteLineColor);
        completedLineColor = ta.getColor(R.styleable.HorizontalStepsViewIndicator_completedLineColor,completedLineColor);

        unCompleteLineHeight = ta.getDimension(R.styleable.HorizontalStepsViewIndicator_unCompleteLineHeight,unCompleteLineHeight);
        completedLineHeight = ta.getDimension(R.styleable.HorizontalStepsViewIndicator_completedLineHeight,completedLineHeight);

        unCompleteLineKind = ta.getInt(R.styleable.HorizontalStepsViewIndicator_unCompleteLineKind,unCompleteLineKind);
        completedLineKind = ta.getInt(R.styleable.HorizontalStepsViewIndicator_completedLineKind,completedLineKind);

        leftGap = (int) ta.getDimension(R.styleable.HorizontalStepsViewIndicator_leftGap,leftGap);
        topGap = (int) ta.getDimension(R.styleable.HorizontalStepsViewIndicator_topGap,topGap);
        mCircleRadius = (int) ta.getDimension(R.styleable.HorizontalStepsViewIndicator_circleRadius,mCircleRadius);
        mLineLength = (int) ta.getDimension(R.styleable.HorizontalStepsViewIndicator_lineLength,mLineLength);
        iconAndTextGap = (int) ta.getDimension(R.styleable.HorizontalStepsViewIndicator_iconAndTextGap,iconAndTextGap);
        ta.recycle();
    }

    public void setInfoArrayList(ArrayList<Info> $InfoArrayList) {
        mInfoArrayList = $InfoArrayList;
        if(mInfoArrayList!=null&&mInfoArrayList.size()>0){
            for (int i = 0; i < mInfoArrayList.size(); i++) {
                Info info = mInfoArrayList.get(i);
                if(info.getState()==Info.STEP_COMPLETEING){
                    completeingPosition = i;
                }
            }
            if(completeingPosition==0){
                completeingPosition = mInfoArrayList.size()-1;
            }
        }
        requestLayout();
    }
}
