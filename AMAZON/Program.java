import java.util.*;

public class Program{
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        Admin.products.add(Admin.l1);
        Admin.products.add(Admin.l2);
        Admin.products.add(Admin.l3);
        Admin.products.add(Admin.l4);
        Admin.products.add(Admin.l5);
        Admin.products.add(Admin.l6);
        Admin.products.add(Admin.l7);
        Admin.products.add(Admin.l8);
        Admin.products.add(Admin.l9);

        System.out.print("\033[H\033[2J");
        System.out.println("1. Admin");
        System.out.println("2. Merchant");
        System.out.println("3. User");
        System.out.println("4. Exit");
        int op = sc.nextInt();
        switch(op){
            case 1:
            Admin.login();
            break;
            case 2:
            Merchant.home();
            break;
            case 3:
            User.home();
            break;
            case 4:
            System.exit(0);
            break;
            default:
            System.out.println("Invalid Input");
        }
    }
}

class Admin{
    static private Scanner sc = new Scanner(System.in);
    static int total = 0;
    static int off = 0;
    static ArrayList<String> l1 = new ArrayList<>(Arrays.asList("a", "Watch", "Apple", "10", "25000", "8"));
    static ArrayList<String> l2 = new ArrayList<>(Arrays.asList("b", "Watch", "Titan", "9", "18000", "5"));
    static ArrayList<String> l3 = new ArrayList<>(Arrays.asList("c", "Watch", "Fossil", "4", "12000", "8"));
    static ArrayList<String> l4 = new ArrayList<>(Arrays.asList("a", "Phone", "Redmi", "7", "20000", "7"));
    static ArrayList<String> l5 = new ArrayList<>(Arrays.asList("b", "Phone", "GooglePixels", "10", "65000", "8"));
    static ArrayList<String> l6 = new ArrayList<>(Arrays.asList("c", "Phone", "OnePlus", "10", "50000", "8"));
    static ArrayList<String> l7 = new ArrayList<>(Arrays.asList("a", "Bag", "Wildcraft", "10", "25000", "8"));
    static ArrayList<String> l8 = new ArrayList<>(Arrays.asList("b", "Bag", "Nike", "10", "25000", "8"));
    static ArrayList<String> l9 = new ArrayList<>(Arrays.asList("c", "Bag", "Skybags", "10", "25000", "8"));
    static ArrayList<ArrayList<String>> cart = new ArrayList<>();
    static ArrayList<ArrayList<String>> products = new ArrayList<>();
    static ArrayList<String> userName = new ArrayList<>(Arrays.asList("Harini","Razia"));
    static ArrayList<String> userPass = new ArrayList<>(Arrays.asList("123", "456"));
    static ArrayList<String> merName = new ArrayList<>(Arrays.asList("a", "b","c"));
    static ArrayList<String> merPass = new ArrayList<>(Arrays.asList("12", "34", "56"));
    static ArrayList<String> app = new ArrayList<>();
    static ArrayList<String> appPass = new ArrayList<>();
    static ArrayList<String> mini = new ArrayList<>();

    static void login(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Admin ID: ");
        String nm = sc.next();
        if(nm.equals("Harini")){
            System.out.print("\033[H\033[2J");
            System.out.println("Enter Admin Password: ");
            String ps = sc.next();
            if(ps.equals("48")){
                adFct();
            }
            else{
                System.out.println("Invalid AdminId");
                System.out.println();
                System.out.println("Press Enter for Back");
                try {
                    System.in.read();
                    Program.main(null);
                } catch (Exception e) {
                }
            }
        }
        else {
            System.out.println("Invalid AdminId");
            System.out.println();
            System.out.println("Press Enter for Back");
            try {
                System.in.read();
                Program.main(null);
            } catch (Exception e) {
            }
        }
    }

    static void adFct(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. Add merchant");
        System.out.println("2. Remove merchant");
        System.out.println("3. Approve Merchant");
        System.out.println("4. View Products");
        System.out.println("5. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
            addMer();
            break;
            case 2:
            remMer();
            break;
            case 3:
            approve();
            break;
            case 4:
            viewProd();
            break;
            case 5:
            Program.main(null);
            break;
            default:
            System.out.println("Invalid input");
        }
    }

    static void addMer(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Merchant Name: ");
        sc.nextLine();
        String mname = sc.nextLine();
        app.add(mname);
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Password");
        String mpass = sc.next();
        appPass.add(mpass);
        System.out.println();
        Merchant.count++;
        System.out.println("Your request sent for approval");
        System.out.println();
        System.out.println("Press Enter for Back");
        try {
            System.in.read();
            adFct();
        } catch (Exception e) {}
    }

