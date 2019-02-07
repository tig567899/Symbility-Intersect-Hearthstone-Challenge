package tig567899.symbilitychallenge;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static final String TAG = "GridAdapter";
    private ArrayList<Card> cards;
    private ArrayList<String> queries;
    private Context ctx;
    private QueryDeletionInterface qdi;

    public GridAdapter(ArrayList<Card> cards, ArrayList<String> queries, Context ctx, QueryDeletionInterface qdi)
    {
        this.ctx = ctx;
        this.queries = queries;
        this.cards = cards;
        this.qdi = qdi;
    }
    @NonNull
    @Override
    public GenericHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        if (i == 0)
        {
            v = LayoutInflater.from(ctx).inflate(R.layout.query_item, viewGroup, false);
            return new QueryHolder(v);
        }
        else if (i == 1)
        {
            v = LayoutInflater.from(ctx).inflate(R.layout.blank_item, viewGroup, false);
            return new BlankHolder(v);
        }
        else
        {
            v = LayoutInflater.from(ctx).inflate(R.layout.grid_item, viewGroup, false);
            return new CardHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        GenericHolder holder = (GenericHolder) viewHolder;
        holder.bindViews(i);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < queries.size())
        {
            return 0;
        }
        else if (position == queries.size() && position %2 == 1)
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return cards.size() + queries.size();
    }

    public class GenericHolder extends RecyclerView.ViewHolder {
        public GenericHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bindViews(int position){};
    }

    public class CardHolder extends GenericHolder
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

        @Override
        public void bindViews(int pos) {
            int i = pos-queries.size();
            if (queries.size() %2 == 1)
            {
                i--;
            }
            super.bindViews(i);
            Card c = cards.get(i);
            if (c.getImgurl() == null || c.getImgurl().equals(""))
            {
                Glide.with(ctx)
                        .load(R.drawable.placeholder)
                        .into(img);
            }
            else
            {
                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder);
                Glide.with(ctx)
                        .load(c.getImgurl())
                        .apply(options)
                        .into(img);
            }

            playerClass.setText(c.getPlayerClass());
            name.setText(c.getName());
            type.setText(c.getType());
        }
    }

    public class QueryHolder extends GenericHolder{
        TextView query;
        ImageView clear_button;
        public QueryHolder(@NonNull View itemView) {
            super(itemView);
            query = itemView.findViewById(R.id.query_text);
            clear_button = itemView.findViewById(R.id.clear);

            clear_button.setOnClickListener(view -> {
                qdi.onDelete(query.getText().toString());
            });
        }

        @Override
        public void bindViews(int position) {
            super.bindViews(position);
            query.setText(queries.get(position));
        }
    }

    public class BlankHolder extends GenericHolder
    {
        BlankHolder(View itemView) {
            super(itemView);
        }
    }

    public interface QueryDeletionInterface
    {
        void onDelete(String item);
    }
}
