
import java.util.*;
public class Program {
    private static Scanner sc = new Scanner(System.in);;
    public static void main(String[] args) {
        int i = 0;
            Admin.Train.add(new ArrayList<ArrayList<String>>());
            for (int j = 0; j < Admin.row; j++) {
                Admin.Train.get(i).add(new ArrayList<String>(Arrays.asList("0", "0", "0", "0", "0")));
            }

        Admin.TrainInfo.add(new TrainClass(String.valueOf(Admin.TrainID), "KurlaExpress", "Coimbatore-Mumbai",
                "Coimbatore Tiruppur Bangalore Pune Mumbai"));
        Admin.TrainID++;

        User.us.add(new User("Ha1", "Harini", "48"));
            System.out.print("\033[H\033[2J");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    Admin.login();
                    break;
                case 2:
                    User.userMain();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }

            
    }
}

class TrainClass {
    public String TrainId;
    public String TrainName;
    public String TrainRte;
    public String TrainStations;
    TrainClass(String trainId, String trainName, String trainRte, String trainStations) {
        this.TrainId = trainId;
        this.TrainName = trainName;
        this.TrainRte = trainRte;
        this.TrainStations = trainStations;
    }
}

class Admin {
    static boolean flag = false;
    static int TrainID = 1;
    static int row = 5;
    static ArrayList<ArrayList<ArrayList<String>>> Train = new ArrayList<>();
    static List<TrainClass> TrainInfo = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    static void login() {
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Admin Id: ");
        String adminId = sc.next();
        if(adminId.equals("Harini")){
            System.out.print("\033[H\033[2J");
            System.out.println("Enter Password: ");
            String password = sc.next();
            if ( password.equals("48")) {
                adminMain();
            } else {
                System.out.println("Invalid Password");
        }
        }
        else{
            System.out.println("Invalid AdminID");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
            try {
                System.in.read();
                Program.main(null);
            }
             catch (Exception e) {}
    }


    static void adminMain() {
        System.out.print("\033[H\033[2J");
        System.out.println("1. Add Train");
        System.out.println("2. Declare Seats");
        System.out.println("3. Show Trains");
        System.out.println("4. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
                addTrain();
                break;
            case 2:
                declareSeats();
                break;
            case 3:
                flag = true;
                printTrains();
                break;
            case 4:
                Program.main(null);
                break;
            default:
                System.out.println("Invalid input");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
            try {
                System.in.read();
                adminMain();
            }
             catch (Exception e) {}
    }


    static void addTrain() {
        System.out.print("\033[H\033[2J");
        System.out.print("Enter Name : ");
        String Name = sc.next();

        System.out.print("\033[H\033[2J");
        System.out.print("Enter Route : ");
        String Route = sc.next();
        
        System.out.print("\033[H\033[2J");
        System.out.print("Enter Stations : ");
        sc.nextLine();
        String Station = sc.nextLine();
        Train.add(new ArrayList<ArrayList<String>>());
        TrainInfo.add(new TrainClass(String.valueOf(TrainID), Name, Route, Station));
        TrainID++;

        System.out.println();
        System.out.println("Press ENTER to back");
        try {
            System.in.read();
            adminMain();
        } 
        catch (Exception e) {}
    }


    static void declareSeats() {
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Train Id : ");
        String id = sc.next();
        boolean flag = false;
        for (int i = 0; i < TrainInfo.size(); i++) {
            if (TrainInfo.get(i).TrainId.equals(id)) {
                flag = true;
            }
        }
        if (flag) {
            System.out.println("Enter No. of Seats : ");
            int No = sc.nextInt();
            int Id = Integer.parseInt(id) - 1;
                Train.get(Id).clear();
                for (int i = 0; i < No; i++) {
                    String[] tp = TrainInfo.get(Id).TrainStations.split(" ");
                    ArrayList<String> temp = new ArrayList<String>();
                    for (int j = 0; j < tp.length; j++) {
                        temp.add("0");
                    }
                    Train.get(Id).add(temp);
                }
            System.out.println();
            System.out.println("Seat Declared");
            System.out.println();
            System.out.println("Press ENTER to back");
            try {
                System.in.read();
                adminMain();
            } catch (Exception e) {
            }
        } else {
            System.out.println();
            System.out.println("Invalid TrainID");
            System.out.println();
            System.out.println("Press ENTER to back");
            try {
                System.in.read();
                adminMain();
            } catch (Exception e) {
            }
        }
    }

    static void printTrains() {
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < Train.size(); i++) {
            for (int j = 0; j < Train.get(i).size(); j++) { 
                for (int k = 0; k < Train.get(i).get(j).size(); k++) { 
                    System.out.printf("%-5s", Train.get(i).get(j).get(k));
                }
                System.out.println();
            }
            System.out.println("Train ID => " + TrainInfo.get(i).TrainId);
            System.out.println("Train Name => " + TrainInfo.get(i).TrainName);
            System.out.println("Train Route => " + TrainInfo.get(i).TrainRte);
            System.out.println("Train Stations => " + TrainInfo.get(i).TrainStations);
            System.out.println("Availability of Seats => " + checkAvailability(Integer.parseInt(TrainInfo.get(i).TrainId) - 1));
        }
        System.out.println();
        System.out.println("Press ENTER to back");
        try {
            System.in.read();
            if (flag) {
                adminMain();
            } else {
                User.userHome();
            }
        } catch (Exception e) {
        }
    }

    static int checkAvailability(int i) {
        int res = 0;
        for (int j = 0; j < Train.get(i).size(); j++) {
            for (int k = 0; k < Train.get(i).get(j).size(); k++) {
                if (Train.get(i).get(j).get(k).equals("0")) {
                    res++;
                }
            }
        }
        return res;
    }

    
}

class Tickets {
    static int tnoGen = 1;
    public int tno;
    public int ttID;
    public int tst;
    public int tfrom;
    public int tto;
    public String pID;
    public String tn;
    public String trt;
    public String tsts;

