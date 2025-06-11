class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        Map<List<Integer>, Integer> memo = new HashMap<>();
        return dfs(needs, price, special, memo);
    }

    private int dfs(List<Integer> needs, List<Integer> price, List<List<Integer>> special, Map<List<Integer>, Integer> memo) {
        if (memo.containsKey(needs)) {
            return memo.get(needs);
        }

        int cost = 0;
        for (int i = 0; i < needs.size(); i++) {
            cost += needs.get(i) * price.get(i); 
        }

        for (List<Integer> offer : special) {
            List<Integer> dup = new ArrayList<>(needs);
            boolean valid = true;

            for (int i = 0; i < needs.size(); i++) {
                int remain = dup.get(i) - offer.get(i);
                if (remain < 0) {
                    valid = false;
                    break;
                }
                dup.set(i, remain);
            }

            if (valid) {
                int offerCost = offer.get(offer.size() - 1) + dfs(dup, price, special, memo);
                cost = Math.min(cost, offerCost);
            }
        }

        memo.put(needs, cost);
        return cost;
    }
}
