package com.unpri.charactermanager.model;

public class Character {
    // ===== ATRIBUT (private) =====
    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;                          // BARU: atribut mana

    // ===== CONSTRUCTOR 1: untuk membuat karakter BARU =====
    public Character(String name, CharacterType type, int level) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    // ===== CONSTRUCTOR 2: untuk MEMUAT karakter dari database =====
    public Character(int id, String name, CharacterType type, int level, int hitPoints, int attackPower) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
    }

    // ===== HELPER PRIVATE =====
    private int calculateBaseHP() {
        return 100 + (level * 10);
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);
    }

    // ===== GETTER =====
    public int getId() { return id; }
    public String getName() { return name; }
    public CharacterType getType() { return type; }
    public int getLevel() { return level; }
    public int getHitPoints() { return hitPoints; }
    public int getAttackPower() { return attackPower; }

    // BARU: getter mana
    public int getMana() { return mana; }

    // ===== SETTER =====
    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("Level minimal adalah 1");
        }
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    // BARU: setter mana dengan validasi
    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("Mana tidak boleh negatif");
        }
        this.mana = mana;
    }

    // BARU: karakter dianggap hidup jika HP lebih dari 0
    public boolean isAlive() {
        return hitPoints > 0;
    }

    // ===== toString() =====
    @Override
    public String toString() {
        return "Character{id=" + id +
               ", name='" + name + '\'' +
               ", type=" + type +
               ", level=" + level +
               ", hp=" + hitPoints +
               ", atk=" + attackPower +
               ", mana=" + mana + '}';
    }
}