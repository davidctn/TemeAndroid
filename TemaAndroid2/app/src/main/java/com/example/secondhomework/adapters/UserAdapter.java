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
import com.example.secondhomework.fragments.FirstFragment;
import com.example.secondhomework.interfaces.OnUserItemClick;
import com.example.secondhomework.models.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<User> users;
    private OnUserItemClick onUserItemClick;

    private int previousExpandedPosition = -1;
    private int mExpandedPosition = -1;

    public UserAdapter(ArrayList<User> users){
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_user,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);
        ImageView arrow = holder.itemView.findViewById(R.id.arrow_image);
        //on item click
        final boolean isExpanded = position == mExpandedPosition;
        holder.setVisibility(isExpanded);

        if (isExpanded){
            previousExpandedPosition = position;
        }
        holder.itemView.setActivated(isExpanded);
        arrow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                mExpandedPosition = isExpanded ? -1:position;
                notifyItemChanged(previousExpandedPosition);
                notifyItemChanged(position);
            }
        });
        //on click
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (onUserItemClick != null)
                {
                    String choosenId = holder.posId;
                    onUserItemClick.moveToSecondFragment(choosenId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView.getContext() instanceof OnUserItemClick){
            onUserItemClick = (OnUserItemClick) recyclerView.getContext();
        }
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private String posId;
        private TextView username;
        private TextView email;
        private ImageView arrow;
        private TextView postTitle;
        private TextView postBody;

        public UserViewHolder(View view){
            super(view);
            username = view.findViewById(R.id.username_id);
            email = view.findViewById(R.id.email_id);
            arrow = view.findViewById(R.id.arrow_image);
            postTitle = view.findViewById(R.id.post_title);
            postBody = view.findViewById(R.id.post_body);
            posId="1";
        }

        public void bind(User user) {
            posId = user.getId();
            username.setText(user.getUsername());
            email.setText(user.getEmail());
            postTitle.setText(user.getPostTitle());
            postBody.setText(user.getPostBody());
            String imageUrl = "https://pngtree.com/freepng/down-glyph-black-icon_4008289.png";
            /*ImageLoader imageLoader = VolleyConfigSingleton.getInstance(arrow.getContext().getApplicationContext()).getImageLoader();
            imageLoader.get(imageUrl, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    arrow.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });*/
        }

        public void setVisibility(boolean vis){
            postTitle.setVisibility(vis?View.VISIBLE:View.GONE);

        }
    }
}
