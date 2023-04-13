# Roman Numbers Validator and Converter
Author: Fyodor Mamayev 2023

## Functionality
Class RomanNumbers in file RomanNumbers.java has two methods: `boolean isValid(String romanNumber)` and `int convert(String romanNumber)`. Method isValid() checks if the string is a correct Roman number, while the convert() method returns the numerical value of the Roman number.   
The Roman number must not have less than 1 or more than 15 characters. Otherwise, both methods will throw IllegalArgumentException. The convert() method throws IllegalArgumentException if the number sent to it is not valid.  

## What counts as a correct Roman number?
A Roman number's digits are Latin letters having numerical values.   
In this project, the rules for constructing Roman numbers are:   
1. Only letters I (1), V (5), X (10), L (50), C (100), D (500) and M (1000) are allowed.   
2. Letters can be uppercase or lowercase, but if a number has some uppercase and some lowercase letters, it is not considered as correct.   
3. The value of a Roman number is a sum of numerical values of all its digits (letters). But, if a smaller digit is standing before a larger one, its value is instead subtracted from the total value.   
4. When we write an Arabic number, we break it down into units, tens, hundreds, and so on. Whan we write a Roman number, we do the same, but the number of units, tens, etc. is written using multiple Roman digits (Latin letters.) Here are the exact rules for that:  


| Number of | Hundreds | Tens | Units |
|-----------|----------|------|-------|
| 1         | C        | X    | I     |
| 2         | CC       | XX   | II    |
| 3         | CCC      | XXX  | III   |
| 4         | CD       | XL   | IV    |
| 5         | D        | L    | V     |
| 6         | DC       | LX   | VI    |
| 7         | DCC      | LXX  | VII   |
| 8         | DCCC     | LXXX | VIII  |
| 9         | CM       | XC   | IX    |
  

The only exception is number IIII, which is how 4 is sometimes represented (generally on clocks). One thousand is written as M. The thousands are written with as many M's as possible. _(Note: this just for ability to write Roman numbers greater than 3999. Normally, different signs like ↂ, CCIƆƆ or X̅ were used for that.)_  
  
Examples:
**VIII** = 8, **XCIV** = 94, **DLXXIX** = 579, **CCCXLIII** = 343, **MCMXCVIII** = 1998, **CDIV** = 404.
  
