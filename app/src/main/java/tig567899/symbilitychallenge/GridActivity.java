package tig567899.symbilitychallenge;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity implements ApiConnector.ResponseInterface {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        progressBar = findViewById(R.id.loading);

        recyclerView = findViewById(R.id.card_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.cards);
        ApiConnector.get(this);
    }

    @Override
    public void onSuccess() {
        ArrayList<Card> cards = (ArrayList<Card>) ApiConnector.getData();
        Toast.makeText(this, "Successful Connection " + cards.size(), Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);

        gridAdapter = new GridAdapter(cards, this);
        recyclerView.setAdapter(gridAdapter);
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, "Failed Connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
