package tig567899.symbilitychallenge;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity implements ApiConnector.ResponseInterface, GridAdapter.QueryDeletionInterface {

    private static final String TAG = "GridActivity";
    ProgressBar progressBar;
    RecyclerView recyclerView;
    GridAdapter gridAdapter;
    ImageView search_btn;
    ArrayList<String> queries;
    ArrayList<Card> cards;
    ArrayList<Card> searched_cards;
    boolean searched = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        progressBar = findViewById(R.id.loading);

        recyclerView = findViewById(R.id.card_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        search_btn = findViewById(R.id.search_icon);
        search_btn.setOnClickListener(view -> {
            if (searched)
            {
                showSearchDialog();
            }
        });
        ApiConnector.get(this);
    }

    @Override
    public void onSuccess() {
        cards = (ArrayList<Card>) ApiConnector.getData();
        searched_cards = new ArrayList<>();
        searched_cards.addAll(cards);
        progressBar.setVisibility(View.GONE);
        queries = new ArrayList<>();
        searched = true;

        gridAdapter = new GridAdapter(searched_cards, queries, this, this);
        gridAdapter.setHasStableIds(false);
        recyclerView.setAdapter(gridAdapter);
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, "Failed Connection", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (searched)
        {
            searched_cards.clear();
            searched_cards.addAll(cards);
            Log.d(TAG, "Searched cards size: " + searched_cards.size());

            gridAdapter.notifyDataSetChanged();
            queries.clear();
        }

    }

    private void showSearchDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Search");
        final FrameLayout frameView = new FrameLayout(this);
        builder.setView(frameView);
        View v = LayoutInflater.from(this).inflate(R.layout.searchbox_layout, frameView);
        EditText edit = v.findViewById(R.id.search_box);

        builder.setPositiveButton("Search", (dialogInterface, i) -> {
            String query = edit.getText().toString();
            for (int t = 0; t < queries.size(); ++t)
            {
                if (queries.get(t).equals(query))
                {
                    dialogInterface.dismiss();
                    return;
                }
            }
            queries.add(query);
            filterQueries();
            dialogInterface.dismiss();
        });

        builder.setNegativeButton("Cancel", (dialogInterface, i) ->
        {
            dialogInterface.dismiss();
        });
        builder.show();
    }

    private void filterQueries()
    {
        searched_cards.clear();
        searched_cards.addAll(cards);
        for (int i = 0; i < queries.size(); ++i)
        {
            String query = queries.get(i);
            ArrayList<Card> tempsearch = new ArrayList<>();
            Log.d(TAG, "Query: " + query);
            for (int x = 0; x < searched_cards.size(); ++x)
            {
                int querysize = query.length();
                for (int y = 0; y < searched_cards.get(x).getName().length(); ++y)
                {
                    if (y + querysize > searched_cards.get(x).getName().length())
                    {
                        break;
                    }
                    if (searched_cards.get(x).getName().substring(y, y+querysize).toLowerCase().equals(query.toLowerCase()))
                    {
                        tempsearch.add(searched_cards.get(x));
                    }
                }
            }
            Log.d(TAG, "Searched cards size: " + tempsearch.size());
            searched_cards.clear();
            searched_cards.addAll(tempsearch);
        }
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDelete(String item) {
        for (int i = 0; i < queries.size(); ++i)
        {
            if (queries.get(i).toLowerCase().equals(item.toLowerCase()))
            {
                queries.remove(i);
                filterQueries();
                break;
            }
        }
    }
}
