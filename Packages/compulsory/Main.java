package compulsory;

import optional.RandomGenerator;
import optional.Matching;
import optional.Problem;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static Map<Hospital, List<Resident>> createMapHospitalsResidents(Hospital[] h, Resident[] r) {
        Map<Hospital, List<Resident>> hospitalPref = new TreeMap<>();
        hospitalPref.put(h[0], Arrays.asList(r[3], r[0], r[1], r[2]));
        hospitalPref.put(h[1], Arrays.asList(r[0], r[2], r[1]));
        hospitalPref.put(h[2], Arrays.asList(r[0], r[1], r[3]));
        return hospitalPref;
    }

    public static Map<Resident, List<Hospital>> createMapResidentsHospitals(Resident[] r, Hospital[] h) {
        Map<Resident, List<Hospital>> residentPref = new HashMap<>();
        residentPref.put(r[0], Arrays.asList(h[0], h[1], h[2]));
        residentPref.put(r[1], Arrays.asList(h[0], h[1], h[2]));
        residentPref.put(r[2], Arrays.asList(h[0], h[1]));
        residentPref.put(r[3], Arrays.asList(h[0], h[2]));
        return residentPref;
    }

    /**
     * queries that display the residents who find acceptable some given hospitals
     *
     * @param residentList  a list of residents
     * @param prefHospitals a list of hospitals
     * @param resPrefMap    a map containing a resident and a list of hospitals
     * @return a list of residents who find acceptable some given hospitals
     */
    public static List<Resident> getPrefResidents(List<Resident> residentList, List<Hospital> prefHospitals,
                                                  Map<Resident, List<Hospital>> resPrefMap) {
        return residentList.stream()
                .filter(resident -> resPrefMap.get(resident).containsAll(prefHospitals))
                .collect(Collectors.toList());
    }

    /**
     * queries that display the hospitals that have a given resident as their top preference
     *
     * @param hospitalList         a list of hospitals
     * @param hospitalsPreferences a map containing a hospital and a list of residents
     * @param preferredResident    a resident
     * @return a list of hospitals that have a given resident as their top preference
     */
    public static List<Hospital> getPrefHospitals(Set<Hospital> hospitalList, Map<Hospital, List<Resident>> hospitalsPreferences,
                                                  Resident preferredResident) {
        return hospitalList.stream()
                .filter(hospital -> hospitalsPreferences.get(hospital).get(0).equals(preferredResident))
                .collect(Collectors.toList());
    }

    /**
     * checks if a matching is stable
     * @param matching a matching (resident, hospital)
     * @param residentsPreferences a map containing a resident and a list of hospitals
     * @param hospitalsPreferences a map containing a hospital and a list of residents
     */

    public static void checkStableMatching(Matching matching,
                                           Map<Resident, List<Hospital>> residentsPreferences,
                                           Map<Hospital, List<Resident>> hospitalsPreferences) {
        if (matching.isStable(residentsPreferences, hospitalsPreferences)) {
            System.out.println("Matching is stable");
        } else {
            System.out.println("Matching is not stable");
        }
    }


    public static void main(String[] args) {

        System.out.println("Compulsory:");
        Resident[] residents = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Resident("R" + i))
                .toArray(Resident[]::new);

        System.out.print("Residents: ");
        for (Resident resident : residents) {
            if (!resident.equals(residents[residents.length - 1])) {
                System.out.print(resident + ", ");
            } else {
                System.out.println(resident);
            }

        }

        Hospital[] hospitals = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Hospital("H" + i))
                .toArray(Hospital[]::new);

        hospitals[0].setCapacity(1);
        hospitals[1].setCapacity(2);
        hospitals[2].setCapacity(2);

        System.out.print("Hospitals: ");
        for (Hospital hospital : hospitals) {
            if (!hospital.equals(hospitals[hospitals.length - 1])) {
                System.out.print(hospital + ", ");
            } else {
                System.out.println(hospital);
            }

        }
        System.out.println();

        //creating a list of residents, using an ArrayList implementation
        List<Resident> residentsList = new ArrayList<>(Arrays.asList(residents));
        System.out.println("List of Residents: " + residentsList);

        //sort the residents using a comparator
        residentsList.sort(Comparator.comparing(Resident::getName));
        //System.out.println("Sorted list of Residents: " + residentsList);
        //System.out.println();

        //creating a set of hospitals, using a TreeSet implementation
        Set<Hospital> hospitalsList = new TreeSet<>(Arrays.asList(hospitals));
        System.out.println("TreeSet of Hospitals: " + hospitalsList);
        System.out.println();

        //TreeMap
        Map<Hospital, List<Resident>> hospitalPreferences = createMapHospitalsResidents(hospitals, residents);
        System.out.println("TreeMap created: " + hospitalPreferences);
        //HashMap
        Map<Resident, List<Hospital>> residentPreferences = createMapResidentsHospitals(residents, hospitals);
        System.out.println("HashMap created: " + residentPreferences);

        System.out.println();

        ////queries that display the residents who find acceptable H0 and H2
        List<Hospital> preferredHospitals = Arrays.asList(hospitals[0], hospitals[2]);
        System.out.print("Residents who find acceptable H0 and H2: ");
        System.out.println(getPrefResidents(residentsList, preferredHospitals, residentPreferences));

        ////queries that display the hospitals that have R0 as their top preference
        System.out.print("The hospitals that have R0 as their top preference: ");
        System.out.println(getPrefHospitals(hospitalsList, hospitalPreferences, residents[0]));


        System.out.println();
        System.out.println("Optional:");
        System.out.println();

        Problem problem = new Problem(new HashSet<>(residentsList), hospitalsList, residentPreferences, hospitalPreferences);
        Matching matching = problem.solve();
        System.out.println(matching);
        checkStableMatching(matching, residentPreferences, hospitalPreferences);

        System.out.println();
        System.out.println("Generated Problem:");
        RandomGenerator generator = new RandomGenerator(8, 3);

        Set<Resident> generatedResidents = generator.generateResidents();
        Set<Hospital> generatedHospitals = generator.generateHospitals();
        residentPreferences = generator.generateResidentsPreferences(generatedResidents, generatedHospitals);
        hospitalPreferences = generator.generateHospitalsPreferences(generatedHospitals, generatedResidents);

        Problem generatedProblem = new Problem(generatedResidents, generatedHospitals, residentPreferences, hospitalPreferences);
        Matching generatedMatching = generatedProblem.solve();
        System.out.println(generatedMatching);
        checkStableMatching(generatedMatching, residentPreferences, hospitalPreferences);




    }
}
