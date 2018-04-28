package drawer.soar.com.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ModeListView  extends ListView{
	
	private static final String TAG = "ModeListView" ;

	@SuppressLint("UseSparseArrays")
	private  List<ListAttr> mList=new ArrayList<ListAttr>();
	private Context mContext;
	private ModeAdapter mModeAdapter;
	private DrawLayout mDrawLayout;


	

	public ModeListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	
	
	public ModeListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.mContext=context;
		this.setClickable(true);
		this.setVerticalScrollBarEnabled(false);
        this.setDividerHeight(0);
        this.setSelector(android.R.color.transparent);
		mModeAdapter=new ModeAdapter(mContext);
		setAdapter(mModeAdapter);
		mModeAdapter.setDatas(mList);
	}


	public void  setDrawLayout(DrawLayout drawLayout){
		this.mDrawLayout = drawLayout;
	}


	 
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return onTouchEvent(ev);
	}
	
	float x = 0;
	 float y = 0;
	 @SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		 
		 switch(event.getAction()){
		 case MotionEvent.ACTION_UP:

			 if(Math.abs(event.getX() - x) < Math.abs(event.getY() - y) ){
				 return super.onTouchEvent(event);
			 }else{
				 return mDrawLayout.onTouchEvent(event);
			 }

		 case MotionEvent.ACTION_DOWN:
			 x = event.getX();
			 y = event.getY();
			 mDrawLayout.initDown(event);
			 return super.onTouchEvent(event);
		 case MotionEvent.ACTION_MOVE:
			 if(Math.abs(event.getX() - x) < Math.abs(event.getY() - y) ){
				 return super.onTouchEvent(event);
			 }
			 
		 }
		return mDrawLayout.mGestureDetector.onTouchEvent(event);
	}
	 
	 
	 
	 public List<ListAttr>  getMethod(){
		 return mList;
	 } 
	 
	 
	//set listview wid and heig
		 public void setListViewHeightBasedOnChildren(ListView listView) {
	   	  ListAdapter listAdapter = listView.getAdapter();
	   	  if (listAdapter == null) {
	   	   return;
	   	  }
	   	  int totalHeight = 0;
	   	  int maxWid=0;
	   	  for (int i = 0; i < listAdapter.getCount(); i++) { 
	   	   View listItem = listAdapter.getView(i, null, listView);
	   	   listItem.measure(0, 0); 
	   	   if(maxWid<listItem.getMeasuredWidth()){
	   		   maxWid=listItem.getMeasuredWidth();
	   	   }
	   	   totalHeight += listItem.getMeasuredHeight(); 
	   	  }
	   	  ViewGroup.LayoutParams params = listView.getLayoutParams();
	   	  params.height = totalHeight+dipTopx(mContext,16);
//	   	  params.width = maxWid;
	   	  listView.setLayoutParams(params);

	   	 }
		 
		 public static int dipTopx(Context context, float dipValue){
		        final float scale = context.getResources().getDisplayMetrics().density;
		        return (int)(dipValue * scale + 0.5f);
			} 
		 

		public void  setData(List<ListAttr> list){
			 this.mList=list;
			updateModeMenu();
		 }

		 public void updateModeMenu(){
			 if(mModeAdapter != null){
				 mModeAdapter.setDatas(mList);
			 }
		 }

}
