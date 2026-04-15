#include <vector>
#include <algorithm>
#include <unordered_map>
using namespace std;

class Solution {
public:
    vector<int> smallerNumbersThanCurrent(vector<int>& nums) {
        vector<int> sorted = nums;
        sort(sorted.begin(), sorted.end());

        unordered_map<int,int> mp;

        for(int i = 0; i < sorted.size(); i++) {
            if(mp.count(sorted[i]) == 0)
                mp[sorted[i]] = i;
        }

        vector<int> ans;
        for(int num : nums) {
            ans.push_back(mp[num]);
        }

        return ans;
    }
};
