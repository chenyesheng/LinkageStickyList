package cn.ys.linkagestickylist.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import cn.ys.linkagestickylist.FoodInfo;
import cn.ys.linkagestickylist.R;
import cn.ys.linkagestickylist.StickyListHeaders.StickyListHeadersAdapter;

/**
 * 
 * @author ys
 * @date 2015-9-1
 */
public class StickyListAdapter extends BaseAdapter implements StickyListHeadersAdapter,
		SectionIndexer {

	private Context mContext;
	private int[] mSectionIndices;
    private String[] mSectionLetters;
	private LayoutInflater mInflater;
	private List<FoodInfo> list;
	
	public StickyListAdapter(Context c, List<FoodInfo> list) {
		this.mContext = c;
		this.list = list;
		mInflater = LayoutInflater.from(c);

		mSectionIndices = getSectionIndices();
        mSectionLetters = getSectionLetters();
	}

	private int[] getSectionIndices() {
    	if(list.size()<=0){
    		 int[] sections = new int[0];
    		 return sections;
    	}
        ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
        String lastFirstChar =list.get(0).getFoodClass().getFoodClassName();
        sectionIndices.add(0);
        for (int i = 1; i < list.size(); i++) {
            if (!list.get(i).getFoodClass().getFoodClassName().equals(lastFirstChar) ) {
                lastFirstChar = list.get(i).getFoodClass().getFoodClassName();
                sectionIndices.add(i);
            }
        }
        int[] sections = new int[sectionIndices.size()];
        for (int i = 0; i < sectionIndices.size(); i++) {
            sections[i] = sectionIndices.get(i);
            Log.e("", "AA:sections["+i+"]----" + sections[i]);
        }
        return sections;
    }

    private String[] getSectionLetters() {
    	String[] letters = new String[mSectionIndices.length];
        for (int i = 0; i < mSectionIndices.length; i++) {
            letters[i] = list.get(mSectionIndices[i]).getFoodClass().getFoodClassName();
            Log.e("", "AA:letters["+i+"]----"+letters[i]);
        }
        return letters;
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.main_item, parent, false);
			holder.text = (TextView) convertView.findViewById(R.id.text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.text.setText(list.get(position).getFoodInfoName());
		
		return convertView;
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return mSectionLetters;
	}
	
	@Override
	public int getPositionForSection(int section) {
		// TODO Auto-generated method stub
		if (mSectionIndices.length == 0) {
            return 0;
        }
        
        if (section >= mSectionIndices.length) {
            section = mSectionIndices.length - 1;
        } else if (section < 0) {
            section = 0;
        }
        return mSectionIndices[section];
	}

	@Override
	public int getSectionForPosition(int position) {
		// TODO Auto-generated method stub
		for (int i = 0; i < mSectionIndices.length; i++) {
            if (position < mSectionIndices[i]) {
                return i - 1;
            }
        }
        return mSectionIndices.length - 1;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		HeaderViewHolder holder;

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = mInflater.inflate(R.layout.main_item, parent, false);
            holder.headText = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        // set header text as first char in name
      //  CharSequence headerChar = mCountries[position].subSequence(0, 1);
        holder.headText.setText(list.get(position).getFoodClass().getFoodClassName());
        holder.headText.setBackgroundResource(R.color.red);
        holder.headText.setTextColor(Color.WHITE);

        return convertView;
	}

//    Remember that these have to be static, postion=1 should always return the same Id that is.
	@Override
	public long getHeaderId(int position) {
		// return the first character of the country as ID because this is what
        // headers are based upon
		return list.get(position).getFoodClass().getFoodClassId();
	}

	class HeaderViewHolder {
        TextView headText;
    }
	
	class ViewHolder {
		TextView text;
	}

}
