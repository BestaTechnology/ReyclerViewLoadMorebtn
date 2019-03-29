package net.mysirg.reyclerviewloadmorebtn.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.mysirg.reyclerviewloadmorebtn.Models.ModelClass;
import net.mysirg.reyclerviewloadmorebtn.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter{
    private List<ModelClass> mModelClass;


    public MainAdapter(List<ModelClass> mModelClass) {
        this.mModelClass = mModelClass;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_item, viewGroup, false);

        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {


      int image = mModelClass.get(position).getImage();
      String title = mModelClass.get(position).getTitle();
        ((MyViewHolder)viewHolder).mImageview.setImageResource(image);
        ((MyViewHolder)viewHolder).mTextTitle.setText(title);



    }



    @Override
    public int getItemCount() {
        return mModelClass.size();
    }

    public void searchFilter(String newText) {


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageview;
        TextView mTextTitle;


        public MyViewHolder(View itemView) {

            super(itemView);

            mImageview = itemView.findViewById(R.id.imgView_id);
            mTextTitle = itemView.findViewById(R.id.title_id);
        }
    }
}