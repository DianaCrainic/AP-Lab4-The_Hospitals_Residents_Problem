package optional;

import compulsory.Resident;
import compulsory.Hospital;

import java.util.Set;

public class Partition {
    private Set<Resident> residentSet;
    private Set<Hospital> hospitalSet;

    public Partition(Set<Resident> residentSet, Set<Hospital> hospitalSet) {
        this.residentSet = residentSet;
        this.hospitalSet = hospitalSet;
    }

    public Set<Resident> getResidentSet() {
        return residentSet;
    }

    public void setResidentSet(Set<Resident> residentSet) {
        this.residentSet = residentSet;
    }

    public Set<Hospital> getHospitalSet() {
        return hospitalSet;
    }

    public void setHospitalSet(Set<Hospital> hospitalSet) {
        this.hospitalSet = hospitalSet;
    }
}

