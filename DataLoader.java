import java.util.HashMap;
import java.io.*;
import java.util.*;  

public class DataLoader {

    private String filePath; 
    private HashMap<Integer, Integer> unsortedBallsCount = new HashMap<>();
    private HashMap<Integer, Integer> sortedHashMap = new LinkedHashMap<Integer, Integer>();
    

    public DataLoader(String filePath) {
        this.filePath = filePath;
        loadData();
        sortHashMap();
    }

    public HashMap<Integer, Integer> getHashMap() {
        return sortedHashMap;
    }

    public void loadData() {
        try {
            BufferedReader lottoData = new BufferedReader(new FileReader(filePath)); 
            
            //skipping row 0 
            String line = lottoData.readLine(); 
            
            while ((line = lottoData.readLine()) != null) {  
                String[] rowData = getArray(line);  
                
                if (rowData != null) {
                    for (int i = 1; i < 8; i++){
                        Integer number = Integer.parseInt(rowData[i]);
                        if (unsortedBallsCount.containsKey(number)) {
                            unsortedBallsCount.put(number, unsortedBallsCount.get(number) + 1);
                        } else {
                            unsortedBallsCount.put(number, 1);
                        }
                    }
                }
            }  
            lottoData.close();
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
    }

    private String[] getArray(String line) {
        String[] rowData = line.split(",");

        //general checks for row validation
        if (rowData.length != 11) return null;

        for (int i = 1; i < 8; i++) {
            if (!isInteger(rowData[i])) return null;

            Integer number = Integer.parseInt(rowData[i]);
            if (number < 1 || number > 59) return null; 
        }

        return rowData; 
    }

    private void sortHashMap(){
        List<Map.Entry<Integer, Integer> > list =
            new LinkedList<Map.Entry<Integer, Integer> >(unsortedBallsCount.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,Map.Entry<Integer, Integer> o2){
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        
        for (Map.Entry<Integer, Integer> aa : list) {
            sortedHashMap.put(aa.getKey(), aa.getValue());
        }
    }

    private static boolean isInteger(String str) {
        if (str == null) return false;
        
        int length = str.length();
        if (length == 0) return false;
        
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) return false;
            i = 1;
        }

        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }
}