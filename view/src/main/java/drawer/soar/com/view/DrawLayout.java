package drawer.soar.com.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DrawLayout extends LinearLayout{
	private final static String TAG="DrawLayout";
	private ModeListView mModeListView;
	private float touchPosition=0;
	private int itemId=0;
	private ListViewItemView item;
	@SuppressLint("UseSparseArrays")
	private HashMap<Integer, ListViewItemView> itemView=new HashMap<Integer, ListViewItemView>();
	private float x=0,y=0;

	private Scroller mScroller;
	public GestureDetector mGestureDetector;
	private MenuDrawable mMenuDrawable;
    
	private ItemOnClick mItemOnClick;
	private List<ListAttr> mList=new ArrayList<ListAttr>();
	
	private LinearLayout modelistparent;
	private RotateImageView mImageView;

	public DrawLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public DrawLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.setClickable(true);
		this.setLongClickable(true);
		mScroller = new Scroller(context);
		mGestureDetector = new GestureDetector(context, new CustomGestureListener());
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		mModeListView.post(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int x=0;x<mModeListView.getChildCount();x++){
					itemView.put(x, (ListViewItemView) mModeListView.getChildAt(x));
				}
			}
		});
		mModeListView.setDrawLayout(this);
		super.onLayout(changed, l, t, r, b);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction()){
			case MotionEvent.ACTION_UP:
				if(null!=item) {
					if (Math.abs(event.getX() - x) < 50 && Math.abs(event.getY() - y) < 50 && item.getTop() < event.getY() && event.getY() < (item.getTop() + item.getHeight())) {
						//onclick
						int position = Integer.parseInt(item.getTag(R.id.position ).toString());
						ListAttr clickItem = mList.get(position);
						if (clickItem.isEnable()) {
							mItemOnClick.onItemClick(position , clickItem.getMenuName());
							mMenuDrawable.closeDrawers();
							for(int i = 0 ; i < mList.size() ; i ++){
								if(i == position){
									mList.get(i).setSelect(true);
								}else{
									mList.get(i).setSelect(false);
								}
							}
						}
						mModeListView.setData(mList);
					}
				}
				break;
			default:
				return mGestureDetector.onTouchEvent(event);
			}

		return super.onTouchEvent(event);
	}
	//929908
	//get position for listview item
		 @SuppressLint("NewApi")
		public int getPosition(float touchPosition){
			 
			 Log.d(TAG,"touchPosition:"+touchPosition+"  mListView.getTop():"+mModeListView.getTop()+"  mLIstView.getheight5:"+mModeListView.getHeight()+"   mlistview.getbottom:"+mModeListView.getBottom()+"  "+itemView.size());
			 
			 if(touchPosition >mModeListView.getTop()+modelistparent.getTop() && touchPosition<mModeListView.getTop()+mModeListView.getHeight()+modelistparent.getTop()){
				 return getItemArea(touchPosition);
			 }else if(touchPosition<mModeListView.getTop()+modelistparent.getTop()){
				 return 0;
			 }else if(touchPosition>(mModeListView.getTop()+modelistparent.getTop()+mModeListView.getHeight())){
				 return itemView.size()-1;
			 }
			 
			return 0;
		 }
		 
		 private int getItemArea(float touchPosition){
			 
			 for(int x=0;x<itemView.size();x++){
				 if((mModeListView.getTop()+modelistparent.getTop()+((ListViewItemView)(itemView.get(x))).getTop())<touchPosition && touchPosition<(mModeListView.getTop()+modelistparent.getTop()+((ListViewItemView)(itemView.get(x))).getTop())+((ListViewItemView) itemView.get(x)).getHeight()){
				 	return x;
				 }else{
					 
				 }
			 }
			 return 0;
		 }
		 
		 public void setModeListView(ModeListView modeListView,LinearLayout modelistparent){
			 this.mModeListView=modeListView;
			 this.mList=mModeListView.getMethod();
			 this.modelistparent=modelistparent;
			 
		 }
		 
			class CustomGestureListener implements GestureDetector.OnGestureListener {

				@Override
				public boolean onDown(MotionEvent e) {
					// TODO Auto-generated method stub
					x=e.getX();
					y=e.getY();
					touchPosition=e.getY();
					itemId=mMenuDrawable.touchItemId;
					Log.d(TAG,"ondown item.size:"+itemView.size()+" ItemId:"+itemId);
					item=(ListViewItemView)(itemView.get(itemId));
					mImageView=(RotateImageView)item.findViewById(R.id.mode_image);
					//set image when on down
					if(mList.get(itemId).isSelect()){
						return true;
					}
					if(Math.abs(e.getX()-x)<4 && Math.abs(e.getY()-y)<4 && (mModeListView.getTop()+item.getTop()+modelistparent.getTop())<e.getY() && e.getY()<(mModeListView.getTop()+item.getTop()+modelistparent.getTop()+item.getHeight())){
						mImageView.setImageResource(mList.get(itemId).getMenuPic_pressed());
					}
					//get listview item
					
					return true;
				}

				@Override
				public void onShowPress(MotionEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public boolean onSingleTapUp(MotionEvent e) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean onScroll(MotionEvent e1, MotionEvent e2,
						float distanceX, float distanceY) {
					return false;
				}

				@Override
				public void onLongPress(MotionEvent e) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
						float velocityY) {
					// TODO Auto-generated method stub
					return false;
				}
				
			}
			
			public void setMenuDrawable(MenuDrawable view){
				mMenuDrawable=view;
			}
			
			 public void setDrawLayoutCall(ItemOnClick item){
				 mItemOnClick = item;
			 }
			 public HashMap<Integer, ListViewItemView> getItemView(){
				 return itemView;
			 } 
			
			 
			 public  void disableMenu(int id){
					mList.get(id).setEnable(false);
					mModeListView.setData(mList);
				 }
				 public  void enableMenu(int id){
					mList.get(id).setEnable(true);
					mModeListView.setData(mList);
				}
				 
				 public void updatePicforDown(){
					 
				 } 
				 
				 public void initDown(MotionEvent e){
					 	x=e.getX();
						y=e.getY();
						touchPosition=e.getY();
						itemId=mMenuDrawable.touchItemId;
						
						Log.d(TAG,"ondown item.size:"+itemView.size()+" ItemId:"+itemId);
						item=(ListViewItemView)(itemView.get(itemId));
						mImageView=(RotateImageView)item.findViewById(R.id.mode_image);
						//set image when on down
						 if(mList.get(itemId).isSelect()){
							 return;
						 }
						if(Math.abs(e.getX()-x)<4 && Math.abs(e.getY()-y)<4 && item.getTop()<e.getY() && e.getY()<(item.getTop()+item.getHeight()) && mList.get(Integer.parseInt(item.getTag(R.id.position ).toString())).isEnable()){
							mImageView.setImageResource(mList.get(Integer.parseInt(item.getTag(R.id.position ).toString())).getMenuPic_pressed());
						}
				 }
}
