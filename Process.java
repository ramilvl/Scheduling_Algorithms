public class Process {
    int process_id;
    int arrival_time;
    int running_time;
    int waiting_time;
    int turnaround_time;
    int remainingTimes;
    int completionTimes;


    public Process(int process_id, int arrivalTime, int running_time) {
        this.process_id = process_id;
        this.arrival_time = arrivalTime;
        this.running_time = running_time;
        this.waiting_time = 0;
        this.turnaround_time = 0;
        this.remainingTimes = 0;
        this.completionTimes = 0;
    }
}
