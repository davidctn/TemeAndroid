package com.example.secondhomework.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondhomework.R;
import com.example.secondhomework.interfaces.OnUserItemClick;
import com.example.secondhomework.models.Album;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private ArrayList<Album> albums;
    private OnUserItemClick onUserItemClick;

    public AlbumAdapter(ArrayList<Album> albums){
        this.albums = albums;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_album,parent,false);
        AlbumViewHolder albumViewHolder = new AlbumViewHolder(view);
        return albumViewHolder;
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.bind(album);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (onUserItemClick != null){
                    String choosenPosition = holder.albumId;
                    onUserItemClick.moveToThirdFragment(choosenPosition);
                }
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView.getContext() instanceof OnUserItemClick){
            onUserItemClick = (OnUserItemClick) recyclerView.getContext();
        }
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder{
        private String albumId;
        private TextView albumTitle;


        public AlbumViewHolder(View view){
            super(view);
            albumId = "1";
            //get from layout
            albumTitle = view.findViewById(R.id.album_title);
        }

        public void bind(Album album){
            albumTitle.setText(album.getTitle());
            albumId = album.getId();
        }
    }
}
