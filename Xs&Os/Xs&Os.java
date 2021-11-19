import java.util.*;

public class Xs&Os
{
    static ArrayList<Integer> playerPos = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPos = new ArrayList<Integer>();
    
    public static void main(String[] args) {
        char[][] board = {
            {' ', '|',' ','|',' '},
            {'-', '+','-','+','-'},
            {' ', '|',' ','|',' '},
            {'-', '+','-','+','-'},
            {' ', '|',' ','|',' '}
        };
        
        System.out.println("Welcome to Xs and Os \nPlease use 0 - 9 for placements \n--------");
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            System.out.println("Enter placement of X?");
            int pos = sc.nextInt();
            
            while(playerPos.contains(pos) || cpuPos.contains(pos)) {
                System.out.println("Invalid.. Go again");
                pos = sc.nextInt();
            }
            
            place(board, pos, true);
            checkWinner();
            
            Random r = new Random();
            int posCPU = r.nextInt(9) + 1;
            
            while(playerPos.contains(posCPU) || cpuPos.contains(posCPU)) 
                posCPU = r.nextInt(9) + 1;
                
            place(board, posCPU, false);
            print(board);
            
            String result = checkWinner();
            
            if(result.length() > 0) {
                System.out.print(result);
                break;
            }
        }
    }
    
    public static void print(char[][] board) {
        for(char[] row: board) {
            for(char pos: row)
                System.out.print(pos);
            System.out.println();
        }   
    }
    
    public static void place(char[][] board, int pos, boolean player) {
        char sym = ' ';
        if(player) {
            sym = 'X';
            playerPos.add(pos);
        } else {
            sym = 'O';
            cpuPos.add(pos);
        }
            
        switch(pos) {
            case 1: board[0][0] = sym; break;
            case 2: board[0][2] = sym; break;
            case 3: board[0][4] = sym; break;
            case 4: board[2][0] = sym; break;
            case 5: board[2][2] = sym; break;
            case 6: board[2][4] = sym; break;
            case 7: board[4][0] = sym; break;
            case 8: board[4][2] = sym; break;
            case 9: board[4][4] = sym; break;
        }
    }
    
    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List dia1 = Arrays.asList(1, 5, 9);
        List dia2 = Arrays.asList(7, 5, 3);
        
        List<List> winConditions = new ArrayList<List>();
        winConditions.add(topRow);
        winConditions.add(midRow);
        winConditions.add(botRow);
        winConditions.add(leftCol);
        winConditions.add(midCol);
        winConditions.add(rightCol);
        winConditions.add(dia1);
        winConditions.add(dia2);
        
        for(List l:winConditions) {
            if(playerPos.containsAll(l))
                return "GG";
            else if (cpuPos.containsAll(l)) 
                return "Bot";
            else if(playerPos.size() + cpuPos.size() == 9) 
                return "tie";
        }
        return "";
    }
}
