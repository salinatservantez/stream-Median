/**
 *This program calculates the median for a stream of data while using priority queues and constructors.
 * @author Salina Servantez
 * @edu.uwp.cs.340.section001
 *  * @edu.uwp.cs.340.assignment2-Stream Median
 *  * @bugs none
 */

import java.util.*;

public class StreamMedian {

    //public double median;

    PriorityQueue<Integer> bigger = new PriorityQueue<>();
    PriorityQueue<Integer> smaller = new PriorityQueue<>(new Compare());

    /**
     * main method provided
     * @param args

    public static void main(String[]args)
    {
        StreamMedian sm = new StreamMedian();
        Random random = new Random(0);
        for (int i = 0; i < 20; i++) {
            int r = random.nextInt(100);
            sm.insert(r);
            double x = sm.getMedian();
            System.out.println(x + " ");
        }

        System.out.println();
    }
*/
    public StreamMedian(){
        //this.median = median;
    }

    /**
     * This method adds the next element of the stream to the data used for calculating the median.
     * @param i
     */
    public void insert(Integer i) {
        if (smaller.size() == 0)
            smaller.add(i);
       else if (i >= smaller.peek()) {
           bigger.add(i);
       } else
           smaller.add(i);

        //rebalance if neccesary

        if(smaller.size()-bigger.size()==2) {
            bigger.add(smaller.poll());
        } else if(bigger.size()-smaller.size()==2){
            smaller.add(bigger.poll());
        }
    }


    /**
     * This his method finds and returns the median of all the data inserted.
     * @return bigger peek or smaller peek value
     */
    public double getMedian() {
        if (bigger.size() == 0 && smaller.size() == 0) {
            return 0;
        } else if (bigger.size() == 0) {
            return smaller.peek();
        } else if (smaller.size() == 0) {
            return bigger.peek();
        }

        if(smaller.size()==bigger.size())
            return(smaller.peek()+bigger.peek())/ 2.0;
        else if(smaller.size()<bigger.size())
            return bigger.peek();
        else
            return smaller.peek();
    }

    /**
     * This method uses a comparator to compare medians and return a specific order.
     */
    public static class Compare implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2){
            if(i1 < i2)
                return 1;
            else if (i1 > i2)
                return -1;
            else
                return 0;
        }
    }
}
