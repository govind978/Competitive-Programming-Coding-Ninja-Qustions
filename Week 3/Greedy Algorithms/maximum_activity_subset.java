
import java.util.*;

class Activity {
    int start;
    int finish;

    public Activity(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }
}

public class Solution {
    public static int maxActivities(Activity[] activities) {
        Arrays.sort(activities, (a, b) -> Integer.compare(a.finish, b.finish));
        int n = activities.length;
        int count = 1;
        int j = 0;

        for (int i = 1; i < n; i++) {
            if (activities[i].start >= activities[j].finish) {
                count++;
                j = i;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        Activity[] activities = new Activity[N];

        for (int i = 0; i < N; i++) {
            int start = scanner.nextInt();
            int finish = scanner.nextInt();
            activities[i] = new Activity(start, finish);
        }

        int result = maxActivities(activities);
        System.out.println(result);

        scanner.close();
    }
}
