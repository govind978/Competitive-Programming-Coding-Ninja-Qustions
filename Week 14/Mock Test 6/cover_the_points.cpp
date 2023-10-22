/*
Time Complexity: O( N * log(N) ).
Space Complexity: O( N ).
Where ‘N’ is the number of points.
*/
struct Line
{
    int m, b;
    int eval(int x)
    {
        return m * x + b;
    }
    void init(int i, vector<int> &x, vector<int> &dp)
    {
        m = -int(2) * int(x[i - 1]);
        b = int(x[i - 1]) * int(x[i - 1]) + dp[i];
    }
};
double calcInter(const Line &a, const Line &b)
{
    double r = (b.b - a.b) / double(a.m - b.m);
    return r;
}
int getMin(int x, int &sz, vector<double> &inters, vector<Line> &hull)
{
    int i = 0;
    if (sz == 1)
    {
        i = 0;
    }
    else if (x < inters[sz - 2])
    {
        i = sz - 1;
    }
    else
    {
        int lo = 0, hi = sz - 2, mi;
        while (lo < hi)
        {
            mi = (lo + hi) >> 1;
            if (inters[mi] > x)
            {
                lo = mi + 1;
            }
            else
            {
                hi = mi;
            }
        }
        i = lo;
    }
    return hull[i].eval(x);
}
int coverAllPoints(int n, int c, vector<int> &points)
{
    vector<int> dp(n);
    vector<double> inters(n);
    dp[n - 1] = c;
    int sz = 0;
    vector<Line> hull(n);
    hull[sz++].init(n - 1, points, dp);
    for (int i = n - 2; i >= 0; --i)
    {
        dp[i] = c + (points[n - 1] - points[i]) * (points[n - 1] - points[i]);
        dp[i] = min(dp[i], getMin(points[i], sz, inters, hull) + (points[i]) * (points[i]) + (c));
        if (i)
        {
            hull[sz++].init(i, points, dp);
        }
        if (sz - 2 >= 0)
        {
            inters[sz - 2] = calcInter(hull[sz - 1], hull[sz - 2]);
        }
        while (sz > 2 && inters[sz - 2] > inters[sz - 3])
        {
            hull[sz - 2] = hull[sz - 1];
            sz--;
            if (sz - 2 >= 0)
            {
                inters[sz - 2] = calcInter(hull[sz - 1], hull[sz - 2]);
            }
        }
    }
    return dp[0];
}