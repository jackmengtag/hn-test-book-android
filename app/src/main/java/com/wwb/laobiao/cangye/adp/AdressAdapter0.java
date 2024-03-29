package com.wwb.laobiao.cangye.adp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wwb.laobiao.address.bean.AdressModel;
import com.wwb.laobiao.address.bean.SelAdressModel;
import com.yangna.lbdsp.R;

import java.util.List;

public class AdressAdapter0 extends BaseAdapter {
    private static final String SELSTR ="已设为默认" ;
    private static final String UNSELSTR ="设为默认" ;
    Context mContext; public List<SelAdressModel.Body.record> mlist;
    private LayoutInflater inflater;
    private MsgInterface msgInterface;

    public AdressAdapter0(Context context, List<SelAdressModel.Body.record> mlist) {
        // TODO Auto-generated constructor stub
        mContext=context;
        this.mlist=mlist;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    protected View Findadaptopview(View v, int id) {
        // TODO Auto-generated method stub
        if(v==null)
        {
            return null;
        }
        if(v.getClass().equals(LinearLayout.class))
        {
            if(v.getId()==id)
            {
                return v;
            }
            else
            {
                return	Findadaptopview((View) v.getParent(),id);
            }
        }
        else
        {

            return	Findadaptopview((View) v.getParent(),id);
        }

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();

            view = inflater.inflate(R.layout.adplistview_adress_item, null);
//		    holder.edittext=(EditText)view.findViewById(R.id.editText1);//textView_sel
            holder.textsel=(TextView)view.findViewById(R.id.textView_sel);
            holder.name=(TextView)view.findViewById(R.id.textView_00);
            holder.mobile=(TextView)view.findViewById(R.id.textView_1);
            holder.instructions=(TextView)view.findViewById(R.id.textView_1);
            holder.idex=(TextView)view.findViewById(R.id.textView2);
            holder.deltbt=(Button) view.findViewById(R.id.textView_delet);
            holder.editbt=(Button) view.findViewById(R.id.textView_edit);
            holder.mlay=(LinearLayout)view.findViewById(R.id.lay0);
            holder.mlaysel=(LinearLayout)view.findViewById(R.id.lay_sel_iv);
            holder.imageView=(ImageView) view.findViewById(R.id.imageView_sel);
            holder.deltbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View mv =Findadaptopview(v, R.id.idexkong);
                    if(mv==null)
                    {return;}
                    TextView tvx = (TextView) mv.findViewById(R.id.textView2);
                    try {
                        selectItem=Integer.valueOf(tvx.getText().toString());
//                        notifyDataSetInvalidated();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    if(msgInterface!=null)
                    {
                        msgInterface.onsel(selectItem,2);
                    }
                }
            });
            holder.editbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View mv =Findadaptopview(v, R.id.idexkong);
                    if(mv==null)
                    {return;}
                    TextView tvx = (TextView) mv.findViewById(R.id.textView2);
                    try {
                        selectItem=Integer.valueOf(tvx.getText().toString());
//                        notifyDataSetInvalidated();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    if(msgInterface!=null)
                    {
                        msgInterface.onsel(selectItem,1);
                    }
                }
            });
            holder.idex.setText(""+position);
            holder.idex.setVisibility(view.GONE);  //lay0
            holder.imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    View mv =Findadaptopview(v, R.id.idexkong);
                    TextView tvx = (TextView) mv.findViewById(R.id.textView2);
                    try {
                        selectItem=Integer.valueOf(tvx.getText().toString());
                        notifyDataSetInvalidated();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                   if(msgInterface!=null)
                   {
                       msgInterface.onsel(selectItem,0);
                   }
                }
            });
            holder.mlaysel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    View mv =Findadaptopview(v, R.id.idexkong);
                    TextView tvx = (TextView) mv.findViewById(R.id.textView2);
                    try {
                        selectItem=Integer.valueOf(tvx.getText().toString());
                        notifyDataSetInvalidated();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    if(msgInterface!=null)
                    {
                        msgInterface.onsel(selectItem,0);
                    }
                }
            });
            holder.mlay.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    View mv =Findadaptopview(v, R.id.idexkong);
                    TextView tvx = (TextView) mv.findViewById(R.id.textView2);
                    try {
                        selectItem=Integer.valueOf(tvx.getText().toString());
                        notifyDataSetInvalidated();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    if(msgInterface!=null)
                    {
                        msgInterface.onsel(selectItem,0);
                    }
                }
            });
            view.setTag(holder);

        }else {
            holder = (Holder) view.getTag();
        }
        if( mlist.size()>0)
        {
            holder.name.setText(mlist.get(position).getname());
            holder.mobile.setText(mlist.get(position).getmobile());
            holder.instructions.setText(mlist.get(position).getaddress());
            holder.idex.setText(""+position);
//		    holder.watertime.setText(mlist.get(position).timestr);  listbacksel0
        }
        final int fposition=position;
        if (position == selectItem) {
//	    	 convertView
//	    	 view.performClick();
//	    	 view.setBackgroundColor(Mycolor.Gback2);
//	     	 holder.watertext.setTextColor(Mycolor.GText1);
            holder.mlaysel.setBackgroundResource(R.drawable.bg_f52d2d_r_22);
            holder.textsel.setText(SELSTR);
            holder.textsel.setTextColor(R.drawable.bg_f52d2d_r_22);
//            holder.imageView.setBackgroundResource(R.drawable.y_xingxing);
        }
        else {
//	    	 view.setBackgroundColor(Mycolor.Yd152Anysys);
//	     	holder.watertext.setTextColor(Mycolor.GText0);
//	     	view.setBackground(background);
           // view.setBackgroundResource(R.drawable.bg_white_5radius);
            holder.mlaysel.setBackgroundResource(R.drawable.bg_white_5radius);
            holder.textsel.setText(UNSELSTR);//@color/black
//            holder.textsel.setTextColor(R.drawable.black_background);
            holder.textsel.setTextColor(Color.BLACK);
//            holder.imageView.setBackgroundResource(R.drawable.w_xingxing);

        }

        return view;


    }

    public  int setSelectItem(int selectItem) {
        if(this.selectItem!=selectItem)
        {
            this.selectItem = selectItem;
            return 1;
        }
        return 0;
    }
    private int  selectItem=-1;

    public void setinterface(MsgInterface msgInterface) {
        this.msgInterface=msgInterface;
    }
