/*
    Time Complexity: O((N + Q)*log(N))
    Space Complexity: O(N)

    Where 'N' is the number of equations and 'Q' is the number of queries.
*/

#include <unordered_map>

// Function to find root of the node
string getRoot(unordered_map<string, string> &roots, unordered_map<string, double> &distances, string node)
{
    // If the node is not present it's the root of itself
    if (roots.count(node) == 0)
    {
        roots[node] = node;

        // Set the distance/ factor as from the root
        distances[node] = 1.0;
        return node;
    }

    // If the node is root of itself then its the highest root
    if (roots[node] == node)
    {
        return node;
    }

    // Get the root of the parent node and set as the root of current node
    string parent = roots[node];
    string parentRoot = getRoot(roots, distances, parent);
    roots[node] = parentRoot;

    // Get the factor of parent from the root and multiply current node's factor to the root
    distances[node] = distances[node] * distances[parent];

    // Return parent node's root
    return parentRoot;
}

vector<double> evaluateEquations(vector<pair<string, string>> &equations, vector<pair<string, string>> &queries, vector<double> &values)
{
    unordered_map<string, double> distances;
    unordered_map<string, string> roots;

    // Iterate through every given equation
    for (int i = 0; i < equations.size(); i++)
    {
        pair<string, string> equation = equations[i];

        // Find the root of both nodes
        string root1 = getRoot(roots, distances, equation.first);
        string root2 = getRoot(roots, distances, equation.second);

        // The second node the now the parent of first node (Union operation)
        roots[root1] = root2;

        // The current value of the equation as the factor
        distances[root1] = (1.0 * distances[equation.second] * values[i]) / distances[equation.first];
    }

    vector<double> results;

    for (int i = 0; i < queries.size(); i++)
    {
        pair<string, string> query = queries[i];

        // If query is not present in roots then cant find the solution
        if ((roots.count(query.first) == 0 || roots.count(query.second) == 0))
        {
            results.push_back(-1.0);
            continue;
        }

        string root1 = getRoot(roots, distances, query.first);
        string root2 = getRoot(roots, distances, query.second);

        // If the roots are differnt that means differnt components and solution not possible
        if (root1 != root2)
        {
            results.push_back(-1.0);
            continue;
        }

        // Get the answer by dividing both factors
        results.push_back((1.0 * distances[query.first]) / distances[query.second]);
    }

    return results;
}
