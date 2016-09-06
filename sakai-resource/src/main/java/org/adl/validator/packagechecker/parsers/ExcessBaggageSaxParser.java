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

******************************************************************************/
package org.adl.validator.packagechecker.parsers;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.adl.validator.util.ValidatorMessage;
import org.xml.sax.Attributes;

/**
 * This parser obtains the information needed to create a list of files
 * referenced by the xml instance
 * 
 * @author ADL Technical Team
 *
 */
public class ExcessBaggageSaxParser extends ValidatorSaxParser
{   
   /**
    * String representing the current element on which the parse is located
    */
   protected String mCurrentElement;
   
   /**
    * A String containing the character data of a given element
    */
   private String mCharData;
        
   /**
    * Contains a set of all used file hrefs
    */
   final private Set<String> mFileHrefs;
   
   /**
    * Array to hold the three possible xml:base values which can be applied to
    * a given href
    */
   private String[] mXMLBase;
  
   /**
    * The default constructor
    */
   public ExcessBaggageSaxParser()
   {
      super();
      mParseSuccess = true;
      mParseMessages = new ArrayList<ValidatorMessage>();
      mCurrentElement = "";
      this.configureParser();
      
      // Create space for xml:base values and set them to null
      mXMLBase = new String[BASE_SIZE];
      mXMLBase[BASE_ONE] = "";
      mXMLBase[BASE_TWO] = "";
      mXMLBase[BASE_THREE] = "";
      
      mCharData = "";
      
      mFileHrefs = new LinkedHashSet<String>();
   }
   
   /** 
    * This method will analyze the attributes, names, and namespace of a given element
    * 
    * @param iNamespaceURI Is a String value which holds the namespace of the element
    * @param iLocalName Is a String value containing the local name of the element
    * @param iRawName Is a String value containing the raw name of the element, the
    * element namepace prefix is included
    * @param iAttrs Ia a Attributes value containing the elements attributes
    */
   public void startElement(final String iNamespaceURI, final String iLocalName, 
         final String iRawName, final Attributes iAttrs) 
   {
      if ( iLocalName.equals(LOCATION) && iNamespaceURI.equals(ADLCP) )
      {
         // This is a special case, we must only get location elements belonging to the 
         // ALDCP namespace
         mCurrentElement = "adlcp:location";
      }
      else
      {
         mCurrentElement = iLocalName;
      }

      // Reset for next element
      
      if ( iLocalName.equals(MANIFEST) && iNamespaceURI.equals(IMSCP) )
      {
         mXMLBase[BASE_ONE] = "";
         mXMLBase[BASE_TWO] = "";
         mXMLBase[BASE_THREE] = "";
      }
      
      
      
         
         // Collect all possible XML:Base attributes
         if ( ( iLocalName.equals(MANIFEST) && iNamespaceURI.equals(IMSCP) ) || 
              ( iLocalName.equals(RESOURCES) && iNamespaceURI.equals(IMSCP) )|| 
              ( iLocalName.equals(RESOURCE) && iNamespaceURI.equals(IMSCP) ) )
         {
            
            String xmlBase = "";
            mXMLBase[BASE_THREE] = "";
            if (iAttrs != null)
            {
               final int len = iAttrs.getLength();
               for (int i = 0; i < len; i++)
               {
                  if ( iAttrs.getLocalName(i).equals("base") && iAttrs.getURI(i).equals(XML) )
                  {
                     xmlBase = iAttrs.getValue(i);
                     
                     if ( iLocalName.equals(MANIFEST) )
                     {
                        mXMLBase[BASE_ONE] = xmlBase;
                     }
                     else if ( iLocalName.equals(RESOURCES) )
                     {
                        mXMLBase[BASE_TWO] = xmlBase;
                     }
                     else if ( iLocalName.equals(RESOURCE) )
                     {
                        mXMLBase[BASE_THREE] = xmlBase;              
                     }
                     
                     xmlBase = "";
                  }                  
               }            
            }
         }
         
         // Obtain the href from the resource element AFTER the xml:base is obtained
         if ( iLocalName.equals(RESOURCE) && iNamespaceURI.equals(IMSCP) && (iAttrs != null) )
         {
            final int len = iAttrs.getLength();
            for (int i = 0; i < len; i++)
            {
               // Save schemaLocation to variable
               if ( iAttrs.getLocalName(i).equals(HREF))
               {
                  final String filePath = mXMLBase[BASE_ONE] + mXMLBase[BASE_TWO] + 
                  mXMLBase[BASE_THREE] + iAttrs.getValue(i);
                  mFileHrefs.add(filePath);
               }
            }
         }
            
         // Collect all possible file references
         String filePath = "";
            
         if ( iLocalName.equals(FILE) && iNamespaceURI.equals(IMSCP) && (iAttrs != null))
         {
            final int len = iAttrs.getLength();
            for (int i = 0; i < len; i++)
            {
               // Save schemaLocation to variable
               if ( iAttrs.getLocalName(i).equals(HREF))
               {
                  filePath = mXMLBase[BASE_ONE] + mXMLBase[BASE_TWO] + mXMLBase[BASE_THREE] + 
                  iAttrs.getValue(i);
                  mFileHrefs.add(filePath);
               }
            }   
         }
   }
   
   /** 
    * This method is called when we reach the end tag of an element
    * 
    * @param iNamespaceURI Is a String value which holds the name-space of the element
    * @param iLocalName Is a String value containing the local name of the element
    * @param iName Is a String value containing the raw name of the element
    */
   public void endElement(final String iNamespaceURI, final String iLocalName, final String iName)
   {
      mCurrentElement = "";

      // If character data exists, add the path to the list
      if ( !"".equals(mCharData) )
      {
         mCharData = mXMLBase[BASE_ONE] + mXMLBase[BASE_TWO] + mXMLBase[BASE_THREE] + mCharData;
         mFileHrefs.add(mCharData);
         mCharData = "";
      }
   }
   
   /** Characters. */   
   /** This method will obtain the value of an element during a given parse
    * 
    * @param iCh Contains the element value
    * @param iStart Contains the starting index
    * @param iLength Contains the value length
    * 
    */
   public void characters(final char iCh[], final int iStart, final int iLength) 
   {
        if ( "adlcp:location".equals(mCurrentElement) ) 
        {
           mCharData = mCharData + (new String(iCh, iStart, iLength));
        }     
   }
   
   /**
    * This method returns the completed list of file URIs
    * 
    * @return a Set of all file URIs used in the XML file
    */
   public Set<String> getFileHrefs()
   {
      return mFileHrefs;
   }


}
