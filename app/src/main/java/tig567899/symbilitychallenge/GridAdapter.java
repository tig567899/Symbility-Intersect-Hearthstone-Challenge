package tig567899.symbilitychallenge;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter {

    ArrayList<Card> cards;
    Context ctx;
    public GridAdapter(ArrayList<Card> cards, Context ctx)
    {
        this.ctx = ctx;
        this.cards = cards;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.grid_item, viewGroup, false);
        return new CardHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CardHolder ch = (CardHolder)viewHolder;
        Card c = cards.get(i);
        if (c.getImgurl() == null || c.getImgurl().equals(""))
        {
            Glide.with(ctx)
                    .load(R.drawable.placeholder)
                    .into(ch.img);
        }
        else
        {
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder);
            Glide.with(ctx)
                    .load(c.getImgurl())
                    .apply(options)
                    .into(ch.img);
        }

        ch.playerClass.setText(c.getPlayerClass());
        ch.name.setText(c.getName());
        ch.type.setText(c.getType());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class CardHolder extends RecyclerView.ViewHolder
    {
        public ImageView img;
        public TextView name;
        public TextView type;
        public TextView playerClass;
        public CardHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.card_image);
            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
            playerClass = itemView.findViewById(R.id.player_class);
        }
    }
}
