
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

@SuppressLint("ClickableViewAccessibility")
public class AccurateSeekBar extends SeekBar {

	private float lastXPosition;
	
	public AccurateSeekBar(Context context) {
		super(context);
	}

	public AccurateSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AccurateSeekBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
	        case MotionEvent.ACTION_MOVE:
	    		if (Math.abs(event.getY()) < (this.getHeight() * 2)) {
	        		this.setProgress((int)(this.getMax() * (float)(event.getX() / ((float)this.getWidth() - 1))));
	    		} else {
	        		float trackingHorizontalDistance = event.getX() - this.lastXPosition;
	        		float valuePerPixel = (float)this.getMax() / (float)this.getWidth();
	        		float valueDivisor = Math.abs(event.getY()) / (float)this.getHeight();
	    			float offset = (trackingHorizontalDistance * valuePerPixel) / valueDivisor;
	    			this.setProgress(this.getProgress() + (int)offset);
	    		}
	            break;
        }
        this.lastXPosition = event.getX();
        return true;
	}
	
}
