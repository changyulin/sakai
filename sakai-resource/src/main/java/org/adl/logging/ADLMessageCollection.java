/*******************************************************************************

ADL SCORM 2004 4th Edition Sample Run-Time Environment

The ADL SCORM 2004 4th Ed. Sample Run-Time Environment is licensed under
Creative Commons Attribution-Noncommercial-Share Alike 3.0 United States.

The Advanced Distributed Learning Initiative allows you to:
  *  Share - to copy, distribute and transmit the work.
  *  Remix - to adapt the work. 

Under the following conditions:
  *  Attribution. You must attribute the work in the manner specified by the author or
     licensor (but not in any way that suggests that they endorse you or your use
     of the work).
  *  Noncommercial. You may not use this work for commercial purposes. 
  *  Share Alike. If you alter, transform, or build upon this work, you may distribute
     the resulting work only under the same or similar license to this one. 

For any reuse or distribution, you must make clear to others the license terms of this work. 

Any of the above conditions can be waived if you get permission from the ADL Initiative. 
Nothing in this license impairs or restricts the author's moral rights.

*******************************************************************************/
package org.adl.logging;

import java.util.LinkedList;
import java.util.List;

import org.adl.util.LogMessage;

/**
 * Superclass that holds the majority of the functionality for the
 * LogMessageCollections.
 *   
 * @author ADL Technical Team
 */
public class ADLMessageCollection
{
    /**
     * LinkedList used to hold the messages bound for the Summary Log
     */ 
    private final transient List<LogMessage> mMessages;
    
    /**
     * Holds the value to indicate whether or not the SummaryLogWriter has been
     * told to wait()
     */ 
    private transient boolean isWaiting = false;
    
    /**
     * Default Constructor - declares the LinkedList that acts as the collection
     * object/queue
     */
    public ADLMessageCollection()
    {
       mMessages = new LinkedList<LogMessage>();
    }
    
    /**
     * This method adds a LogMessage object to the end of the LinkedList. If the 
     * LogWriter Thread was told to wait, notify it that there are
     * messages queued
     * 
     * @param iMessage the message to be added to the collection 
     */
    public void addMessage(final LogMessage iMessage)
    {
       synchronized(this)
       {
          mMessages.add(iMessage);

          // If the SummaryLogWriter has been told to wait, let it know that there
          //  are messages waiting to be written to the Summary Log
          if(isWaiting)
          {
             notifyAll();
             isWaiting = false;
          }
       }
    }
    
    public void addAll(final List<LogMessage> iMessages)
    {
       synchronized(this)
       {
          mMessages.addAll(iMessages);

          // If the SummaryLogWriter has been told to wait, let it know that there
          //  are messages waiting to be written to the Summary Log
          if(isWaiting)
          {
             notifyAll();
             isWaiting = false;
          }
       }
    }
    
    /**
     * This method removes and returns the first LogMessage object in the
     * LinkedList. If there are messages waiting to be written to 
     * the Log. If there are NO Messages in the list waiting to be written tell
     * the LogWriter Thread to wait
     * 
     * @return LogMessage The first message in the queue
     */
    public LogMessage getMessage()
    {
       synchronized(this)
       {
          return mMessages.remove(0);
       }
    }
    
    /**
     * This method returns true if there are messages waiting to be written to 
     * the Summary Log, and false if there aren't. If there are NO Messages in 
     * the list waiting to be written tell the SummaryLogWriter Thraed to wait
     * 
     * @return boolean this method returns whether or not there are messages
     *         in the queue
     */
    public boolean hasMessages()
    {
       synchronized(this)
       {
          if (mMessages.isEmpty())
          {
             // If there aren't any messages queued to be written to the Summary 
             //  Log tell the SummaryLogWriter to wait until notified and set the 
             //  mSummaryLogWriterIsWaiting flag to true
             try
             {
                isWaiting = true;
                wait();
             }
             catch(InterruptedException ie)
             {
                // This exception is only thrown in Java 1.6.0_10 and greater.
                // All logs complete correctly, the browser simply does not end
                // the thread correctly when the browser exits.
             
                // System.out.println("Exception in SummaryLogMessageCollection.hasMessages():" + ie );          
             }
          }// end if
          
          return !mMessages.isEmpty();
       }
    }
    
    /**
     * Returns the size of the collection.
     * 
     * @return Size of the collection.
     */
    public int getSize()
    {
       return mMessages.size();
    }
}