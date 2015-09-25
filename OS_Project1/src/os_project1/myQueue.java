
package os_project1;

/**
 *
 * @author adrian
 */
public class myQueue {
    public Page[] array;
    private int maxSize, front, rear, numElems;  
    
    
    //constructor
    public myQueue(int size) { 
        maxSize = size;
        array = new Page[size];
        front = numElems = 0;
        rear = -1;
    }
    
    
    /**
    * Performs linear search 
    * @param page
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
            
        }
    }
       
    /**
     * Determines if the queue is empty.
     * @return True if the queue is empty; otherwise, false.
     */
    public boolean isEmpty(){
        return numElems == 0;
    }
    
    /**
     * Determines if the queue is full.
     * @return True if the queue is full; otherwise, false.
     */
    public boolean isFull() {
        return numElems == maxSize;
    }
    
    /**
     * Removes an item from the front of the queue.
     * 
     * Note:  The isEmpty method should be called first to prevent errors.
     * @return The item that was removed.
     */
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
            
        }
        return temp;
    }
    
    /**
     * 
     * 
     * Note:  The isEmpty method should be called first to prevent errors.
     * @param next The next page coming so we don't want to remove it if it is in there
     * @return Page object that was removed.
     */
    public Page remove(Page next)             
    {
        Page temp = array[front];   
        
        boolean found = false;
        
        if (linearSearch(next))  {                          // if next is in there
            
            for(int i = 0; i < numElems && !found; i++)        
        {
                if (array[i] == next) {                         // find it and remove
                   temp = array[i];
                   found = true;
                   
                        if (i != front) {                        // shift if removed is not front
                            
                            for(int j = i; j<numElems-1; j++)   // **Shift to the left to fill empty array slot left by array[i]
                                {
                                    array[j] = array[j+1];
                                } 
                        }
                        
                   numElems--; 
                   front++;                                     // increment either way

                       if (front == maxSize)  //wraparound
                        {
                            front = 0;
                        }

                }


        }
        }
        
        else                                            // if it isn't process as normal
        {
            temp = array[front++];
            if (front == maxSize)  //wraparound
            {
                front = 0;
            }
            numElems--;           
        }
        
        
        return temp;

    }
    
}