    static void remMer(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter merchantName:");
        sc.nextLine();
        String str = sc.next();
        int i = merName.indexOf(str);
        if(i>=0){
            merName.remove(i);
            merPass.remove(i);
            System.out.println("Removed successful");
        }
        else{
            System.out.println("Not Found");
        }
        System.out.println();
        System.out.println("Press Enter for Back");
        try {
            System.in.read();
            adFct();
        } catch (Exception e) {
        }
    }

    static void approve(){
        System.out.print("\033[H\033[2J");
        if(app.size()>0){
        for(int i=Merchant.count-1;i<app.size() && i>=0;i--){
            System.out.println(app.get(i));
            System.out.println("Do you want to approve? y/n");
            String s = sc.next().toLowerCase();
            if(s.equals("y")){
                merName.add(app.get(i));
                merPass.add(appPass.get(i));
                app.remove(i);
                appPass.remove(i);
                System.out.println("Approved successful");
                Merchant.count--;
            }
            else{
                app.remove(i);
                appPass.remove(i);
                System.out.println("Declined");
            }
        }
        }
        else{
            System.out.println("No new Requests");
        }

        System.out.println();
        System.out.println("Press Enter for Back");
        try {
            System.in.read();
            adFct();
        } catch (Exception e) {
        }
    }


