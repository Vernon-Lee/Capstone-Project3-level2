public class projectDetail {
    //Attributes
    public int number;
    public String name;
    public String buildingType;
    public String address;
    public String ERFNumber;
    public long totFee;
    public long totPaid;
    public String startDate;
    public String deadline;
    public String complete;

    // to string
    public String toString() {
        String output = "Project number: " + number;
        output = output + "\nProject name: " + name;
        output = output + "\nBuilding type: " + buildingType;
        output = output + "\nAddress: " + address;
        output = output + "\nERF number: " + ERFNumber;
        output = output + "\nProject fee: " + totFee;
        output = output + "\nAmount paid: " + totPaid;
        output = output + "\nProject start date: " + startDate;
        output = output + "\nProject deadline: " + deadline;
        output = output + "\nProject complete: " + complete + "\n";
        return output;
    }

    // Get number
    public int getNumber() {
        return number;
    }

    // Set number
    public void setNumber(int number) {
        this.number = number;
    }

    // Get name
    public String getName() {
        return name;
    }

    // Set name
    public void setName(String name) {
        this.name = name;
    }

    // Get building type
    public String getBuildingType() {
        return buildingType;
    }

    // Set building type
    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    // Get address
    public String getAddress() {
        return address;
    }

    // Set address
    public void setAddress(String address) {
        this.address = address;
    }

    // Get ERF number
    public String getERFNumber() {
        return ERFNumber;
    }

    // Set ERF number
    public void setERFNumber(String ERFNumber) {
        this.ERFNumber = ERFNumber;
    }

    // Get total fee
    public long getTotFee() {
        return totFee;
    }

    // Set total fee
    public void setTotFee(long totFee) {
        this.totFee = totFee;
    }

    // Get total fee paid
    public long getTotPaid() {
        return totPaid;
    }

    // Set total fee paid
    public void setTotPaid(long totPaid) {
        this.totPaid = totPaid;
    }

    // Get start date
    public String getStartDate() {
        return startDate;
    }

    // Set start date
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    // Get deadline
    public String getDeadline() {
        return deadline;
    }

    // Set deadline
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    // get complete
    public String getComplete() {
        return complete;
    }

    // Set complete
    public void setComplete(String complete) {
        this.complete = complete;
    }
}


