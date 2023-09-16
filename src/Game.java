import java.util.Scanner;

public class Game {

    public void start() throws InterruptedException {


        System.out.println("\nIssız adaya hoş geldin..!");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Lütfen isminizi giriniz: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        System.out.println("\nHoş geldin " + player.getName() + " macera seni bekliyor, hızlı ol...\n");
        player.selectChar();
        Location location = new SafeHouse(player);
        location.onLocation();

    }





}
