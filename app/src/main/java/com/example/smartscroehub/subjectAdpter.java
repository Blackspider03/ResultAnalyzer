package com.example.smartscroehub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class subjectAdpter extends RecyclerView.Adapter<subjectAdpter.ImageViewHolder> {
    private final Context mContext;
    private final List<basicstud> mUploads;
    private static OnItemClickListener mlistner;

    public subjectAdpter(Context context, List<basicstud> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.singlesubject, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        basicstud uploadCurrent = mUploads.get(position);

        holder.textViewName.setText((uploadCurrent.getStudent_prn()));
        holder.textView1.setText( (uploadCurrent.getStudent_name()));
        holder.textView2.setText((uploadCurrent.getStudent_branch()));






    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textViewName,textView1,textView2,textView3,textViewbtn;
        public ImageView imageView;
        public LinearLayout linearLayout;
        public CardView cardView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.singlesub);







            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mlistner !=null){
                int position = getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION){
                    mlistner.onItemClick(position);
                }
            }
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
        void buyClick(int position);
        void cartClick(int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mlistner = listener;
    }
}
