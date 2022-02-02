import java.time.LocalDate;
import java.util.*;

public class Program{
    private static Scanner sc = new Scanner(System.in);
    static LocalDate cDate = LocalDate.now();
    static LocalDate uDate = cDate.plusDays(1);
    public static void main(String[] args) {
        Vehicle.vehicle.add(new Vehicle(Admin.vId, "Car", "Volkswagen", 1948, 10000, 0, 3, true));
        Admin.vId++;
        Vehicle.vehicle.add(new Vehicle(Admin.vId, "Bike", "Rx100", 4819, 3000, 0, 3, true));
        Admin.vId++;
        Vehicle.vehicle.add(new Vehicle(Admin.vId, "Car", "Hyundai", 5486, 7000, 0, 3, true));
        Admin.vId++;
        Vehicle.vehicle.add(new Vehicle(Admin.vId, "Bike", "Pulsar", 9849, 2000, 0, 3, true));
        Admin.vId++;
        Vehicle.vehicle.add(new Vehicle(Admin.vId, "Car", "Honda", 3572, 6000, 0, 3, true));
        Admin.vId++;
        Vehicle.vehicle.add(new Vehicle(Admin.vId, "Bike", "Scooty", 7550, 1500, 0, 3, true));
        Admin.vId++;

        User.user.add(new User("Harini", "harini@gmail.com", "123", 30000));
        User.user.add(new User("Razia", "razia@gmail.com", "123", 30000));

        System.out.print("\033[H\033[2J");
        System.out.println("1. Admin");
        System.out.println("2. Borrower");
        System.out.println("3. Exit");
        int op = sc.nextInt();
        switch(op){
            case 1:
                Admin.login();
                break;
            case 2:
                Renter.main();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input");
        }
    }
}

class Renter{
    private static Scanner sc = new Scanner(System.in);
    static int car = 0;
    static int bike = 0;
    static String cUser = "";

    static void login(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter User Id :");
        sc.nextLine();
        String mail = sc.nextLine();
        boolean r = false;
        boolean b = false;
        for(int i=0;i<User.user.size();i++){
            if(User.user.get(i).uMail.equals(mail)){
                r = true;
                System.out.print("\033[H\033[2J");
                System.out.println("Enter User Password :");
                String pass = sc.nextLine();
                if(User.user.get(i).uPass.equals(pass)){
                    b = true;
                    cUser = User.user.get(i).uName;
                    function();
                }
            }
        }
        if(!r){
            System.out.println("Invalid User ID");
            System.out.println();
            System.out.println("Press ENTER for back");
            try {
                System.in.read();
                Program.main(null);
            } catch (Exception e) {
            }
        }
        else if(!b){
            System.out.println("Invalid Password");
            System.out.println();
            System.out.println("Press ENTER for back");
            try {
                System.in.read();
                main();
            } catch (Exception e) {
            }
        }
    }

    static void function(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. View vehicle");
        System.out.println("2. Search");
        System.out.println("3. Extend Date");
        System.out.println("4. Return Vehicle");
        System.out.println("5. History");
        System.out.println("6. Cart");
        System.out.println("7. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
                view();
                break;
            case 2:
                search();
                break;
            case 3:
                extend();
                break;
            case 4:
                retrn();
                break;
            case 5:
                history();
                break;
            case 6:
                cart();
                break;
            case 7:
                main();
                break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try {
                    System.in.read();
                    main();
                } catch (Exception e) {
                }
        }
    }

    static ArrayList<String> history = new ArrayList<>();

    static void history(){
        System.out.print("\033[H\033[2J");
        if(history.size() > 0){
            for(int i=0;i<history.size();i++){
                System.out.println(history.get(i));
            }
        }
        else{
            System.out.println("No historys yet");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            function();
        } catch (Exception e) {
        }
    }

    static void hist(String User, String a, String b, LocalDate date){
        history.add(User+", You have "+a+" - "+b+" on "+date);
    }

