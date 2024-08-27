package de.xenodev.utils;

import de.xenodev.xBuilding;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class LocationBuilder {

    public static Boolean existsLocation(String locationName){
        return xBuilding.getInstance().getConfig().get("Locations." + locationName) != null;
    }

    public static void setLocation(String locationName, Location location){
        xBuilding.getInstance().getConfig().set("Locations." + locationName, location);
        xBuilding.getInstance().saveConfig();
    }

    public static void delLocation(String locationName){
        xBuilding.getInstance().getConfig().set("Locations." + locationName, null);
        xBuilding.getInstance().saveConfig();
    }

    public static Location getLocation(String locationName){
        if(existsLocation(locationName)){
            return xBuilding.getInstance().getConfig().getLocation("Locations." + locationName);
        }else{
            return null;
        }
    }

    public static List<String> getLocationNames(){
        ArrayList<String> arrayList = new ArrayList<>();
        if(xBuilding.getInstance().getConfig().getConfigurationSection("Locations") != null) {
            for (String names : xBuilding.getInstance().getConfig().getConfigurationSection("Locations").getKeys(false)) {
                arrayList.add(names);
            }
        }
        return arrayList;
    }

}
