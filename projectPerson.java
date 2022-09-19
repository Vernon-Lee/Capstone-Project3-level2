public class projectPerson {
    // Attributes
    String personCount;
    String personDescription; // contractor, customer or architect
    String personName;
    String personTelNum;
    String personEmail;
    String personAddress;

    public String toString() {
        String output;
        output = "Description: " + personDescription;
        output = output + "\nName: " + personName;
        output = output + "\nTelephone number: " + personTelNum;
        output = output + "\nEmail: " + personEmail;
        output = output + "\nAddress: " + personAddress;
        return output;
    }

    // get person count
    public String getPersonCount() {
        return personCount;
    }

    // set person count
    public void setPersonCount(String personCount) {
        this.personCount = personCount;
    }

    // get description
    public String getPersonDescription() {
        return personDescription;
    }

    // set description
    public void setPersonDescription(String personDescription) {
        this.personDescription = personDescription;
    }

    // get name
    public String getPersonName() {
        return personName;
    }

    // set name
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    //get telephone number
    public String getPersonTelNum() {
        return personTelNum;
    }

    // set telephone number
    public void setPersonTelNum(String personTelNum) {
        this.personTelNum = personTelNum;
    }

    // get email
    public String getPersonEmail() {
        return personEmail;
    }

    // set email
    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    // get address
    public String getPersonAddress() {
        return personAddress;
    }

    // set address
    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }
}
