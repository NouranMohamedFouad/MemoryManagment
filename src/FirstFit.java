import java.util.ArrayList;

public class FirstFit
{
    private ArrayList<Partition> partitions=new ArrayList<>();
    private int allocation[];

    public void firstFit(ArrayList<Partition> partition,int PartitionNum,Process process[],int ProcessNum)
    {

        allocation=new int[ProcessNum];
        partitions=partition;

        /////////////////////////////////
        for(int i=0; i<ProcessNum; i++)
        {
            allocation[i]=-1;
        }
        /////////////////////////////////
        for(int i=0; i<PartitionNum; i++)
        {
            partitions.get(i).setContent(" ");
        }
        //////////////////////////////////
        for(int i=0; i<ProcessNum; i++)
        {
            //-----------------------------------//
            for(int j=0;j< partitions.size();j++)
            {
                if(partitions.get(j).getContent()==" " && partitions.get(j).getSize() >= process[i].getSize())
                {
                    allocation[i]=j;
                    partitions.get(j).setContent(process[i].getName() +"  ("+process[i].getSize()+" KB)");
                    //////////////////////////////////////////////////////////
                    if(partitions.get(j).getSize() > process[i].getSize())
                    {
                        partitions.add(j+1,new Partition("partition"+PartitionNum++,partitions.get(j).getSize()-process[i].getSize()));
                        partitions.get(j+1).setContent(" ");
                    }
                    partitions.get(j).setSize(process[i].getSize());
                    break;
                }
            }
        }
        ///////////////////////////////////

        for(int i = 0; i <partitions.size(); i++)
        {
            if (partitions.get(i).getContent()==" ")
            {
                partitions.get(i).setContent("External Fragment");

            }
        }
        ///////////////////////////////////

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void display(ArrayList<Partition> partition,int PartitionNum,Process process[],int ProcessNum)
    {
        partitions=partition;
        firstFit(partitions,PartitionNum,process,ProcessNum);
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
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void compaction( ArrayList<Partition> partitions,int PartitionNum,Process process[],int ProcessNum)
    {
        int totalSize = 0;
        for (int i = 0; i < partitions.size(); i++)
        {
            if (partitions.get(i).getContent()=="External Fragment")
            {
                totalSize += partitions.get(i).getSize();
            }
        }
        partitions.add(partitions.size(),new Partition("partition"+partitions.size(),totalSize));

        partitions.get((partitions.size()-1)).setContent(" ");
        int highestCompated=partitions.size()-1;

        firstFit(partitions,partitions.size(),process,ProcessNum);
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