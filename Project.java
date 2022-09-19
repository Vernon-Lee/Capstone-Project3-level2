import java.io.*;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Project {
    public static void main(String[] args) throws IOException {
        menu();
    }

    // menu
    private static void menu() throws IOException {
        // main menu
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 1 of the options below: ");
        System.out.println("1 - Capture Project Details");
        System.out.println("2 - Change project due date");
        System.out.println("3 - Change total amount of fee paid to date");
        System.out.println("4 - Update a contractor's contact details");
        System.out.println("5 - Finalise a project");
        System.out.println("6 - File statistics");
        System.out.println("7 - Exit");
        try {
            //ask user to give input
            System.out.print("\nPlease enter option here: ");
            int menuNum = input.nextInt();
            if (menuNum == 1) {
                // call 'projectObject' method
                System.out.println("Create project object");
                projectObject();
            } else if (menuNum == 2) {
                // call 'changeDueDate' method
                System.out.println("Project due date is changed to: ");
                changeDueDate();
            } else if (menuNum == 3) {
                // call 'changeFeePaid' method
                System.out.println("Total amount paid is changed to: ");
                changeFeePaid();
            } else if (menuNum == 4) {
                // call 'contactDetails' method
                System.out.println("Updated contractor's contact details: ");
                contactDetails();
            } else if (menuNum == 5) {
                // call 'finalized' method
                finalise();
            } else if (menuNum == 6) {
                fileStatistics();
            } else
                System.exit(0);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input select any number from (1-7).");
            e.printStackTrace();
        }
    }

    // Create objects
    public static void projectObject() throws IOException {
        // Allow user to give input
        Scanner scanner = new Scanner(System.in);
        Scanner number = new Scanner(System.in);
        System.out.print("Project number: ");
        int num = number.nextInt();
        System.out.print("Project name: ");
        String name = scanner.nextLine();
        System.out.print("Type of building being designed: ");
        String buildingType = scanner.nextLine();
        System.out.print("Address for the project: ");
        String address = scanner.nextLine();
        System.out.print("ERF number: ");
        String ERFNumber = scanner.nextLine();
        System.out.print("Total fee being charged: ");
        long totFee = scanner.nextLong();
        System.out.print("Total amount paid: ");
        long totPaid = scanner.nextLong();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        System.out.println("Project start date: " + dateFormat.format(date));
        Scanner input = new Scanner(System.in);
        System.out.print("Deadline for the project(DD-MM-YY): ");
        String deadline = input.nextLine();
        System.out.print("Project completed: ");
        String complete = input.nextLine();
        // create project object
        projectDetail projectObj = new projectDetail();
        projectObj.setNumber(num);
        projectObj.setName(name);
        projectObj.setBuildingType(buildingType);
        projectObj.setAddress(address);
        projectObj.setERFNumber(ERFNumber);
        projectObj.setTotFee(totFee);
        projectObj.setTotPaid(totPaid);
        projectObj.setStartDate(dateFormat.format(date));
        projectObj.setDeadline(deadline);
        projectObj.setComplete(complete);
        // will be written to 'projects.txt'
        String projectData = String.format("%d,%s,%s,%s,%s,%d,%d,%s,%s,%s",
                projectObj.getNumber(), projectObj.getName(), projectObj.getBuildingType(),
                projectObj.getAddress(), projectObj.getERFNumber(), projectObj.getTotFee(),
                projectObj.getTotPaid(), projectObj.getStartDate(), projectObj.getDeadline(),
                projectObj.getComplete());
        // write 'projectData' to 'projects.txt' file
        BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt", true));
        writer.write(projectData + '\n');
        writer.close();
        sortFile("projects.txt");
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("\nDo you want to return to the main menu? ");
        String userInput = scanner1.nextLine().toLowerCase();
        if (userInput.equals("yes")) {
            System.out.println("\nReturning to menu");
            menu();
        } else if (userInput.equals("no")) {
            System.out.println("Existing program.");
            System.exit(0);
        }
    }

    // Change the due date of the project
    public static void changeDueDate() throws IOException {
        // read data from file
        // append data to list
        BufferedReader reader = new BufferedReader(new FileReader("projects.txt"));
        String line;
        List<String> projectObj = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            projectObj.add(line);
        }
        // print list with data
        System.out.println("The list of projects: \n" + projectObj);
        // close file
        reader.close();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nSelect project from list: ");
        int lineNumber = scanner.nextInt();
        String text;
        int lineNum;
        try {
            BufferedReader read = new BufferedReader(new FileReader("projects.txt"));
            for (lineNum = 1; lineNum < 10; lineNum++) {
                if (lineNum == lineNumber) {
                    text = read.readLine();
                    String[] arr = text.split(",");
                    int arrayLength = arr.length;
                    String[] array = Arrays.copyOfRange(arr, 0, arrayLength);
                    System.out.println("The project you selected: \n" + Arrays.toString(array));
                    // finalize project
                    System.out.println("\nChange project due date");
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Project current due date: " + getItem(array, 8));
                    System.out.print("Do you still want to change the due date? ");
                    String totPaid = scan.nextLine().toLowerCase();
                    if (totPaid.equals("no")) {
                        System.out.println("\nReturning to menu");
                        menu();
                    }
                    if (totPaid.equals("yes")) {
                        System.out.print("Give new due date: ");
                        String deadline = scan.nextLine();
                        // create project object
                        projectDetail project = new projectDetail();
                        project.setNumber(Integer.parseInt(getItem(array, 0)));
                        project.setName(getItem(array, 1));
                        project.setBuildingType(getItem(array, 2));
                        project.setAddress(getItem(array, 2));
                        project.setERFNumber(getItem(array, 4));
                        project.setTotFee(Integer.parseInt(getItem(array, 5)));
                        project.setTotPaid(Integer.parseInt(getItem(array, 6)));
                        project.setStartDate(getItem(array, 7));
                        project.setDeadline(deadline);
                        project.setComplete(getItem(array, 9));
                        // will be written to 'projects.txt'
                        String projectData = String.format("%d,%s,%s,%s,%s,%d,%d,%s,%s,%s",
                                project.getNumber(), project.getName(), project.getBuildingType(),
                                project.getAddress(), project.getERFNumber(), project.getTotFee(),
                                project.getTotPaid(), project.getStartDate(), project.getDeadline(),
                                project.getComplete());
                        // write 'projectData' to 'projects.txt' file
                        BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt", true));
                        writer.write(projectData + '\n');
                        writer.close();
                    }
                } else
                    read.readLine();
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // delete line from file
        replace("projects.txt", lineNumber);
        sortFile("projects.txt");
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("\nDo you want to return to the main menu? ");
        String userInput = scanner1.nextLine().toLowerCase();
        if (userInput.equals("yes")) {
            System.out.println("\nReturning to menu");
            menu();
        } else if (userInput.equals("no")) {
            System.out.println("Existing program.");
            System.exit(0);
        }
    }

    // Change the total amount of the fee paid
    public static void changeFeePaid() throws IOException {
        // read data from file
        // append data to list
        BufferedReader reader = new BufferedReader(new FileReader("projects.txt"));
        String line;
        List<String> projectObject = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            projectObject.add(line);
        }
        // print list with data
        System.out.println("The list of projects: \n" + projectObject);
        // close file
        reader.close();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nSelect project from list: ");
        int lineNumber = scanner.nextInt();
        String text;
        int lineNum;
        try {
            BufferedReader read = new BufferedReader(new FileReader("projects.txt"));
            for (lineNum = 1; lineNum < 10; lineNum++) {
                if (lineNum == lineNumber) {
                    text = read.readLine();
                    String[] arr = text.split(",");
                    int arrayLength = arr.length;
                    String[] array = Arrays.copyOfRange(arr, 0, arrayLength);
                    System.out.println("The project you selected: \n" + Arrays.toString(array));
                    // finalize project
                    System.out.println("\nChange total amount fee paid:");
                    Scanner scan = new Scanner(System.in);
                    // amount outstanding
                    System.out.println("Total amount charged: " + Integer.parseInt(getItem(array, 5))
                            + '\n' + "Total amount at first payment: " + Integer.parseInt(getItem(array, 6)));
                    int outStanding = Integer.parseInt(getItem(array, 5)) - Integer.parseInt(getItem(array, 6));
                    System.out.println("Outstanding amount: " + outStanding);
                    // ask if user wants to continue
                    System.out.print("Do you still want to change amount paid? ");
                    String userAnswer = scan.nextLine().toLowerCase();
                    if (userAnswer.equals("no")) {
                        System.out.println("\nReturning to menu");
                        menu();
                    }
                    if (userAnswer.equals("yes")) {
                        System.out.print("Amount paid up to date: ");
                        int totFee = scan.nextInt();
                        // create project object
                        projectDetail project = new projectDetail();
                        project.setNumber(Integer.parseInt(getItem(array, 0)));
                        project.setName(getItem(array, 1));
                        project.setBuildingType(getItem(array, 2));
                        project.setAddress(getItem(array, 2));
                        project.setERFNumber(getItem(array, 4));
                        project.setTotFee(Integer.parseInt(getItem(array, 5)));
                        project.setTotPaid(totFee);
                        project.setStartDate(getItem(array, 7));
                        project.setDeadline(getItem(array, 8));
                        project.setComplete(getItem(array, 9));
                        // will be written to 'projects.txt'
                        String projectData = String.format("%d,%s,%s,%s,%s,%d,%d,%s,%s,%s",
                                project.getNumber(), project.getName(), project.getBuildingType(),
                                project.getAddress(), project.getERFNumber(), project.getTotFee(),
                                project.getTotPaid(), project.getStartDate(), project.getDeadline(),
                                project.getComplete());
                        // write 'projectData' to 'projects.txt' file
                        BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt", true));
                        writer.write(projectData + '\n');
                        writer.close();
                    }
                } else
                    read.readLine();
            }// close file that was read
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // delete line from file
        replace("projects.txt", lineNumber);
        sortFile("projects.txt");
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("\nDo you want to return to the main menu? ");
        String userInput = scanner1.nextLine().toLowerCase();
        if (userInput.equals("yes")) {
            System.out.println("\nReturning to menu");
            menu();
        } else if (userInput.equals("no")) {
            System.out.println("Existing program.");
            System.exit(0);
        }
    }

    // Update contractor's contact details
    public static void contactDetails() throws IOException {
        // create person object
        try {
            Scanner userInput = new Scanner(System.in);
            System.out.print("Do you want to create contractor object? ");
            String answer = userInput.nextLine().toLowerCase();
            if (answer.equals("yes")) {
                // create object for person
                System.out.print("Person object number: ");
                String personCount = userInput.nextLine();
                String description = "contractor";
                System.out.print("Person name: ");
                String name = userInput.nextLine();
                System.out.print("Person telephone number: ");
                String tel = userInput.nextLine();
                System.out.print("Person email: ");
                String email = userInput.nextLine();
                System.out.print("Person address: ");
                String address = userInput.nextLine();
                // person object
                projectPerson personDetails = new projectPerson();
                personDetails.setPersonCount(personCount);
                personDetails.setPersonDescription(description);
                personDetails.setPersonName(name);
                personDetails.setPersonTelNum(tel);
                personDetails.setPersonEmail(email);
                personDetails.setPersonAddress(address);
                // format object details
                String personData = String.format("%s,%s,%s,%s,%s,%s", personDetails.getPersonCount(),
                        personDetails.getPersonDescription(), personDetails.getPersonName(),
                        personDetails.getPersonTelNum(), personDetails.getPersonEmail(),
                        personDetails.getPersonAddress());
                // write 'personData' to 'person.txt' file
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("person.txt", true));
                bufferedWriter.write(personData + '\n');
                bufferedWriter.close();
            } else {
                boolean file = isFileEmpty(new File("person.txt"));
                if (file) {
                    System.out.println("File is empty.");
                    System.exit(0);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            e.printStackTrace();
        }
        // read data from file
        // append data to list
        BufferedReader reader = new BufferedReader(new FileReader("person.txt"));
        String line;
        List<String> projectObj = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            projectObj.add(line);
        }
        // print list with data
        System.out.println("The list of projects: \n" + projectObj);
        // close file
        reader.close();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nSelect project from list: ");
        int lineNumber = scanner.nextInt();
        String text;
        int lineNum;
        try {
            BufferedReader read = new BufferedReader(new FileReader("person.txt"));
            for (lineNum = 1; lineNum < 10; lineNum++) {
                if (lineNum == lineNumber) {
                    text = read.readLine();
                    String[] arr = text.split(",");
                    int arrayLength = arr.length;
                    String[] array = Arrays.copyOfRange(arr, 0, arrayLength);
                    System.out.println("The project you selected: \n" + Arrays.toString(array));
                    // ask if user wants to continue
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Do you still want to change contact details? ");
                    String contact = scan.nextLine().toLowerCase();
                    if (contact.equals("no")) {
                        System.exit(0);
                    }
                    if (contact.equals("yes")) {
                        System.out.print("Contact details: ");
                        String tel = scan.nextLine();
                        // create project object
                        projectPerson personDetails = new projectPerson();
                        personDetails.personDescription = getItem(array, 0);
                        personDetails.personName = getItem(array, 1);
                        personDetails.personTelNum = tel;
                        personDetails.personEmail = getItem(array, 3);
                        personDetails.personAddress = getItem(array, 4);
                        // will be written to 'projects.txt'
                        String projectData = String.format("%s,%s,%s,%s,%s"
                                , personDetails.personDescription, personDetails.personName, personDetails.personTelNum,
                                personDetails.personEmail, personDetails.personAddress);
                        // write 'projectData' to 'projects.txt' file
                        BufferedWriter writer = new BufferedWriter(new FileWriter("person.txt", true));
                        writer.write(projectData + '\n');
                        writer.close();
                    }
                } else
                    read.readLine();
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //function delete line from file that already exist
        replace("person.txt", lineNumber);
        sortFile("person.txt");
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("\nDo you want to return to the main menu? ");
        String userInput = scanner1.nextLine().toLowerCase();
        if (userInput.equals("yes")) {
            System.out.println("\nReturning to menu");
            menu();
        } else if (userInput.equals("no")) {
            System.out.println("Existing program.");
            System.exit(0);
        }
    }

    // Finalise the project
    public static void finalise() throws IOException {
        // read data from file
        // append data to list
        BufferedReader reader = new BufferedReader(new FileReader("projects.txt"));
        String line;
        List<String> project = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            project.add(line);
        }
        // print list with data
        System.out.println("The list of projects: \n" + project);
        // close file
        reader.close();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInput number of project you want to finalise: ");
        int lineNumber = scanner.nextInt();
        String text;
        int lineNum;
        try {
            BufferedReader read = new BufferedReader(new FileReader("projects.txt"));
            for (lineNum = 1; lineNum < 10; lineNum++) {
                if (lineNum == lineNumber) {
                    text = read.readLine();
                    String[] arr = text.split(",");
                    int arrayLength = arr.length;
                    String[] array = Arrays.copyOfRange(arr, 0, arrayLength);
                    System.out.println("The project you selected: \n" + Arrays.toString(array));
                    // finalize project
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Project Total Fee: " + Integer.parseInt(getItem(array, 5)));
                    System.out.println("Total amount charged: " + Integer.parseInt(getItem(array, 5))
                            + '\n' + "Total amount at first payment: " + Integer.parseInt(getItem(array, 6)));
                    int outStanding = Integer.parseInt(getItem(array, 5)) - Integer.parseInt(getItem(array, 6));
                    System.out.println("Customer needs to pay: " + outStanding);
                    System.out.print("Did the customer pay the full amount? ");
                    String totPaid = scan.nextLine().toLowerCase();
                    if (totPaid.equals("no")) {
                        System.out.println("Can not finalise this project if there is outstanding payment.");
                        System.out.println("\nReturning to menu");
                        menu();
                    }
                    if (totPaid.equals("yes")) {
                        System.out.print("Project completed: ");
                        String complete = scan.nextLine();
                        // create project object
                        projectDetail projectObject = new projectDetail();
                        projectObject.setNumber(Integer.parseInt(getItem(array, 0)));
                        projectObject.setName(getItem(array, 1));
                        projectObject.setBuildingType(getItem(array, 2));
                        projectObject.setAddress(getItem(array, 2));
                        projectObject.setERFNumber(getItem(array, 4));
                        projectObject.setTotFee(Integer.parseInt(getItem(array, 5)));
                        projectObject.setTotPaid(Integer.parseInt(getItem(array, 6)));
                        projectObject.setStartDate(getItem(array, 7));
                        projectObject.setDeadline(getItem(array, 8));
                        projectObject.setComplete(complete);
                        // will be written to 'projects.txt'
                        String projectData = String.format("%d,%s,%s,%s,%s,%d,%d,%s,%s,%s",
                                projectObject.getNumber(), projectObject.getName(), projectObject.getBuildingType(),
                                projectObject.getAddress(), projectObject.getERFNumber(), projectObject.getTotFee(),
                                projectObject.getTotPaid(), projectObject.getStartDate(), projectObject.getDeadline(),
                                projectObject.getComplete());
                        // write 'projectData' to 'projects.txt' file
                        BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt", true));
                        writer.write(projectData + '\n');
                        writer.close();
                    }
                } else
                    read.readLine();
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // delete line from file
        replace("projects.txt", lineNumber);
        sortFile("projects.txt");
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("\nDo you want to return to the main menu? ");
        String userInput = scanner1.nextLine().toLowerCase();
        if (userInput.equals("yes")) {
            System.out.println("\nReturning to menu");
            menu();
        } else if (userInput.equals("no")) {
            System.out.println("Existing program.");
            System.exit(0);
        }
    }

    // Read projects from existing file
    public static void fileStatistics() throws IOException {
        // read data from file
        // append data to list
        BufferedReader reader = new BufferedReader(new FileReader("projects.txt"));
        String line;
        List<String> projectObj = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            projectObj.add(line);
        }
        // print list with data
        System.out.println("The list of projects: \n" + projectObj);
        // close file
        reader.close();
        // Allow user to see list of incomplete projects
        // user can input if they want to see incomplete projects or not
        Scanner input = new Scanner(System.in);
        System.out.print("\nDo you wish to see incomplete projects? ");
        String answer = input.nextLine();
        if (answer.equals("Yes")) {
            // open 'project.txt' file and reads the file
            Scanner scan = new Scanner(new File("projects.txt"));
            while (scan.hasNext()) {
                String fileLine = scan.nextLine();
                // if the file contains the words 'No'
                // the line in the file will be added to a list
                if (fileLine.contains("No")) {
                    // create list
                    List<String> incompleteProjects = new ArrayList<>();
                    // add data of incomplete projects to the list
                    incompleteProjects.add(fileLine);
                    // print the list
                    System.out.println(incompleteProjects);
                }
            }
            // close file
            scan.close();
        }
        System.out.print("\nDo you wish to see projects past due date? ");
        String userInput = input.nextLine().toLowerCase();
        if (userInput.equals("yes")) {
            // allow user to see list of projects that are past the due date
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            dateFormat.format(date);
            BufferedReader fileReader = null;
            try {
                File file = new File("projects.txt");
                FileReader read = new FileReader(file);
                fileReader = new BufferedReader(read);
                String fileLine;
                // create a list to store date
                String[] arrString;
                // create list to store date project that is past due date
                List<String> pastDueDate = new ArrayList<>();
                // create current date object
                //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date todayDate = new Date();
                dateFormat.format(todayDate);
                Date deadlineDate = null;
                // get all the projects past due date
                while ((fileLine = fileReader.readLine()) != null) {
                    //create list
                    // read file data in to 'fileData' list
                    // split lines in 'project.txt' file
                    String[] fileData = fileLine.split(",");
                    // get the length of 'fileData' list
                    int arrLength = fileData.length;
                    // copy 'fileData' list information to new list
                    arrString = Arrays.copyOfRange(fileData, 0, arrLength);
                    // get the deadline date from file
                    deadlineDate = dateFormat.parse(getItem(arrString, 8));
                    // check if deadline dates is past the due date
                    if ((todayDate.after(deadlineDate))) {
                        Scanner scan = new Scanner(file);
                        while (scan.hasNext()) {
                            String nextLine = scan.nextLine();
                            // if deadline date is past due date add to 'pastDueDate' list
                            if (nextLine.contains(dateFormat.format(deadlineDate))) {
                                pastDueDate.add(nextLine);
                            }
                        }
                    }
                }
                // if there is no projects past due date display message on screen
                if ((todayDate.before(deadlineDate))) {
                    System.out.println("No projects past due date.");
                }
                // if there is projects past due date display on screen
                System.out.println("Projects past due date:\n" + pastDueDate);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileReader != null) {
                        fileReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("\nDo you want to return to the main menu? ");
        String inputUser = scanner1.nextLine().toLowerCase();
        if (inputUser.equals("yes")) {
            System.out.println("\nReturning to menu");
            menu();
        } else if (inputUser.equals("no")) {
            System.out.println("Existing program.");
            System.exit(0);
        }
    }

    // Get index from of list
    public static String getItem(String[] arr, int index) {
        return arr[index];
    }

    // Remove line from text file
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void replace(String filePath, int deleteLine) {
        String tempFile = "temp.txt";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);
        int line = 0;
        String currentLine;
        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            while ((currentLine = br.readLine()) != null) {
                line++;
                if (deleteLine != line) {
                    pw.println(currentLine);
                }
            }
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Error has occurred");
            System.out.println(e.getMessage());
        }
        oldFile.delete();
        File input = new File(filePath);
        newFile.renameTo(input);
        System.out.println("\nProject was updated successfully.");
    }

    // Check if file is empty
    public static boolean isFileEmpty(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br.readLine() == null;
    }

    public static void sortFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();

        lines.sort(Collator.getInstance());

        FileWriter writer = new FileWriter(filePath);
        for (String str : lines) {
            writer.write(str + "\r\n");
        }
        writer.close();
    }

}
