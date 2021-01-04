/**
 * Created by 17 on 2021-01-02.
 */

/**the rules
 *
 * @ add and remove must take constant time, except during resizing operations.
 * @ get and size must take constant time.
 * @ The starting size of your array should be 8.
 * @  For arrays of length 16 or more, your usage factor should always be at least 25%, For smaller arrays, your usage factor can be arbitrarily low.
 *
 *
 *
 *
 *
 *
 */




// items: [0 6 9 1 2 3 0 0 0 0 0];
    //size: 6;
    //addLast: the items x we want add to the position x;

public class ArrayDeque<Item> {
    private Item [] items;
    private static int initialCapacity = 8; //the stating length of array
    private static int cFactor = 2; //the factor that being used every time being resizing
    private static double mRatio = 0.25; //the minimun ration before contraction
    private static int mCapacity = 16; //the minimun capacity for contraction resizing; due to the array that length smaller than 16,
                                       // the usage rule will be not that strict.
    private static int eFactor = 2; //expanding factor
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;



    /**create an empty list*/
    public ArrayDeque() {
        capacity = initialCapacity;

        items = (Item []) new Object [capacity];
        nextFirst = capacity - 1;
        nextLast = 0;
        size = 0;
    }


    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }else {
            return false;
        }
    }

    private int onePlus(int index){
        if (index == capacity - 1){
            return 0;
        }else{
            return index + 1;
        }
    }

    private int oneMinus(int index){
        if (index == 0){
            return capacity - 1;
        }else{
            return index - 1;
        }
    }

    public void printDeuqe() {
        int currentIndex = onePlus(nextFirst);
        while (currentIndex != nextLast) {
            System.out.print(items[currentIndex] + " ");
            currentIndex = onePlus(currentIndex);
        }
        System.out.println();
    }



    /**
     * resize the old array to a new array with given capacity
     *
     * */

    public void resize (int newcapacity) {
        Item [] newItems = (Item[]) new Object [newcapacity];

        int currentFirst = onePlus(nextFirst);
        int currentLast = oneMinus(nextLast);

        if (currentFirst < currentLast) {
            int length = currentLast - currentFirst + 1;
            System.arraycopy(items, currentFirst, newItems, 0, length);
            nextFirst = newcapacity - 1;
            nextLast = length;
        }else{
            int lengthFirsts = capacity - currentFirst;
            int newCurrentFirst = newcapacity - lengthFirsts;
            int lengthLasts = nextLast;
            System.arraycopy(items, currentFirst, newItems, newCurrentFirst, lengthFirsts);
            System.arraycopy(items, 0, newItems, 0, lengthLasts);
            nextFirst = newcapacity - lengthFirsts - 1;
        }

        capacity = newcapacity;
        items = newItems;
    }



    /**check if the item need to be expended: 1) if the size == capacity, if so then execute it */
    private void expand() {
        if (size == capacity){
            int newcapacity = capacity * eFactor;
            resize(newcapacity);
        }
    }

    /**check if the items needs to be conracted: 1) if the ratioNow < 25%(mRatio), the contract the whole capacity */
    private void contract() {
        double ratioNow = size/capacity;
        if (capacity >= mCapacity && ratioNow < mRatio){
            int newcapacity = capacity/ cFactor;
            resize(newcapacity);
        }
    }


    public void addFirst(Item x){
        items[nextFirst] = x;
        nextFirst = oneMinus(nextFirst);
        size += 1;

        expand(); //to check if the array is full or need to be resizing;
    }


    /**insert x into the back of the list*/
    public void addLast(Item x){
        items[nextLast] = x;
        nextLast = onePlus(nextFirst);
        size += 1;

        expand();


    }

    public Item removeFrist() {
        if (isEmpty()){
            return null;
        }

        int currFirst = onePlus(nextFirst);
        Item removed = items[currFirst];
        items[currFirst] = null;
        nextFirst = currFirst;
        size -= 1;

        contract();

        return removed;

    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }

        int currentLast = oneMinus(nextLast);
        Item removed = items[currentLast];
        items[currentLast] = null;
        nextLast = currentLast;
        size -= 1;

        return removed;
    }




    // return the item at the index of x;
    public Item get(int index) {
        if (index >= size) {
            return null;
        } else {
            int indexFromBegin = nextFirst + index + 1;
            if (indexFromBegin >= capacity){
                indexFromBegin -= capacity;
            }
            return items[indexFromBegin];
        }
    }


    /****
     *
     *
     * public static void main(String args[]){
     *         System.out.println("text is running.\n" );
     *
     *
     *
     *
     * for the test*/




    }






