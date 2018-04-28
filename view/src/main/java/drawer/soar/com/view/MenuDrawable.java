package drawer.soar.com.view;


import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;



public class MenuDrawable extends DrawerLayout{
	private Context mContext;
	private ModeListView mModeListView;
	private ListViewItemView mListViewItemView;
	private DrawLayout mDrawLayout;
	public int touchItemId = -1;
	private int mOriginTouchItemId =-1;
	private final static int INITPOSITION=-300;//animation init position
	private final static int ANIMATION_DUR=100;
	private final static float NOT_DOCHANGE_ALPHA_DRAWER=0.3f;
	private final static float NOT_DOCHANGE_ALPHA_MODELISTVIEW=0.5f;

	private final static int delay=40;
	private boolean isDrawerOpen=false;
	
	
	public MenuDrawable(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("ClickableViewAccessibility")
	public MenuDrawable(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		this.setDrawerListener(new DrawerListener() {
			
			@Override
			public void onDrawerStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onDrawerSlide(View arg0, float dis) {
				// TODO Auto-generated method stub
				//touchItemId do nothing ,others to start animation
				doAnimation(arg0);
				//setapha
				if(dis<NOT_DOCHANGE_ALPHA_DRAWER){
					arg0.setAlpha(NOT_DOCHANGE_ALPHA_DRAWER);
				}else{
					arg0.setAlpha(dis);
				}
				if(dis<NOT_DOCHANGE_ALPHA_MODELISTVIEW){
					mModeListView.setAlpha(0);
				}else{
					mModeListView.setAlpha((dis-NOT_DOCHANGE_ALPHA_MODELISTVIEW)*(1f/(1f-NOT_DOCHANGE_ALPHA_MODELISTVIEW)));
				}
				
			}
			
			@Override
			public void onDrawerOpened(View arg0) {
				// TODO Auto-generated method stub
				isDrawerOpen =true;
			}
			
			@Override
			public void onDrawerClosed(View arg0) {
				// TODO Auto-generated method stub
				isDrawerOpen =false;
			}
		});
	}

	
	 @Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		 
		 switch(event.getAction()){
		 case MotionEvent.ACTION_DOWN:
			 
			 if(touchItemId != -1 && mOriginTouchItemId !=touchItemId){
				 mOriginTouchItemId = touchItemId;
			 }
			 touchItemId=mDrawLayout.getPosition(event.getY());
			 if(mOriginTouchItemId == -1){
				 mOriginTouchItemId = touchItemId;
			 }
			 mListViewItemView=(ListViewItemView) mModeListView.getChildAt(touchItemId);
			 if(!isDrawerOpen(Gravity.LEFT)){
				 initItemPosition();
				 //update ui
				 updateUI();
			 }
			 break;
			 default :
				 break;
		 }
		return super.onInterceptTouchEvent(event);
	}
	 

	 public void setDrawerLayout(Context mContext,DrawLayout mDrawLayout,ModeListView mModeListView){
		 this.mContext = mContext;
		 this.mModeListView=mModeListView;
		 this.mDrawLayout=mDrawLayout;
	 }
	 
	private void startAnimator(final ListViewItemView view,float start , float end ,int delay){
		 	ObjectAnimator mAnimator = ObjectAnimator.ofFloat(view, "translationX", start, end);
		 	mAnimator.addListener(new AnimatorListener() {
				
				@Override
				public void onAnimationStart(Animator animation) {
					// TODO Auto-generated method stub
					if(animation.isRunning()){
						animation.end();
					}
				}
				
				@Override
				public void onAnimationRepeat(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
				}
			});
		 		mAnimator.setDuration(ANIMATION_DUR);
				mAnimator.setStartDelay(delay);
		        mAnimator.setInterpolator(new MenuDrawablerInterpolator(2.0f));
		        mAnimator.start();
	 }
	 
	 private void initItemPosition(){
		 for(int x=0;x<mModeListView.getChildCount();x++){
			 if(mOriginTouchItemId !=-1 && x!=mOriginTouchItemId){
				 startAnimator((ListViewItemView)mModeListView.getChildAt(x), INITPOSITION,mModeListView.getChildAt(x).getY(), 0);
			 }
		 }
	 }
	 
	 public void setOrientation(int orientation,boolean animation){
		 for(int listItem=0;listItem<mModeListView.getChildCount();listItem++){
			 RotateImageView imageItem=(RotateImageView)(mModeListView.getChildAt(listItem)).findViewById(R.id.mode_image);
			 RotateLayout textParent=(RotateLayout)(mModeListView.getChildAt(listItem)).findViewById(R.id.mode_text_parent);
			 imageItem.setOrientation(orientation, true);
			 textParent.setOrientation(orientation, true);
			 
		 }
	 }
	 
	 
	 private void doAnimation(View view){
		 if(touchItemId>=0 && touchItemId <=mModeListView.getChildCount()-1){
				for(int x=0;x<mModeListView.getChildCount();){
					startAnimator((ListViewItemView)(mModeListView.getChildAt(touchItemId)),mModeListView.getChildAt(touchItemId).getX(),view.getX() ,0);
					
					x++;
					
					int up=touchItemId+x;
					
					if(up<=mModeListView.getChildCount()-1){
						startAnimator((ListViewItemView)(mModeListView.getChildAt(up)),mModeListView.getChildAt(up).getX(),view.getX() ,delay*x);
					}
					
					int down=touchItemId-x;
					if(down>=0){
						startAnimator((ListViewItemView)(mModeListView.getChildAt(down)),mModeListView.getChildAt(down).getX() ,view.getX() ,delay*x);
					}
				}
			}else{
				//donothing
			}
	 }
	 
	 private void updateUI(){
		 if(mModeListView!=null){
			 mModeListView.updateModeMenu();
		 }
	 }
	 public boolean isDrawerOpened(){
		 return isDrawerOpen;
	 }
}