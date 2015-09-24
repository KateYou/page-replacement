package os_project1;

/**
 * Page class
 * Creates instance of the Page object
 * @author Nadiia, Adrian
 */
public class Page 
{
    private int pageNum;
    private int pageAge;
    
    //constructor
    public Page(int number)
    {
    pageNum = number;
    pageAge = 0;
    }
    /**
     * Returns page number
     * @return pageNum 
     */
    public int getPageNum()
    {
        return pageNum;
    }
    
    /**
     * Returns 0 if page wasn't recently used and 1 if it was used. 
     * @return pageAge 
     */
    public int getRecUsed()
    {
        return pageAge;
    }
    
    /**
     * Increments recently used number by 1
     */
    public void setRecUsed()
    {
        pageAge++;
    }
    
    /**
     * Sets recently used number to 0;
     * @param num
     */
    public void setRecUsed(int num)
    {
        pageAge = num;
    }
            
    
    /**
     * Converts page number to string for display method (testing)
     * @return pageNum
     */
    @Override
    public String toString()
    {
        return Integer.toString(pageNum);
    }
}