    static void fine(String User, String a, int fine) {
        history.add(User+", You are "+a+" Rs." + fine);
    }

    //Harini, you have rented a Bike on date;
    //Harini, you have returned a Bike on date;
    // Harini, you are fined Rs;

    static int lateReturn = 50;

    static void retrn(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Vehicle Name:");
        sc.nextLine();
        String name = sc.nextLine();
        boolean r = false;
        int km =0;
        int index = -1;
        for (int i = 0; i < Cart.out.size(); i++) {
            if (Cart.out.get(i).vName.equals(name)) {
                for(int k=0;k<User.user.size();k++){
                    if(User.user.get(k).uName.equals(cUser)){
                        int pamt = Cart.out.get(i).vRate;
                        User.user.get(k).iamt = User.user.get(k).iamt - pamt;
                        System.out.println("Balance :" + User.user.get(k).iamt);
                        int fine = 0;
                        r = true;
                        System.out.println();
                        System.out.println("Is vehicle lost? y/n");
                        String s = sc.next().toLowerCase();
                        if(s.equals("y")){
                            if(Cart.out.get(i).vType.equals("Car")){
                            fine = fine + 10000;
                            }
                            else{
                            fine = fine + 3000;
                            }
                        }
                        else{
                        index = i;
                        System.out.println();
                        System.out.println("Enter Returned date");
                        int n = sc.nextInt();
                        LocalDate ret = Program.cDate.plusDays(n);
                        hist(cUser, "returned", name, ret);
                        fine = fine + (n*lateReturn);
                        System.out.println();
                        System.out.println("Enter Kms Vehicle runned :");
                        km = sc.nextInt();
                        for (int l = 0; l < Vehicle.vehicle.size(); l++) {
                            if (Vehicle.vehicle.get(l).vName.equals(Cart.out.get(i).vName)) {
                                Vehicle.vehicle.get(l).vKms = Vehicle.vehicle.get(l).vKms + km;
                                if(Vehicle.vehicle.get(l).vKms > 3000){
                                    Vehicle.vehicle.get(l).vServ = false;
                                }
                            }
                        }
                        if(km >= 500){
                            int ch =(int)(0.15*Cart.out.get(i).vRate);
                            fine = fine + ch;
                        }
                        System.out.println();
                        System.out.println("Any Damage ? y/n");
                        sc.nextLine();
                        String s2 = sc.next().toLowerCase();
                        int d = 0;
                        if(s2.equals("y")){
                            System.out.println();
                            System.out.println("Enter damage : Low/Medium/High");
                            String dam = sc.next().toLowerCase();
                            if(dam.equals("low")){
                                d = (int)(0.2*Cart.out.get(i).vRate);
                            }
                            else if(dam.equals("medium")){
                                d = (int)(0.5*Cart.out.get(i).vRate);
                            }
                            else if(dam.equals("high")){
                                d = (int)(0.7*Cart.out.get(i).vRate);
                            }
                        }
                        fine = fine + d;
                        for (int l = 0; l < Vehicle.vehicle.size(); l++) {
                            if (Vehicle.vehicle.get(l).vName.equals(Cart.out.get(i).vName)) {
                                Vehicle.vehicle.get(l).vCount++;
                            }
                        }
                    }
                    if(fine > 0){
                        fine(cUser,"fined",fine);
                    }
                    System.out.println(fine);
                    System.out.println();
                    System.out.println("Your overall Fine : Rs."+fine);
                    User.user.get(k).iamt = User.user.get(k).iamt - fine;
                    
                    if(Cart.out.get(i).vType.equals("Car")){
                        --car;
                    }
                    else{
                        --bike;
                    }
                    Cart.out.remove(i);
                    System.out.println("Balance :" +User.user.get(k).iamt);
                    
                    
                    }
                }
                
            }
        }
        if (!r) {
            System.out.println("Vehicle not found");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            function();
        } catch (Exception e) {}
    }