    static void viewProd(){
        System.out.print("\033[H\033[2J");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s","S.No","M Name","Product","Brand","Quantity","Price","Rating/10");
        for(int i=0;i<products.size();i++){
            System.out.println();
            System.out.printf("%-15s",i+1);
            for(int j=0;j<products.get(i).size();j++){
                System.out.printf("%-15s", products.get(i).get(j));
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Press Enter for Back");
        try {
            System.in.read();
            adFct();
        } catch (Exception e) {}
    }
}

class Merchant{
    static int count = 0;
        private static Scanner sc = new Scanner(System.in);
        static void home(){
            System.out.print("\033[H\033[2J");
            System.out.println("1. Login");
            System.out.println("2. SignUp");
            System.out.println("3. Back");
            int op = sc.nextInt();
            switch (op){
                case 1:
                Login();
                break;
                case 2:
                newMerch();
                break;
                case 3:
                Program.main(null);
                break;
                default:
                    System.out.println("Invalid input");
                    System.out.println();
                    System.out.println("press ENTER for back");
                    try {
                        System.in.read();
                        Program.main(null);
                    } catch (Exception e) {
                    }
            }
           
        }

        static void Login(){
            System.out.print("\033[H\033[2J");
            System.out.println("Enter merchant name:");
            sc.nextLine();
            String mname = sc.next();
            int i = Admin.merName.indexOf(mname);
            if(i>=0){
                System.out.println("Enter Password: ");
                String mpass = sc.next();
                if(Admin.merPass.get(i).equals(mpass)){
                    merFct(mname);
                }
                else{
                    System.out.println("Invalid Password");
                    System.out.println();
                    System.out.println("press ENTER for back");
                    try {
                        System.in.read();
                        home();
                    } catch (Exception e) {
                    }
                }
            }
            else{
                System.out.println("Invalid Merchant Name");
                System.out.println();
                System.out.println("press ENTER for back");
                try {
                    System.in.read();
                    home();
                } catch (Exception e) {
                }
            } 
            
        }

        static void newMerch(){
            System.out.print("\033[H\033[2J");
            System.out.println("Enter merchant name:");
            sc.nextLine();
            String mname = sc.nextLine();
            Admin.app.add(mname);
            System.out.println("Enter Password: ");
            String mpass = sc.next();
            Admin.appPass.add(mpass);
            count++;
            System.out.println("Request sent for approval");
            System.out.println();
            System.out.println("press ENTER for back");
            try {
                System.in.read();
                home();
            } catch (Exception e) {
            }
        }

        static void merFct(String m){
            System.out.print("\033[H\033[2J");
            System.out.println("1. View Products");
            System.out.println("2. Add Products");
            System.out.println("3. Remove Product");
            System.out.println("4. Back");
            int op = sc.nextInt();
            switch(op){
                case 1:
                viewProd(m);
                break;
                case 2:
                addProd(m);
                break;
                case 3:
                remProd(m);
                break;
                case 4:
                Program.main(null);
                break;
                default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try{
                    System.in.read();
                Program.main(null);
                }
                catch(Exception e) {}
            } 
        }

        static void viewProd(String m){
            System.out.println("\033[H\033[2J");
            System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s", "S.No", "M Name", "Product", "Brand", "Quantity",
                    "Price", "Rating/10");
            System.out.println();
            int k = 1;
            for (int i = 0; i < Admin.products.size(); i++) {
                if(Admin.products.get(i).get(0).equals(m)){
                    System.out.println();
                System.out.printf("%-15s", k); k++;
                for (int j = 0; j < Admin.products.get(i).size(); j++) {
                    System.out.printf("%-15s", Admin.products.get(i).get(j));
                }
                }
            }
            System.out.println();
            System.out.println();
            System.out.println("Press Enter for Back");
            try {
                System.in.read();
                merFct(m);
            } catch (Exception e) {
            }
        }

        static void addProd(String m){
            System.out.println("\033[H\033[2J");
            ArrayList<String> str = new ArrayList<>();
            str.add(m);
            System.out.println("Enter ProductName: ");
            sc.nextLine();
            String p = sc.nextLine();
            str.add(p);
            System.out.println();
            System.out.println("Enter BrandName: ");
            String b = sc.nextLine();
            str.add(b);
            System.out.println();
            System.out.println("Enter Quantity: ");
            String q = sc.nextLine();
            str.add(q);
            System.out.println();
            System.out.println("Enter Rate: ");
            String r = sc.nextLine();
            str.add(r);
            System.out.println();
            System.out.println("Enter rating/10: ");
            String rt = sc.nextLine();
            str.add(rt);
            for(int i=0;i<6;i++){
                System.out.print(str.get(i)+" ");
            }
            System.out.println();
            Admin.products.add(str);
            System.out.println("\033[H\033[2J");
            System.out.println("Product Added Successful");
            System.out.println();
            System.out.println("Press ENTER for back");
            try{
                System.in.read();
                merFct(m);
            }
            catch(Exception e){}
        }

        static void remProd(String m){
            System.out.print("\033[H\033[2J");
            System.out.println("Enter ProductName: ");
            sc.nextLine();
            String str = sc.nextLine();
            System.out.println("Enter BrandName:");
            String sr = sc.nextLine();
            for (int i = 0; i < Admin.products.size(); i++) {
                if (Admin.products.get(i).get(0).equals(m) && Admin.products.get(i).get(1).equals(str) && Admin.products
                        .get(i).get(2).equals(sr)) {
                    Admin.products.remove(i);
                }
            }
            System.out.println();
            System.out.println("Product Removed");
            System.out.println();
            System.out.println("Press ENTER for back");
            try{
                System.in.read();
                merFct(m);
            }
            catch (Exception e) {
            }
        }
}

class User{
    static private Scanner sc = new Scanner(System.in);
    static void home(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. Login");
        System.out.println("2. SignUp");
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

    static void login(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter UserName: ");
        sc.nextLine();
        String uname = sc.nextLine();
        int i = Admin.userName.indexOf(uname);
            if(i>=0){
                System.out.print("\033[H\033[2J");
                System.out.println("Enter Password: ");
                String upass = sc.next();
                if(Admin.userPass.get(i).equals(upass)){
                    urFct(uname);
                }
                else{
                    System.out.println("Invalid Password");
                    System.out.println();
                    System.out.println("press ENTER for back");
                    try {
                        System.in.read();
                        home();
                    } catch (Exception e) {
                    }
                }
            }
            else{
                System.out.println("Invalid User Name");
                System.out.println();
                System.out.println("press ENTER for back");
                try {
                    System.in.read();
                    home();
                } 
                catch (Exception e) {}
            }
    }

    static void signup(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter UserName:");
        sc.nextLine();
        String uname = sc.nextLine();
        Admin.userName.add(uname);
        System.out.println();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter password");
        String upass = sc.nextLine();
        Admin.userPass.add(upass);
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            home();
        } catch (Exception e) {
        }
    }

    static void urFct(String u){
        System.out.print("\033[H\033[2J");
        System.out.println("--------------WELCOME---------------");
        System.out.println("1. View Products");
        System.out.println("2. Offers");
        System.out.println("3. Orders history");
        System.out.println("4. Cart");
        System.out.println("5. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
            viewProd(u);
            break;
            case 2:
            offerUpd(u);
            break;
            case 3:
            history(u);
            break;
            case 4:
            cart(u);
            break;
            case 5:
            home();
            break;
            default:
            System.out.println("Invalid input");
            System.out.println();
            System.out.println("Press ENTER for back");
            try{
                System.in.read();
                home();
            }
            catch (Exception e){}
        }
    }


    static void offerUpd(String u){
        System.out.print("\033[H\033[2J");
        System.out.println("-----SEASON OFFER-----");
        System.out.println("PURCHASE MORE THAN Rs.10000");
        System.out.println("and get 20% OFF");
        System.out.println("HURRY UP!!!!");
        System.out.println("ends in 24h 12m");
        System.out.println();
        System.out.println("Press ENTER to continue");
        try {
            System.in.read();
            urFct(u);
        } catch (Exception e) {
        }
    }

