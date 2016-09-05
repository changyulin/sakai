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

package org.adl.datamodels;

import java.io.Serializable;

/**
 * <strong>Filename:</strong> DMErrorCodes.java<br><br>
 * 
 * <strong>Description:</strong><br><br>
 * Enumeration of all abstract data model errors.
 * 
 * @author ADL Technical Team
 */
public class DMErrorCodes implements Serializable
{
   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>No Error
    * <br><b>0</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int NO_ERROR                 =    0;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>General Argument Error
    * <br><b>201</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int GEN_ARGUMENT_ERROR       =  201;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>General Get Failure
    * <br><b>301</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int GEN_GET_FAILURE          =  301;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>General Set Failure
    * <br><b>351</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int GEN_SET_FAILURE          =  351;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Undefined Data Model Element
    * <br><b>401</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int UNDEFINED_ELEMENT        =  401;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Unimplemented Data Model Element
    * <br><b>402</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int NOT_IMPLEMENTED          =  402;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Data Model Element Value Not Initialized
    * <br><b>403</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int NOT_INITIALIZED          =  403;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Data Model Element Is Read Only
    * <br><b>404</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int READ_ONLY                =  404;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Data Model Element Value Is Write Only
    * <br><b>405</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int WRITE_ONLY               =  405;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Data Model Element Type Mismatch
    * <br><b>406</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int TYPE_MISMATCH            =  406;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Data Model Element Value Value Out Of Range
    * <br><b>407</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int VALUE_OUT_OF_RANGE       =  407;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Data Model Dependency Not Established
    * <br><b>408</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int DEP_NOT_ESTABLISHED      =  408;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Data Model Element Does Not Have Children
    * <br><b>1000</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int DOES_NOT_HAVE_CHILDREN   = 1000;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Data Model Element Does Not Have Count
    * <br><b>1001</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int DOES_NOT_HAVE_COUNT      = 1001;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Data Model Element Does Not Have Version
    * <br><b>1002</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int DOES_NOT_HAVE_VERSION    = 1002;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Data Model Element Collection Set Out Of Order
    * <br><b>1003</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int SET_OUT_OF_ORDER         = 1003;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Data Model Element Collection Out Of Range
    * <br><b>1004</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int OUT_OF_RANGE             = 1004;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Data Model Element Not Specified
    * <br><b>1005</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int ELEMENT_NOT_SPECIFIED    = 1005;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Uniqueness Constraint Violated
    * <br><b>1006</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int NOT_UNIQUE               = 1006;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Maximum number of records exceeded
    * <br><b>1007</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int MAX_EXCEEDED             = 1007;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Invalid Argument
    * <br><b>1008</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int INVALID_ARGUMENT         = 1008;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Attempt to overwrite an existing ID
    * <br><b>1009</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int OVERWRITE_ID             = 1009;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Invalid Set of a Keyword 
    * <br><b>2000</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int SET_KEYWORD              = 2000;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Internal Processing Error -- Invalid Data Model Request
    * <br><b>9000</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int INVALID_REQUEST          = 9000;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Internal Processing Error -- Unknown Exception
    * <br><b>9001</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int UNKNOWN_EXCEPTION        = 9001;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>SPM Exceeded
    * <br><b>9002</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int SPM_EXCEEDED             = 9002;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Nothing to Compare
    * <br><b>9500</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int COMPARE_NOTHING           = 9500;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Equal
    * <br><b>9501</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int COMPARE_EQUAL             = 9501;

   /**
    * Enumeration of possible implementation specific data model exceptions.
    * <br>Not Equal
    * <br><b>9502</b>
    * <br><br>[DATA MODEL IMPLEMENTATION CONSTANT]
    */
   public static final int COMPARE_NOTEQUAL          = 9502;

} // end DMErrorCodes