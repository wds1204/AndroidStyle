package com.example.app_style;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectorActivity extends Activity {

    @Bind(R.id.btn_default)
    Button btnDefault;
    @Bind(R.id.btn_disable)
    Button btnDisable;
    @Bind(R.id.btn_selected)
    Button btnSelected;
    @Bind(R.id.btn_activate)
    Button btnActivate;
    @Bind(R.id.list)
    ListView list;

    private ArrayList<String> mArrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        ButterKnife.bind(this);
        getData();
        list.setAdapter(new MyAdapter());
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SelectorActivity.this, "Item Click on " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.btn_default, R.id.btn_disable, R.id.btn_selected, R.id.btn_activate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_default:
                break;
            case R.id.btn_disable:
                break;
            case R.id.btn_selected:
                btnSelected.setText(btnSelected.isSelected() ? "未选中" : "已选中");
                btnSelected.setSelected(btnSelected.isSelected() ? false : true);
                break;
            case R.id.btn_activate:
                btnActivate.setText(btnActivate.isActivated() ? "未激活" : "已激活");
                btnActivate.setActivated(btnActivate.isActivated() ? false : true);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }


    private ArrayList<String> getData() {
        mArrayList.add("测试数据0");
        mArrayList.add("测试数据1");
        mArrayList.add("测试数据2");
        mArrayList.add("测试数据3");
        mArrayList.add("测试数据4");
        mArrayList.add("测试数据5");
        return mArrayList;
    }

    class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        @Override
        public int getCount() {
            inflater = LayoutInflater.from(SelectorActivity.this);
            return mArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return mArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_list, parent, false);
                holder = new ViewHolder();
                holder.titleTxt = (TextView) convertView.findViewById(R.id.txt_title);
                holder.button = (Button) convertView.findViewById(R.id.btn);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.titleTxt.setText(mArrayList.get(position));
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SelectorActivity.this, "Button " + position + " click", Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }

        class ViewHolder {
            TextView titleTxt;
            Button button;
        }
    }
}
