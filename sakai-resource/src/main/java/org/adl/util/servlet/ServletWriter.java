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

package org.adl.util.servlet;

import java.net.*;
import java.io.*;
import org.adl.util.debug.DebugIndicator;


/**
 * Provides a means to 'POST' multiple serialized objects to a servlet.<br><br>
 *
 * <strong>Filename:</strong> ServletWriter<br><br>
 *
 * <strong>Description:</strong><br>
 * This class provides a method of posting multiple serialized objects to a
 * Java servlet and getting objects in return. This code was inspired by code
 * samples from the book 'Java Servlet Programming' by Jason Hunter and William
 * Crawford (O'Reilly & Associates. 1998).<br><br>
 *
 * <strong>Design Issues:</strong><br>
 * This implementation is intended to be used by the SCORM 2004 4th Edtion Sample
 * RTE. <br>
 * <br>
 *
 * <strong>Implementation Issues:</strong><br><br>
 *
 * <strong>Known Problems:</strong><br><br>
 *
 * <strong>Side Effects:</strong><br><br>
 *
 * <strong>References:</strong><br>
 * <ul>
 *     <li>SCORM 2004 4th Edition
 * </ul>
 *
 * @author ADL Technical Team
 */
public class ServletWriter
{

   /**
    * This controls display of log messages to the java console
    */
   private static boolean _Debug = DebugIndicator.ON;


   /**
    * Provides a means to 'POST' multiple serialized objects to a servlet.
    *
    * @param iServlet   The URL of the target servlet.
    *
    * @param iObjs      A list of objects to be serialized during the POST.
    *
    * @return A stream of serialized objects.
    * @exception Exception
    */
   static public ObjectInputStream postObjects(URL iServlet,
                                               Serializable iObjs[])
                                               throws Exception
   {

      if ( _Debug )
      {
         System.out.println("In ServletWriter::postObjects()");
      }

      URLConnection con = null;

      try
      {
         if ( _Debug )
         {
            System.out.println("Opening HTTP URL connection to " +
                               "servlet.");
         }

         con = iServlet.openConnection();
      }
      catch ( Exception e )
      {
         System.out.println("e = 1");

         if ( _Debug )
         {
            System.out.println("Exception caught in " +
                               "ServletWriter::postObjects()");
            e.printStackTrace();
         }

         System.out.println(e.getMessage());
         throw e;
      }


      if ( _Debug )
      {
         System.out.println("HTTP connection to servlet is open");
         System.out.println("configuring HTTP connection properties");
      }

      con.setDoInput(true);
      con.setDoOutput(true);
      con.setUseCaches(false);
      con.setRequestProperty("Content-Type","text/plain");
      con.setAllowUserInteraction(false);

      // Write the arguments as post data
      ObjectOutputStream out = null;

      try
      {
         if ( _Debug )
         {
            System.out.println("Creating new http output stream");
         }

         out = new ObjectOutputStream(con.getOutputStream());

         if ( _Debug )
         {
            System.out.println("Created new http output stream.");
            System.out.println("Writing command and data to servlet...");
         }

         int numObjects = iObjs.length;

         if ( _Debug )
         {
            System.out.println ("Num objects: " + numObjects);
         }

         for ( int i = 0; i < numObjects; i++ )
         {
            out.writeObject( iObjs[i]);

            if ( _Debug )
            {
               System.out.println("Just wrote a serialized object on " +
                                  "output stream... " +
                                  iObjs[i].getClass().getName());
            }
         }
      }
      catch ( Exception e )
      {
         if ( _Debug )
         {
            System.out.println("Exception caught in " +
                               "ServletWriter::postObjects()");
            System.out.println(e.getMessage());
         }

         e.printStackTrace();
         throw e;
      }

      try
      {
         if ( _Debug )
         {
            System.out.println("Flushing Object Output Stream.");
         }
         out.flush();
      }
      catch ( IOException ioe )
      {
         if ( _Debug )
         {
            System.out.println("Caught IOException when calling " +
                               "out.flush()");
            System.out.println(ioe.getMessage());
         }

         ioe.printStackTrace();
         throw ioe;
      }
      catch ( Exception e )
      {
         if ( _Debug )
         {
            System.out.println("Caught Exception when calling " +
                               "out.flush()" );
            System.out.println(e.getMessage());
         }

         e.printStackTrace();
         throw e;
      }

      try
      {
         if ( _Debug )
         {
            System.out.println("Closing object output stream.");
         }
         out.close();
      }
      catch ( IOException  ioe )
      {
         if ( _Debug )
         {
            System.out.println("Caught IOException when calling " +
                               "out.close()");
            System.out.println(ioe.getMessage());
         }

         ioe.printStackTrace();
         throw ioe;
      }
      catch ( Exception e )
      {
         if ( _Debug )
         {
            System.out.println("Caught Exception when calling " +
                               "out.close()");
            System.out.println(e.getMessage());
         }

         e.printStackTrace();
         throw e;
      }

      ObjectInputStream in;

      try
      {
         if ( _Debug )
         {
            System.out.println("Creating new http input stream.");
         }

         in = new ObjectInputStream(con.getInputStream());
      }
      catch ( Exception e )
      {
         if ( _Debug )
         {
            System.out.println("Exception caught in " +
                               "ServletWriter::postObjects()");
            System.out.println( e.getMessage() );
         }
         e.printStackTrace();
         throw e;
      }

      return in;
   }

} // ServletWriter
