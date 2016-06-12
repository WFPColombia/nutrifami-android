package org.pma.nutrifami.view.activity.game;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.pma.nutrifami.R;
import org.pma.nutrifami.model.unit.PairUnit;
import org.pma.nutrifami.view.adapter.PairDataAdapter;
import org.pma.nutrifami.view.container.PairDataContainer;
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

        leftRecyclerView.setHasFixedSize(true);
        rightRecyclerView.setHasFixedSize(true);
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.mLeftDataAdapter = new PairDataAdapter(this);
        this.mRightDataAdapter = new PairDataAdapter(this);

        leftRecyclerView.setAdapter(this.mLeftDataAdapter);
        rightRecyclerView.setAdapter(this.mRightDataAdapter);

        updateWithCurrentUnit();
    }

    private void updateWithCurrentUnit() {
        final PairLogicResolver pairLogicResolver = new PairLogicResolver();
        final PairDataContainer[][] pairDataContainers = pairLogicResolver.createPairDataContainers((PairUnit) getCurrentUnit());

        this.mAnswerMap = pairLogicResolver.getAnswerMap();
        this.mLeftDataAdapter.updatePairs(pairDataContainers[0]);
        this.mRightDataAdapter.updatePairs(pairDataContainers[1]);
    }

    @Override
    public void onClick(String pairPart) {
        final PairLogicResolver pairLogicResolver = new PairLogicResolver();
        pairLogicResolver.switchPartState(
                pairPart,
                this.mLeftDataAdapter.getDataContainers(),
                this.mRightDataAdapter.getDataContainers(),
                this.mAnswerMap);
        if (pairLogicResolver.areAllCorrect(this.mLeftDataAdapter)) {
            if (isLastUnit()) {
                saveAndFinish(this, getLesson());
            } else {
                increaseUnitCounter();
                updateWithCurrentUnit();
            }
        } else {
            this.mLeftDataAdapter.notifyDataSetChanged();
            this.mRightDataAdapter.notifyDataSetChanged();
        }
    }
}
