package cn.ys.linkagestickylist.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.ys.linkagestickylist.FoodClass;
import cn.ys.linkagestickylist.R;

/**
 * 
 * @author ys
 * @date 2015-9-1
 */
public class FoodsClassAdapter extends BaseAdapter {
	private Context context;
	private List<FoodClass> data;
	private int Select=0;
	
	public FoodsClassAdapter(Context c, List<FoodClass> list) {
		context = c;
		data = list; 
	}

	public void SetSelect(int select){
		Select=select;	
	}
	public int GetSelect(){
		return Select;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mViewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.main_item, null);
			mViewHolder = new ViewHolder();
			mViewHolder.tv_title = (TextView) convertView
					.findViewById(R.id.text);

			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		
		Log.e("", "A:getView---position="+position);
		FoodClass info = data.get(position);
		mViewHolder.tv_title.setText(info.getFoodClassName());
		if(position == Select){
			mViewHolder.tv_title.setBackgroundResource(R.color.gray);
			mViewHolder.tv_title.setTextColor(Color.WHITE);
		}else{
			mViewHolder.tv_title.setBackgroundResource(R.color.white);
			mViewHolder.tv_title.setTextColor(Color.BLACK);
		}
		
		
		return convertView;
	}

	static class ViewHolder {
		TextView tv_title;
	}

}
