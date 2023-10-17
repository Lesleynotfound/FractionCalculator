class Fraction {
    private int numerator;
    private int denominator;

    Fraction(int numerator, int denominatorVal){
        this.numerator = getSimplifiedNumerator(numerator, denominator);
        this.denominator = getSimplifiedDenominator(numerator, denominator);
    }

    Fraction(int whole, int numerator, int denominator){
        if (whole < 0){
            numerator = -1 * (Math.abs(whole) * denominator + numerator);
        }
        else{
            numerator = whole * denominator + numerator;
        }

        this.numerator = getSimplifiedNumerator(numerator, denominator);// 只在名字指向起争议的情况下使用this
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

    static int getSimplifiedNumerator(int numerator, int denominator){
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
    void setNumerator(int numerator){
        this.numerator = numerator;
    }

    void setDenominator(int denominator){
        this.denominator = denominator;
    }

    boolean equals(Fraction other){
        return numerator == other.numerator && denominator == other.denominator;
    }

    public String toString(){
        if (numerator % denominator == 0){
            return String.valueOf(getWhole());
        }
        else if (Math.abs(numerator) < Math.abs(denominator)){
            String.format("%d/%d", numerator, denominator);
        }
        int whole = getWhole();
        int newNum = Math.abs(numerator) % Math.abs(denominator);
        return String.format("%d %d/%d", whole, newNum, denominator);
    }

    public Fraction clone(){
        return new Fraction(numerator, denominator);
    }




    public static void main(String[] args) {
        Fraction f = new Fraction(2, 3);
       
        System.out.println(f.numerator);
        System.out.println(f.denominator);
       
        Fraction g = new Fraction(4);

        System.out.println(g.numerator); 
        System.out.println(g.denominator); 

        Fraction h = new Fraction(-1, 2, 3);
        System.out.println(h.numerator); // -5
        System.out.println(h.denominator); // 3

        Fraction k = new Fraction(2, 3);
        Fraction m = new Fraction(4, 6);

        System.out.println(f.equals(k)); 

        System.out.println(gcd(36, -120));

        System.out.println(f.equals(m));

        Fraction p = new Fraction(-1, 2, 3);
        Fraction q = new Fraction(-5, 3);

        System.out.println(p.equals(q));

        Fraction v = new Fraction(-1, 2);
        Fraction b = new Fraction(1, -2);
        System.out.println(v.equals(b)); // fix this so it yields true

        Fraction c = new Fraction(-1, 3, 4);
        Fraction d = new Fraction(7, -4);
        System.out.println(c.equals(d));

        System.out.println(c);
        System.out.println(new Fraction(1));
        System.out.println(new Fraction(14, -7));
        System.out.println(d);

        System.out.println(f.getClass());

        Point q1 = new Point(1, 2);
        // you can use getClass to check if two objects are the same type (class)
        if (q1.getClass().equals(f.getClass())){
            System.out.println("Same type");
        }
        else{
            System.out.println("Not the same type");
        }



    }

}

// class Something{
//     public static void main(String[] args) {
//         Fraction f = new Fraction(2, 3, 4);

//         System.out.println(f.getNumerator());
        
//         //f.numerator = 11;
//         //f.denominator = -5;

//         String word = "jdhsfhjkd";
//         System.out.println(word.length());
        
//     }
// }



// this.slope = slope当变量的名字等于class里的那个名字，用this来指代这个是class里的那个名字