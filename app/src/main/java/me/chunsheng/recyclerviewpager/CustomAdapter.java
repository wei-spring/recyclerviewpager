/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package me.chunsheng.recyclerviewpager;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private int[] mDataSet;
    private boolean isFull;
    private List<Boolean> scale;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private View v;

        public ViewHolder(View v) {
            super(v);
            this.v = v;
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d(TAG, "Element " + getPosition() + " clicked.");
                }
            });
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }

        public View getView() {
            return v;
        }


    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(int[] dataSet, boolean isFull) {
        mDataSet = dataSet;
        this.isFull = isFull;
    }

    public CustomAdapter(int[] dataSet, boolean isFull, List<Boolean> scale) {
        mDataSet = dataSet;
        this.scale = scale;
        this.isFull = isFull;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v;
        if (isFull) {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.image_row_item, viewGroup, false);
        } else {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.text_row_item, viewGroup, false);
        }

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        //Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.imageView.setImageResource(mDataSet[position]);
        if (scale != null && scale.size() > 0)
            if (scale.get(position)) {
                (makeScaleAnimatorSet(viewHolder.getView(), 1, (float) 1.0, 500)).start();
            } else {
                (makeScaleAnimatorSet(viewHolder.getView(), 1, (float) 0.9, 500)).start();
            }
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    private AnimatorSet makeScaleAnimatorSet(View view, float fromScale, float toScale, long duration) {
        AnimatorSet animatorSet = new AnimatorSet();
        List<Animator> animators = makeScaleAnimatorList(view, fromScale, toScale, duration);
        animatorSet.playTogether(animators);
        return animatorSet;
    }

    private List<Animator> makeScaleAnimatorList(View view, float fromScale, float toScale, long duration) {
        List<Animator> result = new ArrayList<>();
        ValueAnimator scaleXAnimation = ObjectAnimator.ofFloat(view, View.SCALE_X,
                fromScale, toScale);
        scaleXAnimation.setInterpolator(new OvershootInterpolator());
        scaleXAnimation.setDuration(duration);
        result.add(scaleXAnimation);

        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(view, View.SCALE_Y,
                fromScale, toScale);
        scaleYAnimation.setInterpolator(new OvershootInterpolator());
        scaleYAnimation.setDuration(duration);
        result.add(scaleYAnimation);
        return result;
    }
}
