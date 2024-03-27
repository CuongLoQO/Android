package com.example.btllogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserviewHolder> {

    private Context mContex;
    private List<User> mListUser;

    public UserAdapter(Context mContex) {
        this.mContex = mContex;
    }
    public void setdata(List<User> list){
        this.mListUser=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new UserviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserviewHolder holder, int position) {
        User user =mListUser.get(position);
        if(user==null){
            return;
        }
        holder.imageView.setImageURI(user.getResouceImage());
        holder.tvName.setText(user.getName());
        holder.price.setText(user.getPrice());
    }

    @Override
    public int getItemCount() {
        if(mListUser !=null){
            return  mListUser.size();
        }
        return 0;
    }

    public class UserviewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tvName,price;
        public UserviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_user);
            tvName=itemView.findViewById(R.id.tvName);
            price=itemView.findViewById(R.id.tvPrice);
        }
    }

}
