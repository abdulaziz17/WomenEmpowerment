package com.example.admin.womenempowerment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.womenempowerment.Interface.ItemClickListener;
import com.example.admin.womenempowerment.Model.RSSObject;
import com.example.admin.womenempowerment.R;

/**
 * Created by Admin on 3/18/2018.
 */



class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{
    public TextView txtTitle,txtPubDate,txtContent;
    private ItemClickListener itemClickListener;

    public FeedViewHolder(View itemView) {
        super(itemView);
        txtTitle=(TextView) itemView.findViewById(R.id.txtTitle);
        txtPubDate=(TextView) itemView.findViewById(R.id.txtPubDate);
        txtContent=(TextView) itemView.findViewById(R.id.txtContent);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),true);
        return true;
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private RSSObject rssObject;
    private Context mcontext;
    private LayoutInflater inflater;

    public FeedAdapter(RSSObject rssObject, Context mcontext) {
        this.rssObject = rssObject;
        this.mcontext = mcontext;
        inflater=LayoutInflater.from(mcontext);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview=inflater.inflate(R.layout.row,parent,false);
        return new FeedViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        holder.txtTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.txtPubDate.setText(rssObject.getItems().get(position).getPubDate());
        holder.txtContent.setText(rssObject.getItems().get(position).getContent());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick)
            {
                if(!isLongClick)
                {
                    Intent bi=new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink()));
                    mcontext.startActivity(bi);
                }

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return rssObject.items.size();
    }
}
