import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
public class FirstComeFirstServe {
    public static void calculationForFCFS(ArrayList<Process> processes) throws IOException {
        Collections.sort(processes, new Comparator());

        int currentTime=0;
        double average_waiting_time = 0;
        double average_turnaround_time = 0;
        double cpu_utilization = 0;
        String output = "";
        int finish_time=0;
        int start_time=0;
        int stepCount = 0;


        for(Process p : processes){z
            int idleTime = 0;
            if(currentTime < p.arrival_time){
                currentTime = p.arrival_time;
            }
            if(p.arrival_time - finish_time != 0 && p.arrival_time - finish_time > 0){
                idleTime += p.arrival_time - finish_time;
                while(idleTime != 0){
                    output += "Time " + (stepCount++) + ": idle\n";
                    idleTime--;
                }
            }

            start_time = currentTime;
            currentTime += p.running_time;
            finish_time = currentTime;

            int notIdle = 0;
            notIdle = currentTime - start_time;

            while(notIdle != 0){
                output += "Time " + (stepCount++) + ": running process " + p.process_id + "\n";
                notIdle--;
            }

            p.waiting_time = start_time- p.arrival_time;
            average_waiting_time += p.waiting_time;

            p.turnaround_time = finish_time- p.arrival_time;
            average_turnaround_time += p.turnaround_time;
            cpu_utilization += p.running_time;

        }


        FileWriter writer = new FileWriter("C:\\Users\\ASUS\\Desktop\\OpSystem\\Carnelian_FCFS.txt"); //Put location of the output txt file
        writer.write(output + "\n");

        output = String.format("Average waiting time: %.2f" , (average_waiting_time/ processes.size())) + "\n"
                + String.format("Average turnaround time: %.2f" , (average_turnaround_time/ processes.size())) + "\n"
                +String.format("CPU utilization: %.1f" , (cpu_utilization / currentTime) * 100) + "%";

        writer.write(output);
        writer.close();

    }

}