    static void solve(int i, int km){
        
    }

    static void extend(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Vehicle Name:");
        sc.nextLine();
        String name = sc.nextLine();
        boolean r = false;
        for(int i=0;i<Cart.cart.size();i++){
            if(Cart.cart.get(i).vName.equals(name)){
                r = true;
                Cart.cart.get(i).date = Program.uDate;
                System.out.println();
                System.out.println("Return the Vehicle before " + Cart.cart.get(i).date);
                System.out.println("Returning after due will be charged fine");
            }
        }
        if(!r) {
            System.out.println("Vehicle not found");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            function();
        } catch (Exception e) {
        }
    }

    static void search(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Vehicle Name :");
        sc.nextLine();
        String name = sc.nextLine();
        boolean b = false;
        for(int i=0;i<Vehicle.vehicle.size();i++){
            if(Vehicle.vehicle.get(i).vName.equals(name)){
                b = true;
                System.out.printf("%-10s%-13s%-10s%-10s%-10s%-10s", "ID", "Name", "No.", "Rate", "Kms", "Availability");
                System.out.println();
                System.out.printf("%-10s%-13s%-10s%-10s%-10s%-10s", Vehicle.vehicle
                        .get(i).vId, Vehicle.vehicle.get(i).vName, Vehicle.vehicle.get(i).vNo,
                        Vehicle.vehicle.get(i).vRate,
                        Vehicle.vehicle.get(i).vKms, Vehicle.vehicle.get(i).vCount);
                System.out.println();
            }
        }
        if(!b){
            System.out.println("Vehicle not found");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            function();
        } catch (Exception e) {
        }
    }

