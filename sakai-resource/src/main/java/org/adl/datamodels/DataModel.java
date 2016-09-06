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


/**
 * Defines the interface to a run-time data model that is managed for a SCO.
 * <br><br>
 * 
 * <strong>Filename:</strong> DataModel.java<br><br>
 * 
 * <strong>Description:</strong><br>
 * Provides a standard interface to access a data model's elments.<br><br>
 * 
 * <strong>Design Issues:</strong><br>
 * This is an abstract class.  Specific run-time data models should provide a 
 * concrete implementation.  The <code>DMFactory</code> should be updated to    
 * provide access to all concrete implementations.<br><br>
 * 
 * <strong>Implementation Issues:</strong> None<br><br>
 * 
 * <strong>Known Problems:</strong> None<br><br>
 * 
 * <strong>Side Effects:</strong> None<br><br>
 * 
 * <strong>References:</strong> SCORM 2004<br>
 *  
 * @author ADL Technical Team
 */
public abstract class DataModel
{

   /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
   
    Public Methods
   
   -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

   /**
    * Constant for smallest permitted maximum of 4000.
    */
   public static final int LONG_SPM = 4000;
   
   /**
    * Constant for smallest permitted maximum of 250.
    */
   public static final int SHORT_SPM = 250;

   /**
    * Describes this data model's binding string.
    * 
    * @return This data model's binding string.
    */
   public abstract String getDMBindingString();


   /**
    * Provides the requested data model element.
    * 
    * @param iElement Describes the requested element's dot-notation bound name.
    * 
    * @return The <code>DMElement</code> corresponding to the requested element
    *         or <code>null</code> if the element does not exist in the data
    *         model.
    */
   public abstract DMElement getDMElement(String iElement);


   /**
    * Performs data model specific initialization.
    * 
    * @return An abstract data model error code indicating the result of this
    *         operation.
    */
   public abstract int initialize();


   /**
    * Performs data model specific termination.
    * 
    * @return An abstract data model error code indicating the result of this
    *         operation.
    */
   public abstract int terminate();


   /**
    * Processes a SetValue() request against this data model.
    * 
    * @param iRequest The request (<code>DMRequest</code>) being processed.
    * 
    * @return An abstract data model error code indicating the result of this
    *         operation.
    */
   public abstract int setValue(DMRequest iRequest);
   
   /**
    * Processes a SetValue() request against this data model.
    * 
    * @param iRequest The request (<code>DMRequest</code>) being processed.
    * 
    * @param iSetBySCO Identifies if the value is being set by the SCO or not. 
    * 
    * @return An abstract data model error code indicating the result of this
    *         operation.
    */
   public abstract int setValue(DMRequest iRequest, boolean iSetBySCO);

   /**
    * Processes an equals() request against this data model. Compares two 
    * values of the same data model element for equality.
    * 
    * @param iRequest The request (<code>DMRequest</code>) being processed.
    * 
    * @param iValidate Indicates if the provided value should be validated.
    * 
    * @return An abstract data model error code indicating the result of this
    *         operation.
    */
   public abstract int equals(DMRequest iRequest, boolean iValidate);


   /**
    * Processes an equals() request against this data model. Compares two 
    * values of the same data model element for equality.
    * 
    * @param iRequest The request (<code>DMRequest</code>) being processed.
    * 
    * @return An abstract data model error code indicating the result of this
    *         operation.
    */
   public int equals(DMRequest iRequest)
   {
      return equals(iRequest, true);
   }


   /**
    * Processes a validate() request against this data model. Checks the value
    * provided for validity for the specified element.
    * 
    * @param iRequest The request (<code>DMRequest</code>) being processed.
    * 
    * @return An abstract data model error code indicating the result of this
    *         operation.
    */
   public abstract int validate(DMRequest iRequest);


   /**
    * Processes a GetValue() request against this data model.
    * 
    * @param iRequest The request (<code>DMRequest</code>) being processed.
    * 
    * @param oInfo    Provides the value returned by this request.
    * 
    * @return An abstract data model error code indicating the result of this
    *         operation.
    */
   public abstract int getValue(DMRequest iRequest, DMProcessingInfo oInfo);


   /**
    * Displays the contents of the entire data model.
    */
   public abstract void showAllElements();


} // end DataModel
