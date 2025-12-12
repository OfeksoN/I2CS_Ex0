//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public static boolean isPrime(long prime) {
    if (prime != 2 && prime % 2 == 0 || prime <= 1)  /// Checks if the number is even and different than 2 (exclusive case of prime) or lower than 1
        return false;
    for (long i = 3; i <= Math.sqrt(prime); i += 2) {  /// Starting from 3 checks every un-even number
        if (prime % i == 0) {                         /// Checks if uneven number is a prime number
            return false;
        }
    }
    return true;                                    /// Returns true when number is a prime
}

public static long getPrimePair(long s, long n){
    long p1 = s;                                     /// An integer variable to hold the value of the first number
    while (!isPrime(p1) || !isPrime(p1 + n)){
        p1+=2;
        }
    return p1;
}

public static long getClosestPrimePair(long s, long n) {
    long Pf = getPrimePair(s, n);
    long Pl = Pf + n;
    boolean check = true;
    while (check) {
        boolean found = false;
        for (long i = (Pf + 1); i < Pl; i = i + 1) {
            if (isPrime(i)) {
                found = true;
                break;
            }
        }
        if (!found) {
            return Pf;
        } else {
            Pf = getPrimePair(Pl, n);
            Pl = Pf + n;
        }
    }
    return -1;
}

public static long getMthClosestPrimePair(long s, long n) {
    long i = 0;
    long first = getPrimePair(5, n);
    long Pf = getClosestPrimePair(first,n);
    long Pl = first + n;
        while(i < s){
            Pf = getClosestPrimePair(Pl , n);
            Pl = Pf + n;
            i++;
        }
     return Pf;
}

void main(){
    System.out.println(getPrimePair(8,1));
    Scanner scan = new Scanner(System.in);
        System.out.println("Enter a number to check if prime: ");
        long prime = scan.nextLong();
    while (prime <= 0) {
        System.out.println("A number below 1 can't be a prime number, try again:");
        prime = scan.nextLong();
    }
        System.out.println(isPrime(prime));

        System.out.println("Enter a positive number: ");
        long s = scan.nextLong();
    while (s < 0) {
        System.out.println("You need to enter a positive number:");
        s = scan.nextLong();
    }
        System.out.println("Enter a positive even number: ");
        long n = scan.nextLong();
    while (n < 0 || n % 2 != 0) {
        System.out.println("You need to enter a positive and even number:");
        n = scan.nextLong();
    }
        System.out.println("The closest prime pair: " + "[" + getPrimePair(s, n) + "," + (getPrimePair(s, n) + n) + "]");
        System.out.println("The closest prime pair with no primes inbetween: " + "[" + getClosestPrimePair(s, n) + "," + (getClosestPrimePair(s, n) + n) + "]");
        System.out.println("The Prime pair in the " + s + "th place is:" + "[" + getMthClosestPrimePair(s, n) + "," + (getMthClosestPrimePair(s,n) + n) + "]");
}

