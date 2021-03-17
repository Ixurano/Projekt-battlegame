package com.assignment;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtils {


    public static void savedObject(Object objectToSave, String fileName){

        try {
            FileOutputStream outStream = new FileOutputStream(fileName);
            ObjectOutputStream obj = new ObjectOutputStream(outStream);

            obj.writeObject(objectToSave);

            obj.close();
            outStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object loadObject(String fileName){

        Object retObj = null;

        try{
           FileInputStream inStream = new FileInputStream(fileName);
           ObjectInputStream obj = new ObjectInputStream(inStream);

           retObj = obj.readObject();

           obj.close();
           inStream.close();

        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        return retObj;
    }

    public static void writeTextFile(String saveStr, String fileName) {

        try {

            FileOutputStream outStream = new FileOutputStream(fileName);
            OutputStreamWriter writer = new OutputStreamWriter(outStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(saveStr);
            bufferedWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readTextFile(String fileName) {

        StringBuilder retStr = new StringBuilder();

        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            //Då vi använder bufferedReader kan vi ta en hel rad i gången.
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                retStr.append(line);
            }

            bufferedReader.close();
            reader.close();

        } catch (FileNotFoundException e) {
            return fileName + " not found";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retStr.toString();

    }
}
