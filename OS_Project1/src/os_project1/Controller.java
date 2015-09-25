package os_project1;


import java.util.Random;

/**
 * Creates collection of page objects
 * Executes management system algorithms
 * Displays statistics
 */
public class Controller {
    
    private Queue fifoQueue;         //run those 2 100 times for each 2 - 5 frames
    private PriorityQueue lruQueue;
    private myQueue newQueue;   //**
    
    private Page[] pagesArray = new Page [50];     // Declares and Instantiates an array of Page objects size 50, comment out during the test
    
    //********Test_1 book's example Test_1**********************
    //comment out this for test, comment out previous array declaration
    //where A = 1, B = 2, C = 3, D = 4    to test methods and be able to compare results with the ones from book
    // A B A C A B D B A C D (PAGE 72 IN THE BOOK)
    // 1 2 1 3 1 2 4 2 1 3 4
    
//    public int[] testArray = {1,2,1,3,1,2,4,2,1,3,4};
//    private Page[] pagesArray = new Page[11];
   
    //******************************************************
   
    private double numOfPages; // the divisor
    private int fifoFaults = 0;
    private int lruFaults = 0;
    private int fifoHits = 0;
    private int lruHits = 0;
    private int newFaults = 0;  //**
    private int newHits = 0;
    
    private double fifoFailureRate;
    private double lruFailureRate;
    private double newFailureRate;
    Random generator = new Random(5);
    
    private int numFrames = 2;        // <-- change number of frames here
    
    /**
     * Main Processing Method of Class that Controls Program 
     */
    public void execute() 
    {
        //populates array with newly created page objects, make it as a comment if you do book example test
        for (int i = 0; i < pagesArray.length; i++)
        {
            pagesArray[i] = new Page(getPages()); //passes randomly generated number into Page constructor and fills pagesArray
            // System.out.print(pagesArray[i].toString());
        }  
        
        
       
        //********Test_1 book's example Test_1**********************
        // please comment in previous method before doing this test
//         for (int i = 0; i < pagesArray.length; i++)
//         {
//           pagesArray[i] = new Page(testArray[i]); 
//           System.out.print(pagesArray[i].toString());
//         }  
//         System.out.println();
        //*******************************************************
        
        
        for (int i = 2; i < 6; i++)  {              // Run algorithm for each of 2 - 5 page frames
        
        fifoFaults = 0;                             // Reset counts
        lruFaults = 0;
        fifoHits = 0;
        lruHits = 0;
        newFaults = 0; //**
        newHits = 0;
        
        fifoQueue = new Queue(i);                   // Constructs Queue 
        lruQueue = new PriorityQueue (i);
        newQueue = new myQueue(i);  //**
        
        fifoPageFaults(pagesArray);                 // Run run random set of pages through algorithm
        lruPageFaults(pagesArray);
        newPageFaults(pagesArray); //**
        
        numOfPages = pagesArray.length;
        
        System.out.println("Results for page frame count of:  " + i);
        System.out.println("---------------------------------------");
        
        System.out.println("Fifo Page Hits: " + fifoHits);
        System.out.println("Fifo Page Faults: " + fifoFaults);  
        fifoFailureRate = (fifoFaults / numOfPages) *100;
        System.out.println("Avg Fifo Failure Rate: " + fifoFailureRate +"%"); 
        System.out.println();
        
        System.out.println("LRU Page Hits: " + lruHits);
        System.out.println("LRU Page Faults: " + lruFaults);  
        lruFailureRate = (lruFaults / numOfPages) * 100;
        System.out.println("Avg LRU Failure Rate: " + lruFailureRate +"%"); 
        System.out.println();
        System.out.println();
        
        System.out.println("User Page Hits: " + newHits);
        System.out.println("User Page Faults: " + newFaults);  
        newFailureRate = (newFaults / numOfPages) * 100;
        System.out.println("Avg User Failure Rate: " + newFailureRate +"%"); 
        System.out.println();
        System.out.println();
        
        }
    
    }
    
    
    
