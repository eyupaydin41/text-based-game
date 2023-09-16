import java.util.Scanner;

public class ToolStore extends NormalLoc{


    Scanner scanner = new Scanner(System.in);
    public ToolStore(Player player) {
        super(player, "Mağaza", 2);
    }

    @Override
    public void onLocation() throws InterruptedException {
        System.out.println("\nŞuan " + this.getName() + " alanındasınız.\n");

        Store[] stores = {new Weapon(),new Armor()};



        System.out.println("-------- Mağaza --------");
        for (Store store:stores) {
            System.out.println(store.getId()+ "- " +store.getName());
        }
        System.out.println("------------------------");
        System.out.println("*- Güvenli Eve Dön");

        System.out.print("\nYapmak istediğiniz işlemi seçiniz: ");
        String selectOption = scanner.next();


        switch (selectOption) {
            case "1" -> storeWeapon();

            case "2" -> storeArmor();
            case "*" -> {
                Location location = new SafeHouse(getPlayer());
                location.onLocation();
            }
        }

    }

    public void storeWeapon() throws InterruptedException {
        Weapon[] weapons = {new Weapon("Tabanca",1,2,25),
                new Weapon("Kılıç  ",2,3,35),
                new Weapon("Tüfek  ",3,7,45)};

        System.out.println("\n-------- Silahlar --------");
        for (Weapon weapon : weapons) {
            System.out.println(weapon.getWeapon_id() + "- " + weapon.getWeapon_name() + " - "  + "Hasar: +" + weapon.getWeapon_damage() + " - " + weapon.getWeapon_price() + "TL");
        }
        System.out.println("--------------------------");
        System.out.println("Mevcut Paranız: " + getPlayer().getMoney() + "TL");
        System.out.println("*- Menüye Geri Dön");

        System.out.print("\nAlmak istediğiniz silahı (ID) seçin veya çıkmak için '*' girin: ");
        String selectWeapon = scanner.next();

        while (!selectWeapon.equals("*")) {
            boolean validWeaponSelected = false;

            for (Weapon weapon : weapons) {

                if (selectWeapon.equals(Integer.toString(weapon.getWeapon_id()))) {
                    validWeaponSelected = true;

                    if (getPlayer().getMoney() >= weapon.getWeapon_price()) {
                        System.out.println("\nTebrikler! " + weapon.getWeapon_name() + " silahını satın aldınız.");
                        System.out.println("Yeni Hasarınız: " + getPlayer().getDamage() + "(+" + weapon.getWeapon_damage() + ")\n");
                        getPlayer().getInventory().setWeapon(weapon);
                        getPlayer().setMoney(getPlayer().getMoney() - weapon.getWeapon_price());
                        System.out.println("Mevcut Paranız: " + getPlayer().getMoney() + "TL");


                    } else {
                        System.out.println("\nUYARI! Paranız Yetersiz.");
                    }
                }
            }

            if (!validWeaponSelected) {
                System.out.println("\nUYARI! Geçersiz ID girdiniz.");
            }
            System.out.print("\nAlmak istediğiniz silahı (ID) seçin veya çıkmak için '*' girin: ");
            selectWeapon = scanner.next();
        }

        Location location = new ToolStore(getPlayer());
        location.onLocation();

    }

    public void storeArmor() throws InterruptedException {
        Armor[] armors = {new Armor("Hafif",1,1,15),
                new Armor("Orta ",2,3,25),
                new Armor("Ağır ",3,5,40)};

        System.out.println("\n-------- Zırhlar --------");
        for (Armor armor : armors) {
            System.out.println(armor.getArmor_id() + "- " + armor.getArmor_name() + " - " + "Hasar Engelleme: +" + armor.getArmor_defense() + " - " + armor.getArmor_price() + "TL");
        }
        System.out.println("-------------------------");
        System.out.println("Mevcut Paranız: " + getPlayer().getMoney() + "TL");
        System.out.println("*- Menüye Geri Dön");

        System.out.print("\nAlmak istediğiniz silahı (ID) seçin veya çıkmak için '*' girin: ");
        String selectArmor = scanner.next();

        while (!selectArmor.equals("*")) {
            boolean validArmorSelected = false;
            for (Armor armor : armors) {
                if (selectArmor.equals(Integer.toString(armor.getArmor_id()))) {
                    validArmorSelected = true;
                    if (getPlayer().getMoney() >= armor.getArmor_price()) {
                        System.out.println("\nTebrikler " + armor.getArmor_name() + " Zırhı satın aldınız.!");
                        System.out.println("Yeni Hasar Engellemeniz : " + armor.getArmor_defense());
                        getPlayer().getInventory().setArmor(armor);
                        getPlayer().setMoney(getPlayer().getMoney() - armor.getArmor_price());

                        System.out.println("\nMevcut Paranız: " + getPlayer().getMoney() + "TL");
                    } else {
                        System.out.println("\nParanız yetersiz, lütfen paranızın yettiği eşyaları almayı deneyin.");
                    }
                }
            }
            if (!validArmorSelected) {
                System.out.println("\nUYARI! Geçersiz ID girdiniz.");
            }
            System.out.print("\nAlmak istediğiniz silahı (ID) seçin veya çıkmak için '*' girin: ");
            selectArmor = scanner.next();
        }
        Location location = new ToolStore(getPlayer());
        location.onLocation();

    }
}
