import java.util.Scanner;

class Fraction {
    private int numerator;
    private int denominator;

    /** Constructor for a Fraction.
     * @param numerator represents the numerator 
     * @param denominator represents the denominator
     */
    Fraction(int numerator, int denominator) throws IllegalArgumentException{
        // this refer's to the Fraction so this.numerator that means this fraction's numerator
        if (denominator == 0){
            throw new IllegalArgumentException("Denominator cannot be zero"); 
        }

        this.numerator = getSimplifiedNumerator(numerator, denominator);
        this.denominator = getSimplifiedDenominator(numerator, denominator);
    }

    Fraction(int whole, int numerator, int denominator) throws IllegalArgumentException{
        if (denominator == 0){
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        if (whole < 0){
            numerator = -1 * (Math.abs(whole) * denominator + numerator);
        }
        else{
            numerator = whole * denominator + numerator;
        }

        this.numerator = getSimplifiedNumerator(numerator, denominator);
        this.denominator = getSimplifiedDenominator(numerator, denominator);
    }

    Fraction(int whole){
        numerator = whole;
        denominator = 1;
    }

    static int gcd(int n, int m){
        if (n == 0 || m == 0){
            return Math.max(Math.abs(n), Math.abs(m));
        }
        int d = Math.min(Math.abs(n), Math.abs(m));
        while (n % d != 0 || m % d != 0){
            d --;
        }
        return d;
    }

    static int getSimplifiedNumerator(int numerator, int denominator) throws IllegalArgumentException{
        if (denominator == 0 ){
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        if (numerator * denominator < 0){
            return -1 * Math.abs(numerator) / gcd(numerator, denominator);
        }
        return Math.abs(numerator) / gcd(numerator, denominator);
    }

    static int getSimplifiedDenominator(int numerator, int denominator){
        return Math.abs(denominator) / gcd(numerator, denominator);
    }

    // getter is a method that returns the value of a attribute;

    int getNumerator(){
        return numerator;
    }

    int getDenominator(){
        return denominator;
    }

    int getWhole(){
        return numerator / denominator;
    }

    // setters are methods that set new values for the attributes
    void setNumerator(int numeratorVal){
        numerator = numeratorVal;
    }

    void setDenominator(int denominatorVal){
        denominator = denominatorVal;
    }

    boolean equals(Fraction other){
        return numerator == other.numerator && denominator == other.denominator;
    }

    public String toString(){
        if (numerator % denominator == 0){
            return String.valueOf(getWhole());
        }
        else if (Math.abs(numerator) < Math.abs(denominator)){
            return String.format("%d/%d", numerator, denominator);
        }
        int whole = getWhole();
        int newNum = Math.abs(numerator) % Math.abs(denominator);
        return String.format("%d %d/%d", whole, newNum, denominator);
    }

    public Fraction clone(){
        return new Fraction(numerator, denominator);
    }

    boolean lessThanZero(){
        // returns True is this is less than zero
        return numerator < 0;
    }

    boolean greaterThanZero(){
        return numerator > 0;
    }

    // static double ChangeintoDouble(String expression){
    //     if (expression.matches("-*[1-9]+[0-9]*\\.[0-9]*")){
    //         double DoubelWhole = Double.valueOf(expression);
    //     }
    // }

    /**
     * @param expression the Stirng we take from the user's input
     * @return fraction type
     */
    static Fraction valueOf(String expression){
        if (expression.matches("-*[1-9]+[0-9]*")){
            int wholeVal = Integer.valueOf(expression);
            return new Fraction(wholeVal);
        }
        else if(expression.matches("[-*[1-9]+[0-9]*/[1-9]+[0-9]*]")){
            int numeratorVal = Integer.valueOf(expression.substring(0, expression.indexOf("/")));
            int denominatorVal = Integer.valueOf(expression.substring(expression.indexOf("/") + 1));
            return new Fraction(numeratorVal, denominatorVal);
        }
        else{ //if(expression.matches("[[[1-9]+[0-9]* [1-9]+[0-9]*/[1-9]+[0-9]*]]")){
            int wholeVal = Integer.valueOf(expression.substring(0, expression.indexOf(" ")));
            int numeratorVal = Integer.valueOf(expression.substring(expression.indexOf(" ") + 1, expression.indexOf("/")));
            int denominatorVal = Integer.valueOf(expression.substring(expression.indexOf("/") + 1));
            return new Fraction(wholeVal, numeratorVal, denominatorVal);
        }
        
    }

    // Calculate methods and test methods

    /**
     * @param other the sum that user want to substract
     * @return the sum of the two fractions
     */
    Fraction add(Fraction other){
        // a/b + c/d
        // = (a * d + b * c) / (b * d)
        int commonDenominator = denominator * other.denominator; 
        int numeratorOfResult = numerator * other.denominator + other.numerator * denominator;
        return new Fraction (numeratorOfResult, commonDenominator);
    }
    
    /**
     * @param other the fraction that user want to substract
     * @return the result of substract of the two fractions
     */
    Fraction substract(Fraction other){
        return this.add(new Fraction(other.numerator = -1 * other.numerator));
    }

    /**
     * @param other the fraction that user want to multiply
     * @return the result of the mutiply of the two fractions
     */
    Fraction multiply(Fraction other){
        this.numerator = numerator * other.numerator;
        this.denominator = denominator * other.numerator;
        return new Fraction(numerator, denominator);
    }

    Fraction divide(Fraction other){
        return this.multiply(new Fraction (other.denominator, other.numerator));
    }

    static void testAdd(){
        Fraction f = new Fraction(-3, 4);
        Fraction g = new Fraction(1, 4, 5);
        System.out.println(f.add(g)); //  1 1/20
    }




    public static void main(String[] args) {
        // try{
        //     int num = input.nextInt();
        //     int den = input.nextInt();
        //     Fraction f = new Fraction(num, den);
        //     System.out.println(f);
        // }
        // catch (IllegalArgumentException e){
        //     System.out.println(e.getMessage());
        // }
        
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        String fractions = "";
        String operator = "";
        input.useDelimiter(" \\+ | - | \\* | / ");
        while (input.hasNext()){
            String adding = input.next();
            fractions = fractions + "," + adding;
            operator = line.replace(adding, "");
        }
        fractions = fractions.substring(1) + ",";
        operator = operator.replace(" ", "");
        String newfractions = "";

        Scanner origin = new Scanner(fractions);
        origin.useDelimiter(",");
        while (operator.contains("*|/")){
            for (int i = 0; i < operator.length(); i++){
                String testfraction1 = origin.next();
                String testoperator = operator.substring(i, i + 1);
                if(testoperator.equals("*")){
                    String testfraction2 = origin.next();
                    String mutiplyanswer = Fraction.valueOf(testfraction1).multiply(Fraction.valueOf(testfraction2)).toString();
                    for (int x = 0; x < operator.length(); x ++){
                        if (x == i){
                            newfractions = newfractions + mutiplyanswer + ",";
                            fractions = fractions.substring(fractions.indexOf(",")+1);
                        }
                        else if(x == i+1){
                            fractions = fractions.substring(fractions.indexOf(",")+1);
                        }
                        else{
                            newfractions = newfractions + fractions.substring(0, fractions.indexOf(",")+1);
                            fractions = fractions.substring(fractions.indexOf(",")+1);
                        }
                    }
                    operator = operator.substring(0, i) + operator.substring(i+1);
                    break;
                }
                else{
                    String testfraction2 = origin.next();
                    String divideanswer = Fraction.valueOf(testfraction1).divide(Fraction.valueOf(testfraction2)).toString();
                    for (int x = 0; x < operator.length(); x ++){
                        if (x == i){
                            newfractions = newfractions + divideanswer + ",";
                            fractions = fractions.substring(fractions.indexOf(",")+1);
                        }
                        else if(x == i+1){
                            fractions = fractions.substring(fractions.indexOf(",")+1);
                        }
                        else{
                            newfractions = newfractions + fractions.substring(0, fractions.indexOf(",")+1);
                            fractions = fractions.substring(fractions.indexOf(",")+1);
                        }
                    }
                    operator = operator.substring(0, i) + operator.substring(i+1);
                    break;
                }
            }
        }
        while (operator.contains("+|-")){
            for (int i = 0; i < operator.length(); i++){
                String testfraction1 = origin.next();
                String testoperator = operator.substring(i, i + 1);
                if(testoperator.equals("+")){
                    String testfraction2 = origin.next();
                    String addanswer = Fraction.valueOf(testfraction1).add(Fraction.valueOf(testfraction2)).toString();
                    for (int x = 0; x < operator.length(); x ++){
                        if (x == i){                        
                            newfractions = newfractions + addanswer + ",";
                            fractions = fractions.substring(fractions.indexOf(",")+1);
                        }
                        else if(x == i+1){
                            fractions = fractions.substring(fractions.indexOf(",")+1);
                        }
                        else{
                            newfractions = newfractions + fractions.substring(0, fractions.indexOf(",")+1);
                            fractions = fractions.substring(fractions.indexOf(",")+1);
                        }
                    }
                    operator = operator.substring(0, i) + operator.substring(i+1);
                    break;
                }
                else{
                    String testfraction2 = origin.next();
                    String substractionanswer = Fraction.valueOf(testfraction1).divide(Fraction.valueOf(testfraction2)).toString();
                    for (int x = 0; x < operator.length(); x ++){
                        if (x == i){
                            newfractions = newfractions + substractionanswer + ",";
                            fractions = fractions.substring(fractions.indexOf(",")+1);
                        }
                        else if(x == i+1){
                            fractions = fractions.substring(fractions.indexOf(",")+1);
                        }
                        else{
                            newfractions = newfractions + fractions.substring(0, fractions.indexOf(",")+1);
                            fractions = fractions.substring(fractions.indexOf(",")+1);
                        }
                    }
                    operator = operator.substring(0, i) + operator.substring(i+1);
                    break;
                }
            }
        }
        System.out.println(newfractions);
        input.close();
        origin.close();
    }
    
}



