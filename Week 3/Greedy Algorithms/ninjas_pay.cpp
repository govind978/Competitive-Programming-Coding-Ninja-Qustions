#include <algorithm>
bool cmp(const vector<int> &v1, const vector<int> &v2)
{
    if (v1[0] * v2[1] == v1[1] * v2[0])
        return v1[2] < v2[2];
    return v1[0] * v2[1] > v1[1] * v2[0];
}

vector<int> minLoss(int n, vector<int> &time, vector<int> &loss)
{
    // Write your code here.
    vector<vector<int>> tasks;
    for (int i = 0; i < n; i++)
    {
        vector<int> temp;
        temp.push_back(loss[i]);
        temp.push_back(time[i]);
        temp.push_back(i + 1);
        tasks.push_back(temp);
    }
    sort(tasks.begin(), tasks.end(), cmp);
    vector<int> res;
    for (int i = 0; i < n; i++)
    {
        res.push_back(tasks[i][2]);
    }
    return res;
}