    static void history2(String p,String b,String u){
        Admin.mini.add(u + ", You have ordered "+b +" " + p);
    }

    static void history(String u){
        
        System.out.print("\033[H\033[2J");

        if(Admin.mini.size()>0){
        for(int i=0;i<Admin.mini.size();i++){
            System.out.println(Admin.mini.get(i));
        }
        }
        else{
            System.out.println("No orders placed yet!");
            System.out.println();
            System.out.println("Press ENTER to continue");
            try {
                System.in.read();
                urFct(u);
            } catch (Exception e) {
            }
        }
    }

    static void viewProd(String u){
        System.out.print("\033[H\033[2J");
        System.out.println("------Products------");
        System.out.println("1. Watch");
        System.out.println("2. Phone");
        System.out.println("3. Bag");
        System.out.println("4. Cart");
        System.out.println("5. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
            display("Watch",u);
            break;
            case 2:
            display("Phone",u);
            break;
            case 3:
            display("Bag",u);
            break;
            case 4:
            cart(u);
            case 5:
            urFct(u);
            break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try{
                    System.in.read();
                    urFct(u);
                }
                catch(Exception e) {}
        }
    }

    static void offers(String u){
        int a = Integer.parseInt(u);
        double d = 0;
        if(a>10000){
            d = 0.2 * a;
        }
        Admin.off = (int) d;
        
    }

    static void display(String p,String u){
        System.out.print("\033[H\033[2J");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s", "S.No", "M Name", "Product", "Brand", "Quantity",
                "Price", "Rating/10");
        System.out.println();
        int k = 1;
        ArrayList<String> str = new ArrayList<>();
        for (int i = 0; i < Admin.products.size(); i++) {
            if (Admin.products.get(i).get(1).equals(p)) {
                System.out.println();
                System.out.printf("%-15s", k);
                k++;
                for (int j = 0; j < Admin.products.get(i).size(); j++) {
                    System.out.printf("%-15s", Admin.products.get(i).get(j));
                }
                str.add(Admin.products.get(i).get(4));
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Select to Add to Cart");
        int n = sc.nextInt();
        String a = str.get(n-1);
        for(int i=0;i<Admin.products.size();i++){
            if(Admin.products.get(i).get(1).equals(p) && Admin.products.get(i).get(4).equals(a)){
                Admin.cart.add(Admin.products.get(i));
                System.out.println();
                System.out.print("\033[H\033[2J");
                System.out.println("Product added to the cart");
            }
        }
        System.out.println("Press Enter for Back");
        try {
            System.in.read();
            urFct(u);
        } catch (Exception e) {
        }
    }


    static void cart(String u){
        System.out.print("\033[H\033[2J");
        if(Admin.cart.size()>0){
            for(int i=Admin.cart.size()-1;i>=0;i--){
                    System.out.println("Product: "+ Admin.cart.get(i).get(1)+" Brand: " +Admin.cart.get(i).get(2)+" - Rs." + Admin.cart.get(i).get(4) );
                    System.out.println("Proceed for checkout? y/n");
                    String s = sc.next().toLowerCase();
                    if(s.equals("y")){
                        int a = Integer.parseInt(Admin.cart.get(i).get(4));
                        history2(Admin.cart.get(i).get(1), Admin.cart.get(i).get(2),u);
                        Admin.total += a;
                        Admin.cart.remove(i);
                    }
                    else{
                        System.out.println("Thanks");
                    }
            }
            int tt = Admin.total;
            System.out.print("\033[H\033[2J");
            System.out.println("Total: Rs." + tt);
            String o = Integer.toString(tt);
            offers(o);
            System.out.println("Offer: -" + Admin.off);
            tt = tt - Admin.off;
            System.out.println("GST & Delivery charges : +250");
            tt = tt + 250;
            System.out.println("TOTAL => Rs." + tt);
            System.out.println();
            System.out.println("Proceed for checkOut? y/n");
            String s = sc.next();
            if (s.equals("y")) {
                System.out.println("----Order Placed---- \n-Thanks for purchasing!-");
            } else {
                System.out.println("Thanks");
            }
            System.out.println();
            System.out.println("Press ENTER for back");
            try {
                System.in.read();
                urFct(u);
            } catch (Exception e) {
            }
        }
        else{
            System.out.println("No items in the cart");
            System.out.println();
            System.out.println("Press ENTER for back");
            try {
                System.in.read();
                urFct(u);
            } catch (Exception e) {
            }
        }
        
    }

}