    static void view(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. Bikes");
        System.out.println("2. Cars");
        System.out.println("3. Back");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                bikes();
                break;
            case 2:
                cars();
                break;
            case 3:
                function();
                break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try {
                    System.in.read();
                    function();
                } catch (Exception e) {
                }
        }
    }

    static void cars() {
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-13s%-10s%-10s%-10s%-10s", "ID", "Name", "No.", "Rate", "Kms", "Availability");
        System.out.println();
        System.out.println();
        for (int i = 0; i < Vehicle.vehicle.size(); i++) {
            if (Vehicle.vehicle.get(i).vType.equals("Car") && Vehicle.vehicle.get(i).vCount > 0 && Vehicle.vehicle.get(i).vServ) {
                System.out.printf("%-10s%-13s%-10s%-10s%-10s%-10s", Vehicle.vehicle
                        .get(i).vId, Vehicle.vehicle.get(i).vName, Vehicle.vehicle.get(i).vNo,
                        Vehicle.vehicle.get(i).vRate,
                        Vehicle.vehicle.get(i).vKms, Vehicle.vehicle.get(i).vCount );
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Select ID to Add to Cart");
        int n = sc.nextInt();
        Cart.cart.add(new Cart(n, Vehicle.vehicle.get(n-1).vType, Vehicle.vehicle.get(n-1).vName, Vehicle.vehicle.get(n-1).vNo, Vehicle.vehicle.get(n-1).vRate, Program.cDate));
        System.out.println();
        System.out.println("Vehicle Added to the cart");
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            view();
        } catch (Exception e) {
        }
    }

    static void bikes() {
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-13s%-10s%-10s%-10s%-10s", "ID", "Name", "No.", "Rate", "Kms", "Availability");
        System.out.println();
        System.out.println();
        for (int i = 0; i < Vehicle.vehicle.size(); i++) {
            if (Vehicle.vehicle.get(i).vType.equals("Bike") && Vehicle.vehicle.get(i).vCount > 0
                    && Vehicle.vehicle.get(i).vServ) {
                System.out.printf("%-10s%-13s%-10s%-10s%-10s%-10s", Vehicle.vehicle
                        .get(i).vId, Vehicle.vehicle.get(i).vName, Vehicle.vehicle.get(i).vNo,
                        Vehicle.vehicle.get(i).vRate,
                        Vehicle.vehicle.get(i).vKms, Vehicle.vehicle.get(i).vCount);
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Select ID to Add to Cart");
        int n = sc.nextInt();
        Cart.cart.add(new Cart(n, Vehicle.vehicle.get(n - 1).vType, Vehicle.vehicle.get(n - 1).vName,
                Vehicle.vehicle.get(n - 1).vNo, Vehicle.vehicle.get(n - 1).vRate, Program.cDate));
        System.out.println();
        System.out.println("Vehicle Added to the cart");
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            view();
        } catch (Exception e) {
        }
    }

    static void cart(){
        System.out.print("\033[H\033[2J");
        if(Cart.cart.size() >= 1){
            for(int i=0;i<Cart.cart.size();i++){//
            System.out.println("Type : " + Cart.cart.get(i).vType + " Name : " + Cart.cart.get(i).vName + " Rate : " + Cart.cart
                    .get(i).vRate);
            System.out.println("Do you need to checkout? y/n");
            String s = sc.next().toLowerCase();
            if(s.equals("y")){
                if(Cart.cart.get(i).vType.equals("Car")){
                    car++;
                }
                else{
                    bike++;
                }
                int amt = 0;
                for(int k=0;k<User.user.size();k++){
                    if(User.user.get(k).uName.equals(cUser)){
                        amt = User.user.get(k).iamt;
                    }
                }
                if((Cart.cart.get(i).vType.equals("Car") && car == 1) && amt >= 13000){
                    Cart.out.add(new Cart(Cart.cart.get(i).vId, Cart.cart.get(i).vType, Cart.cart.get(i).vName, Cart.cart.get(i).vNo, Cart.cart.get(i).vRate, Program.cDate));
                    for(int j =0;j<Vehicle.vehicle.size();j++){
                        if(Cart.cart.get(i).vId == Vehicle.vehicle.get(j).vId){
                            Vehicle.vehicle.get(j).vCount = Vehicle.vehicle.get(j).vCount - 1;
                        }
                    }
                    hist(cUser, "rented", Cart.cart.get(i).vName, Program.cDate);
                    System.out.println("Return the Vehicle before " + Program.cDate);
                    System.out.println("Returning after due will be charged fine");
                    System.out.println();
                }
                else if ((Cart.cart.get(i).vType.equals("Bike") && bike == 1) && amt >= 13000) {
                    Cart.out.add(new Cart(Cart.cart.get(i).vId, Cart.cart.get(i).vType, Cart.cart.get(i).vName, Cart.cart.get(i).vNo, Cart.cart.get(i).vRate, Program.cDate));
                    for (int j = 0; j < Vehicle.vehicle.size(); j++) {
                        if (Cart.cart.get(i).vId == Vehicle.vehicle.get(j).vId) {
                            Vehicle.vehicle.get(j).vCount = Vehicle.vehicle.get(j).vCount - 1;
                        }
                    }
                    hist(cUser, "rented", Cart.cart.get(i).vName, Program.cDate);
                    System.out.println("Return the Vehicle before " + Program.cDate);
                    System.out.println("Returning after due will be charged fine");
                    System.out.println();
                }
                else{
                    System.out.println("Can Rent only 1 Bike & 1 Car at once");
                    break;
                }
            }
            }//
            Cart.cart.clear();
        }
        else{
        System.out.println("No items in the cart");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            function();
        } catch (Exception e) {
        }
    }

    static void signup(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter User Name:");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Mail ID:");
        String mail = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Password:");
        String pass = sc.nextLine();
        User.user.add(new User(name, mail, pass, 30000));
        System.out.println("Renter Added");
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            main();
        } catch (Exception e) {}

    }

    static void main(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. Login");
        System.out.println("2. Signup");
        System.out.println("3. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
                login();
                break;
            case 2:
                signup();
                break;
            case 3:
                Program.main(null);
                break;
            default: 
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try {
                    System.in.read();
                    Program.main(null);
                } catch (Exception e) {
                }
        }

    }
}

