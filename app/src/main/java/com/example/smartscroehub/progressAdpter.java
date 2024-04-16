package com.example.smartscroehub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class progressAdpter extends RecyclerView.Adapter<progressAdpter.ImageViewHolder> {
    private final Context mContext;
    private final List<progress> mUploads;
    private static OnItemClickListener mlistner;

    public progressAdpter(Context context, List<progress> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.singeprogress, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        progress uploadCurrent = mUploads.get(position);

        String progress1 = uploadCurrent.getProgress().toString();
        holder.textViewName.setText((uploadCurrent.getSubject()));
        holder.textView1.setText( (uploadCurrent.getProgress()));
        holder.progressBar.setProgress(Integer.parseInt(progress1));

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textViewName,textView1,textView2,textView3,textViewbtn;
        public ProgressBar progressBar;

        public ImageViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView28);
            textView1 = itemView.findViewById(R.id.textView30);
            progressBar = itemView.findViewById(R.id.progressBar3);







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
