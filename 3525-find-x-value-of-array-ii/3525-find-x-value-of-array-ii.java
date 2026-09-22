class Solution {
    public int[] resultArray(int[] nums, int k, int[][] queries) {

        int n = nums.length;

        SegmentTree st = new SegmentTree(nums, k);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int idx = queries[q][0];
            int val = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            st.update(idx, val % k);

            SegmentTree.Node res = st.query(start, n - 1);

            int identity = 1 % k;
            ans[q] = (int) res.cnt[identity][x];
        }

        return ans;
    }

    static class SegmentTree {

        class Node {
            int[] trans;
            long[][] cnt;

            Node() {
                trans = new int[k];
                cnt = new long[k][k];
            }
        }

        int n;
        int k;
        Node[] tree;

        SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;

            tree = new Node[4 * n];
            build(1, 0, n - 1, nums);
        }

        Node makeLeaf(int value) {
            value %= k;

            Node node = new Node();

            for (int s = 0; s < k; s++) {
                int to = (s * value) % k;
                node.trans[s] = to;
                node.cnt[s][to] = 1;
            }

            return node;
        }

        Node merge(Node A, Node B) {
            if (A == null) return B;
            if (B == null) return A;

            Node res = new Node();

            for (int s = 0; s < k; s++) {
                res.trans[s] = B.trans[A.trans[s]];
            }

            for (int s = 0; s < k; s++) {
                int afterA = A.trans[s];

                for (int r = 0; r < k; r++) {
                    res.cnt[s][r] =
                            A.cnt[s][r]
                                    + B.cnt[afterA][r];
                }
            }

            return res;
        }

        void build(int idx, int l, int r, int[] nums) {
            if (l == r) {
                tree[idx] = makeLeaf(nums[l]);
                return;
            }

            int mid = (l + r) >> 1;

            build(idx * 2, l, mid, nums);
            build(idx * 2 + 1, mid + 1, r, nums);

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        void update(int pos, int value) {
            update(1, 0, n - 1, pos, value);
        }

        void update(int idx, int l, int r, int pos, int value) {
            if (l == r) {
                tree[idx] = makeLeaf(value);
                return;
            }

            int mid = (l + r) >> 1;

            if (pos <= mid) {
                update(idx * 2, l, mid, pos, value);
            } else {
                update(idx * 2 + 1, mid + 1, r, pos, value);
            }

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        Node query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        Node query(int idx, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) {
                return tree[idx];
            }

            int mid = (l + r) >> 1;

            if (qr <= mid) {
                return query(idx * 2, l, mid, ql, qr);
            }

            if (ql > mid) {
                return query(idx * 2 + 1, mid + 1, r, ql, qr);
            }

            Node left = query(idx * 2, l, mid, ql, qr);
            Node right = query(idx * 2 + 1, mid + 1, r, ql, qr);

            return merge(left, right);
        }
    }
}