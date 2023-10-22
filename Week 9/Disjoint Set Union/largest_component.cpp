/*

    Time Complexity: O(N * sqrt(Arr[i]))

    Space complexity: O(N + max(Arr[i]))



    Where N is the size of the given array.

*/

int findSet(int v, vector<int> &parent)
{

    if (v == parent[v])
    {

        return v;
    }

    parent[v] = findSet(parent[v], parent);

    return parent[v];
}

void makeSet(int v, vector<int> &parent, vector<int> &componentSize)
{

    parent[v] = v;

    componentSize[v] = 1;
}

void unionSets(int a, int b, vector<int> &parent, vector<int> &componentSize)
{

    a = findSet(a, parent);

    b = findSet(b, parent);

    if (a != b)
    {

        if (componentSize[a] < componentSize[b])
        {

            swap(a, b);
        }

        parent[b] = a;

        componentSize[a] += componentSize[b];
    }
}

vector<int> findPrimeFactors(int val)
{

    vector<int> pf;

    for (int i = 2; i * i <= val; i++)
    {

        if (val % i == 0)
        {

            while (val % i == 0)
            {

                val /= i;
            }

            pf.push_back(i);
        }
    }

    if (val > 1)
    {

        pf.push_back(val);
    }

    return pf;
}

int largestComponent(vector<int> &arr, int n)
{

    // parent: Stores the parent of the set.

    // componentSize: Stores the size of the set.

    vector<int> parent(n), componentSize(n);

    for (int i = 0; i < n; i++)
    {

        makeSet(i, parent, componentSize);
    }

    // Finding prime factors.

    vector<int> pf[n];

    for (int i = 0; i < n; i++)
    {

        pf[i] = findPrimeFactors(arr[i]);
    }

    // Finding maximum element in the array.

    int maxElement = 0;

    for (int i = 0; i < n; i++)
    {

        maxElement = max(maxElement, arr[i]);
    }

    vector<int> isPresent(maxElement + 1, -1);

    for (int i = 0; i < n; i++)
    {

        for (int j = 0; j < pf[i].size(); j++)
        {

            int x = pf[i][j];

            if (isPresent[x] != -1)
            {

                unionSets(isPresent[x], i, parent, componentSize);
            }

            isPresent[x] = i;
        }
    }

    // Stores the size of largest component.

    int ans = 0;

    for (int i = 0; i < n; i++)
    {

        ans = max(ans, componentSize[i]);
    }

    return ans;
}