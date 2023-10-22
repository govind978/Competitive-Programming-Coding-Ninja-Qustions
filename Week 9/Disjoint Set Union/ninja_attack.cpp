/*
    Time complexity: O(N)
    Space complexity: O(N)

    Where  N represents the size of array.
*/

vector<int> parent;

void makeSet()
{
    for (int i = 0; i < parent.size(); i++)
    {
        parent[i] = i;
    }
}

int findSet(int a)
{
    return (a == parent[a]) ? a : (parent[a] = findSet(parent[a]));
}

void unionSet(int a, int b)
{
    a = findSet(a), b = findSet(b);
    if (a != b)
    {
        parent[a] = b;
    }
}

int calc(vector<int> &a, vector<int> &b)
{
    int dis = 0;
    unordered_map<int, int> cnt;

    for (auto val : a)
    {
        cnt[val] += 1;
    }

    for (auto val : b)
    {
        if (cnt[val] > 0)
        {
            cnt[val] -= 1;
            dis += 1;
        }
    }
    return a.size() - dis;
}

int ninjaAttack(vector<int> ninja, vector<int> enemies, vector<vector<int>> allowedSwaps)
{

    int n = ninja.size(), dis = 0;
    vector<vector<int>> setWiseNinja(n, vector<int>()), setWiseEnemies(n, vector<int>());
    parent.assign(n, 0);
    makeSet();

    for (auto s : allowedSwaps)
    {
        unionSet(s[0], s[1]);
    }

    // arrange the elements set wise
    for (int i = 0; i < n; i++)
    {
        int j = findSet(i);
        setWiseNinja[j].push_back(ninja[i]);
        setWiseEnemies[j].push_back(enemies[i]);
    }

    // go over to each set and compare
    for (int i = 0; i < n; i++)
    {
        dis += calc(setWiseNinja[i], setWiseEnemies[i]);
    }

    return dis;
}
