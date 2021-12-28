package com.xauv.algorithm.题目.算法实现.回溯法;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/11/07 17:01
 * @Author ling yue
 * @Package com.xauv.algorithm
 * @Desc
 *
 * NxN 棋盘上的皇后 可以攻击
 * 1所在行 2所在列 3左上 4左下 5右上 6右下 所有单位
 * 如何放置 N 个皇后可以使她们互相不会攻击
 */
public class N皇后 {

    public static void main(String[] args) {
        int n = 10;
        List<Character[][]> characters = solveNQueens(n);
        System.out.println(n + "皇后问题共：" + characters.size() + "种解法，分别是：");
        printSolutions(characters);
    }

    public static void printSolutions(List<Character[][]> characters) {
        for (int i = 0; i < characters.size(); i++) {
            Character[][] solution = characters.get(i);
            for (Character[] c : solution) {
                for (Character character : c) {
                    System.out.print(character);
                }
                System.out.println();
            }
            System.out.println("==========");
        }
    }

    public static List<Character[][]> solveNQueens(int n) {
        Character[][] board = new Character[n][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = board[i].length - 1; j >= 0; j--) {
                board[i][j] = '口';
            }
        }
        List<Character[][]> boards = new ArrayList<>();
        backTrack(0, n, board, boards);
        return boards;
    }

    public static void backTrack(int row, int n, Character[][] board, List<Character[][]> boards) {

        if (row == board.length) {
            Character[][] savedBoards = new Character[n][n];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    savedBoards[i][j] = board[i][j];
                }
            }
            boards.add(savedBoards);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!valid(row, i, n, board)) {
                continue;
            }
            board[row][i] = 'Q';
            backTrack(row + 1, n, board, boards);
            board[row][i] = '口';
        }
    }

    public static boolean valid(int row, int column, int n, Character[][] board) {
        // 检查行冲突
        for (Character character : board[row]) {
            if (character.equals('Q')) {
                return false;
            }
        }
        for (Character[] characters : board) {
            if (characters[column].equals('Q')) {
                return false;
            }
        }
        // 检查四角冲突
        // 左上角
        for (int i = row-1, j = column-1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j].equals('Q')) {
                return false;
            }
        }

        // 右下角
        for (int i = row+1,j = column+1; i < n && j < n; i++, j++) {
            if (board[i][j].equals('Q')) {
                return false;
            }
        }

        // 左下角
        for (int i = row+1, j = column-1; i < n && j >= 0; i++, j--) {
            if (board[i][j].equals('Q')) {
                return false;
            }
        }

        // 右上角
        for (int i = row-1,j = column+1; i >=0 && j < n ; i--,j++) {
            if (board[i][j].equals('Q')) {
                return false;
            }
        }
        return true;
    }
}
