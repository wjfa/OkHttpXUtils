package com.feicui.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.newsapp.R;
import com.feicui.app.adapter.entiry.News;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by Administrator on 2016/11/26.
 * /**
 *  新闻数据适配器
 *
 * 1.holdview  绑定控件三个控件赋值图片为默认

 */

public class NewsAdapter extends MyBaseAdapter<News>{

//    private LoadImage loadImage;
//    private Bitmap defaultBitmap;

    private ListView listView;
    private ImageOptions imageOptions;


    public NewsAdapter(Context context,ListView listView) {
        super(context);
        this.listView=listView;
        imageOptions = new ImageOptions.Builder()
//              .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.ic_accessible_black_24dp)//加载中默认显示图片
                .setFailureDrawableId(R.drawable.ic_accessible_black_24dp)//加载失败后默认显示图片
                .build();

    }

//    private LoadImage.ImageLoadListener listener = new LoadImage.ImageLoadListener() {
//        @Override
//        public void imageLoadOK(Bitmap bitmap, String url) {
//            ImageView icon = null;
//            if(url!=null)
//                icon = (ImageView) listView.findViewWithTag(url);
//            if(icon!=null)
//                icon.setImageBitmap(bitmap);
//        }
//    };
//
//
//    public NewsAdapter(Context context, ListView listView) {
//        super(context);
//        defaultBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_accessible_black_24dp);
//        this.listView = listView;
//        loadImage = new LoadImage(context,listener);
//
//    }


    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        HoldView holdView=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item_list_news,null);
            holdView=new HoldView(convertView);
           convertView.setTag(holdView);
        }else {
          holdView= (HoldView) convertView.getTag();
        }

        News news=getItem(position);
        holdView.tv_title.setText(news.getTitle());
        holdView.tv_content.setText(news.getSummary());
        holdView.tv_time.setText(news.getStamp());

        //设置ImageLoader显示
        String mapUrl=news.getIcon();
        x.image().bind(holdView.img_photo,mapUrl,imageOptions);

//        holdView.img_photo.setImageResource(R.drawable.ic_accessible_black_24dp);
//        holdView.img_photo.setImageBitmap(defaultBitmap);
//        loadImage.getBitmap(news.getIcon(),holdView.img_photo);

        return convertView;
    }

    class HoldView{
        ImageView img_photo;
        TextView tv_title,tv_content,tv_time;
        HoldView(View view){
            img_photo= (ImageView) view.findViewById(R.id.img_1);
            tv_title = (TextView) view.findViewById(R.id.tv_1);
            tv_content = (TextView) view.findViewById(R.id.tv_2);
            tv_time = (TextView) view.findViewById(R.id.tv_3);
        }

    }
}
