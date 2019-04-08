package info;

import java.util.ArrayList;

public class SortLists {
    public static ArrayList<Info> sortAlphabetically(ArrayList<Info> list) {
        if (list == null) return null;

        ArrayList<Info> returnList = new ArrayList<Info>();

        // adds first item to returnList
        returnList.add(list.get(0));

        for (int i=1; i<list.size(); i++){
            for (int j=0; j<returnList.size(); j++) {
                // list Info object to be added belongs before the Info object in returnList
                if ((list.get(i).name).compareToIgnoreCase(returnList.get(j).name) < 0) {
                    returnList.add(j, list.get(i));
                    break;
                }
                // checks if item belongs at end
                else {
                    if (j == returnList.size() - 1) {
                        returnList.add(list.get(i));
                        break;
                    }
                }
            }
        }
        return returnList;
    }

    public static ArrayList<Info> sortByDriveTime(ArrayList<Info> list){
        if (list == null) return null;

        ArrayList<Info> returnList = new ArrayList<Info>();

        // adds first item to returnList
        returnList.add(list.get(0));

        for (int i=1; i<list.size(); i++){
            for (int j=0; j<returnList.size(); j++) {
                RestaurantInfo listInfo = (RestaurantInfo) list.get(i);
                RestaurantInfo returnInfo = (RestaurantInfo) returnList.get(j);
                // list Info object to be added belongs before the Info object in returnList
                if (listInfo.driveTimeValue < returnInfo.driveTimeValue) {
                    returnList.add(j, list.get(i));
                    break;
                }
                // checks if item belongs at end
                else {
                    if (j == returnList.size() - 1) {
                        returnList.add(list.get(i));
                        break;
                    }
                }
            }
        }
        return returnList;
    }

    public static ArrayList<Info> sortByPrice(ArrayList<Info> list) {
        if (list == null) return null;

        ArrayList<Info> returnList = new ArrayList<Info>();

        // adds first item to returnList
        returnList.add(list.get(0));

        for (int i=1; i<list.size(); i++) {
            for (int j = 0; j < returnList.size(); j++) {
                RestaurantInfo listInfo = (RestaurantInfo) list.get(i);
                RestaurantInfo returnInfo = (RestaurantInfo) returnList.get(j);
                // list Info object to be added belongs before the Info object in returnList
                if (listInfo.priceLevel.length() < returnInfo.priceLevel.length()) {
                    returnList.add(j, list.get(i));
                    break;
                }
                // checks if item belongs at end
                else {
                    if (j == returnList.size() - 1) {
                        returnList.add(list.get(i));
                        break;
                    }
                }
            }
        }
        return returnList;
    }
}