    Tickets(int tno, String pID, String tn, String trt,
            String tsts, int ttID, int tst, int tfrom, int tto) {
        this.tno = tno;
        this.pID = pID;
        this.tn = tn;
        this.trt = trt;
        this.tsts = tsts;
        this.ttID = ttID;
        this.tst = tst;
        this.tfrom = tfrom;
        this.tto = tto;
    }
}


class User {

    private static Scanner sc = new Scanner(System.in);
    static List<User> us = new ArrayList<>();
    static List<Tickets> TicketDetails = new ArrayList<>();
    static int uniqueID = 2;
    public String UserId;
    public String UserName;
    public String UserPassword;
    static String CurrentUser = "";

    User(String userId, String userName, String userPassword) {
        this.UserId = userId;
        this.UserName = userName;
        this.UserPassword = userPassword;
    }

    static void userMain() {
        System.out.print("\033[H\033[2J");
        System.out.println("1. SignUp");
        System.out.println("2. Login");
        System.out.println("3. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
                signup();
                break;
            case 2:
                login();
                break;
            case 3:
                Program.main(null);
                break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER to back");
                try {
                    System.in.read();
                    userMain();
                } 
                catch (Exception e) {}
        }
    }

    static void login() {
        System.out.print("\033[H\033[2J");
        System.out.println("Enter your UserName");
        String username = sc.next();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter your password");
        String password = sc.next();

        for (int i = 0; i < us.size(); i++) {
            if (us.get(i).UserName.equals(username) && us.get(i).UserPassword.equals(password)) {
                CurrentUser = us.get(i).UserId;
                userHome();
            }
        }

        System.out.println("Invalid UserName or Password");
        System.out.println();
        System.out.println("Press ENTER to back");
        try {
            System.in.read();
            Program.main(null);
        } 
        catch (Exception e) {}
    }

