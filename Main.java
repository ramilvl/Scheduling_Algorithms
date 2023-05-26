import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class Main{
    public static void main(String[] args) throws IOException {

                                /* First-Come First Served Scheduling Algorithm */
//************************************************************************************************************************************//

        ArrayList<Process> arrFCFS = new ArrayList<>();

        FileReader reader = new FileReader("C:\\Users\\ASUS\\Desktop\\OpSystem\\processes.txt"); // Put location of your txt file
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = "";
        bufferedReader.readLine();

        while ((line = bufferedReader.readLine()) != null) {

            String[] str = line.split(", ");
            // System.out.println(str[0] + " " + str[1] + " " + str[2]);
            arrFCFS.add(new Process(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2])));
        }

        FirstComeFirstServe.calculationForFCFS(arrFCFS);

        System.out.println();
        reader.close();
        bufferedReader.close();



                                    /* Shortest Remaining Time First Scheduling Algorithm */
//************************************************************************************************************************************//


        ArrayList<Process> arrSRTF = new ArrayList<>();

        reader = new FileReader("C:\\Users\\ASUS\\Desktop\\OpSystem\\processes.txt"); // Put location of your txt file
        bufferedReader = new BufferedReader(reader);

        line = "";
        bufferedReader.readLine();

        while ((line = bufferedReader.readLine()) != null) {

            String[] str = line.split(", ");
            arrSRTF.add(new Process(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2])));
        }

        Process[] processes2 = new Process[arrSRTF.size()];

        for (int i = 0; i < processes2.length; i++)
            processes2[i] = new Process(arrSRTF.get(i).process_id, arrSRTF.get(i).arrival_time, arrSRTF.get(i).running_time);

        ShortestRemainingTimeFirst.CalculateSRTF(processes2);
        System.out.println();
        reader.close();
        bufferedReader.close();


                                                /*Round Robin Scheduling Algorithm*/
//************************************************************************************************************************************//

        ArrayList<Process> arrRR = new ArrayList<>();

        reader = new FileReader("C:\\Users\\ASUS\\Desktop\\OpSystem\\processes.txt"); // Put location of your txt file
        bufferedReader = new BufferedReader(reader);

        line = "";
        bufferedReader.readLine();

        while ((line = bufferedReader.readLine()) != null) {

            String[] str = line.split(", ");
            arrRR.add(new Process(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2])));
        }

        Process[] processes1 = new Process[arrRR.size()];

        for(int i = 0; i < processes1.length; i++){
            processes1[i] = arrRR.get(i);
        }


        RoundRobin.calcRoundRobin(processes1);
        reader.close();
        bufferedReader.close();


    }
}