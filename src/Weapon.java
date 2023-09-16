public class Weapon extends Store{

    private String weapon_name;
    private int weapon_id;
    private int weapon_damage;
    private int weapon_price;

    public Weapon(String weapon_name, int weapon_id, int weapon_damage, int weapon_price) {
        super(1,"Silahlar");
        this.weapon_damage = weapon_damage;
        this.weapon_price = weapon_price;
        this.weapon_id = weapon_id;
        this.weapon_name = weapon_name;
    }

    public Weapon() {
        super(1,"Silahlar");
    }
    public String getWeapon_name() {
        return weapon_name;
    }

    public void setWeapon_name(String weapon_name) {
        this.weapon_name = weapon_name;
    }

    public int getWeapon_id() {
        return weapon_id;
    }

    public void setWeapon_id(int weapon_id) {
        this.weapon_id = weapon_id;
    }

    public int getWeapon_damage() {
        return weapon_damage;
    }

    public void setWeapon_damage(int weapon_damage) {
        this.weapon_damage = weapon_damage;
    }

    public int getWeapon_price() {
        return weapon_price;
    }

    public void setWeapon_price(int weapon_price) {
        this.weapon_price = weapon_price;
    }



}