    static void userHome() {
        System.out.print("\033[H\033[2J");
        System.out.println("1. Trains & Availability");
        System.out.println("2. Book Tickets");
        System.out.println("3. View Bookings");
        System.out.println("4. Cancel Bookings");
        System.out.println("5. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
                Admin.flag = false;
                Admin.printTrains();
                break;
            case 2:
                bookTickets();
                break;
            case 3:
                viewBookings();
                break;
            case 4:
                cancelBookings();
                break;
            case 5:
                Program.main(null);
                break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER to back");
                try {
                    System.in.read();
                    userHome();
                }
                 catch (Exception e) {}
        }
    }

    static void signup() {
        System.out.print("\033[H\033[2J");
        System.out.println("Enter your username");
        sc.nextLine();
        String uname = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter your password");
        String pass = sc.next();
        String uId = uname.substring(0, 2) + String.valueOf(uniqueID);
        uniqueID++;
        us.add(new User(uId, uname, pass));
        System.out.println();
        System.out.println("User signUp successful");
        System.out.println("Press ENTER to continue");
        try {
            System.in.read();
            userHome();
        } 
        catch (Exception e) {}
    }

    static void bookTickets() {
        System.out.print("\033[H\033[2J");
        System.out.println();
        System.out.printf("%-8s%-15s%-20s%-20s\n\n", "S.no", "Train ID", "Train Name", "Train Route");
        for (int i = 0; i < Admin.TrainInfo.size(); i++) {
            System.out.printf("%-8s%-15s%-20s%-20s\n", i + 1,Admin.TrainInfo.get(i).TrainId, Admin.TrainInfo.get(i).TrainName,
                    Admin.TrainInfo.get(i).TrainRte);
        }
        System.out.println();
        System.out.println("Enter No. of Booking : ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("\033[H\033[2J");
            System.out.print("Enter Train ID : ");
            String idBook = sc.next();
            System.out.println();
            boolean Available = false;
            for (int j = 0; j < Admin.TrainInfo.size(); j++) {
                if (Admin.TrainInfo.get(j).TrainId.equals(idBook)) {
                    Available = true;
                }
            }

            if (Available) {
                
                int tId = Integer.parseInt(idBook) - 1;
                printTrain(tId);
                System.out.println();
                System.out.println("Enter From & To (Eg: 1 3) : ");
                int stationIn = sc.nextInt();
                int stationOut = sc.nextInt();

                int SeatNo = bookSeat(stationIn, stationOut, tId, User.CurrentUser);

                if (SeatNo != -1) {
                    System.out.println("Your Seat No : " + SeatNo);
                } else {
                    TicketDetails.add(new Tickets(-1, CurrentUser, Admin.TrainInfo.get(tId).TrainName,
                            Admin.TrainInfo.get(tId).TrainRte, "Pending",
                            Integer.parseInt(Admin.TrainInfo.get(tId).TrainId),
                            -1, stationIn, stationOut));
                    System.out.println("Seats are full !");
                    System.out.println("You're in the WAITING LIST !");
                }

                System.out.println();
                System.out.println("Press ENTER to back");
                try {
                    System.in.read();
                } 
                catch (Exception e) {}
            }
             else {
                System.out.println();
                System.out.println("Invalid TrainID");
                System.out.println();
                System.out.println("Press ENTER to back");
                try {
                    System.in.read();
                    bookTickets();
                }
                 catch (Exception e) {}
            }
        }

        System.out.println();
        System.out.println("Press ENTER to back");
        try {
            System.in.read();
            userHome();
        }
         catch (Exception e) {}

    }


    static void viewBookings() {
        System.out.print("\033[H\033[2J");
        System.out.printf("%-15s%-20s%-20s%-15s%-15s%-15s%-5s\n", "Ticket No.", "Train Name", "Train Route", "Seat No.",
                "From", "To", "Status");
        for (int i = 0; i < TicketDetails.size(); i++) {
            if (TicketDetails.get(i).pID.equals(User.CurrentUser)) {
                System.out.printf("%-15d%-20s%-20s%-15s%-15d%-15d%-5s\n",
                        TicketDetails.get(i).tno,
                        TicketDetails.get(i).tn, TicketDetails.get(i).trt,
                        TicketDetails.get(i).tst, TicketDetails.get(i).tfrom,
                        TicketDetails.get(i).tto, TicketDetails.get(i).tsts);
            }
        }
        System.out.println();
        System.out.println("Press ENTER to back");
        try {
            System.in.read();
            userHome();
        }
         catch (Exception e) {}
    }

    static void printTrain(int tId) {
        for (int j = 0; j < Admin.Train.get(tId).size(); j++) {
            for (int k = 0; k < Admin.Train.get(tId).get(j).size(); k++) {
                System.out.printf("%-5s", Admin.Train.get(tId).get(j).get(k));
            }
            System.out.println();
        }
        System.out.println("Train ID => " + Admin.TrainInfo.get(tId).TrainId); 
        System.out.println("Train Name => " + Admin.TrainInfo.get(tId).TrainName);
        System.out.println("Train Route => "+ Admin.TrainInfo.get(tId).TrainRte);
        System.out.println("Train Stations => " + Admin.TrainInfo.get(tId).TrainStations);
        System.out.println("Available Seats => "+ Admin.checkAvailability(Integer.parseInt(Admin.TrainInfo.get(tId).TrainId) - 1));
    }

    static void removeSeat(int tId, int tSeat, int tStart, int tEnd) {
        for (int j = tStart - 1; j < tEnd; j++) {
            Admin.Train.get(tId).get(tSeat).set(j, "0");
        }
    }

    static int bookSeat(int from, int to, int tId, String setUser) {
        int res = -1;
        for (int i = 0; i < Admin.Train.get(tId).size(); i++) {
            int total = 0, size = 0;
            for (int k = from - 1; k < to; k++) {
                size++;
                if (Admin.Train.get(tId).get(i).get(k).equals("0")) {
                    total++;
                }
            }
            if (total == size) {
                for (int k = from - 1; k < to; k++) {
                    Admin.Train.get(tId).get(i).set(k, setUser);
                }
                res = i;
                    TicketDetails.add(new Tickets(Tickets.tnoGen, User.CurrentUser,
                            Admin.TrainInfo.get(tId).TrainName, Admin.TrainInfo.get(tId).TrainRte, "Booked",
                            Integer.parseInt(Admin.TrainInfo.get(tId).TrainId), i, from, to));
                    Tickets.tnoGen++;
                return res;
            }
        }
        return res;
    }

    static void pending() {
        for (int i = 0; i < TicketDetails.size(); i++) {
            if (TicketDetails.get(i).tsts.equals("Pending")) {
                int res = bookSeat(TicketDetails.get(i).tfrom, TicketDetails.get(i).tto,
                        TicketDetails.get(i).ttID - 1, TicketDetails.get(i).pID);
                if (res != -1) {
                    TicketDetails.get(i).tsts = "Booked";
                    TicketDetails.get(i).tno = Tickets.tnoGen;
                    TicketDetails.get(i).tst = res;
                    Tickets.tnoGen++;
                }
            }
        }
    }

    static void cancelBookings() {
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Ticket No : ");
        int ticketNum = sc.nextInt();
        boolean isTicket = false;
        for (int i = 0; i < TicketDetails.size(); i++) {
            if (TicketDetails.get(i).tno == ticketNum) {
                isTicket = true;
            }
        }

        if (isTicket) {
            for (int i = 0; i < TicketDetails.size(); i++) {
                if (TicketDetails.get(i).tno == ticketNum) {
                    removeSeat(TicketDetails.get(i).ttID - 1, TicketDetails.get(i).tst,
                            TicketDetails.get(i).tfrom, TicketDetails.get(i).tto);
                    TicketDetails.remove(i);
                    pending();
                    break;
                }
            }

            System.out.println();
            System.out.println("Ticket Cancelled");
        } else {
            System.out.println();
            System.out.println("Ticket No. " + ticketNum + " Not Found");
        }
        System.out.println();
        System.out.println("Press ENTER to back");
        try {
            System.in.read();
            userHome();
        } 
        catch (Exception e) {}
    }
}


