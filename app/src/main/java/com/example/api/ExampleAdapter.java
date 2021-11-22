package com.example.api;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<String> mbreedList = new ArrayList<>();
    private OnItemClickListener mlistener;

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_list, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mlistener);
        return evh;
    }

    public ExampleAdapter(ArrayList<String> breedList){
        mbreedList = breedList;

    }

    @Override
    public void onBindViewHolder( ExampleAdapter.ExampleViewHolder holder, int position) {
        holder.mTextView1.setText(mbreedList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("Recycler", " " + mbreedList.size());
        return mbreedList.size();
    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView1;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.dogName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }



    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemDelete(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mlistener = listener;
    }

}
