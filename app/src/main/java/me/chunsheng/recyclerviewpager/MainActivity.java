package me.chunsheng.recyclerviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import me.chunsheng.recyclerviewpagerlib.SnappingSwipingViewBuilder;
import me.chunsheng.recyclerviewpagerlib.SnappyLinearLayoutManager;
import me.chunsheng.recyclerviewpagerlib.SnappyRecyclerView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rlContainOne;
    RelativeLayout rlContainTwo;
    RelativeLayout rlContainThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlContainOne = (RelativeLayout) findViewById(R.id.rlContainOne);
        rlContainTwo = (RelativeLayout) findViewById(R.id.rlContainTwo);
        rlContainThree = (RelativeLayout) findViewById(R.id.rlContainThree);


        //--------------One-------------------
        final List<Boolean> scale = new ArrayList();
        scale.add(false);
        scale.add(true);
        scale.add(false);
        final CustomAdapter mAdapter = new CustomAdapter(new int[]{R.drawable.chuyinweilai1, R.drawable.chuyinweilai2, R.drawable.chuyinweilai3}, true, scale);

        SnappyRecyclerView recyclerView = new SnappingSwipingViewBuilder(this)
                .setAdapter(mAdapter)
                .setHeadTailExtraMarginDp(17F)
                .setItemMarginDp(5F, 0F, 5F, 0F)
                .setSnapMethod(SnappyLinearLayoutManager.SnappyLinearSmoothScroller.SNAP_CENTER)
                .build();

        recyclerView.scrollToPosition(1);

        recyclerView.smoothScrollToPosition(1);

        recyclerView.setOnItemSelectLitener(new SnappyRecyclerView.OnItemSelectLitener() {
            @Override
            public void onItemSelect(int position) {
                scale.clear();
                for (int i = 0; i < 3; i++) {
                    if (i == position) {
                        scale.add(true);
                    } else {
                        scale.add(false);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        if (rlContainOne != null) {
            recyclerView.setLayoutParams(new ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            rlContainOne.addView(recyclerView);
        }
        //--------------One-------------------


        //--------------Two-------------------
        final CustomAdapter mAdapterTwo = new CustomAdapter(new int[]{R.drawable.chuyinweilai1, R.drawable.chuyinweilai2, R.drawable.chuyinweilai3}, false);

        SnappyRecyclerView recyclerViewTwo = new SnappingSwipingViewBuilder(this)
                .setAdapter(mAdapterTwo)
                .setHeadTailExtraMarginDp(7F)
                .setSnapMethod(SnappyLinearLayoutManager.SnappyLinearSmoothScroller.SNAP_CENTER)
                .build();

        recyclerViewTwo.scrollToPosition(1);

        recyclerViewTwo.smoothScrollToPosition(1);

        recyclerViewTwo.setOnItemSelectLitener(new SnappyRecyclerView.OnItemSelectLitener() {
            @Override
            public void onItemSelect(int position) {
//                scale.clear();
//                for (int i = 0; i < 3; i++) {
//                    if (i == position) {
//                        scale.add(true);
//                    } else {
//                        scale.add(false);
//                    }
//                }
//                mAdapter.notifyDataSetChanged();
            }
        });

        if (rlContainTwo != null) {
            recyclerViewTwo.setLayoutParams(new ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            rlContainTwo.addView(recyclerViewTwo);
        }
        //--------------Two-------------------


    }
}
