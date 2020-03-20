package optional;

import compulsory.Resident;
import compulsory.Hospital;

import java.util.*;

public class Matching {
    private Map<Resident, Hospital> matchingList = new HashMap<>();

    public Matching() {
    }

    public void addPair(Resident firstElement, Hospital secondElement) {
        matchingList.put(firstElement, secondElement);
    }

    /**
     * it is checked if one particular hospital has accepted another resident who is in the list of
     * hospitals preferences after the resident in this pair, then matching is not stable, else it is stable
     * @param residentsPreferences a map containing a resident and a list of preferred hospitals by this resident
     * @param hospitalsPreferences a map containing a hospital and a list of preferred residents by this hospital
     * @return true if a matching stable, false if a matching is not stable
     */
    public boolean isStable(Map<Resident, List<Hospital>> residentsPreferences, Map<Hospital, List<Resident>> hospitalsPreferences) {
        boolean stableMatching = true;
        for (Map.Entry<Resident, Hospital> matching : matchingList.entrySet()) {
            Resident resident = matching.getKey();
            Hospital hospital = matching.getValue();
            for (Hospital thisHospital : residentsPreferences.get(resident)) {
                if (thisHospital != hospital) {
                    boolean ok = true;
                    for (Resident thisResident : hospitalsPreferences.get(thisHospital)) {
                        if (!ok) {
                            for (Map.Entry<Resident, Hospital> match2 : matchingList.entrySet()) {
                                if (match2.getKey() == thisResident && match2.getValue() == thisHospital) {
                                    stableMatching = false;
                                }
                            }
                        }
                        if (thisResident == resident) {
                            ok = false;
                        }
                    }
                } else {
                    stableMatching = true;
                }
            }
        }
        return stableMatching;
    }

    @Override
    public String toString() {
        return "Matching: " + matchingList ;
    }
}
