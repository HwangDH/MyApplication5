package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class noticeListAdapter extends BaseAdapter {

    private Context context;
    private List<notice> noticeList;

    public noticeListAdapter(Context context, List<notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.notice, null);
        TextView dogname = (TextView) v.findViewById(R.id.dogname);
        TextView dogage = (TextView) v.findViewById(R.id.dogage);
        TextView doggender= (TextView) v.findViewById(R.id.doggender);
        TextView testcheck = (TextView) v.findViewById(R.id.testcheck);
        TextView urinecount = (TextView) v.findViewById(R.id.urinecount);

        dogname.setText(noticeList.get(i).getName());
        dogage.setText(noticeList.get(i).getAge());
        doggender.setText(noticeList.get(i).getGender());
        testcheck.setText(noticeList.get(i).getTest());
        urinecount.setText(noticeList.get(i).getUrine());

        v.setTag(noticeList.get(i).getName());
        return v;
    }

}