 /**
  * Mimics FIFO page replacement algorithm
 * @param pages pagesArray of Page objects that is waiting to be loaded in the memory
 */
    public void fifoPageFaults(Page[] pages) 
    {
        for (Page page : pages) 
        {                                           
            if (!fifoQueue.isFull())               //not full
            { 
                if (!fifoQueue.linearSearch(page)) //the page isn't loaded
                {
                    fifoQueue.insert(page);         //load page
                    fifoFaults++;                   //page fault
                    //System.out.println("Not full insert: " + page.toString());
                } 
                else                                //not full, page is already loaded
                {
                    fifoHits++;
                }
            } 
            else                                    //frame is full
            {   
                if (!fifoQueue.linearSearch(page)) //the page isn't loaded
                {
                   fifoFaults++;                   
                   fifoQueue.remove();
                   fifoQueue.insert(page);
                   //System.out.println("Page full insert: " + page.toString());
                } 
                else                               //full, but the page is already loaded
                {
                    fifoHits++;
                } 
            }                  
        }
        
        //System.out.println("END FIFO");
    }
    
    
 /**
 * Mimics LRU page replacement algorithm
 * @param pages pagesArray of Page objects that is waiting to be loaded in the memory
 */
    public void lruPageFaults(Page[] pages) {
        
        for (int i = 0; i< pages.length; i++) 
        {
            lruQueue.addAge();                     //increments age of all pages
            
            if (!lruQueue.isFull())                //not full
            { 
                
                if (!lruQueue.linearSearch(pages[i])) //the page isn't loaded
                {
                    lruQueue.insert(pages[i]);         //load page
                    lruFaults++;                       //page fault
                   // System.out.println("LRUInserted: " + pages[i].toString());
                } 
                else                                  //not full, page is already loaded
                {
                    lruHits++;
                    //System.out.println("hit");
                }
            } 
            else                                      //frame is full
            {   
                if (!lruQueue.linearSearch(pages[i])) //the page isn't loaded
                {
                   lruFaults++;
                   lruQueue.remove(lruQueue.findLRU()); //removes least recently used element
                   lruQueue.insert(pages[i]);           //inserts the page into appropriate location
                   //System.out.println("LRUInserted: " + pages[i].toString());
                } 
                else                                    //full, but the page is already loaded
                {
                   lruHits++;
                    //System.out.println("hit");
                } 
            }                  
        }
    }

        
    
    
   public void newPageFaults(Page[] pages) {
   Page cp = pages[0];
        
        for (Page page : pages)       
        {                        
            if (cp == page) {                         // This only happens once on the first loop to put first page = cp
              continue;                               // just loop again
              
              }
           

            
            else   {  
    
                if (!newQueue.isFull())               //not full
                { 
                    if (!newQueue.linearSearch(cp))    //the page isn't loaded
                    {
                        newQueue.insert(cp);          //load page
                        newFaults++;                   //page fault
                        cp = page;                                                                // cp = page[1}
                    } 
                    else                                //not full, page is already loaded
                    {
                        newHits++;
                        cp = page;
                    }
                } 
                else                                    //frame is full
                {   
                    if (!newQueue.linearSearch(cp))      //the cp page isn't loaded
                    {
                       if (newQueue.linearSearch(page)) {  // If the next page is already in the queue, remove and replace to reset it at the rear of queue
                            newQueue.remove(page);          // so that it is not removed but another page from queue is instead removed
                            newQueue.insert(page);          
                        }
                       newFaults++;                   
                       newQueue.remove();
                       newQueue.insert(cp);
                       cp = page;
                    } 
                    else                               //full, but the page is already loaded
                    {
                        newHits++;
                        cp = page;
                    } 
                } 
                
            }
        }
        
                if (!newQueue.linearSearch(cp))      //this one only happens once for the last loop to capture page which was set = to cp
                    {
                       newFaults++;                   
                       newQueue.remove();
                       newQueue.insert(cp);
                    } 
                else                               //full, but the page is already loaded
                    {
                        newHits++;
                    } 
        
    } 
    
   
    
  /**
  * Generates random numbers
  * @return random integer
  * @author adrian
  */
    private int getPages() 
    {  
        int num = generator.nextInt(6);  
        return num;
    }              
}

