package cmu.andrew.htay.dinewithus.entities;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by HuiJun on 4/12/16.
 */
public class StoreSet implements Serializable {
    private ArrayList<Store> storeList;

    public StoreSet()  {
        storeList = new ArrayList<>();
    }

    public Store getStore(String name) {
        for (Store store : storeList) {
            if(store.getName().equals(name)) {
                return store;
            }
        }
        return null;
    }
    
    
    public ArrayList<Store> getStoreList() {
    	return storeList;
    }
    
    public long getLatitudeForStore(String name) {
        Store store = getStore(name);
        return store.getLatitude();
    }

    public long getLongitudeForStore(String name) {
        Store store = getStore(name);
        return store.getLongitude();
    }

    public int getRatingForStore(String name) {
        Store store = getStore(name);
        return store.getRating();
    }

    public int getOpeningTimeForStore(String name) {
        Store store = getStore(name);
        return store.getOpeningTime();
    }

    public int getClosingTimeForStore(String name) {
        Store store = getStore(name);
        return store.getClosingTime();
    }

    public String getPriceRangeForStore(String name) {
        Store store = getStore(name);
        return store.getPriceRange();
    }

    public String getCuisineForStore(String name) {
        Store store = getStore(name);
        return store.getCuisine();
    }

    public String getDescriptionForStore(String name) {
        Store store = getStore(name);
        return store.getDescription();
    }

    public String getMenuForStore(String name) {
        Store store = getStore(name);
        return store.getMenuURL();
    }

    public String getShopPictureForStore(String name) {
        Store store = getStore(name);
        return store.getShopPictureURL();
    }

    public String getWebsiteForStore(String name) {
        Store store = getStore(name);
        return store.getWebsiteURL();
    }


    public void addStore(Store store) {
        this.storeList.add(store);
    }

    public void deleteStore(String name) {
        if(storeList.isEmpty()) {
            return;
        }

        Store store = getStore(name);
        if(store != null) {
            storeList.remove(store);
        }
    }

    public ArrayList<Store> findStoresByHour(int start, int end) {
        ArrayList<Store> filteredStores = new ArrayList<>();
        for (Store store : storeList) {
            if(store.hasHours(start, end)) {
                filteredStores.add(store);
            }
        }
        return filteredStores;

    }

    public ArrayList<Store> findStoresByCuisine(String cuisine) {
        ArrayList<Store> filteredStores = new ArrayList<>();
        for (Store store : storeList) {
            if(store.hasCuisine(cuisine)) {
                filteredStores.add(store);
            }
        }
        return filteredStores;
    }

    public ArrayList<Store> findStoresByCoordinates(long lat, long lon) {
        ArrayList<Store> filteredStores = new ArrayList<>();
        for (Store store : storeList) {
            if(store.hasCoordinates(lat, lon)) {
                filteredStores.add(store);
            }
        }
        return filteredStores;
    }

    public void removeAllStores() {
        this.storeList.clear();
    }

}
