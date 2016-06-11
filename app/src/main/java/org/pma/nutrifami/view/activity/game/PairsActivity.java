package org.pma.nutrifami.view.activity.game;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.pma.nutrifami.R;
import org.pma.nutrifami.model.unit.PairUnit;
import org.pma.nutrifami.view.adapter.PairDataAdapter;
import org.pma.nutrifami.view.listener.PairClickListener;
import org.pma.nutrifami.view.viewholder.PairLogicResolver;

import java.util.Map;

public class PairsActivity extends GameActivity implements PairClickListener {
    private PairDataAdapter mLeftDataAdapter;
    private PairDataAdapter mRightDataAdapter;
    private Map<String, Integer> mAnswerMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairs);
        setTitle(getString(R.string.game_pairs_title));

        final RecyclerView leftRecyclerView = (RecyclerView) findViewById(R.id.pairs_left_recycler_view);
        final RecyclerView rightRecyclerView = (RecyclerView) findViewById(R.id.pairs_right_recycler_view);
        assert leftRecyclerView != null;
        assert rightRecyclerView != null;

        final PairLogicResolver pairLogicResolver = new PairLogicResolver();
        final PairDataAdapter[] dataAdapters = pairLogicResolver.initializePairDataAdapters(this, (PairUnit) getCurrentUnit());

        this.mAnswerMap = pairLogicResolver.getAnswerMap();
        this.mLeftDataAdapter = dataAdapters[0];
        this.mRightDataAdapter = dataAdapters[1];

        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        leftRecyclerView.setAdapter(this.mLeftDataAdapter);
        rightRecyclerView.setAdapter(this.mRightDataAdapter);
    }

    @Override
    public void onClick(String pairPart) {
        final PairLogicResolver pairLogicResolver = new PairLogicResolver();
        pairLogicResolver.switchPartState(pairPart, this.mLeftDataAdapter, this.mRightDataAdapter, this.mAnswerMap);
        this.mLeftDataAdapter.notifyDataSetChanged();
        this.mRightDataAdapter.notifyDataSetChanged();
        if (pairLogicResolver.areAllCorrect(this.mLeftDataAdapter)) {
            finish();
        }
    }
}
