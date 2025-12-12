
# 📐 Prime Pairs: Java Utilities for Prime Checks & Gaps

*Efficient helpers to check primality and find prime pairs with a fixed gap (twin/cousin/sexy primes), including “closest” prime pairs. Includes unit tests.*

---

## 📚 Overview

This project provides a Java toolkit around prime numbers:

- **`isPrime`** — primality testing for integers.
- **`getPrimePair(s, n)`** — find the smallest prime `p1 ≥ s` where `p2 = p1 + n` is also prime.  
- **`getClosestPrimePair(s, n)`** — like above, but ensures **no primes** exist strictly between `p3` and `p4 = p3 + n` (i.e., they are **consecutive primes** with gap `n`).
- **`getMthClosestPrimePair(m, n)`** — returns the `m`‑th such **closest prime pair**, ordered by the lower prime.  

> ℹ️ `n` must be an **even positive** number (e.g., 2 → twin primes, 4 → cousin primes, 6 → sexy primes).  




## 🧠 Concepts (quick refresher)

Prime: integer > 1 with no positive divisors other than 1 and itself.  
Prime pair with gap n: primes (p, p + n).  
Closest prime pair with gap n: (p, p + n) are consecutive primes (no prime in (p, p + n)).  


## ✅ API
1) boolean isPrime(long x)  

Input: x (integer)  
Returns: true iff x is prime.  
Conventions: x < 2 → false.  
Complexity:  

Trial division up to √x: O(√x) (fast enough for 32‑bit ranges if occasional).  
For large inputs, consider a deterministic Miller–Rabin variant or caching.



2) int getPrimePair(int s, int n)  

Goal: Smallest p1 ≥ s such that p1 and p1 + n are both prime.  
Returns: the lower prime p1.  
Preconditions: s ≥ 2, n > 0 and even. 
Not-found semantics:  

If your implementation searches unboundedly, it will eventually find one for many n (there are infinitely many for n=2 is unproven; your code may add a search limit).  
Recommended: provide an overload with a maxSearch bound or return OptionalInt.  
In a simple baseline, return -1 if not found within an internal limit.  


Example: getPrimePair(10, 6) → 11 (since 11 and 17 are prime; no “closest” requirement here).  


3) int getClosestPrimePair(int s, int n)  

Goal: Smallest p3 ≥ s such that p4 = p3 + n is prime and there are no primes in (p3, p4).  
Returns: the lower prime p3.  
Preconditions: s ≥ 2, n > 0 and even.  
Example: with n = 6 and s = 10,  

(11, 17) ❌ (because 13 is prime in between),  
(17, 23) ❌ (19 lies in between),  
(23, 29) ✅ (closest; no primes in 24..28) ⇒ returns 23.  




4) int getMthClosestPrimePair(int m, int n) 

Goal: Return the lower prime p5 of the m‑th closest prime pair (p5, p5 + n) in ascending order of p5.  
Definition of “closest”: no primes in (p5, p5 + n).  
Parameters: m ≥ 0 (0‑based index), n > 0 and even.  
Examples (for n = 2, twin primes):  

m = 0 → (3, 5) → returns 3  
m = 1 → (5, 7) → returns 5  
m = 2 → (11, 13) → returns 11  

Tip: Implement this by scanning upward from the first candidate and counting each closest pair found until you reach the m‑th.  

## 🛠️ Implementation Notes
Primality (isPrime)

Handle small cases: x < 2 → false, 2 → true, even numbers >2 → false.  
Trial division by odd numbers up to √x.  
Optional optimizations:  

6k ± 1 wheel to skip more composites.  
Bitset or boolean array to cache primes found (if you scan ranges).  
Deterministic Miller–Rabin for 32/64‑bit if very large inputs.  



Scanning for pairs  

For getPrimePair(s, n): iterate p = max(2, s) upward; test p and p+n.  
For closest pairs: additionally verify no prime exists in (p, p+n).  

You can check “consecutive” by confirming the nextPrime(p) is exactly p+n.  



Performance (when ranges are large)  

Segmented Sieve of Eratosthenes for window [s, s + W) and reuse W while sliding upward.  
Keep a small cache of primes up to √(s + window).  
Tune W (e.g., 1–10 million) depending on memory constraints.  


## ⏱️ Complexity

isPrime(x): O(√x) trial division (amortized faster with wheel/caching).  
getPrimePair / getClosestPrimePair: Depends on prime gaps density; average time ≈ number of candidates tested × cost of primality tests.  
getMthClosestPrimePair: proportional to how many pairs you need to encounter until reaching the m‑th.  


## 🧪 Testing
This project includes tests for correctness and edge cases:  

isPrime tests: small numbers, primes, composites, large edges.  
Pair functions:

Typical cases (n = 2, 4, 6, various s).  
Verify “closest” constraint (ensure a known prime in the gap invalidates).  
Boundary behavior when s is prime vs. composite.  
Invalid inputs: n odd or ≤ 0, s < 2, negative inputs.  


Determinism: repeated calls return same results.  
Performance sanity: large s within time bound (optional).  

## 🚦 Input Validation & Error Handling  

Enforce: n even and > 0, s ≥ 2, m ≥ 0.  
On invalid inputs: throw IllegalArgumentException.  
On “not found” within an internal search bound:  

Either return -1, or  
throw NoSuchElementException, or  
return OptionalInt.  
Document which convention your code uses (this README assumes -1).  

## 💡 Examples  

getPrimePair(14, 2) → 17 (since 17 & 19 are prime; p1 is 17).  
getClosestPrimePair(3, 2) → 3 (twin primes 3 and 5; none in between).  
getClosestPrimePair(10, 6) → 23 (23 & 29; nothing prime between).  
getMthClosestPrimePair(0, 4) → 3 (cousin primes 3 & 7).  
getMthClosestPrimePair(1, 4) → 7 (7 & 11).  


## 🧩 Possible Extensions

Expose nextPrime(x) / prevPrime(x) helpers.  
Add a segmented sieve implementation for range queries.  
CLI/REST interface to query pairs and statistics (counts per range).  
Benchmark suite (JMH).  
