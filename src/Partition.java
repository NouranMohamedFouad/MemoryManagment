public class Partition
{
    private String partitionName;
    private String content;

    private int size;

    public Partition()
    {}
    public Partition(String parName,int s)
    {
        partitionName=parName;
        size=s;
    }
    public void setName(String name)
    {
        partitionName=name;
    }
    public String getName()
    {
        return partitionName;
    }
    public void setContent(String c)
    {
        content=c;
    }
    public String getContent()
    {
        return content;
    }
    public void setSize(int s)
    {
        size=s;
    }
    public int getSize()
    {
        return size;
    }
}
