public class Map extends Location{

    SafeHouse safeHouse = new SafeHouse(getPlayer());
    ToolStore toolStore = new ToolStore(getPlayer());
    Cave cave = new Cave(getPlayer());
    Forest forest = new Forest(getPlayer());
    River river = new River(getPlayer());

    private NormalLoc[] normalLocs = {safeHouse, toolStore};
    private BattleLoc[] battleLocs = {cave, forest, river};
    private Location[][] locations = {normalLocs, battleLocs};

    public Map(Player player) {
        super(player, "Map", 0);
    }

    public NormalLoc[] getNormalLocs() {
        return normalLocs;
    }

    public void setNormalLocs(NormalLoc[] normalLocs) {
        this.normalLocs = normalLocs;
    }

    public BattleLoc[] getBattleLocs() {
        return battleLocs;
    }

    public void setBattleLocs(BattleLoc[] battleLocs) {
        this.battleLocs = battleLocs;
    }

    public Location[][] getLocations() {
        return locations;
    }

    public void setLocations(Location[][] locations) {
        this.locations = locations;
    }

    @Override
    public void onLocation() throws InterruptedException {

    }
}
