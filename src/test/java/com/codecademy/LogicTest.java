package com.codecademy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.codecademy.logic.Logic;

/**
 * This class tests the methods in the Logic class for correctness.
 */
public class LogicTest {

     /**
     * Tests the dateTool method for a date that is out of range in a leap year (February 30, 2022).
     * Expects the method to return false.
     */
    @Test
    public void testDateFebInLeapYearOutOfRange(){
        //arrange
        Logic validator= new Logic();

        //act
        boolean valid = validator.dateTool(30, 2, 2022);

        //assert
        assertEquals(false, valid);
    }

    /**
     * Tests the dateTool method for a date that is within range in a leap year (February 12, 2022).
     * Expects the method to return true.
     */
    @Test
    public void testDateFebInLeapYearInRange(){
        //arrange
        Logic validator= new Logic();

        //act
        boolean valid = validator.dateTool(12, 2, 2022);

        //assert
        assertEquals(true, valid);
    }

     /**
     * Tests the dateTool method for a date that is within range in a non-leap year (November 23, 2022).
     * Expects the method to return true.
     */
    @Test
    public void testDateNovInRange(){
        //arrange
        Logic validator= new Logic();

        //act
        boolean valid = validator.dateTool(23, 11, 2022);

        //assert
        assertEquals(true, valid);
    }
   
     /**
     * Tests the dateTool method for a date that is out of range in a non-leap year (April 31, 2006).
     * Expects the method to return false.
     */
    @Test
    public void testDateAprOutOfRange(){
        //arrange
        Logic validator= new Logic();

        //act
        boolean valid = validator.dateTool(31, 4, 2006);

        //assert
        assertEquals(false, valid);
    }

    /**
     * Tests the mailTool method for an email address without an "@" symbol.
     * Expects the method to return false.
     */
    @Test
    public void validateMailAddressReturnsFalseNoAt(){
        //arrange
        Logic validator = new Logic();
        String mailAddress = "atnguyen.com";
        //act
        Boolean result = validator.mailTool(mailAddress);
        //assert
        assertEquals(false, result);
    }

    /**
     * Tests the mailTool method for an email address without a mailbox.
     * Expects the method to return false.
     */
    @Test
    public void validateMailAddressReturnsFalseNoMailbox(){
        //arrange
        Logic validator = new Logic();
        String mailAddress = "@mail.com";
        //act
        Boolean result = validator.mailTool(mailAddress);
        //assert
        assertEquals(false, result);
    }
    
    /**
     * Tests the mailTool method for an email address without a subdomain TLD delimiter.
     * Expects the method to return true.
     */
    @Test
    public void validateMailAddressReturnsTrueNoSubdomainTLDDelimiter(){
        //arrange
        Logic validator = new Logic();
        String mailAddress = "mail@mail.hfgs.com";
        //act
        Boolean result = validator.mailTool(mailAddress);
        //assert
        assertEquals(true, result);
    }

    /**
     * Tests the mailTool method for an email address without a subdomain.
     * Expects the method to return false.
     */
    @Test
    public void validateMailAddressReturnsFalseNoSubdomain(){
        //arrange
        Logic validator = new Logic();
        String mailAddress = "mail@.com";
        //act
        Boolean result = validator.mailTool(mailAddress);
        //assert
        assertEquals(false, result);
    }

    /**
     * Tests the mailTool method for an email address without a TLD.
     * Expects the method to return false.
     */
    @Test
    public void validateMailAddressReturnsFalseNoTLD(){
        //arrange
        Logic validator = new Logic();
        String mailAddress = "mail@mail.";
        //act
        Boolean result = validator.mailTool(mailAddress);
        //assert
        assertEquals(false, result);
    }

    /**
     * Tests the mailTool method for an email address with a correct input.
     * Expects the method to return true.
     */
    @Test
    public void validateMailAddressReturnsTrue(){
        //arrange
        Logic validator = new Logic();
        String mailAddress = "at.nguyen2@mail.com";
        //act
        Boolean result = validator.mailTool(mailAddress);
        //assert
        assertEquals(true, result);
    }

    /**
     * tests grade validation for a valid grade
     */
    @Test
    public void testIsValidGradeReturnsTrue(){
        //arrange
        Logic validator = new Logic();
        double grade = 5.0;
        //act
        Boolean result = validator.isValidGrade(grade);
        //assert
        assertEquals(true, result);
    }

    /**
     * tests grade validation for a grade below 0
     */
    @Test
    public void testIsValidGradeReturnsFalseBelowZero(){
        //arrange
        Logic validator = new Logic();
        double grade = -1.0;
        //act
        Boolean result = validator.isValidGrade(grade);
        //assert
        assertEquals(false, result);
    }

    /**
     * tests grade validation for a grade above 10
     */
    @Test
    public void testIsValidGradeReturnsFalseAboveTen(){
        //arrange
        Logic validator = new Logic();
        double grade = 11.0;
        //act
        Boolean result = validator.isValidGrade(grade);
        //assert
        assertEquals(false, result);
    }

    /**
     * tests postcode validation for a postcode that is null
     */
    @Test (expected = NullPointerException.class)
    public void nullPointerExceptionPostalCode (){
         //arrange
         Logic validator = new Logic();

         //act
        
 
         //assert
        validator.postalCode(null);
        
    }

    /**
     * tests postcode validation for a postcode that is too short
     */
    @Test (expected = IllegalArgumentException.class)
    public void notEnoughNumbers (){
        //arrange
        Logic validator = new Logic();

        //act
       

        //assert
        validator.postalCode("546GE");
   }

   /*tests postcode with rightinput
    * returns true
    */
   @Test
   public void rightInput (){
     //arrange
     Logic validator = new Logic();

     //act
    String valid = validator.postalCode("1234ab");

     //assert
     assertEquals("1234 AB", valid);
}

/*tests postcode with wrong input*/

    @Test(expected = IllegalArgumentException.class)
    public void differentInput (){
     //arrange
     Logic validator = new Logic();

     //act

     //assert
     validator.postalCode("a1324b");
}
}
