package com.example.application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    ArrayList<Users> mUserList;
    LayoutInflater inflater;

    public UserAdapter(Context context, ArrayList<Users> users) {
        inflater = LayoutInflater.from(context);
        this.mUserList = users;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardview, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Users selectedUser = mUserList.get(position);
        holder.setData(selectedUser, position);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView userName, password;
        ImageView userImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.userName);
            password = (TextView) itemView.findViewById(R.id.password);
            userImage = (ImageView) itemView.findViewById(R.id.userImage);
        }

        public void setData(Users selectedUser, int position) {
            this.userName.setText(selectedUser.getUsername());
            this.password.setText(selectedUser.getPassword());
            this.userImage.setImageResource(selectedUser.getImage());
        }

        @Override
        public void onClick(View v) {}
    }
}