//    private cbSeleAdapter1 mcbSeleAdapter1;

    public static class Holder{
        TextView name;
        TextView mobile;
        TextView instructions;
        //		EditText edittext;
        TextView textsel;
        TextView idex;
        LinearLayout mlay;
        LinearLayout mlaysel;

        Button editbt;
        Button deltbt;
        ImageView imageView;


    }

    public int getcurlsel() {
        // TODO Auto-generated method stub
        return selectItem;
    }
    public void clear() {
        // TODO Auto-generated method stub
        mlist.clear();
        notifyDataSetChanged();
    }
    public boolean remove(int getcurlsel) {
        // TODO Auto-generated method stub
        if((getcurlsel<mlist.size())&&(getcurlsel>=0))
        {
            mlist.remove(getcurlsel);
//		  if(mcbSeleAdapter1!=null)
//		  {
//			  mcbSeleAdapter1.onsel(3,getcurlsel,null);
//		  }
            notifyDataSetInvalidated();
            return true;
        }
        return false;
    }
//    public void setcb(cbSeleAdapter1 mcbSeleAdapter1) {
//        // TODO Auto-generated method stub
//        this.mcbSeleAdapter1=mcbSeleAdapter1;
//    }

    @Override
    public boolean isEnabled(int position) {
        // TODO Auto-generated method stub
        return super.isEnabled(position);
//	 return getItemViewType(position) != VIEW_TYPE_CATEGORY;
    }
    public interface MsgInterface {
        void onsel(int selectItem, int flat);
    }
}
