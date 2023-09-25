package BankingApplication;
/*
 * This class will handle Exception of manual type.
 */
public class SomethingWrong  extends RuntimeException {
     SomethingWrong(String s){
        super(s);
    }
}
