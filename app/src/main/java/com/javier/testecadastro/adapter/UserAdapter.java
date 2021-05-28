package com.javier.testecadastro.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.javier.testecadastro.R;
import com.javier.testecadastro.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private List<User> userList;

    public UserAdapter(List<User> user ) {
        this.userList = user;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_adapter, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        User user = userList.get(position);
        holder.user.setText( user.getName() );
        holder.date.setText(user.getDataNasc());
        holder.cod.setText(user.getCode());
    }
    @Override
    public int getItemCount() {
        return this.userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user,date,cod;

        public MyViewHolder(View itemView) {
            super(itemView);

            user = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            cod = itemView.findViewById(R.id.cod);
        }
    }


}
