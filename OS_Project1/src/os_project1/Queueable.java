package os_project1;


public interface Queueable {
   
    /**
     * Adds a Page object to the appropriate location of the queue.
     * @param page The State to add.
     */
    public void insert(Page page);


    /**
     * Determines if the queue is empty.
     * @return True if the queue is empty; otherwise, false.
     */
    public boolean isEmpty();
    

    /**
     * Determines if the queue is full.
     * @return True if the queue is full; otherwise, false.
     */
    public boolean isFull();
    

    /**
     * Removes a Page object from the front of the queue.
     * @return page
     */
    public Page remove();
     
    
    /**
     * Removes a Page object from the specified position in the queue.
     * @param num The position of the queue at which to remove the Page object.
     * @return Page object that was removed.
     */
    public Page remove(int num);
}

