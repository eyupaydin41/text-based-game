import java.util.Scanner;

public class Deadzone extends Location {
    public Deadzone(Player player) {
        super(player, "Mezarlık", 0);
    }

    @Override
    public void onLocation() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Issız Ada'da hayatta kalmayı BAŞARAMADIN!\n");
        Thread.sleep(2000);
        System.out.println("Tekrar denemek ister misin?\n<E> - Evet\n<H> - Hayır");
        String selectChoice = scanner.next();

        boolean isTrue = true;

        while(isTrue) {
            selectChoice = selectChoice.toUpperCase();
            switch (selectChoice) {
                case "E" -> {
                    Game game = new Game();
                    game.start();
                    isTrue = false;
                }
                case "H" -> {
                    System.out.println("Güle güle!");
                    System.exit(0);
                }
                default -> {
                    System.out.println("UYARI! Geçersiz bir harf girdiniz.");
                    selectChoice = scanner.next();

                }
            }

        }

    }
}
