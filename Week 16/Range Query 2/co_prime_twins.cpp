/*
    Time Complexity: O(MAX*log(MAX) + (N + Q)*sqrt(N))
    Space Complexity: O(MAX + Q).

*/

/*
    Function to calculate the reduced form of each number from 1 to maxn.
*/
void preCompute(vector<int> &reduced, int maxn)
{

    // Compute the reduced form using sieve.
    for (int i = 2; i <= maxn; i++)
    {

        // Means that i is a prime.
        if (reduced[i] == 1)
        {

            // Add i to all its multiples.
            for (int j = i; j <= maxn; j += i)
                reduced[j] *= i;
        }
    }
}

/*
    Function to add an element to our range.
*/
void add(int i, vector<int> &cnt, vector<int> &A, int &ans)
{
    ans += cnt[A[i]];
    cnt[A[i]]++;
}

/*
    Function to remove an element to our range.
*/
void remove(int i, vector<int> &cnt, vector<int> &A, int &ans)
{
    cnt[A[i]]--;
    ans -= cnt[A[i]];
}
vector<int> coprimeTwins(vector<int> &A, vector<pair<int, int>> &Queries)
{

    int maxn = *max_element(A.begin(), A.end());
    // Vector to store the reduced form
    vector<int> reduced(maxn + 1, 1);

    // Precompute the reduced form using sieve.
    preCompute(reduced, maxn);

    for (int &x : A)
    {
        // Replace each number with reduced form.
        x = reduced[x];
    }
    // block size set to sqrt(N).
    int block = sqrt(A.size());

    // Array to store the ordering of the queries.
    vector<int> order(Queries.size());
    for (int i = 0; i < order.size(); i++)
    {
        order[i] = i;
    }

    // Sort the order according to the Mo's algorithm order.
    sort(order.begin(), order.end(), [&](int i, int j)
         { return make_pair((Queries[i].first - 1) / block, Queries[i].second) < make_pair((Queries[j].first - 1) / block, Queries[j].second); });

    // Store the frequency of each number.
    vector<int> cnt(maxn + 1);

    // To store the answer for each query.
    vector<int> answer(Queries.size());

    int ans = 0;
    add(0, cnt, A, ans);
    int left = 0, right = 0;

    for (int i : order)
    {
        int L = Queries[i].first - 1, R = Queries[i].second - 1;

        // Add or remove indices to match current interval.
        while (left > L)
            add(--left, cnt, A, ans);
        while (right < R)
            add(++right, cnt, A, ans);
        while (left < L)
            remove(left++, cnt, A, ans);
        while (right > R)
            remove(right--, cnt, A, ans);

        answer[i] = ans;
    }

    return answer;
}