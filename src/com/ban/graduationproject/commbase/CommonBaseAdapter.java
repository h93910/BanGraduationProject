package com.ban.graduationproject.commbase;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 通用的ListView适配器
 * 
 * @author Ban
 * 
 * @param <T>
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter {
	protected List<T> datas;
	protected Context context;
	protected int resourceId;

	public CommonBaseAdapter(Context context, List<T> datas, int resourceId) {
		this.datas = datas;
		this.context = context;
		this.resourceId = resourceId;

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		if (observer != null) {
			super.unregisterDataSetObserver(observer);
		}
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public T getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public abstract View getView(int position, View convertView,
			ViewGroup parent);

}
