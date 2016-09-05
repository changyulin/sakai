/******************************************************************************

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

******************************************************************************/
package org.adl.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

/**
 * <strong>Filename:</strong>
 * MessageCollection.java<br><br>
 *
 * <strong>Description:</strong><br>
 * A <code>MessageCollection</code> handles collections of <code>LogMessage</code>
 * objects.  This class is implemented as a singleton so that it can be created
 * only once and accessed at anytime.<br>
 *
 * <br><strong>Design Issues:</strong><br>
 * Designed as a singleton so multiple objects can use the same instance<br><br>
 *
 * <strong>Implementation Issues:</strong><br>
 * The <code>Vector</code> data structure is used to take advantage of its
 * ordered incremental addition of elements, growable array and capacity
 * and synchronized access capabilities.
 *
 * @author ADL Technical Team
 */
public class MessageCollection
{
   /**
    * linked list of all messages
    */
   private static LinkedList mAllMessages;

   /**
    * The one and only instance of the <code>MessageCollection</code>.
    */
   private static MessageCollection mInstance = new MessageCollection();
   
   /**
    * Describes if 'add' methods should accept new messages to the collection.
    */
   private boolean mAccept = true;

   /**
    * flag indicating if the DetailedLogWriter Thread is in a wait status
    */
   private boolean mDetailedLogWriterIsWaiting = false;

   /**
    * The collection of stored LogMessage objects.
    */
   private Vector mMessages;

   /**
    * A list of MessageTypes the user wants filtered on.
    */
   private Vector mMessageTypeProperties;

   /**
    * Default Constructor.  Initializes the attributes of this class.  This is
    * declared as private to ensure that an instance can not be created by
    * anyone but itself.
    */
   private MessageCollection()
   {
      mMessages = new Vector();
      mAllMessages = new LinkedList();
   }
   
   /**
    * Returns the instance of the class.
    *
    * @return The MessageCollection object.
    */
   public static MessageCollection getInstance()
   {
      return mInstance;
   }

   /**
    * Appends all of the elements in the specified Collection to the end
    * of this collection, in the order that they are returned by the
    * specified Collection's Iterator.
    *
    * <br><br>The behavior of this operation is undefined if the specified Collection is
    * modified while the operation is in progress. (This implies that the
    * behavior of this call is undefined if the specified Collection is this
    * Vector, and this Vector is nonempty.)
    *
    * @param iMessages elements to be appended to this collection.
    *
    * @return True if the collection changed as a result of the call.
    */
   public synchronized boolean add(Collection iMessages)
   {
      boolean result = false;

      if ( mAccept )
      {

         result = true;                                      
         Vector messages = new Vector(iMessages);
         int messagesSize = messages.size();
         int messageTypePropertiesSize = mMessageTypeProperties.size();
         LogMessage currentMessage;
         int currentMessageType;
         int messageType;

         // cycle through the given collection of LogMessage's sequentially
         for ( int i = 0; i < messagesSize; i++ )
         {
            currentMessage = (LogMessage)messages.get(i);
            currentMessageType = currentMessage.getMessageType();

            // cycle through the wanted MessageTypes and add them to the
            // collection
            for ( int j = 0; j < messageTypePropertiesSize; j++ )
            {

               messageType = 
               ((Integer)mMessageTypeProperties.get(j)).intValue();

               if ( currentMessageType == messageType )
               {
                  result = messages.add( currentMessage ) && result;
                  break;
               }
            }
         }
      }

      return result;
   }


   /**
    * Appends the specified <code>LogMessage</code> object to the end of the
    * collection.  The LogMessage will be added whether or not the
    * MessageTypeProperties filter is set.
    *
    * @param iMessage The <code>LogMessage</code> object to be appended to this
    *                 collection.
    */
   public synchronized void add ( LogMessage iMessage )
   {
      //addMessage(iMessage);
      //return true;
      //return mAllMessages.add( iMessage );
      mMessages.add( iMessage );
   }

   /**
    * Adds a message to the end of the LinkedList. If the DetailedLogWriter has
    * been told to wait, notify it so it can start to pull and write messages
    * 
    * @param iMessage The message to be added to the queue
    */
    public synchronized void addMessage(LogMessage iMessage)
    {
       mAllMessages.add(iMessage);

      // If the SummaryLogWriter has been told to wait, let it know that there
      //  are messages waiting to be written to the Summary Log
      if(mDetailedLogWriterIsWaiting)
      {
         notify();
         mDetailedLogWriterIsWaiting = false;
      } 
   }

