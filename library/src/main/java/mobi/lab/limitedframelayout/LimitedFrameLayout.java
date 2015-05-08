package mobi.lab.limitedframelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Layout for which you can specify a maximum width and height
 * Created by Harri Kirik (harri35@gmail.com).
 */
public class LimitedFrameLayout extends FrameLayout {
    private int maxWidthPx;
    private int maxHeightPx;

    public LimitedFrameLayout(Context context) {
        super(context);
    }

    public LimitedFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public LimitedFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        final TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LimitedFrameLayout,
                0, 0);
        try {
            maxWidthPx = a.getDimensionPixelSize(R.styleable.LimitedFrameLayout_maxWidth, 0);
            maxHeightPx = a.getDimensionPixelSize(R.styleable.LimitedFrameLayout_maxHeight, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final int height = MeasureSpec.getSize(heightMeasureSpec);
        if (width > maxWidthPx && maxWidthPx > 0) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(maxWidthPx, MeasureSpec.getMode(widthMeasureSpec));
        }

        if (height > maxHeightPx && maxHeightPx > 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeightPx, MeasureSpec.getMode(heightMeasureSpec));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setMaxWidth(final int maxWidthPx) {
        this.maxWidthPx = maxWidthPx;
        invalidate();
        requestLayout();
    }

    public void setMaxHeight(final int maxHeightPx) {
        this.maxHeightPx = maxHeightPx;
        invalidate();
        requestLayout();
    }
}
