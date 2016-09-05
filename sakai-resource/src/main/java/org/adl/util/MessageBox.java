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

package org.adl.util;

import javax.swing.JOptionPane;

/**
 * <strong>Filename:</strong><br>
 * MessageBox.java<br><br>
 *
 * <strong>Description:</strong><br>
 * A <code>MessageBox</code> abstracts the implementation of messages displayed
 * in a dialog box.  Messages are expressed in four different categories:
 * <ul>
 *    <li>
 *       SEVERE - LogMessage notifying the user that a there is a critical problem
 *                preventing the program from continuing.
 *    </li>
 *    <li>
 *       ERROR - LogMessage notifying the user that there is a substantial problem
 *               that may result in un-expected results.
 *    </li>
 *    <li>
 *       WARNING - LogMessage notifying the user that there is a potential problem
 *                 that may result in un-expected results.
 *    </li>
 *    <li>
 *       INFO - LogMessage notifying the user of imparitive information.
 *    </li>
 * </ul>
 *
 * <strong>Side Effects:</strong><br>
 * If the <code>MessageBox.SEVERE</code> option is used, the program will
 * terminate.
 *
 * @author ADL Technical Team
 */
public final class MessageBox
{
   /**
    * Static Variable for a SEVERE ERROR message
    */
   public static final int SEVERE  = 0;
   
   /**
    * Static Variable for a ERROR message
    */
   public static final int ERROR   = 1;
   
   /**
    * Static Variable for a WARNING message
    */
   public static final int WARNING = 2;
   
   /**
    * Static Variable for a INFORMATIONAL message
    */
   public static final int INFO    = 3;

   /**
    * This default constructor is private and shall not be used.
    */
   private MessageBox()
   {
      // Default constructor - nothing explicitly needed
   }

   /**
    * This constructor interprets the type of message wanted and executes the
    * appropriate dialog box.
    *
    * @param iType   The type of dialog box desired to display the message:
    *                <ol>
    *                  <li><code>SEVERE</code> dialog box.</li>
    *                  <li><code>ERROR</code> dialog box.</li>
    *                  <li><code>WARNING</code> dialog box.</li>
    *                  <li><code>INFO</code> dialog box.</li>
    *                </ol>
    * @param iMsg - The message to be displayed.
    * @param iTtl - The title of this message.
    */
   public static void showMessage( final int iType, final String iMsg, final String iTtl )
   {
      switch( iType )
      {
         case SEVERE:
         {
            severeBox( iMsg, iTtl );
            break;
         }
         case ERROR:
         {
            errorBox( iMsg, iTtl );
            break;
         }
         case WARNING:
         {
            warningBox( iMsg, iTtl );
            break;
         }
         case INFO:
         {
            infoBox( iMsg, iTtl );
            break;
         }
         default:
         {
            System.out.println("message type was not specified");
         }
      }
   }


   /**
    * This method displays a dialog box with the ERROR_MESSAGE symbol and exits
    * the program.
    *
    * @param iMsg - The message to be displayed.
    * @param iTtl - The title of this message.
    */
   public static void severeBox( final String iMsg, final String iTtl )
   {
      JOptionPane.showMessageDialog( null, iMsg, iTtl,
                                     JOptionPane.ERROR_MESSAGE );
      System.exit(0);
   }


   /**
    * This method displays a dialog box with the ERROR_MESSAGE symbol.
    *
    * @param iMsg - The message to be displayed.
    * @param iTtl - The title of this message.
    */
   public static void errorBox( final String iMsg, final String iTtl )
   {
      JOptionPane.showMessageDialog( null, iMsg, iTtl,
                                     JOptionPane.ERROR_MESSAGE );
   }


   /**
    * This method displays a dialog box with the WARNING_MESSAGE symbol.
    *
    * @param iMsg - The message to be displayed.
    * @param iTtl - The title of this message.
    */
   public static void warningBox( final String iMsg, final String iTtl )
   {
      JOptionPane.showMessageDialog( null, iMsg, iTtl,
                                     JOptionPane.WARNING_MESSAGE );
   }


   /**
    * This method displays a dialog box with the INFORMATION_MESSAGE symbol.
    *
    * @param iMsg - The message to be displayed.
    * @param iTtl - The title of this message.
    */
   public static void infoBox( final String iMsg, final String iTtl )
   {
      JOptionPane.showMessageDialog( null, iMsg, iTtl,
                                     JOptionPane.INFORMATION_MESSAGE );
   }
}