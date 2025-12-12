

/**
 * This class is a basis for Ex0 (your first assignment),
 * The definition of the Ex0 can be found here: <a href="https://docs.google.com/document/d/1UtngN203ttQKf5ackCnXs4UnbAROZWHr/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true">...</a>
 * You are asked to complete the functions below amd may add additional functions if needed.

 */
public class Ex0 {
    public final static long ID = 0;  // Do update your ID here


    /**
     * This function checks if n is a prime number.
     * .
     * Pseudocode:
     * i)   Given n is tested to check if it is a prime number or not.
     * ii)  Besides the numbers 2 and 3, returns false to numbers that can be divided by 2 and 3
     * iii) Starting from i = 5 checks every number in skips of 6 until Square root of given n
     * iv)  If n can be divided by i or by (i+2) returns false
     * v)   Otherwise returns true
     * .
     * @param n (Integer) - represented as long
     * @return true if and only if there is no integer (p) within the range of [2,n) which divides n.
     *
     */
    public static boolean isPrime(long n) {
        if (n!=2 && n % 2 == 0 || n!=3 && n % 3 == 0 || n<2)
            return false;
        for (long i = 5; i*i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }


    /**
     * This function finds the first prime integer (p1) >= start, for which p2=p1+n is also a prime number.
     * .
     * Pseudocode:
     * i)   Given start is tested to check if it is a positive number, if not returns -1.
     * ii)  Given start and contain it in variable "p1", check if p1 is even, if so add 1 to make it un-even
     * iii) Checking p1 as un-even number and the number afterward(by adding n(gap) to p1)
     * iv)  If either p1 and (p1+n) are not primes, add 2 to skip even numbers
     * v)   Otherwise returns p1
     *
     * @param start - a starting value from which p1 should be searched for.
     * @param n     - a positive (even) integer value.
     * @return the first prime number p1 such that: i) p1>=start, ii) p1+n is a prime number.
     * in case a wrong value is given to the function
     * (n<0 or n is an odd number) the function should return -1.
     *
     */
    public static long getPrimePair(long start, long n) {
        if (start < 1){
            return -1;
        }
        long p1 = start;
        if (p1 % 2 == 0) {
            p1 ++;
        }
        while (!isPrime(p1) || !isPrime(p1 + n)) {
                p1 +=2;
        }
        return p1;
    }

    /**
     * This function compute the first prime number p1 for which:
     * .
     * Pseudocode:
     * i)   Starting with long variable called "ans" giving him -1
     * ii)  If n is not 2 and even then long i = to the first number entered (start)
     * iii) making sure i is even and then adding 1 to make sure we work with uneven numbers
     * iv)  While statement is true if i+n is a prime number and if i is a prime number we check numbers in between
     * v)   Using a for loop we ensure numbers from j = i+1 until n+i are either primes or not adding 1 each time to j
     * vi)  If so turn flag into "True" and then break to save up calculations
     * vii) If flag is false return i (means we found our closest pair with no pairs between)
     * viii)Loop keeps going in case flag is true by adding n to i and starting calculations all over
     *
     * @param start a positive integer which is the lower bound of p1.
     * @param n     - a positive even integer.
     * @return a prime number p1>=start that the following prime number is p1+n.
     */
    public static long getClosestPrimePair(long start, long n) {
        long ans = -1;
        if(n>=2 && n%2==0) {
            long i = start;
            if (i % 2 == 0) i++;
            while(true) {
                if(isPrime(i+n)) {
                    if(isPrime(i)) {
                        boolean flag = false;
                        for (long j = i + 2; j < n + i; j+=2) {
                            if (isPrime(j)) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            return i;
                        }
                    }
                    i=i+n;
                }
                else{
                    i +=2;
                }
            }
        }
        return ans;
}

    /**
     * This function compute the mth positive integer p1 for which:
     * .
     * Pseudocode:
     * i)   Starting from the number 3 Checking all combinations of ClosestPrimePairs.
     * ii)  Given a positive integer m, m symbolises the amount of combinations checked starting from 3
     * iii) For each combination found replace Pf with the first number of the combination found
     * iv)  Also replace first with (Pf + n) to skip to the next number that might answer our ClosestPrimePair
     * v)   Check for more combinations m times and then return Pf
     *
     * @param m a none negative integer.
     * @param n - a positive even integer.
     * @return a prime number p1>=start that the following prime number is p1+n.
     *
     */
    public static long getMthClosestPrimePair(int m, long n) {
        long first = 3;
        long Pf = 0;
        for (long i = 0 ; i <= m; i+=1){
            Pf = getClosestPrimePair(first, n);
            first = Pf + n;
        }
        return Pf;
    }
}
