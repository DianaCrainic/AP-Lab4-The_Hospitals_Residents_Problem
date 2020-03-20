# Advanced Programming Lab4 
# The Hospitals Residents Problem

- Compulsory
- Optional

This repo contains:
- Compulsory
1. The residents and hospitals from the example.
2. Create a list of residents, using an ArrayList implementation. Sort the residents, using a comparator.
3. Create a set of hospitals, using a TreeSet implementation. Make sure that Hospital objects are comparable.
4. Create two maps (having different implementations) describing the residents and the hospital preferences and print them on the screen.
5. Using Java Stream API, write queries that display the residents who find acceptable H0 and H2, and the hospitals that have R0 as their top preference.

- Optional
1. Create a class that describes the HR Problem.
2. Implement an algorithm for creating a matching.
3. Use a third-party library in order to generate random fake names for residents and hospitals. Create random instances and test the algorithm.
4. Verify if the matching produced by the algorithm is stable.


## Console output:

Compulsory:
Residents: R0, R1, R2, R3
Hospitals: H0(c:1), H1(c:2), H2(c:2)

List of Residents: [R0, R1, R2, R3]
TreeSet of Hospitals: [H0(c:1), H1(c:2), H2(c:2)]

TreeMap created: {H0(c:1)=[R3, R0, R1, R2], H1(c:2)=[R0, R2, R1], H2(c:2)=[R0, R1, R3]}
HashMap created: {R3=[H0(c:1), H2(c:2)], R0=[H0(c:1), H1(c:2), H2(c:2)], R1=[H0(c:1), H1(c:2), H2(c:2)], R2=[H0(c:1), H1(c:2)]}

Residents who find acceptable H0 and H2: [R0, R1, R3]
The hospitals that have R0 as their top preference: [H1(c:2), H2(c:2)]

Optional:

Matching: {R3=H0(c:0), R0=H1(c:0), R1=H2(c:1), R2=H1(c:0)}
Matching is stable

Generated Problem:
Matching: {Mohamed Funk=Bosco(c:4), Lucina Pfeffer=Kohler(c:0), Omega Johnston=Leffler(c:5), Pasquale Ziemann=Kohler(c:0), Adrianne Borer=Leffler(c:5), Hildegard Harber=Kohler(c:0)}
Matching is stable
