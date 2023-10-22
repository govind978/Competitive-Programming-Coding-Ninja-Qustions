struct Point
{
    int x, y;

    Point(int x = 0, int y = 0) : x(x), y(y) {}

    inline Point operator-(const Point &p) const
    {
        return Point(x - p.x, y - p.y);
    }

    inline long long operator*(const Point &p) const
    {
        return (long long)x * p.y - (long long)y * p.x;
    }

    inline bool operator<(const Point &p) const
    {
        return x < p.x || (x == p.x && y < p.y);
    }
};

inline int convexHall(int n, Point p[], Point q[])
{
    if (n < 3)
    {
        memcpy(q, p, sizeof(Point) * n);
        return n;
    }
    else
    {
        int c;
        sort(p, p + n);
        for (int i = c = 0; i < n; q[c++] = p[i++])
        {
            for (; c > 1 && (q[c - 1] - q[c - 2]) * (p[i] - q[c - 1]) < 0; --c)
                ;
        }
        for (int i = n - 2, j = c; i >= 0; q[c++] = p[i--])
        {
            for (; c > j && (q[c - 1] - q[c - 2]) * (p[i] - q[c - 1]) < 0; --c)
                ;
        }
        return c - 1;
    }
}

string wellFitted(int n, vector<vector<int>> polygon1, int m, vector<vector<int>> polygon2)
{
    Point p[n + m], convex[n + m];
    set<Point> poly1, poly2;

    for (int i = 0; i < n; i++)
    {
        p[i].x = polygon1[i][0], p[i].y = polygon1[i][1];
        poly1.insert(p[i]);
    }

    for (int i = 0; i < m; i++)
    {
        p[i + n].x = polygon2[i][0], p[i + n].y = polygon2[i][1];
        poly2.insert(p[i + n]);
    }

    int N = convexHall(n + m, p, convex);
    bool wellfit = true;

    for (int i = 0; i < N; i++)
    {
        if (poly1.find(convex[i]) == poly1.end())
        {
            wellfit = false;
            break;
        }
        if (poly2.find(convex[i]) != poly2.end())
        {
            wellfit = false;
            break;
        }
    }

    if (wellfit)
    {
        return "YES";
    }
    return "NO";
}
