package com.assesment;

import java.util.concurrent.*;

class Producer implements Runnable 
{
    private BlockingQueue<Integer> queue;
 
    public Producer (BlockingQueue<Integer> queue)
    {
        this.queue = queue;
    }
 
    public void run()
    {
        try 
        {
            for (int i = 0; i < 10; i++) 
            {
                queue.put(produce());
 
                Thread.sleep(500);
            }
 
            queue.put(-1);
 
            System.out.println("Producer STOPPED.");
 
        }
        catch (InterruptedException ie) 
        {
            ie.printStackTrace();
        }
    }
 
    private Integer produce()
    {
        Integer number = new Integer((int) (Math.random() * 100));
 
 
        System.out.println("Producing number => " + number);
 
        return number;
    }
}
 
class Consumer implements Runnable
{
    private BlockingQueue<Integer> queue;
 
    public Consumer(BlockingQueue<Integer> queue) 
    {
        this.queue = queue;
    }
 
    public void run() 
    {
        try 
        {
 
            while (true)
            {
                Integer number = queue.take();
 
                if (number == null || number == -1) 
                {
                    break;
                }
 
                consume(number);
 
                Thread.sleep(1000);
            }
 
            System.out.println("Consumer STOPPED.");
        } 
        catch (InterruptedException ie) 
        {
            ie.printStackTrace();
        }
    }
 
    private void consume(Integer number) 
    {
 
        System.out.println("Consuming number <= " + number);
 
    }
}
public class Program 
{
    public static void main(String[] args)
    {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
 
        Thread producer = new Thread(new Producer(queue));
 
        Thread consumer = new Thread(new Consumer(queue));
 
        producer.start();
        consumer.start();
 
    }
}