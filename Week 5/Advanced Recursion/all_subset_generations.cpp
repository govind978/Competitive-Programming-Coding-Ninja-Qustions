
void solve(vector<int> &arr, int index, vector<vector<int>> &ans, vector<int> &output, vector<int> &dp)
{

    if (index >= arr.size())
    {

        if (!(output.empty()))
        {

            ans.push_back(output);
        }

        return;
    }

    if (dp[index] != -1)
    {
    }

    solve(arr, index + 1, ans, output, dp);

    output.push_back(arr[index]);

    solve(arr, index + 1, ans, output, dp);

    output.pop_back();
}

vector<vector<int>> FindAllSubsets(vector<int> &arr)
{

    vector<int> dp(arr.size() + 1, -1);

    vector<vector<int>> ans;

    vector<int> output;

    solve(arr, 0, ans, output, dp);

    return ans;
}