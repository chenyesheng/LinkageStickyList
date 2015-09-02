package cn.ys.linkagestickylist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.ys.linkagestickylist.StickyListHeaders.StickyListHeadersListView;
import cn.ys.linkagestickylist.adapter.FoodsClassAdapter;
import cn.ys.linkagestickylist.adapter.StickyListAdapter;

/**
 * 
 * @author ys
 * @date 2015-9-1
 */
public class MainActivity extends Activity implements
		StickyListHeadersListView.OnHeaderClickListener,
		StickyListHeadersListView.OnStickyHeaderOffsetChangedListener,
		StickyListHeadersListView.OnStickyHeaderChangedListener {

	private ListView mListview;
	private StickyListHeadersListView mStickyListHeadersListView;
	
	private FoodsClassAdapter classAdapter;
	private List<FoodClass> classList = new ArrayList<FoodClass>();
	
	private StickyListAdapter stickyListAdapter;
	private List<FoodInfo> list = new ArrayList<FoodInfo>();

	private String[] foodClass = {"�����ײ�","˫���ײ�","����","���ļ���"};
	
	private String[] foodInfo1 = {"������Ƿ�+��","�������ⷹ+��","������ŷ�+��","�����ŹǷ�+��"};
	private String[] foodInfo2 = {"���˫�˲�+��","����˫�˲�+��","���˫�˲�+��","�Ź�˫�˲�+��"};
	private String[] foodInfo3 = {"�����Ź���","�����Ź���","�����Ź���","������ĭ��"};
	private String[] foodInfo4 = {"±��","��Ѽ��","���","������"};
	
	private boolean isClickClass = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initData();
		initView();
	}

	// ��ʼ������
	private void initView() {
		mListview = (ListView) findViewById(R.id.classlistview);
		classAdapter = new FoodsClassAdapter(MainActivity.this, classList);
		mListview.setAdapter(classAdapter);
		mListview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (currentId == position) {
					return;
				}
				isClickClass = true;
				classAdapter.SetSelect(position);
				classAdapter.notifyDataSetChanged();
				Log.e("", "AA:stickyListAdapter.getSectionForPosition(position)="+stickyListAdapter.getSectionForPosition(stickyListAdapter.getPositionForSection(position)));
				Log.e("", "AA:stickyListAdapter.getPositionForSection(position)="+stickyListAdapter.getPositionForSection(position));
				mStickyListHeadersListView.setSelection(stickyListAdapter.getPositionForSection(position));
			}
		});
		
		stickyListAdapter = new StickyListAdapter(MainActivity.this, list);
		mStickyListHeadersListView = (StickyListHeadersListView) findViewById(R.id.list);

		mStickyListHeadersListView.setOnHeaderClickListener(this);
		mStickyListHeadersListView.setOnStickyHeaderChangedListener(this);
		mStickyListHeadersListView.setOnStickyHeaderOffsetChangedListener(this);
		mStickyListHeadersListView.setDrawingListUnderStickyHeader(true);
		mStickyListHeadersListView.setAreHeadersSticky(true);
		mStickyListHeadersListView.setSelector(R.color.transparent);
		mStickyListHeadersListView.setAdapter(stickyListAdapter);
		mStickyListHeadersListView.getWrappedList().setDivider(new ColorDrawable(getResources().getColor(R.color.gray)));
		mStickyListHeadersListView.getWrappedList().setDividerHeight(2);
	}

	// ��ʼ������
	private void initData() {
		for (int i = 0; i < foodClass.length; i++) {
			FoodClass classInfo = new FoodClass();
			classInfo.setFoodClassId(i);
			classInfo.setFoodClassName(foodClass[i]);
			classList.add(classInfo);
		}
		for (int i = 0; i < classList.size(); i++) {
			String[] array = new String[]{};
			switch (i) {
			case 0:
				array = foodInfo1;
				break;
			case 1:
				array = foodInfo2;
				break;
			case 2:
				array = foodInfo3;
				break;
			case 3:
				array = foodInfo4;
				break;
			default:
				break;
			}
			for (int j = 0; j < array.length; j++) {
				FoodInfo info = new FoodInfo();
				info.setFoodInfoName(array[j]);
				info.setFoodClass(classList.get(i));
				list.add(info);
			}
		}
	}
	
	private int currentId = 0; // ��¼��ǰ��headId
	
	@Override
	public void onStickyHeaderChanged(StickyListHeadersListView l, View header,
			int itemPosition, long headerId) {
		// TODO Auto-generated method stub
		Log.e("", "AA:onStickyHeaderChanged---itemPosition="+itemPosition);
		Log.e("", "AA:onStickyHeaderChanged---headerId="+headerId);
		Log.e("", "AA:onStickyHeaderChanged---currentId="+currentId);
		if (isClickClass) {
			currentId = (int)headerId;
			isClickClass = false;
		} else {
			// ����ʱheadId�ı�����ò�ˢ��listview
			if (currentId != headerId) {
				currentId = (int)headerId;
				classAdapter.SetSelect(currentId);
				classAdapter.notifyDataSetChanged();
			}
		}
	}

	@Override
	public void onStickyHeaderOffsetChanged(StickyListHeadersListView l,
			View header, int offset) {
		// TODO Auto-generated method stub
//		Log.e("", "AA:onStickyHeaderOffsetChanged---offset="+offset);
	}

	@Override
	public void onHeaderClick(StickyListHeadersListView l, View header,
			int itemPosition, long headerId, boolean currentlySticky) {
		// TODO Auto-generated method stub
		Log.e("", "AA:onHeaderClick---itemPosition="+itemPosition);
		Log.e("", "AA:onHeaderClick---headerId="+headerId);
	}

}
