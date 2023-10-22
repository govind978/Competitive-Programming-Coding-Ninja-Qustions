vector<int> eulerTotientFunction(int n)
{

    // Write your code here.

    vector<int> ans;

    for (int i = 1; i <= sqrt(n); i++)
    {

        if (n % i == 0)
        {

            if (i * i == n)
            {

                ans.push_back(i);
            }

            else
            {

                ans.push_back(i);

                ans.push_back(n / i);
            }
        }
    }

    return ans;
}