   /**
    * Removes all of the <code>LogMessage</code> objects from this 
    * collection.
    *
    * <br><br>The collection will be empty after this call returns (unless it throws an
    * exception).
    */
   public void clear()
   {
      // un-pause if currently paused
      mAccept = true;

      mMessages.clear();
   }

   /**
    * Returns the <code>LogMessage</code> at the specified position in this
    * collection.
    *
    * @param iIndex index of element to return.
    *
    * @return <code>LogMessage</code> at the specified index.
    */
   public LogMessage get( int iIndex )
   {
      return(LogMessage)mMessages.get( iIndex );
   }

   /**
    * Returns a <code>Collection</code> of the Messages based on the
    * <code>MessageType</code> properties set within this object.
    *
    * <br><br>The <code>MessageTypeProperties</code> will be empty after this call
    * returns (unless it throws an exception).
    *
    * @return <code>Collection</code> of <code>Messages</code>
    */
   public Collection getByType()
   {
      Vector resultMessages = new Vector();
      int messagesSize = mMessages.size();
      int messageTypePropertiesSize = mMessageTypeProperties.size();
      LogMessage currentMessage;
      int currentMessageType;
      int messageType;
      boolean result = true;

      // cycle through the mMessages and get the Messages sequentially
      for ( int i = 0; i < messagesSize; i++ )
      {
         currentMessage = (LogMessage)mMessages.get(i);
         currentMessageType = currentMessage.getMessageType();

         // cycle through the wanted MessageTypes and add then to the
         // resultMessages
         for ( int j = 0; j < messageTypePropertiesSize; j++ )
         {
            messageType = ((Integer)mMessageTypeProperties.get(j)).intValue();

            if ( currentMessageType == messageType )
            {
               result = resultMessages.add(currentMessage) && result;
               break;
            }
         }
      }

      if ( result )
      {
         clearMessageTypeProperties();
      }

      return resultMessages;
   }

   /**
    * Removes the first LogMessage object from the LinkedList and returns it
    * 
    * @return LogMessage object
    */
    public synchronized LogMessage getMessage()
    {
       // Remove the first node of the linked list and return it
       return (LogMessage)mAllMessages.removeFirst();
    }

   /**
    * This method determines if there are messages queued to be written to the 
    * log. If there ARE NOT any messages waiting to be written to the log, 
    * tell the DetailedLogWriter to wait.
    * 
    * @return True if there are messages queued, false if not
    */
    public synchronized boolean hasMessages()
    {
       if(!(mAllMessages.size() > 0))
       {    
          // If there aren't any messages queued to be written to the Detailed Log
          //  tell the DetailedLogWriter to wait until notified and set the 
          //  DetailedLogWriterIsWaiting flag to true so the addMessage method knows
          //  to notify the DetailedLogWriter to wake up and smell the coffee
         try
         {
            mDetailedLogWriterIsWaiting = true;
            wait();                            
         }
         catch(InterruptedException ie)
         {
            System.out.println("InterruptedException in MessageCollection." +
               "hasMessages(): " + ie);
         }
         catch(Exception e)
         {
            System.out.println("Exception in " +
                            "MessageCollection.hasMessages():" + e );
         }
      }// end if
      
      return mAllMessages.size() > 0;
   }

   /**
    * Tests to see if this collection has no components.
    *
    * @return true if and only if this vector has no components, that is, its
    * size is zero; false otherwise.
    */
   public boolean isEmpty()
   {
      return mMessages.isEmpty();
   }

   /**
    * Pauses the collection so that 'add' methods are not accpeted.
    * This method does not affect the messages currently in the
    * collection.
    * 
    * @param iPause  Indicates if the collction should be 'paused'.
    */
   public void pause(boolean iPause)
   {
      mAccept = !iPause;
   }

   /**
    * Appends a MessageType to the properties list.
    *
    * @param iMessageType type of messages to filter on when the list is
    * accessed.
    *
    * @return true if the properties changed as a result of the call.
    */
   public boolean setMessageTypeProperties( int iMessageType )
   {
      return mMessageTypeProperties.add(new Integer(iMessageType));
   }

   /**
    * Returns the number of components in the collection.
    *
    * @return the number of components in the collection.
    */
   public int size()
   {
      return mMessages.size();
   }

   /**
    * Removes all of the <code>MessageType</code>'s from the list of message
    * types.
    *
    * <br><br>The <code>MessageTypeProperties</code> will be empty after this 
    * call returns (unless an exception is encountered).
    */
   private void clearMessageTypeProperties()
   {
      mMessageTypeProperties.clear();
   }

}// end of Class MessageCollection