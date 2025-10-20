import java.util.Locale;
import java.util.Scanner;

public class Game {

    Player player;

    public Game() {
        Scene entrance = new Scene("A vár bejáratánál állsz. Északra egy nagy faajtó taláható.");
        Scene hall = new Scene("A nagyteremben vagy. Keletre és nyugatra is van egy-egy ajtó.");
        Scene armory = new Scene("A fegyvertárban vagy. Látsz egy fényes kardot az állványon.");

        entrance.setExit(Direction.ESZAK, hall);
        hall.setExit(Direction.DEL, entrance);
        hall.setExit(Direction.NYUGAT, armory);
        armory.setExit(Direction.KELET, hall);

        player = new Player(entrance);
    }

    private void play() {
        Scanner scanner = new Scanner(System.in);
        display("Üdv a várkalandban");

        //ez maga játék ciklus
        while (true) {
            display("-----------------------------------");
            display(player.getCurrentScene().getDescription());
            display(">");
            //scaner nextline az mindid egy telje ssort olvas be
            String input = scanner.nextLine().toLowerCase(Locale.ROOT).trim();
            String[] words = input.split(" ");

            String command = words[0];

            //Ha van második szó itt kinyerjük egy ternary operator-ral -> ternary operator az ugyanaz mint egy if-then, csak tömör
            String subject = words.length > 1 ? words[1] : "";

            switch (command) {
                case "menj":
                    Direction direction = Direction.fromString(subject);
                    moveplayer(direction);
                    break; // ha nincs akkor tovább meg a következő ágra,
                case "kilép":
                    display("Köszi a játékot!");
                    scanner.close();
                    return;
                default:
                    display("Nem értem a parancsot");
                    break;
            }

        }
    }

    private void moveplayer(Direction direction) {
        Scene nextScene = player.getCurrentScene().getExit(direction);
        if (nextScene == null){
            display("Nem mehetsz arra");
        }
        player.setCurrentScene(nextScene);
    }

    private void display(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}