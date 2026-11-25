package com.example.mobileappproject_fitnesstracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "FitnessDB";
    private static final int DB_VERSION = 1;

    private static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS workouts " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "type TEXT, minutes INTEGER, date TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS intake " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "water INTEGER, calories INTEGER, date TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS goals " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "workout INTEGER, water INTEGER, calories INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS workouts");
        db.execSQL("DROP TABLE IF EXISTS intake");
        db.execSQL("DROP TABLE IF EXISTS goals");
        onCreate(db);
    }

    public static String getToday() {
        Calendar c = Calendar.getInstance();
        return MONTHS[c.get(Calendar.MONTH)] + " "
                + c.get(Calendar.DAY_OF_MONTH) + ", " + c.get(Calendar.YEAR);
    }

    public void addWorkout(String type, int minutes, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO workouts (type, minutes, date) VALUES ('"
                + type + "', " + minutes + ", '" + date + "')");
    }

    public void addIntake(int water, int calories) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO intake (water, calories, date) VALUES ("
                + water + ", " + calories + ", '" + getToday() + "')");
    }

    public void saveGoals(int workout, int water, int calories) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM goals");
        db.execSQL("INSERT INTO goals (workout, water, calories) VALUES ("
                + workout + ", " + water + ", " + calories + ")");
    }

    public int getTodayWorkoutMinutes() {
        return totalForToday("SELECT minutes FROM workouts WHERE date = '" + getToday() + "'");
    }

    public int getTodayWater() {
        return totalForToday("SELECT water FROM intake WHERE date = '" + getToday() + "'");
    }

    public int getTodayCalories() {
        return totalForToday("SELECT calories FROM intake WHERE date = '" + getToday() + "'");
    }

    public int getWorkoutGoal() {
        return readGoal("SELECT workout FROM goals");
    }

    public int getWaterGoal() {
        return readGoal("SELECT water FROM goals");
    }

    public int getCalorieGoal() {
        return readGoal("SELECT calories FROM goals");
    }

    private int totalForToday(String sql) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int total = 0;
        while (cursor.moveToNext()) {
            total = total + cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    private int readGoal(String sql) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int goal = 0;
        if (cursor.moveToNext()) {
            goal = cursor.getInt(0);
        }
        cursor.close();
        return goal;
    }
}