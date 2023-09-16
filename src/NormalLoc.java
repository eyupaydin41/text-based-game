public abstract class NormalLoc extends Location{
    public NormalLoc(Player player, String name, int id) {
        super(player, name, id);
    }

    @Override
    public void onLocation() throws InterruptedException {
    }
}
