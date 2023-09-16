public  class Armor extends Store {

    private String armor_name;
    private int armor_id;
    private int armor_defense;
    private int armor_price;

    public Armor() {
        super(2,"Zırhlar");
    }
    public Armor(String armor_name, int armor_id, int armor_defense, int armor_price) {
        super(2,"Zırhlar");
        this.armor_id = armor_id;
        this.armor_price = armor_price;
        this.armor_defense = armor_defense;
        this.armor_name = armor_name;
    }

    public String getArmor_name() {
        return armor_name;
    }

    public void setArmor_name(String armor_name) {
        this.armor_name = armor_name;
    }

    public int getArmor_id() {
        return armor_id;
    }

    public void setArmor_id(int armor_id) {
        this.armor_id = armor_id;
    }

    public int getArmor_defense() {
        return armor_defense;
    }

    public void setArmor_defense(int armor_defense) {
        this.armor_defense = armor_defense;
    }

    public int getArmor_price() {
        return armor_price;
    }

    public void setArmor_price(int armor_price) {
        this.armor_price = armor_price;
    }
}
