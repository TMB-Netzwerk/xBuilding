package de.xenodev.utils;

import de.xenodev.xBuilding;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class TimeBuilder {

    private File file = new File("plugins/" + xBuilding.getInstance().getName(), "time.yml");
    private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    private String name;

    public TimeBuilder(String name){
        this.name = name;
    }

    public void setSeconds(Integer seconds){
        cfg.set(name.toUpperCase() + ".seconds", seconds);
        save();
    }

    public void addSeconds(Integer seconds){
        setSeconds(getSeconds() + seconds);
    }

    public Integer getSeconds(){
        return cfg.getInt(name.toUpperCase() + ".seconds");
    }

    public void setMinutes(Integer minutes){
        cfg.set(name.toUpperCase() + ".minutes", minutes);
        save();
    }

    public void addMinutes(Integer minutes){
        setMinutes(getMinutes() + minutes);
    }

    public Integer getMinutes(){
        return cfg.getInt(name.toUpperCase() + ".minutes");
    }

    public void setHours(Integer hours){
        cfg.set(name.toUpperCase() + ".hours", hours);
        save();
    }

    public void addHours(Integer hours){
        setHours(getHours() + hours);
    }

    public Integer getHours(){
        return cfg.getInt(name.toUpperCase() + ".hours");
    }

    public void setDays(Integer days){
        cfg.set(name.toUpperCase() + ".days", days);
        save();
    }

    public void addDays(Integer days){
        setDays(getDays() + days);
    }

    public Integer getDays(){
        return cfg.getInt(name.toUpperCase() + ".days");
    }

    private void save(){
        try { cfg.save(file); } catch (IOException e) { e.printStackTrace(); }
    }

    public String changeTime(){
        if(getDays() != 0){
            if(getDays() > 1) {
                return getDays() + " Tage";
            }else{
                return getDays() + " Tag";
            }
        }else if(getHours() != 0){
            if(getHours() > 1) {
                return getHours() + " Stunden";
            }else{
                return getHours() + " Stunde";
            }
        }else if(getMinutes() != 0){
            if(getMinutes() > 1) {
                return getMinutes() + " Minuten";
            }else{
                return getMinutes() + " Minute";
            }
        }else{
            if(getSeconds() > 1) {
                return getSeconds() + " Sekunden";
            }else{
                return getSeconds() + " Sekunde";
            }
        }
    }

    public String getName() {
        return name;
    }
}