class Admin{
    private static Scanner sc = new Scanner(System.in);
    static int vId = 1;

    static void login(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Admin Id :");
        String name = sc.nextLine();
        if(name.equals("harini@gmail.com")){
            System.out.print("\033[H\033[2J");
            System.out.println("Enter Password :");
            String pass = sc.nextLine();
            if(pass.equals("48")){
                main();
            }
            else{
                System.out.println("Invalid Password");
                System.out.println();
                System.out.println("Press ENTER for back");
                try {
                    System.in.read();
                    Program.main(null);
                } catch (Exception e) {
                }
            }
        }
        else{
            System.out.println("Invalid Admin ID");
            System.out.println();
            System.out.println("Press ENTER for back");
            try {
                System.in.read();
                Program.main(null);
            } catch (Exception e) {
            }
        }
    }

    static void main(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. Add Vehicle");
        System.out.println("2. View Vehicle");
        System.out.println("3. Modify Vehicle");
        System.out.println("4. Delete Vehicle");
        System.out.println("5. Search");
        System.out.println("6. Add Borrower");
        System.out.println("7. Reports");
        System.out.println("8. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
                addvehi();
                break;
            case 2:
                viewVehi();
                break;
            case 3:
                modifyvehi();
                break;
            case 4:
                deleteVehi();
                break;
            case 5:
                search();
                break;
            case 6:
                addBorr();
                break;
            case 7:
                reports();
                break; 
            case 8:
                Program.main(null);
                break;
            default:
                System.out.println();
                System.out.println("Press ENTER for back");
                try{
                    System.in.read();
                    Program.main(null);
                }
                catch(Exception e) {}
        }
    }

