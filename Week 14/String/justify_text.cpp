/*
    Time complexity: O(N)
    Space complexity: O(N)

    Where 'n' is the number of elements in the given array
*/

const char spc = ' ';
string makePadding(int n)
{
    // Returns n spaces in form of string
    return string(n, spc);
}

vector<string> fullJustify(vector<string> &words, int maxWidth)
{
    int currentIndex = 0, startIndex = 0, width = 0, size = words.size();
    vector<string> currWords, paddings, result;

    while (currentIndex < size)
    {
        int gaps = currentIndex - startIndex + 1; // 2 words will have 1 gap. 3 words will have 2 gaps and so on...
        bool isLastWord = currentIndex == size - 1;

        width += words[currentIndex].size();
        currWords.push_back(words[currentIndex]); // Store all words so far

        if (isLastWord or width + (words[currentIndex + 1].size()) + gaps > maxWidth)
        {
            int numWords = currWords.size();

            if (numWords == 1)
            {
                result.push_back(currWords[0] + makePadding(maxWidth - currWords[0].size()));
                currWords = vector<string>();
                startIndex = currentIndex + 1;
                width = 0;
                currentIndex++;
                continue;
            }

            int padding = maxWidth - width; // Extra width which will be used for spaces
            gaps = numWords - 1;

            int spaces = padding / gaps, extras = padding % gaps, k = 0;
            // Find space per word for our line, and the extras
            string line = "";

            if (isLastWord)
            {
                // Last line doesnt have extra spaces
                spaces = 1;
                extras = 0;
            }

            paddings = vector<string>(numWords, makePadding(spaces));

            while (k < extras)
            {
                paddings[k] += spc;
                k++;
            }

            k = 0;
            for (auto i : currWords)
            {
                // Pad every word except the last.
                line += i + ((k == paddings.size() - 1) ? "" : paddings[k++]);
            }

            if (line.size() < maxWidth)
            {
                // If any space left after last word add extra space to the right.
                line += makePadding(maxWidth - line.size());
            }

            result.push_back(line);       // Add to final result
            currWords = vector<string>(); // Reset for next set of words

            startIndex = currentIndex + 1;
            width = 0;
        }
        currentIndex++;
    }

    return result;
}