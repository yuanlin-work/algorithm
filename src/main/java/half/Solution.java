package half;

/**
 * 二分查找
 */
public class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums.length==0)throw new IllegalArgumentException("arr can not be empty");
        int start = 0,end = nums.length;
        int k = 0;
        while (end - start > 0){
            k = (end - start)/2 + start;
            if(nums[k] == target){
                return k;
            }else if(nums[k] > target){
                end = k;
            }else {
                start = k + 1;
            }
        }
        return start;
    }
}
