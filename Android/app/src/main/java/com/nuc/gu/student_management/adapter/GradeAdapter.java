package com.nuc.gu.student_management.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nuc.gu.student_management.R;
import com.nuc.gu.student_management.bean.GradeBean;

import java.util.List;

/**
 * Created by GU on 2016/10/11.
 */
public class GradeAdapter extends BaseAdapter {

    private Context context;
    private List<GradeBean> list;
    //构造方法
    public GradeAdapter(Context context,List<GradeBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            holder.courseNumber = (TextView) convertView.findViewById(R.id.tv_courseNumber);
            holder.courseName = (TextView) convertView.findViewById(R.id.tv_courseName);
            holder.courseTime = (TextView) convertView.findViewById(R.id.tv_courseTime);
            holder.courseGrade = (TextView) convertView.findViewById(R.id.et_courseGrade);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();

        }
        holder.courseNumber.setText(list.get(position).getNumber());
        holder.courseName.setText(list.get(position).getName());
        holder.courseTime.setText(list.get(position).getTime());
        holder.courseGrade.setText(list.get(position).getGrade());

        convertView.setTag(holder);
        return convertView;

    }

    static class ViewHolder{
        private TextView courseNumber;
        private TextView courseName;
        private TextView courseTime;
        private TextView courseGrade;

    }
}
