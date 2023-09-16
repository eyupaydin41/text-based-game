public abstract class Enemy {
    private int id;
    private String name;
    private int health;
    private int damage;
    private int enemyAward;

    public Enemy(int id, String name, int health, int damage, int enemyAward) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.enemyAward = enemyAward;
    }

    public int getEnemyAward() {
        return enemyAward;
    }

    public void setEnemyAward(int enemyAward) {
        this.enemyAward = enemyAward;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
