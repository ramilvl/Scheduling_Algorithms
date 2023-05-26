import java.io.FileWriter;
import java.io.IOException;

public class ShortestRemainingTimeFirst {

    public static void CalculateSRTF(Process[] processes) throws IOException {

        int idleTime = 0;
        int n = processes.length;
        int[] waitingTimes = new int[n];
        int[] turnaroundTimes = new int[n];
        int[] remainingTimes = new int[n];
        int[] completionTimes = new int[n];
        int currentTime = 0;
        int completed = 0;
        boolean[] isCompleted = new boolean[n];
        String result = "";

        for (int i = 0; i < n; i++) {
            remainingTimes[i] = processes[i].running_time;
        }

        while (completed != n) {
            int shortestIndex = -1;
            int shortestRemainingTime = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && remainingTimes[i] < shortestRemainingTime && processes[i].arrival_time <= currentTime) {
                    shortestIndex = i;
                    shortestRemainingTime = remainingTimes[i];
                }
            }
            if (shortestIndex == -1) {
                result+="time " + currentTime + ": running process: idle" + "\n";
                idleTime++;
                currentTime++;
            }
            else {
                for (int i = 0; i < n; i++) {
                    if (!isCompleted[i] && i != shortestIndex && processes[i].arrival_time <= currentTime) {
                        waitingTimes[i]++;
                    }
                }
                result += "time " + currentTime + ": running process: " + (shortestIndex+1) + "\n";
                remainingTimes[shortestIndex]--;
                currentTime++;
                if (remainingTimes[shortestIndex] == 0) {
                    completionTimes[shortestIndex] = currentTime;
                    turnaroundTimes[shortestIndex] = completionTimes[shortestIndex] - processes[shortestIndex].arrival_time;
                    waitingTimes[shortestIndex] = turnaroundTimes[shortestIndex] - processes[shortestIndex].running_time;
                    isCompleted[shortestIndex] = true;
                    completed++;
                }

            }
        }
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTimes[i];
            totalTurnaroundTime += turnaroundTimes[i];
        }

        FileWriter writer = new FileWriter("C:\\Users\\ASUS\\Desktop\\OpSystem\\Carnelian_SRTF.txt"); //Put location of the output txt file
        writer.write(result + "\n");
        double cpu_utl = (1.0 - (idleTime * 1.0/ currentTime)) * 100.0;
        result = String.format("Average waiting time: %.2f" , (double) totalWaitingTime * 1.0/ n) + "\n"
                + String.format("Average turnaround time: %.2f" , (double) (totalTurnaroundTime *1.0/ n)) + "\n"
                + "CPU Utilization: "  + cpu_utl + "%";

        writer.write(result);
        writer.close();
    }
}