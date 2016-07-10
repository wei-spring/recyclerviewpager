package me.chunsheng.recyclerviewpagerlib;

/**
 * @author carl
 */
public interface SnappyScrollCalculator {
    int computeScrollToItemIndex(int velocityX, int velocityY);
}
