package com.login_page.hp.contacts;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.RecyclerViewHolder>{

    List<contacts> contact;
    public RecyclerAdapter(List<contacts> contact){
        this.contact = contact;
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main,parent,false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        contacts c=contact.get(position);
        holder.tv.setText(c.getName());
        holder.tv2.setText(c.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contact.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView tv,tv2;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_contact_name);
            tv2=itemView.findViewById(R.id.tv_contact_no);
        }
    }
}
