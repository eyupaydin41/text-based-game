import java.util.Scanner;

public class SafeHouse extends NormalLoc{

    public SafeHouse(Player player) {
        super(player, "Güvenli Ev", 1);
    }

    @Override
    public void onLocation() throws InterruptedException {

        endGame();

        System.out.println("\nŞuan " + this.getName() + " alanındasınız.");
        if (!(getPlayer().getHealth() == getPlayer().getMaxhealth())) {
            this.getPlayer().setHealth(getPlayer().getMaxhealth());
            System.out.println("\nYaralarınız iyileştirildi.!");
        }
        menu();


    }

    public void menu() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nNe yapmak istiyorsunuz?\n<G> - Gezintiye Çıkmak\n<K> - Karakter Durumu\n<O> - Ödüllerim\n<Q> - Oyundan Çık");
        String selectChoice = scanner.next();

        boolean isTrue = true;

        while(isTrue) {
            selectChoice = selectChoice.toUpperCase();
            switch (selectChoice) {
                case "G" : getPlayer().selectLoc();
                    break;
                case "K" : {
                    getPlayer().playerInfo();
                    System.out.println("\nNe yapmak istiyorsunuz?");
                    selectChoice = scanner.next();
                    break;
                }
                case "O" : {
                    printAwards();
                    menu();
                    break;
                }
                case "Q" : System.exit(0);
                    break;
                default : {
                    System.out.println("UYARI! Geçersiz bir harf girdiniz.!");
                    selectChoice = scanner.next();
                }
            }

        }
    }

    public void printAwards() {
        System.out.println("\n------ Ödüllerim ------");


        for (BattleLoc battleLoc : getPlayer().map.getBattleLocs()) {
            System.out.println(battleLoc.getAward().getName() + " : " + ((battleLoc.getAward().isTaken()) ? "Alındı" : "Alınmadı"));
        }

        System.out.println("-----------------------");
    }
    public void endGame() throws InterruptedException {
        int takenAwards = 0;
        int allAwards = 0;
        for (BattleLoc battleLoc : getPlayer().map.getBattleLocs()) {
            allAwards++;
            if (battleLoc.getAward().isTaken()) {
                takenAwards++;
            }
        }
        if (takenAwards == allAwards) {
            System.out.println("\nTEBRİKLER!!!");
            Thread.sleep(1000);
            System.out.println("\nBütün ödülleri toplayıp Güvenli Ev'e sağ salim gelmeyi BAŞARDIN!");
            Thread.sleep(2000);
            System.out.println("\nArtık Issız Ada'da yaşayabilmen için her şeye sahipsin!");
            Thread.sleep(2000);
            System.out.println("\nİYİ TATİLLER!");
            Thread.sleep(2000);
            System.exit(0);
        }
    }
}
