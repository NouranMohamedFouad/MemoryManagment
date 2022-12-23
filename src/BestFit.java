import java.util.ArrayList;

public class BestFit
{
    private ArrayList<Partition> partitions=new ArrayList<>();
    private int allocation[];


    public void BestFit(ArrayList<Partition> partition,int PartitionNum,Process process[],int ProcessNum) {

        allocation=new int[ProcessNum];
        partitions=partition;

        /////////////////////////////////
        for (int i = 0; i < ProcessNum; i++) {
            allocation[i] = -1;
        }
        /////////////////////////////////
        for (int i = 0; i < PartitionNum; i++)
        {
            partitions.get(i).setContent(" ");
        }
        //////////////////////////////////
        int minValue=Integer.MAX_VALUE;
        int minIndex=Integer.MAX_VALUE;

        for (int i = 0; i < ProcessNum; i++)
        {
            for(int k=0;k<partitions.size();k++)
            {
                if(partitions.get(k).getSize()<minValue && partitions.get(k).getSize() >= process[i].getSize() && partitions.get(k).getContent()== " ")
                {
                    minValue=partitions.get(k).getSize();
                    minIndex=k;
                }
            }
            for (int j=0;j<partitions.size();j++)
            {
               if(partitions.get(minIndex).getContent()== " ")
                {
                    allocation[i]=minIndex;
                    partitions.get(minIndex).setContent(process[i].getName() + "  (" + process[i].getSize() + " KB)");

                    //////////////////////////////////////////////////////////
                    if (minValue > process[i].getSize())
                    {
                        partitions.add(minIndex+1,new Partition("partition"+PartitionNum++, partitions.get(minIndex).getSize() - process[i].getSize()));
                        partitions.get(minIndex+1).setContent(" ");
                    }
                    partitions.get(minIndex).setSize(process[i].getSize());
                    minValue=Integer.MAX_VALUE;
                    break;
                }
            }
        }
        ///////////////////////////////////
        for (int i = 0; i < partitions.size(); i++)
        {
            if (partitions.get(i).getContent() == " ") {
                partitions.get(i).setContent("External Fragment");

            }
        }
        /*for(int i = 0; i <partitions.size(); i++)
        {
            System.out.println(" "+partitions.get(i).getName()+" ( "+partitions.get(i).getSize()+" KB)  ==> " +partitions.get(i).getContent());
            //System.out.print("\n");
        }
        //////////////////////////////////
        for(int i = 0; i <ProcessNum; i++)
        {
            if (allocation[i] == -1)
            {
                System.out.println(" " + process[i].getName() + " is Not Allocated");
            }
        }
         */
    }
    /////////////////////////////////////////////////////////////////
    public void display(ArrayList<Partition> partition,int PartitionNum,Process process[],int ProcessNum)
    {
        partitions=partition;
        BestFit(partitions,PartitionNum,process,ProcessNum);
        for(int i = 0; i <partitions.size(); i++)
        {
            System.out.println(" "+partitions.get(i).getName()+" ( "+partitions.get(i).getSize()+" KB)  ==> " +partitions.get(i).getContent());
        }
        //////////////////////////////////
        for(int i = 0; i <ProcessNum; i++)
        {
            if(allocation[i]==-1)
            {
                System.out.println(" " + process[i].getName() + " is Not Allocated");
            }
        }
    }
    //////////////////////////////////////////////////////////////
    public void compaction(ArrayList<Partition> partitions,int PartitionNum,Process process[],int ProcessNum)
    {
        int totalSize = 0;
        for(int i=0;i<partitions.size();i++)
        {
            if(partitions.get(i).getContent()=="External Fragment")
            {
                totalSize+=partitions.get(i).getSize();
            }
        }
        partitions.add(partitions.size(),new Partition("partition"+partitions.size(),totalSize));

        partitions.get((partitions.size()-1)).setContent(" ");
        int highestCompated=partitions.size()-1;

        BestFit(partitions,partitions.size(),process,ProcessNum);

        int i=0;
        String compactedPartition=("partition"+highestCompated);

        while(i < partitions.size())
        {
            if(partitions.get(i).getName().equals(compactedPartition))
            {
                break;
            }
            else if(partitions.get(i).getContent().equals("External Fragment"))
            {
                partitions.remove(i);
            }
            else
            {
                i++;
            }
        }
        for (int j= 0; j< partitions.size(); j++)
        {
            System.out.println(" "+partitions.get(j).getName()+" ( "+partitions.get(j).getSize()+" KB)  ==> " +partitions.get(j).getContent());
        }
    }
}
