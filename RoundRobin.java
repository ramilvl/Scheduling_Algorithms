import java.io.FileWriter;
import java.io.IOException;

public class RoundRobin {

    public static void calcRoundRobin(Process[] process) throws IOException {
        int n = process.length;
        int quantum = 1;
        int currentTime = 0;
        int completed = 0;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        String output = "";
        int[] runningTime = new int[n];
        int[] lastExecutionTime = new int[n];
        int idleCalculatorForCpuUtilization = 0;


        for (int i = 0; i < n; i++) {
            runningTime[i] = process[i].running_time;
            lastExecutionTime[i] = -1;
        }

        while (completed < n) {
            boolean doneCycle = false;
            for (int i = 0; i < n; i++) {
                Process p = process[i];
                if (p.running_time > 0 ) {
                    int executionTime = Math.min(p.running_time, quantum);
                    int startTime = currentTime;


                    output += String.format("time %d: running process %d\n", startTime, i);

                    if (lastExecutionTime[i] != -1) {
                        startTime = lastExecutionTime[i];
                    }
                    lastExecutionTime[i] = currentTime;
                    currentTime += executionTime;
                    p.running_time -= executionTime;
                    if (p.running_time == 0) {
                        completed++;
                        p.turnaround_time = currentTime;
                        p.waiting_time = p.turnaround_time - runningTime[i];
                    }

                    doneCycle = true;
                }
            }
            if (!doneCycle) {
                output += String.format("time %d: idle\n", currentTime);
                currentTime++;
                idleCalculatorForCpuUtilization++;
            }
        }

        for (Process p : process) {
            totalWaitingTime += p.waiting_time;
            totalTurnaroundTime += p.turnaround_time;
        }

        output += String.format("\nAverage waiting time: %.2f\n", (totalWaitingTime / process.length))
                + String.format("Average turnaround time: %.2f\n", (totalTurnaroundTime / process.length))
                + "CPU Utilization: "  + (1.0 - ((idleCalculatorForCpuUtilization * 1.0) / currentTime)) * 100.0 + "%";

        FileWriter writer = new FileWriter("C:\\Users\\ASUS\\Desktop\\OpSystem\\Carnelian_RR.txt"); //Put location of the output txt file
        writer.write(output);
        writer.close();
    }



}