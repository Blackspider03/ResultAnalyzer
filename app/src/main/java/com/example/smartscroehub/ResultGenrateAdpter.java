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


public class ResultGenrateAdpter extends RecyclerView.Adapter<ResultGenrateAdpter.ImageViewHolder> {
    private final Context mContext;
    private final List<basicstud> mUploads;
    private static OnItemClickListener mlistner;

    public ResultGenrateAdpter(Context context, List<basicstud> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.singlestudent, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        basicstud uploadCurrent = mUploads.get(position);

        holder.textViewName.setText((uploadCurrent.getStudent_prn()));
        holder.textView1.setText( (uploadCurrent.getStudent_name()));
        holder.textView2.setText((uploadCurrent.getStudent_branch()));

        String studname = uploadCurrent.getStudent_name().toString();
        String prn1 = uploadCurrent.getStudent_prn().toString();
        String email = uploadCurrent.getStudent_email().toString();
        String mobile = uploadCurrent.getStudent_mobile().toString();
        String branch = uploadCurrent.getStudent_branch().toString();
        String year = uploadCurrent.getStudent_year().toString();


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,resultgenratorstep2.class);

                intent.putExtra("m1",studname);
                intent.putExtra("m2",prn1);
                intent.putExtra("m3",year);

                ////////
                intent.putExtra("m4",branch);


                mContext.startActivity(intent);
            }
        });


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
            textViewName = itemView.findViewById(R.id.sprn);
            textView1 = itemView.findViewById(R.id.sname);
            textView2 = itemView.findViewById(R.id.sbranch);
            cardView = itemView.findViewById(R.id.singlecard);







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
