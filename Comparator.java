public class Comparator implements java.util.Comparator<Process> {

    @Override
    public int compare(Process o1, Process o2) {
        int diff = o1.arrival_time - o2.arrival_time;
        if(diff ==0) diff = o1.process_id-o2.process_id;
        return diff;
    }

}