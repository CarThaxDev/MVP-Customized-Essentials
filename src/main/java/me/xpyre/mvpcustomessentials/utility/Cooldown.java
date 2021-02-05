package me.xpyre.mvpcustomessentials.utility;

public class Cooldown {
    private long timeOver;

    public Cooldown(long cooldown){
        timeOver = System.currentTimeMillis() + cooldown;
    }

    protected  boolean isCooldownDone(){
        return System.currentTimeMillis() >= timeOver;
    }

    protected  boolean forceCooldownOver(){
        try{
            timeOver = System.currentTimeMillis();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
