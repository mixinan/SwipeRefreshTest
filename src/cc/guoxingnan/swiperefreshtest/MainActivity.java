package cc.guoxingnan.swiperefreshtest;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements OnRefreshListener{
	private SwipeRefreshLayout swipeLayout;
	private ListView listView;
	private ArrayList<String> data;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		setData();
		initListener();
		
		
	}

	private void initView() {
		swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
		listView = (ListView) findViewById(R.id.listview);
		swipeLayout.setColorSchemeColors(Color.BLUE, Color.RED);
	}

	private void setData() {
		data = new ArrayList<String>();
		for (int i = 'A'; i < 'z'; i++) {
			data.add(i +"£º"+(char)i);
		}
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
				android.R.id.text1, data);
		listView.setAdapter(adapter);
		
	}

	private void initListener() {
		swipeLayout.setOnRefreshListener(this);
	}

	private Handler handler = new Handler();
	
	@Override
	public void onRefresh() {
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				data.add(0, "----------------");
				data.add(0, "ÐÂÊý¾Ý: "+System.currentTimeMillis());
				adapter.notifyDataSetChanged();
				swipeLayout.setRefreshing(false);
			}
		}, 1000);
		
	}

}
