class Solution {
    public int reverseDegree(String s) {
        int sum = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            int value= 'z'-c+1;
            int position = i+1;
            int product = value*position;
            sum += product;

        }
        return sum;
    }
}