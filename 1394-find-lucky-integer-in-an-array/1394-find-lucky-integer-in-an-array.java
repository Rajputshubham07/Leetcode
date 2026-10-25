
class Solution {
    public int findLucky(int[] arr) {
        int[] freq = new int[501];

        // Count frequency of each number
        for (int num : arr) {
            freq[num]++;
        }

        // Find the largest lucky integer
        for (int i = 500; i >= 1; i--) {
            if (freq[i] == i) {
                return i;
            }
        }

        return -1;
    }
}
