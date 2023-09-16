import java.util.Scanner;

public class Player {
    private Inventory inventory;
    private String name;
    private int damage;
    private int health;
    private int maxhealth;
    private int money;
    private String charName;

    Map map = new Map(this);

    Scanner scanner = new Scanner(System.in);

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();

    }

    public int getMaxhealth() {
        return maxhealth;
    }

    public void setMaxhealth(int maxhealth) {
        this.maxhealth = maxhealth;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void selectChar() {

        Samurai samurai = new Samurai();
        Archer archer = new Archer();
        Knight knight = new Knight();

        GameChar[] charlist = {samurai, archer, knight};




        System.out.println("Karakter | ID | Hasar | Sağlık | Para");
        System.out.println("-------------------------------------");
        for (GameChar character: charlist) {
            System.out.println(character.getName() + "  | " + character.getId() + "  | " + character.getDamage() + "     | " + character.getHealth() + "     | " + character.getMoney());
        }
        System.out.println("-------------------------------------\n");

        System.out.print("Lütfen oynamak istediğiniz karakteri (ID) seçiniz: ");
        String charid = scanner.next();

        for (GameChar character: charlist) {

            if (charid.equals(Integer.toString(character.getId()))) {
                selectedChar(character);
            }
        }

        if (getCharName() == null) {
            System.out.println("\nUYARI! Geçerli bir ID numarası giriniz!\n");
            selectChar();
        }

    }

    public void selectedChar(GameChar character) {
        setCharName(character.getName());
        setMoney(character.getMoney());
        setHealth(character.getHealth());
        setMaxhealth(character.getHealth());
        setDamage(character.getDamage());
        System.out.println("\nSeçtiğiniz karakter: " + getCharName());
        System.out.println("Sağlığınız: " + getHealth());
        System.out.println("Paranız: " + getMoney());
        System.out.println("Hasarınız: " + getDamage() + "");
    }

    public void playerInfo() {
        System.out.println("\nCan: " + getHealth() +
                           "\nPara: " + getMoney() +
                           "\nSilah: " + getInventory().getWeapon().getWeapon_name() +
                           "\nHasar: " + getDamage() + "(+" +getInventory().getWeapon().getWeapon_damage() + ")" +
                           "\nZırh: " + getInventory().getArmor().getArmor_name() +
                           "\nDefans: " + getInventory().getArmor().getArmor_defense());
    }

    public void selectLoc() throws InterruptedException {
        Location location = null;



        System.out.println("\nGüvenli Bölgeler\n--------------");

        for (NormalLoc normalLoc : map.getNormalLocs()) {
            System.out.println(normalLoc.getId() + "- " + normalLoc.getName());
        }

        System.out.println("\nTehlikeli Bölgeler\n--------------");

        for (BattleLoc battleLoc : map.getBattleLocs()) {
            System.out.println(battleLoc.getId() + "- " + battleLoc.getName());
        }

        System.out.print("\nLütfen gitmek istediğiniz yeri (ID) seçiniz: ");
        String selectLoc = scanner.next();

        for (Location[] locs: map.getLocations()) {
            for (Location loc:locs) {
                if (selectLoc.equals(Integer.toString(loc.getId()))) {
                    loc.onLocation();
                    location = loc;

                }
            }
        }

        if (location == null) {
            System.out.println("\nUYARI! Geçerli bir ID numarası giriniz!\n");
            selectLoc();
        }




    }

}
