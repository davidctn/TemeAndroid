package com.example.secondhomework.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.secondhomework.R;
import com.example.secondhomework.VolleyConfigSingleton;
import com.example.secondhomework.interfaces.OnUserItemClick;
import com.example.secondhomework.models.Photo;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private ArrayList<Photo> photos;
    private OnUserItemClick onUserItemClick;

    public PhotoAdapter(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_photo,parent,false);
        PhotoViewHolder photoViewHolder = new PhotoViewHolder(view);
        return photoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.bind(photo);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView photoImage;
        private ImageView thumbnail;

        public PhotoViewHolder(View view){
            super(view);
            //get from view
            title = view.findViewById(R.id.photo_title);
            photoImage = view.findViewById(R.id.photo_id);
            thumbnail = view.findViewById(R.id.thumbnail_id);
        }

        public void bind(Photo photo){
            title.setText(photo.getPhotoTitle());
            String imageUrl = photo.getPhotoUrl();
            ImageLoader imageLoader = VolleyConfigSingleton.getInstance(photoImage.getContext().getApplicationContext()).getImageLoader();
            imageLoader.get(imageUrl, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    photoImage.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            imageUrl = photo.getThumbnailUrl();
            ImageLoader thumbnailLoader = VolleyConfigSingleton.getInstance(thumbnail.getContext().getApplicationContext()).getImageLoader();
            thumbnailLoader.get(imageUrl, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    thumbnail.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }

    }
}
