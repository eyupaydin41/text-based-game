import java.util.Random;
import java.util.Scanner;

public abstract class BattleLoc extends Location {
    private Enemy enemy;

    private int maxEnemy;

    private Award award;

    Scanner scanner = new Scanner(System.in);

    public BattleLoc(Player player, String name, int id, Enemy enemy, Award award, int maxEnemy) {
        super(player, name, id);
        this.enemy = enemy;
        this.award = award;
        this.maxEnemy = maxEnemy;
    }

    @Override
    public void onLocation() throws InterruptedException {
        if (this.getAward().isTaken()) {
            System.out.println("\nBu bölgeyi zaten temizleyip, ödülü kazandınız!");
            Thread.sleep(1500);
            Location location = new SafeHouse(getPlayer());
            location.onLocation();
        } else {
            int enmyNumber = randomEnemyNumber();
            System.out.println("\nŞuan " + this.getName() + " alanındasınız.\n");
            System.out.println("Dikkatli Ol " + getPlayer().getName() + "! Burası " + enmyNumber + " tane " + this.getEnemy().getName() + " yaratığının bölgesi.!");
            System.out.print("<S> - Savaş\n" +
                    "<K> - Kaç\n" +
                    "Tercihini yap: ");

            String selectChoice = scanner.next();

            boolean isValid = false;

            while (!isValid) {
                selectChoice = selectChoice.toUpperCase();
                switch (selectChoice) {
                    case "S":
                        isValid = true;
                        fight(enmyNumber);
                        break;
                    case "K":
                        isValid = true;
                        Location location = new SafeHouse(getPlayer());
                        location.onLocation();
                        break;

                    default:
                        System.out.println("UYARI! Geçersiz bir harf girdiniz.");
                        selectChoice = scanner.next();
                }
            }
        }
    }

    public void fight(int enmyNumber) throws InterruptedException {

        int maxEnemyHealth = getEnemy().getHealth();

        System.out.println("\nEyvah! Karşında bir " + this.getEnemy().getName() + " var dikkatli ol!");

        for (int i = 1; i <= enmyNumber; i++) {
            this.getEnemy().setHealth(maxEnemyHealth);
            while ((getEnemy().getHealth() > 0)) {
                System.out.println(this.getEnemy().getName() + " sana doğru geliyor, hemen ona saldır! (Düşmanın Canı: " + this.getEnemy().getHealth() + ")");
                System.out.print("S - Saldır\n" +
                        "K - Kaç\n" +
                        "Tercihini Yap: ");
                String selectFightChoice = scanner.next();

                boolean isValid = false;

                while (!isValid) {
                    selectFightChoice = selectFightChoice.toUpperCase();
                    switch (selectFightChoice) {
                        case "S":
                            isValid = true;
                            getEnemy().setHealth(this.getEnemy().getHealth() - (this.getPlayer().getDamage() + this.getPlayer().getInventory().getWeapon().getWeapon_damage()));
                            if (getEnemy().getHealth() > 0) {
                                System.out.println("\nDüşman yaralandı! (Verilen Hasar = " + (this.getPlayer().getDamage() + this.getPlayer().getInventory().getWeapon().getWeapon_damage()) + ")");
                            } else {
                                System.out.println("\nDüşman öldü! (Verilen Hasar = " + (this.getPlayer().getDamage() + this.getPlayer().getInventory().getWeapon().getWeapon_damage()) + ")");
                                System.out.println(getEnemy().getEnemyAward() + "TL kazandın!\n");
                                getPlayer().setMoney((getPlayer().getMoney() + getEnemy().getEnemyAward()));
                                if (i != enmyNumber) {
                                    System.out.println("Dikkat et! İlerde daha fazla yaratık olabilir.!\n");
                                    Thread.sleep(2000);
                                    System.out.println("\nEyvah! Karşında bir tane daha " + this.getEnemy().getName() + " var dikkatli ol!");
                                }
                                else {
                                    System.out.println("Bütün düşmanları temizledin. Tebrikler.!!" );

            // BattleLoc ta ödülü alınca tekrar girilmiyor

                                    for (BattleLoc battleloc: getPlayer().map.getBattleLocs()) {
                                        if (battleloc.getName().equals(this.getName())) {
                                            if (!(this.getAward().isTaken())) {
                                                battleloc.getAward().setTaken(true);
                                                System.out.println(battleloc.getAward().getName() + " ödülünü kazandınız!");
                                            }
                                            else {
                                                System.out.println("Zaten " + battleloc.getAward().getName() + " ödülünü kazanmıştın!");
                                            }
                                        }
                                    }

                                    getEnemy().setHealth(maxEnemyHealth);

                                    Thread.sleep(1500);

                                    Location location = new SafeHouse(getPlayer());
                                    location.onLocation();

                                }
                            }
                            if (getEnemy().getHealth() > 0) {
                                enemyAttack();
                                if (!(getPlayer().getHealth() > 0)) {
                                    Location location = new Deadzone(getPlayer());
                                    location.onLocation();
                                }
                            }
                            break;
                        case "K":
                            isValid = true;
                            Location location = new SafeHouse(getPlayer());
                            location.onLocation();
                            break;
                        default:
                            System.out.println("UYARI! Geçersiz bir harf girdiniz.");
                            selectFightChoice = scanner.next();
                    }
                }
            }
        }
    }

    public void enemyAttack() throws InterruptedException {
        System.out.println("\n" + this.getEnemy().getName() + " üzerine doğru geliyor, dikkat et!");
        Thread.sleep(2000);
        if (this.getEnemy().getDamage() > this.getPlayer().getInventory().getArmor().getArmor_defense()) {
            this.getPlayer().setHealth(this.getPlayer().getHealth() - (this.getEnemy().getDamage() - this.getPlayer().getInventory().getArmor().getArmor_defense()));
            if (this.getPlayer().getHealth() > 0) {
                System.out.println("Yaralandın! ( Alınan Hasar: " + ( this.getEnemy().getDamage() - this.getPlayer().getInventory().getArmor().getArmor_defense() ) + " ) " + " ( Canın: " + this.getPlayer().getHealth() + " )\n");
                Thread.sleep(2000);
            } else {
                this.getPlayer().setHealth(0);
                System.out.println("\nÖLDÜN!\n");
                Thread.sleep(2000);
            }
        }
        else {
                System.out.println(getEnemy().getName() + " ıskaladı!" + " ( Canın: " + this.getPlayer().getHealth() + " )\n");
            Thread.sleep(2000);
        }


    }

    public int randomEnemyNumber() {
        Random random = new Random();

        return random.nextInt(this.getMaxEnemy()) + 3;
    }

    public int getMaxEnemy() {
        return maxEnemy;
    }

    public void setMaxEnemy(int maxEnemy) {
        this.maxEnemy = maxEnemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }
}



