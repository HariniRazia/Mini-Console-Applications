import java.util.*;
class Program {
    private static Scanner sc = new Scanner(System.in);
    static int amt, total, count = 0, transCount = 0, account;
    static int amtType[] = { 2000, 1000, 500, 200, 100 };
    static int amtNo[] = { 0, 0, 0, 0, 0 };
    static ArrayList<String> arr = new ArrayList<>();
    static String uname[] = { "Harini", "Razia", "Shravani" };
    static String upass[] = { "1234", "567", "1001" };
    static String ubank[] = { "State", "Indian", "KVB" };
    static int uamt[] = { 50000, 30000, 40000 };

    public static void main(String[] args) {
        mainFunct();
    }

    static void mainFunct() { 
        System.out.print("\033[H\033[2J");
        System.out.println("----ATM MACHINE----");
        System.out.println("1. User Login");
        System.out.println("2. Admin Login");
        System.out.println("3. Quit");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                userLogin();
                break;
            case 2:
                adminLogin();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.println("Invalid Input");
        }
    }

    static void adminLogin(){ 
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Admin ID: ");
        sc.nextLine();
        String name = sc.nextLine();
        if(name.equals("Harini")){
            System.out.print("\033[H\033[2J");
            System.out.println("Enter Password: ");
            String passw = sc.next();
            if(passw.equals("48")){
                adminFunct();
            } 
            else{
                System.out.println("Password incorrect");
                System.out.println();
                System.out.println("Press Enter to go back");
                try{
                System.in.read();
                mainFunct();
                }
                catch(Exception e) {}
            }
        }
        else{
            System.out.print("\033[H\033[2J");
            System.out.println("Admin ID not found!");
        }
    }

    static void userLogin() { 
        System.out.print("\033[H\033[2J");
        System.out.println("Enter UserName: ");
        sc.nextLine();
        int l = 0;
        String name = sc.nextLine();
        for (int i = 0; i < uname.length; i++) {
            if (name.equals(uname[i])) {
                l = i;
                System.out.print("\033[H\033[2J");
            break;
            } else {
                System.out.print("\033[H\033[2J");
                System.out.println("Invalid UserName");
                System.out.println();
                System.out.println("Press ENTER for back");
                try{
                    System.in.read();
                    mainFunct();
                }
                catch(Exception e) {}
            }
        }
        if (name.equals(uname[l])) {
            System.out.print("\033[H\033[2J");
            System.out.println("Enter Password: ");
            String pw = sc.next();
            if (pw.equals(upass[l])) {
                userFunct(l);
            } else {
                System.out.print("\033[H\033[2J");
                System.out.println("Invalid Password");
                System.out.println();
                System.out.println("Press ENTER for back");
                try {
                    System.in.read();
                    mainFunct();
                } catch (Exception e) {
                }
            }
        }
                
            
    }

    static void adminFunct() { 
        System.out.print("\033[H\033[2J");
        System.out.println("1. Add amount");
        System.out.println("2. Show amount");
        System.out.println("3. Back");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                addAmt();
                break;
            case 2:
                showAmt();
                break;
            case 3:
                    mainFunct();
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.println("Invalid Input");
        }
    }

    static void addAmt() { 
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < amtType.length; i++) {
            System.out.print("\033[H\033[2J");
            System.out.println("Number of " + amtType[i] + " is: ");
            int ch = sc.nextInt();
            amtNo[i] += ch;
        }
        total = 0;
        for (int i = 0; i < amtType.length; i++) {
            total += (amtNo[i] * amtType[i]);
        }
        System.out.print("\033[H\033[2J");
        System.out.println("Amount added Successfully");
        System.out.println();
        System.out.println("Press Enter to go back");
        try {
            System.in.read();
            adminFunct();
        } catch (Exception e) {
        }
    }

    static void showAmt() { 
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < amtNo.length; i++) {
            System.out.println("Number of Rs." + amtType[i] + " is " + amtNo[i]);
        }
        System.out.println();
        System.out.println("Total Amount : Rs." + total);
        System.out.println();
        System.out.println("Press Enter to go back");
        try {
            System.in.read();
            adminFunct();
        } catch (Exception e) {
        }
    }

    static void userFunct(int i) { 
        System.out.print("\033[H\033[2J");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Amount");
        System.out.println("3. Withdraw Amount");
        System.out.println("4. Mini Statement");
        System.out.println("5. Transfer Money");
        System.out.println("6. Pin Change");
        System.out.println("7. Back");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                balCheck(i);
                break;
            case 2:
                depAmt(i);
                break;
            case 3:
                withDraw(i);
                break;
            case 4:
                miniState(i);
                break;
            case 5:
                moneyTrans(i);
                break;
            case 6:
                pinChg(i);
                break;
            case 7:
                mainFunct();
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.println("Invalid Input");
        }
    }

    static void balCheck(int i) { 
        System.out.print("\033[H\033[2J");
        System.out.println("Your account balance: Rs." + uamt[i]);
        System.out.println();
        System.out.println("Press Enter to go back");
        try {
            System.in.read();
            userFunct(i);
        } catch (Exception e) {
        }
    }

    static void depAmt(int i) {// t 
        System.out.print("\033[H\033[2J");
        System.out.println("Enter amount: ");
        int n = 0;
        int t = 0;
        for (int k = 0; k < amtType.length; k++) {
            System.out.print("\033[H\033[2J");
            System.out.println("Number of " + amtType[k] + " is: ");
            n = sc.nextInt();
            amtNo[k] += n;
        t += n * amtType[k];
        }
        uamt[i] += t;
        total += t;
        transCount++;
        trans(String.valueOf(t), "Deposited");
        System.out.print("\033[H\033[2J");
        System.out.println("Amount Deposited successfully");
        System.out.println();
        System.out.println("Press Enter to go back");
        try {
            System.in.read();
            userFunct(i);
        } catch (Exception e) {
        }
    }

    static void withDraw(int i) {// t 
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Amount: ");
        int a = sc.nextInt();
        if (a < uamt[i]) {
            if (a < total) {
                if (uamt[i] > a + 100) {
                    if (transCount < 1 || a % 100 == 0) {
                        uamt[i] -= a;
                        with(a);
                        transCount++;
                        trans(String.valueOf(a), "Withdrawn");
                        System.out.print("\033[H\033[2J");
                        System.out.println("Amount withDrawn successfully");
                        System.out.println();
                        System.out.println("Press Enter to go back");
                        try {
                            System.in.read();
                            userFunct(i);
                        } catch (Exception e) {
                        }
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.println("Please Enter a amount rounding to nearest 100");
                        System.out.println();
                        System.out.println("Press Enter to go back");
                        try {
                            System.in.read();
                            userFunct(i);
                        } catch (Exception e) {
                        }
                    }
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.println("There should be minimum balance in the account");
                    System.out.println();
                    System.out.println("Press Enter to go back");
                    try {
                        System.in.read();
                        userFunct(i);
                    } catch (Exception e) {
                    }
                }
            } else {
                System.out.print("\033[H\033[2J");
                System.out.println("No amount in the dispensary\nPlease Try again later!");
                System.out.println();
                System.out.println("Press Enter to go back");
                try {
                    System.in.read();
                    userFunct(i);
                } catch (Exception e) {
                }
            }
        } else {
            System.out.print("\033[H\033[2J");
            System.out.println("Insufficient Balance " + uamt[i]);
            System.out.println();
            System.out.println("Press Enter to go back");
            try {
                System.in.read();
                userFunct(i);
            } catch (Exception e) {
            }
        }
    }

    static void miniState(int i) {
        System.out.print("\033[H\033[2J");
        if (transCount >= 6) {
            int n = arr.size();
            // Recent transactions first
            for (int j = n - 1; j >= 0; --j) {
                System.out.println(arr.get(j));
            }
        } else {
            System.out.println("Minimum 6 Transactions required");
        }
        System.out.println();
        System.out.println("Press Enter to go back");
        try {
            System.in.read();
            userFunct(i);
        } catch (Exception e) {
        }
    }

    static void moneyTrans(int i) {// t
        System.out.print("\033[H\033[2J");
        System.out.println("Enter the toUser Name: ");
        String s = sc.next().trim();
        if (Arrays.asList(uname).contains(s)) {
            System.out.print("\033[H\033[2J");
            System.out.println("Enter amount: ");
            int a = sc.nextInt();
            if (a + 100 < uamt[i]) {
                for (int k = 0; k < uname.length; k++) {
                    if (s.equals(uname[k])) {
                        uamt[i] -= a;
                        uamt[k] += a;
                        break;
                    }
                }
                with(a);
                trans(String.valueOf(a), "Transferred");
                transCount++;
                System.out.print("\033[H\033[2J");
                System.out.println("Money successfully transferred");
                System.out.println();
                System.out.println("Press Enter to go back");
                try {
                    System.in.read();
                    userFunct(i);
                } catch (Exception e) {
                }
            } else {
                System.out.print("\033[H\033[2J");
                System.out.println("Insufficient balance in your account " + uamt[i]);
                System.out.println();
                System.out.println("Press Enter to go back");
                try {
                    System.in.read();
                    userFunct(i);
                } catch (Exception e) {
                }
            }
        } else {
            System.out.print("\033[H\033[2J");
            System.out.println("No such users found!");
            System.out.println();
            System.out.println("Press Enter to go back");
            try {
                System.in.read();
                userFunct(i);
            } catch (Exception e) {
            }
        }
    }

    static void pinChg(int i) { 
        System.out.print("\033[H\033[2J");
        System.out.println("Enter current Password: ");
        String s = sc.next().trim();
        if (s.equals(upass[i])) {
            System.out.print("\033[H\033[2J");
            System.out.println("Enter new Password: ");
            String np = sc.next();
            upass[i] = np;
            System.out.print("\033[H\033[2J");
            System.out.println("Pin changed successfully");
            System.out.println();
            System.out.println("Press Enter to go back");
            try {
                System.in.read();
                userFunct(i);
            } catch (Exception e) {
            }
        } else {
            System.out.print("\033[H\033[2J");
            System.out.println("Password Incorrect");
            System.out.println();
            System.out.println("Press Enter to go back");
            try {
                System.in.read();
                userFunct(i);
            } catch (Exception e) {
            }
        }
    }

    static void trans(String a, String b) {
        arr.add("The amount of Rs." + a + " has been " + b);
    }

    static void with(int i) { 
        int a;
        for (int k = 0; k < amtNo.length; k++) { 
            if (i >= amtType[k]) { 
                a = i / amtType[k];
                amtNo[k] -= a;
                i = i % amtType[k];
            }
        }
    }

}