    static void reports(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. Service Needed");
        System.out.println("2. View All Vehicles");
        System.out.println("3. Vehicle Status");
        System.out.println("4. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
                service();
                break;
            case 2:
                viewAll();
                break;
            case 3:
                status();
                break;
            case 4:
                main();
                break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try{
                    System.in.read();
                    main();
                }
                catch(Exception e) {}
        }
    }

    
    static void viewAll() {
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-10s%-13s%-10s%-10s%-10s%-15s", "ID", "Type", "Name", "No.", "Rate", "Kms",
                "Availability");
        System.out.println();
        System.out.println();
        for (int i = 0; i < Vehicle.vehicle.size(); i++) {
                System.out.printf("%-10s%-10s%-13s%-10s%-10s%-10s%-15s", Vehicle.vehicle
                        .get(i).vId, Vehicle.vehicle.get(i).vType, Vehicle.vehicle.get(i).vName,
                        Vehicle.vehicle.get(i).vNo,
                        Vehicle.vehicle.get(i).vRate,
                        Vehicle.vehicle.get(i).vKms, Vehicle.vehicle.get(i).vCount);
                System.out.println();
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            reports();
        } catch (Exception e) {
        }
    }

    static void service(){
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-10s%-13s%-10s%-10s%-10s%-15s", "ID", "Type", "Name", "No.", "Rate", "Kms", "Availability");
        System.out.println();
        System.out.println();
        for (int i = 0; i < Vehicle.vehicle.size(); i++) {
            if (!Vehicle.vehicle.get(i).vServ) {
                System.out.printf("%-10s%-10s%-13s%-10s%-10s%-10s%-15s", Vehicle.vehicle
                        .get(i).vId, Vehicle.vehicle.get(i).vType , Vehicle.vehicle.get(i).vName, Vehicle.vehicle.get(i).vNo,
                        Vehicle.vehicle.get(i).vRate,
                        Vehicle.vehicle.get(i).vKms, Vehicle.vehicle.get(i).vCount);
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Provide Service ? y/n");
        String s = sc.next().toLowerCase();
        if(s.equals("y")){
        for(int i=0;i<Vehicle.vehicle.size();i++){
            if(!Vehicle.vehicle.get(i).vServ){
                Vehicle.vehicle.get(i).vKms = 0;
                Vehicle.vehicle.get(i).vServ = true;
            }
        }
        System.out.println("Service Done");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            reports();
        } catch (Exception e) {
        }
    }

    static void status(){
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-10s%-15s%-10s%-10s%-10s","ID","Type","Name","No.","Rate","Due");
        System.out.println();
        for(int i=0;i<Cart.out.size();i++){
            System.out.printf("%-10s%-10s%-15s%-10s%-10s%-10s", Cart.out.get(i).vId, 
                    Cart.out.get(i).vType, Cart.out.get(i).vName, Cart.out.get(i).vNo, Cart.out.get(i).vRate, 
                    Cart.out.get(i).date);
                    System.out.println();
        }
    }

    static void addBorr(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter User Name:");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Mail ID:");
        String mail = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Password:");
        String pass = sc.nextLine();
        User.user.add(new User(name, mail, pass, 30000));
        System.out.println("Renter Added");
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            main();
        } catch (Exception e) {
        }
    }

    static void search(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Vehicle Name :");
        sc.nextLine();
        String name = sc.nextLine();
        boolean r = false;
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-10s%-10s%-10s%-15s%-10s", "ID", "No.", "Rate", "Kms",
                "Availability",
                "Service");
        System.out.println();
        for(int i=0;i<Vehicle.vehicle.size();i++){
            if(Vehicle.vehicle.get(i).vName.equals(name)){
                r = true;
                System.out.printf("%-10s%-10s%-10s%-10s%-15s%-10s", Vehicle.vehicle
                        .get(i).vId, Vehicle.vehicle.get(i).vNo,
                        Vehicle.vehicle.get(i).vRate,
                        Vehicle.vehicle.get(i).vKms, Vehicle.vehicle.get(i).vCount, Vehicle.vehicle.get(i).vServ);
                        System.out.println();
            }
        }
        if (!r) {
            System.out.println("Vehicle not found");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            main();
        } catch (Exception e) {
        }
    }

    static void modifyvehi(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Vehicle Name :");
        sc.nextLine();
        String name = sc.nextLine();
        boolean r = false;
        for(int i=0;i<Vehicle.vehicle.size();i++){
            if(Vehicle.vehicle.get(i).vName.equals(name)){
                r = true;
                System.out.print("\033[H\033[2J");
                System.out.println("Enter Rate :");
                int rate = sc.nextInt();
                Vehicle.vehicle.get(i).vRate = rate;
                System.out.println();
                System.out.println("Enter Vehicle Count :");
                int count = sc.nextInt();
                Vehicle.vehicle.get(i).vCount = count;
                System.out.println();
                System.out.println("Vehicle Modified");
            }
        }
        if(!r){
            System.out.println("Vehicle not found");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            main();
        } catch (Exception e) {
        }
    }

    static void viewVehi(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. Bikes");
        System.out.println("2. Cars");
        System.out.println("3. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
                bikes();
                break;
            case 2:
                cars();
                break;
            case 3:
                main();
                break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try {
                    System.in.read();
                    Program.main(null);
                } catch (Exception e) {
                }
        }
    }


    static void addvehi(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Vehicle Type : Bike/Car");
        sc.nextLine();
        String type = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Vehicle Name : ");
        String name = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Vehicle No.");
        int num = sc.nextInt();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Vehicle Rate :");
        int rate = sc.nextInt();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Vehicle Count :");
        int count = sc.nextInt();

        Vehicle.vehicle.add(new Vehicle(Admin.vId, type, name, num, rate, 0, count, true));
        Admin.vId++;

        System.out.println("Vehicle Added Successful");

        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            main();
        } catch (Exception e) {
        }
    }

    static void deleteVehi(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Vehicle Name : ");
        sc.nextLine();
        String name = sc.nextLine();
        boolean r = false;
        for(int i=0;i<Vehicle.vehicle.size();i++){
            if(Vehicle.vehicle.get(i).vName.equals(name)){
                r = true;
                Vehicle.vehicle.remove(i);
                System.out.println("Vehicle Successfully Removed");
            }
        }
        if(!r){
            System.out.println("Vehicle Not found");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            main();
        } catch (Exception e) {
        }
    }

    static void cars() {
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-13s%-10s%-10s%-10s%-15s%-10s", "ID", "Name", "No.", "Rate", "Kms", "Availability",
                "Service");
        System.out.println();
        System.out.println();
        for (int i = 0; i < Vehicle.vehicle.size(); i++) {
            if (Vehicle.vehicle.get(i).vType.equals("Car")) {
                System.out.printf("%-10s%-13s%-10s%-10s%-10s%-15s%-10s", Vehicle.vehicle
                        .get(i).vId, Vehicle.vehicle.get(i).vName, Vehicle.vehicle.get(i).vNo,
                        Vehicle.vehicle.get(i).vRate,
                        Vehicle.vehicle.get(i).vKms, Vehicle.vehicle.get(i).vCount, Vehicle.vehicle.get(i).vServ);
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            viewVehi();
        } catch (Exception e) {
        }
    }

    static void bikes() {
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-11s%-10s%-10s%-10s%-15s%-10s","ID","Name","No.","Rate","Kms","Availability","Service");
        System.out.println();
        System.out.println();
        for(int i=0;i<Vehicle.vehicle.size();i++){
            if(Vehicle.vehicle.get(i).vType.equals("Bike")){
                System.out.printf("%-10s%-11s%-10s%-10s%-10s%-15s%-10s", Vehicle.vehicle
                        .get(i).vId, Vehicle.vehicle.get(i).vName, Vehicle.vehicle.get(i).vNo, 
                        Vehicle.vehicle.get(i).vRate, 
                        Vehicle.vehicle.get(i).vKms, Vehicle.vehicle.get(i).vCount, Vehicle.vehicle.get(i).vServ);
                        System.out.println();
            }
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            viewVehi();
        } catch (Exception e) {
        }
    }
}

