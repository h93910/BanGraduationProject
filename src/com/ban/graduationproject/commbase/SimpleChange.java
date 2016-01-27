package com.ban.graduationproject.commbase;

import java.util.List;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 简单控件改变类\n 如点击一组字体中的一个，改变成一种颜色，其实变成默认或指定的另个颜色\n
 * 如点击一组图片中的一个，改变成一种图片，其实变成默认或指定的另个图片
 * 
 * @author Ban
 * 
 */
public class SimpleChange {
	private Context context;

	private List<TextView> textViewSet;// TextView的集合
	private int defaultColor;// 原始颜色
	private int changeColor;// 要变成的颜色

	private List<ImageView> imageViewSet;// ImageView的集合
	private List<Integer> defaultPic;// 默认图片集合
	private List<Integer> changePic;// 要变成的图片集合

	protected int lastTimePosition = 0;//上回的位置

	/**
	 * 只有改变TextView字体颜色的
	 * 
	 * @param context
	 *            上下文
	 * @param textViewSet
	 *            TextView的集合
	 * @param defaultColor
	 *            原始颜色
	 * @param changeColor
	 *            要变成的颜色
	 */
	public SimpleChange(Context context, List<TextView> textViewSet,
			int defaultColor, int changeColor) {
		this.context = context;
		this.textViewSet = textViewSet;
		this.defaultColor = defaultColor;
		this.changeColor = changeColor;
	}

	/**
	 * 改变TextView连同ImageView
	 * 
	 * @param context
	 *            上下文
	 * @param textViewSet
	 *            TextView的集合
	 * @param defaultColor
	 *            原始颜色
	 * @param changeColor
	 *            要变成的颜色
	 * @param imageViewSet
	 *            ImageView的集合
	 * @param defaultPic
	 *            默认图片集合
	 * @param changePic
	 *            要变成的图片集合
	 */
	public SimpleChange(Context context, List<TextView> textViewSet,
			int defaultColor, int changeColor, List<ImageView> imageViewSet,
			List<Integer> defaultPic, List<Integer> changePic) {
		this.context = context;
		this.textViewSet = textViewSet;
		this.defaultColor = defaultColor;
		this.changeColor = changeColor;
		this.imageViewSet = imageViewSet;
		this.defaultPic = defaultPic;
		this.changePic = changePic;
	}

	/**
	 * 执行变化
	 * 
	 * @param position
	 *            要改变的位置，包括文字集中的位置或图片集中的位置
	 */
	public void executeChange(int position) {
		if (textViewSet != null) {
			changeColor(position);
		}
		if (imageViewSet != null) {
			changePic(position);
		}

		changeOther(position);
		lastTimePosition = position;
	}

	/**
	 * 改变TextView颜色
	 * 
	 * @param position
	 */
	private void changeColor(int position) {
		for (int i = 0; i < textViewSet.size(); i++) {
			if (i == position) {
				continue;
			}
			textViewSet.get(i).setTextColor(
					context.getResources().getColor(defaultColor));
		}
		textViewSet.get(position).setTextColor(
				context.getResources().getColor(changeColor));
	}

	/**
	 * 改变ImageView颜色
	 * 
	 * @param position
	 */
	private void changePic(int position) {
		for (int i = 0; i < imageViewSet.size(); i++) {
			if (i == position) {
				continue;
			}
			imageViewSet.get(i).setImageResource(defaultPic.get(i));
		}
		imageViewSet.get(position).setImageResource(changePic.get(position));
	}

	/**
	 * 做别的变化，空方法，等待重写
	 * 
	 * @param position
	 */
	public void changeOther(int position) {
	}

	public int getSize() {
		if (textViewSet != null) {
			return textViewSet.size();
		}
		return 0;
	}

}
