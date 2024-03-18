package com.yangna.lbdsp.book.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.request.RequestOptions;
import com.yangna.lbdsp.book.bean.HnBook;
import com.yangna.lbdsp.R;
import com.yangna.lbdsp.common.base.ListBaseAdapter;
import com.yangna.lbdsp.common.manager.UIManager;
import com.yangna.lbdsp.common.utils.TextStyleUtils;
import com.yangna.lbdsp.mall.impl.ClickListener;
import com.yangna.lbdsp.mall.view.CommentsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 书籍管理adapter
 */
public class BookListAdapter extends ListBaseAdapter<HnBook> {

    private TextStyleUtils.TextStyle ts = new TextStyleUtils.TextStyle(ContextCompat.getColor(context, R.color.text_color_black), 10);

    private ClickListener mClickListener;

    private List<Map<String,Object>> bookList=new ArrayList<>();

    public BookListAdapter(Context context, ClickListener clickListener) {
        super(context);
        mClickListener = clickListener;
    }

//    public BookListAdapter(Context context, List<Map<String,Object>> bookList) {
//        super(context);
//        this.bookList =bookList;
////        notifyDataSetChanged();
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

//        final HnBook hnBook = this.bookList.get(position);
        final Map<String,Object> map = this.bookList.get(position);

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_book_list_item, null);
            holder = new ViewHolder();

            holder.name_tv = convertView.findViewById(R.id.name_tv);
            holder.title_tv = convertView.findViewById(R.id.title_tv);
            holder.author_tv = convertView.findViewById(R.id.author_tv);
            holder.goods_img = convertView.findViewById(R.id.goods_img);
            holder.sbn_tv = convertView.findViewById(R.id.sbn_tv);


            holder.ivIcon = convertView.findViewById(R.id.ivIcon);
            holder.edit_btn = convertView.findViewById(R.id.edit_btn);
            holder.delete_btn = convertView.findViewById(R.id.delete_btn);
//            holder.three = convertView.findViewById(R.id.three);
//            holder.four = convertView.findViewById(R.id.four);
            holder.publish_time = convertView.findViewById(R.id.publish_time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.log);
//        Glide.with(context).asDrawable().load(hnBook.getImageUrl()).apply(options).into(holder.goods_img);
//
//        Glide.with(context).asDrawable().load(hnBook.getShoplogo()).apply(options).into(holder.ivIcon);

        if (map.get("publishtime") != null) {
            holder.publish_time.setText(map.get("publishtime").toString());
        }

        if (map.get("bookname") != null) {
            holder.name_tv.setText(map.get("bookname").toString());
        } else {
            holder.name_tv.setText("暂时没有店铺的名字");
        }

        if (map.get("title") != null) {
            holder.title_tv.setText(map.get("title").toString());
        } else {
            holder.title_tv.setText("商品内容出错");
        }

        if (map.get("author") != null) {
            holder.author_tv.setText(map.get("author").toString());
        } else {
            holder.title_tv.setText("商品内容出错");
        }

        if (map.get("sbn") != null) {
            holder.sbn_tv.setText(map.get("sbn").toString());
        } else {
            holder.title_tv.setText("商品内容出错");
        }


        //  编辑事件
            holder.edit_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String, Object> editMap = new HashMap<>();
                    editMap.put("id", map.get("id"));
                    UIManager.switcher(context, editMap, CommentsActivity.class);
                }
            });

        //  删除事件
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> deleteMap = new HashMap<>();
                deleteMap.put("id", map.get("id"));
                UIManager.switcher(context, deleteMap, CommentsActivity.class);
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView sbn_tv, title_tv, name_tv, author_tv, time_tv, publish_time;
        ImageView goods_img, ivIcon;
        Button pingjia, edit_btn, delete_btn, three, four;
    }

}

