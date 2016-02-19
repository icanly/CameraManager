package com.rtmap.cameramanager;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.rtmap.erdoushop.shop.photocrop.Crop;

import java.io.File;
import java.util.ArrayList;

/**
 * @author tongqian.ni
 */
public class AlbumFragment extends Fragment {
    private ArrayList<PhotoItem> photos = new ArrayList<PhotoItem>();
    private int mWidth;

    public AlbumFragment(int width) {
        super();
        this.mWidth=width;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static Fragment newInstance(ArrayList<PhotoItem> photos, int width) {
        Fragment fragment = new AlbumFragment(width);
        Bundle args = new Bundle();
        args.putSerializable("photos", photos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_album, null);
        photos = (ArrayList<PhotoItem>) getArguments().getSerializable("photos");
        albums = (GridView) root.findViewById(R.id.albums);
        albums.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                PhotoItem photoItem = photos.get(arg2);
                Uri uri = photoItem.getImageUri().startsWith("file:") ? Uri.parse(photoItem
                        .getImageUri()) : Uri.parse("file://" + photoItem.getImageUri());
                Uri destination = Uri.fromFile(new File(getActivity().getApplicationContext().getCacheDir(), "cropped"));
                Crop.of(uri, destination).asSquare().start(getActivity());
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        albums.setAdapter(new GalleryAdapter(getActivity(), photos,this.mWidth));
    }

	private GridView albums;
}
