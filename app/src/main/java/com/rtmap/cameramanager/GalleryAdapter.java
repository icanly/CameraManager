package com.rtmap.cameramanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * @author tongqian.ni
 *
 */
public class GalleryAdapter extends BaseAdapter {

    private int mWidth;
    private Context mContext;
    private List<PhotoItem> values;
    public static GalleryHolder holder;

    /**
     * @param albumActivity
     * @param values
     */
    public GalleryAdapter(Context context, List<PhotoItem> values, int width) {
        this.mContext = context;
        this.values = values;
        this.mWidth=width;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final GalleryHolder holder;
        int width = mWidth;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.item_gallery, null);
            holder = new GalleryHolder();
            holder.sample = (ImageView) convertView.findViewById(R.id.gallery_sample_image);
            holder.sample.setLayoutParams(new AbsListView.LayoutParams(width, width));
            convertView.setTag(holder);
        } else {
            holder = (GalleryHolder) convertView.getTag();
        }
        final PhotoItem gallery = (PhotoItem) getItem(position);

        ImageLoaderUtils.displayLocalImage(gallery.getImageUri(), holder.sample,null);
        
        return convertView;
    }

    class GalleryHolder {
        ImageView sample;
    }




}
