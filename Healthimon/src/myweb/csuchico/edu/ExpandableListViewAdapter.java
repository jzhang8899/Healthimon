package myweb.csuchico.edu;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

	private String[]groupStrings=null;
	private String[][]childStrings=null;
	private Context context=null;
	
	public ExpandableListViewAdapter(String[]groupStrings,String[][]childStrings,Context context)
	{
		this.groupStrings=groupStrings;
		this.childStrings=childStrings;
		this.context=context;
	}
	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return childStrings[arg0][arg1];
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg0*100+arg1;
	}


	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return childStrings[arg0].length;
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return groupStrings[arg0];
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return groupStrings.length;
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}


	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		// TODO Auto-generated method stub
		
		View view=null;
		if (arg3==null) {
			TextView textView=new TextView(context);
			view=textView;
		}
		else {
			view=arg3;
		}
		
		((TextView)view).setText(childStrings[arg0][arg1]);
		((TextView)view).setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
		return view;
	}
	
	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		
		
		View view=null;
		if (arg2==null) {
			LayoutInflater layoutInflater=LayoutInflater.from(context);
			view=layoutInflater.inflate(R.layout.group_row_add_view, null);
		}else {
			view=arg2;
		}
		
		TextView tv = (TextView) view.findViewById(R.id.exerciseGroup);
		tv.setText(groupStrings[arg0]);	
		return view;
	}

}
