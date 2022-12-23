import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        Scanner str=new Scanner(System.in);

        System.out.print("Please enter number of partitions: ");
        int partitionSize=s.nextInt();

        Partition partitions[]=new Partition[partitionSize];


        for(int i=0;i<partitionSize;i++)
        {
            partitions[i]=new Partition();
        }

        for(int i=0;i<partitionSize;i++)
        {
            System.out.println("Please enter Name of partition["+i+"]:");
            String name=str.nextLine();
            System.out.println("Please enter size of partition["+i+"]:");
            int PSize=s.nextInt();
            partitions[i].setName(name);
            partitions[i].setSize(PSize);
        }

        //////////////////////////////////////////////////////////////
        System.out.print("Please enter number of processes: ");
        int processSize=s.nextInt();

        Process processes[]=new Process[processSize];

        for(int i=0;i<processSize;i++)
        {
            processes[i]=new Process();
        }

        for(int i=0;i<processSize;i++)
        {
            System.out.println("Please enter Name of process["+(i+1)+"]:");
            String name=str.nextLine();
            System.out.println("Please enter size of process["+(i+1)+"]:");
            int PSize=s.nextInt();
            processes[i].setName(name);
            processes[i].setSize(PSize);
        }

        /*
        partitions[0].setName("partition0");
        partitions[1].setName("partition1");
        partitions[2].setName("partition2");
        partitions[3].setName("partition3");
        partitions[4].setName("partition4");
        partitions[5].setName("partition5");

        partitions[0].setSize(90);
        partitions[1].setSize(20);
        partitions[2].setSize(5);
        partitions[3].setSize(30);
        partitions[4].setSize(120);
        partitions[5].setSize(80);


        processes[0].setName("process1");
        processes[1].setName("process2");
        processes[2].setName("process3");
        processes[3].setName("process4");

        processes[0].setSize(15);
        processes[1].setSize(90);
        processes[2].setSize(30);
        processes[3].setSize(100);

         */

        ////////////////////////////////////////////////////////////
        ArrayList<Partition> partitionsCopy=new ArrayList<>();
        for(int i=0;i<partitionSize;i++)
        {
            partitionsCopy.add(partitions[i]);
        }

        int choice;
        int compaction;

        System.out.println("\nSelect the policy you want to apply: ");
        System.out.println("1-first fit");
        System.out.println("2-best fit");
        System.out.println("3-worst fit");

        System.out.print("Select Policy: ");
        choice = s.nextInt();
        //////////////////////////////////////////////
        if(choice==1)
        {
            System.out.println("Applying First Fit: ");
            System.out.println("-----------------------------------------------------");
            FirstFit firstF=new FirstFit();
            firstF.display(partitionsCopy,partitionSize,processes,processSize);
            System.out.println("\n-----------------------------------------------------");
            System.out.println("Do You Want To Compact   1)YES  2)NO ?");
            compaction=s.nextInt();
            if(compaction==1)
            {
                firstF.compaction(partitionsCopy,partitionSize,processes,processSize);
            }
            else if(compaction==2)
            {
                System.out.println("NO Compaction Occurred ");
            }
        }
        else if(choice==2)
        {
            System.out.println("-----------------------------------------------------");
            BestFit bestF=new BestFit();
            bestF.display(partitionsCopy,partitionSize,processes,processSize);
            System.out.println("\n-----------------------------------------------------");
            System.out.println("Do You Want To Compact   1)YES  2)NO ?");
            compaction=s.nextInt();
            if(compaction==1)
            {
                bestF.compaction(partitionsCopy,partitionSize,processes,processSize);
            }
            else if(compaction==2)
            {
                System.out.println("NO Compaction Occurred ");
            }
        }
        if(choice==3)
        {
            System.out.println("-----------------------------------------------------");
            WorstFit worstF=new WorstFit();
            worstF.display(partitionsCopy,partitionSize,processes,processSize);
            System.out.println("\n-----------------------------------------------------");
            System.out.println("Do You Want To Compact   1)YES  2)NO ?");
            compaction=s.nextInt();
            if(compaction==1)
            {
                worstF.compaction(partitionsCopy,partitionSize,processes,processSize);
            }
            else if(compaction==2)
            {
                System.out.println("NO Compaction Occurred ");
            }
        }
    }
}