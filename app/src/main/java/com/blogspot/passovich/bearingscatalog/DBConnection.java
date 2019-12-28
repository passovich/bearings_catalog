package com.blogspot.passovich.bearingscatalog;

public class DBConnection {
    public final int TABLE_NUMBER = 20;         //Колличество таблиц в базе
    private static String dBStructure[][]={
    {"_id", "mark", "d_small",  "D",  "B" },   //table_0
    {"_id", "mark", "d_small",  "D",  "B" },   //table_1
    {"_id", "mark", "d_small",  "D",  "B" },   //table_2
    {"_id", "mark", "d_small",  "D",  "B" },   //table_3
    {"_id", "mark", "d_small",  "D",  "B" },   //table_4
    {"_id", "mark", "d_small",  "D",  "B" },   //table_5
    {"_id", "mark", "d_small",  "D",  "B" },   //table_6
    {"_id", "mark", "d_small",  "D",  "B" },   //table_7
    {"_id", "mark", "d_small",  "D",  "B" },   //table_8
    {"_id", "mark", "d_small",  "D",  "B" },   //table_9
    {"_id", "mark", "d",        "d1", "B2"},   //table_10
    {"_id", "mark", "d_small",  "D",  "B" },   //table_11
    {"_id", "mark", "d_small",  "D",  "T" },   //table_12
    {"_id", "mark", "d_small",  "D",  "T" },   //table_13
    {"_id", "mark", "d_small",  "D",  "T" },   //table_14
    {"_id", "mark", "d_small",  "D",  "T" },   //table_15
    {"_id", "mark", "d_small",  "D",  "B" },   //table_16
    {"_id", "mark", "d_small",  "D",  "T" },   //table_17
    {"_id", "mark", "d2_small", "D",  "T1"},   //table_18
    {"_id", "mark", "d_small",  "D",  "T" },   //table_19
};
    private static String simpleViewColumns [][] = {
            {"_id", "mark", "d_small",  "D",  "B" },   //table_0
            {"_id", "mark", "d_small",  "D",  "B" },   //table_1
            {"_id", "mark", "d_small",  "D",  "B" },   //table_2
            {"_id", "mark", "d_small",  "D",  "B" },   //table_3
            {"_id", "mark", "d_small",  "D",  "B" },   //table_4
            {"_id", "mark", "d_small",  "D",  "B" },   //table_5
            {"_id", "mark", "d_small",  "D",  "B" },   //table_6
            {"_id", "mark", "d_small",  "D",  "B" },   //table_7
            {"_id", "mark", "d_small",  "D",  "B" },   //table_8
            {"_id", "mark", "d_small",  "D",  "B" },   //table_9
            {"_id", "mark", "d",        "d1", "B2"},  //table_10
            {"_id", "mark", "d_small",  "D",  "B" },   //table_11
            {"_id", "mark", "d_small",  "D",  "T" },   //table_12
            {"_id", "mark", "d_small",  "D",  "T" },   //table_13
            {"_id", "mark", "d_small",  "D",  "T" },   //table_14
            {"_id", "mark", "d_small",  "D",  "T" },   //table_15
            {"_id", "mark", "d_small",  "D",  "B" },   //table_16
            {"_id", "mark", "d_small",  "D",  "T" },   //table_17
            {"_id", "mark", "d2_small", "D",  "T1"},  //table_18
            {"_id", "mark", "d_small",  "D",  "T" },   //table_19
    };
    private static String searchColumns [][] = {
            {"mark", "d_small",  "D",  "B" }, //table_0
            {"mark", "d_small",  "D",  "B" }, //table_1
            {"mark", "d_small",  "D",  "B" }, //table_2
            {"mark", "d_small",  "D",  "B" }, //table_3
            {"mark", "d_small",  "D",  "B" }, //table_4
            {"mark", "d_small",  "D",  "B" }, //table_5
            {"mark", "d_small",  "D",  "B" }, //table_6
            {"mark", "d_small",  "D",  "B" }, //table_7
            {"mark", "d_small",  "D",  "B" }, //table_8
            {"mark", "d_small",  "D",  "B" }, //table_9
            {"mark", "d",        "d1", "B2"}, //table_10
            {"mark", "d_small",  "D",  "B" }, //table_11
            {"mark", "d_small",  "D",  "T" }, //table_12
            {"mark", "d_small",  "D",  "T" }, //table_13
            {"mark", "d_small",  "D",  "T" }, //table_14
            {"mark", "d_small",  "D",  "T" }, //table_15
            {"mark", "d_small",  "D",  "B" }, //table_16
            {"mark", "d_small",  "D",  "T" }, //table_17
            {"mark", "d2_small", "D",  "T1"}, //table_18
            {"mark", "d_small",  "D",  "T" }, //table_19
    } ;
    private static final Integer[]icons = {
            R.drawable.icon_01, R.drawable.icon_02,
            R.drawable.icon_03, R.drawable.icon_04,
            R.drawable.icon_05, R.drawable.icon_06,
            R.drawable.icon_07, R.drawable.icon_08,
            R.drawable.icon_09, R.drawable.icon_10,
            R.drawable.icon_11, R.drawable.icon_12,
            R.drawable.icon_13, R.drawable.icon_14,
            R.drawable.icon_15, R.drawable.icon_16,
            R.drawable.icon_17, R.drawable.icon_18,
            R.drawable.icon_19, R.drawable.icon_20,
    };
    private static final Integer[]smallDrawings = {
            R.drawable.draw_01, R.drawable.draw_02,
            R.drawable.draw_03, R.drawable.draw_04,
            R.drawable.draw_05, R.drawable.draw_06,
            R.drawable.draw_07, R.drawable.draw_08,
            R.drawable.draw_09, R.drawable.draw_10,
            R.drawable.draw_11, R.drawable.draw_12,
            R.drawable.draw_13, R.drawable.draw_14,
            R.drawable.draw_15, R.drawable.draw_16,
            R.drawable.draw_17, R.drawable.draw_18,
            R.drawable.draw_19, R.drawable.draw_20
    };
    public void getViewColumns(int tableID, String columns []){
        for (int i = 0; i < columns.length; i++){
            columns[i] = String.valueOf(simpleViewColumns[tableID][i]);
        }
    }
    public void getSearchColumns(int tableID, String columns []){
        for (int i = 0; i < columns.length; i++){
            columns[i] = String.valueOf(searchColumns[tableID][i]);
        }
    }
    public void getSearchParametrsColumns(int tableID, String columns []){
        for (int i = 0; i < columns.length; i++){
            columns[i] = String.valueOf(searchColumns[tableID][i]) + " = ?";
        }
    }
    public String getColumnName(int tableNumber, int columnNumber){
        return dBStructure[tableNumber][columnNumber + 1];
    }

    public int getIcon(int id){
        return icons[id];
    }
    public int getSmallDrawings(int id){
        return smallDrawings[id];
    }
}
