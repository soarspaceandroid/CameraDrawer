package drawer.soar.com.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ModeAdapter extends BaseAdapter{
	private List<ListAttr> datas = new ArrayList<>();
	private LayoutInflater inflate;
	
	private Context mContext;

	
	public ModeAdapter(Context context ) {
		super();
		// TODO Auto-generated constructor stub
		this.mContext = context;
		inflate=LayoutInflater.from(context);
	}
	


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHodler viewHodler = null;
		if(convertView==null) {
			viewHodler=new ViewHodler();
			convertView = inflate.inflate(R.layout.mode_listview_item, null);
			viewHodler.view = (ListViewItemView) convertView.findViewById(R.id.mode_item);
			viewHodler.image = (RotateImageView) convertView.findViewById(R.id.mode_image);
			viewHodler.text = (TextView) convertView.findViewById(R.id.mode_text);
			viewHodler.text.setText(datas.get(position).getMenuName());
			convertView.setTag(R.id.view_holder ,viewHodler);
		}else{
			viewHodler = (ViewHodler)convertView.getTag(R.id.view_holder);
		}
			viewHodler.image.setImageResource(datas.get(position).getMenuPic());

//
			if (datas.get(position).isEnable()) {
				convertView.setEnabled(true);
				viewHodler.text.setTextColor(mContext.getResources().getColor(android.R.color.white));
				if(datas.get(position).isSelect()){
					viewHodler.image.setImageResource(datas.get(position).getMenuPic_selected());
				}else{
					viewHodler.image.setImageResource(datas.get(position).getMenuPic());
				}
			} else {
				convertView.setEnabled(false);
				viewHodler.text.setTextColor(mContext.getResources().getColor(android.R.color.holo_red_dark));
			}
			convertView.setTag(R.id.position ,position);

		return convertView;
	}


	public void setDatas(List<ListAttr> datas) {
		this.datas = datas;
		notifyDataSetChanged();
	}

	class ViewHodler{
		private ListViewItemView view;
		private RotateImageView image;
		private TextView text;
	}

	
	
}
