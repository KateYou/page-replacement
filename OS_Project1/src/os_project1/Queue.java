package os_project1;

/**
 * Creates a circular array of frames and performs its operations
 * @author adrian, nadiia
 */
public class Queue implements Queueable {
    
    public Page[] array;
    private int maxSize, front, rear, numElems;  
    
    
    //constructor
    public Queue(int size) { 
        maxSize = size;
        array = new Page[size];
        front = numElems = 0;
        rear = -1;
    }
    
    
    /**
    * Performs linear search 
    * @return found
    */
    public boolean linearSearch(Page page) {
        boolean found = false;
        
        for(int i = 0; i < numElems && !found; i++)        //loops until page is found or it reaches the end of array
        {
            if(array[i].getPageNum() == page.getPageNum()) //page found
            {
                found = true;
            }
        }
        return found;
    }
 
    /**
     * Adds an item to the queue.
     *
     * Note:  The isFull method should be called first to prevent errors.
     * @param page is an item to add.
     */
    @Override
    public void insert(Page page) 
    {
        if(!isFull())
        {
            if(rear == maxSize-1) //deals with wraparound
            {
                rear = -1;
            }
            array[++rear] = page;
            numElems++;
            // System.out.println("Insert: " + page.toString());
        }
    }
       
    /**
     * Determines if the queue is empty.
     * @return True if the queue is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty(){
        return numElems == 0;
    }
    
    /**
     * Determines if the queue is full.
     * @return True if the queue is full; otherwise, false.
     */
    @Override
    public boolean isFull() {
        return numElems == maxSize;
    }
    
    /**
     * Removes an item from the front of the queue.
     * 
     * Note:  The isEmpty method should be called first to prevent errors.
     * @return The item that was removed.
     */
    @Override
    public Page remove() {
        Page temp = array[front];   
        if (!isEmpty())
        {
            temp = array[front++];
            if (front == maxSize)  //wraparound
            {
                front = 0;
            }
            numElems--;
            //System.out.println("Removed: " + temp.toString());
        }
        return temp;
    }
    
    /**
     * Removes a Page object from the specified position in the queue.
     * 
     * Note:  The isEmpty method should be called first to prevent errors.
     * @param max The position of the least recently used page in the queue
     * @return Page object that was removed.
     */
    @Override
    public Page remove(int max)             
    {
        Page removed = array[max];
        for(int i = max; i<numElems;i++)
        {
            array[i] = array[i+1]; 
        }
        numElems--; 
        System.out.println("LRURemoved: " + removed.toString());
        return removed;
    }
    
}
    
    


