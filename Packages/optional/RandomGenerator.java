package optional;

import compulsory.Hospital;
import compulsory.Resident;
import com.github.javafaker.Faker;

import java.util.*;

public class RandomGenerator {
    private int numberOfResidents;
    private int numberOfHospitals;

    public RandomGenerator(int numberOfResidents, int numberOfHospitals) {
        this.numberOfResidents = numberOfResidents;
        this.numberOfHospitals = numberOfHospitals;
    }

    /**
     * a set of residents are generated
     * the resident name is generated using faker
     * @return a set of residents
     */
    public Set<Resident> generateResidents() {
        Faker faker = new Faker();
        Set<Resident> residentSet = new HashSet<>();
        int indexResidentSet = 0;
        while (indexResidentSet < numberOfResidents) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String name = firstName + " " + lastName;
            Resident resident = new Resident(name);
            residentSet.add(resident);
            indexResidentSet++;
        }
        return residentSet;
    }

    /**
     * a set of hospitals are generated
     * the hospital name is generated using faker
     * @return a set of hospitals
     */
    public Set<Hospital> generateHospitals() {
        Faker faker = new Faker();
        Random random = new Random();
        Set<Hospital> hospitalSet = new HashSet<>();
        for (int indexHospitalSet = 0; indexHospitalSet < numberOfHospitals; indexHospitalSet++) {
            String name = faker.address().lastName();
            int capacity = random.nextInt(numberOfResidents) + 1;
            Hospital hospital = new Hospital(name, capacity);
            hospitalSet.add(hospital);
        }
        return hospitalSet;
    }

    /**
     * generates a set of a randomized number of hospitals
     * the list of hospitals is shuffled then is added to map
     * @param residentSet
     * @param hospitalSet
     * @return
     */
    public Map<Resident, List<Hospital>> generateResidentsPreferences(Set<Resident> residentSet, Set<Hospital> hospitalSet) {
        Map<Resident, List<Hospital>> residentsPreferences = new HashMap<>();
        Random random = new Random();
        for (Resident resident : residentSet) {
            List<Hospital> hospitals = new ArrayList<>(hospitalSet);
            int randomNumberOfPreferences = random.nextInt(numberOfHospitals) + 1;
            Collections.shuffle(hospitals);
            residentsPreferences.put(resident, hospitals.subList(0, randomNumberOfPreferences));
        }
        return residentsPreferences;
    }

    /**
     * generates a set of randomized number of residents
     * the list of residents is shuffled then is added to map
     * @param hospitalSet
     * @param residentSet
     * @return
     */
    public Map<Hospital, List<Resident>> generateHospitalsPreferences(Set<Hospital> hospitalSet, Set<Resident> residentSet) {
        Map<Hospital, List<Resident>> hospitalsPreferences = new HashMap<>();
        Random random = new Random();
        for (Hospital hospital : hospitalSet) {
            List<Resident> residents = new ArrayList<>(residentSet);
            int randomNumberOfPreferences = random.nextInt(numberOfResidents) + 1;
            Collections.shuffle(residents);
            hospitalsPreferences.put(hospital, residents.subList(0, randomNumberOfPreferences));
        }
        return hospitalsPreferences;
    }
}