class Vehicle{
    public int vId;
    public String vName;
    public int vNo;
    public int vRate;
    public String vType;
    public int vKms;
    public int vCount;
    public boolean vServ;
    Vehicle(int vId, String vType, String vName, int vNo, int vRate, int vKms, int vCount, boolean vServ){
        this.vId = vId;
        this.vType = vType;
        this.vName = vName;
        this.vNo = vNo;
        this.vCount = vCount;
        this.vRate = vRate;
        this.vKms = vKms;
        this.vServ = vServ;
    }

    static List<Vehicle> vehicle = new ArrayList<>();
}

class Cart{
    public int vId;
    public String vName;
    public int vNo;
    public int vRate;
    public String vType;
    public LocalDate date;
    Cart(int vId, String vType, String vName, int vNo, int vRate, LocalDate date){
        this.vId = vId;
        this.vType = vType;
        this.vName = vName;
        this.vNo = vNo;
        this.vRate = vRate;
        this.date = date;
    }

    static List<Cart> cart = new ArrayList<>();
    static List<Cart> out = new ArrayList<>();
}

class User{
    public String uName;
    public String uMail;
    public String uPass;
    public int iamt;
    User(String uName,String uMail,String uPass, int iamt){
        this.uName = uName;
        this.uMail = uMail;
        this.uPass = uPass;
        this.iamt = iamt;
    }
    static List<User> user = new ArrayList<>();
}