public class Main {  
    public static void main(String[] args) {
    
        var filePath = "lotto-draw-history.csv";
        var loader = new DataLoader(filePath);
        var ballsCount = loader.getHashMap(); 

        //printing out values
        var keys = ballsCount.keySet().iterator();
        for (int i = 0; i < 6; i++) {
            var keyName = keys.next();
            System.out.println(keyName);
            System.out.println(ballsCount.get(keyName));
            System.out.println("----");
        }
        
    }
} 